package com.btjf.service.order;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.btjf.business.common.exception.BusinessException;
import com.btjf.common.page.Page;
import com.btjf.controller.weixin.vo.WxEmpVo;
import com.btjf.mapper.order.ProductionProcedureConfirmMapper;
import com.btjf.model.order.Order;
import com.btjf.model.order.ProductionProcedureConfirm;
import com.btjf.model.order.ProductionProcedureScan;
import com.btjf.model.product.ProductProcedure;
import com.btjf.model.product.ProductProcedureWorkshop;
import com.btjf.service.productpm.ProductWorkshopService;
import com.btjf.util.BigDecimalUtil;
import com.btjf.vo.ProcedureYieldVo;
import com.btjf.vo.weixin.EmpProcedureDetailVo;
import com.btjf.vo.weixin.EmpProcedureListVo;
import com.btjf.vo.weixin.OrderProductVo;
import com.btjf.vo.weixin.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by liuyq on 2019/8/18.
 */
@Service
@Transactional(readOnly = false, rollbackFor = Exception.class)
public class ProductionProcedureConfirmService {

    private static final Logger LOGGER = Logger
            .getLogger(ProductionProcedureScanService.class);

    @Resource
    private ProductionProcedureConfirmMapper productionProcedureConfirmMapper;

    @Resource
    private ProductionProcedureScanService productionProcedureScanService;

    @Resource
    private ProductWorkshopService productWorkshopService;

    public List<Order> getOrderByMouth(String date, String deptName) {
        return productionProcedureConfirmMapper.getOrderByMouth(date, deptName);
    }

    public List<OrderProductVo> getOrderProductByMouth(String orderNo, String deptName) {
        return productionProcedureConfirmMapper.getOrderProductByMouth(orderNo, deptName);
    }

    public List<EmpProcedureListVo> getEmpNum(String orderNo, String productNo, String deptName) {
        List<ProductProcedure> list =
                productionProcedureConfirmMapper.getByOrderAndProduct(orderNo, productNo, deptName);
        List<EmpProcedureListVo> volist = null;
        if (list != null && list.size() > 0) {
            volist = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                EmpProcedureListVo vo = new EmpProcedureListVo();
                vo.setId(list.get(i).getId());
                vo.setName(list.get(i).getProcedureName());
                vo.setSort(list.get(i).getSort());
                List<EmpProcedureDetailVo> dlist = productionProcedureConfirmMapper.getEmpNum(orderNo, productNo, vo.getId(), deptName);
                vo.setList(dlist);
                volist.add(vo);
            }
        }

        return volist;
    }

    public List<ProductionProcedureConfirm> select(ProductionProcedureConfirm productionProcedureConfirm) {
        if (productionProcedureConfirm == null) return null;
        return productionProcedureConfirmMapper.select(productionProcedureConfirm);
    }

    public Integer add(Integer orderId, String orderNo, Integer louId, String billOutNo, String productNo, String productionNo, WxEmpVo wxEmpVo, Boolean isCreateInspectionorSalary) {

        List<ProductionProcedureScan> productionProcedureScans = productionProcedureScanService.select(orderNo, productNo, productionNo, louId, billOutNo, null);
        if (CollectionUtils.isEmpty(productionProcedureScans)) throw new BusinessException("该订单工序还没有员工处理");
        productionProcedureScans.stream().filter(t -> t != null).forEach(t -> {
            ProductionProcedureConfirm productionProcedureConfirm = new ProductionProcedureConfirm();
            productionProcedureConfirm.setOrderNo(t.getOrderNo());
            productionProcedureConfirm.setType(1);
            productionProcedureConfirm.setIsDelete(0);
            productionProcedureConfirm.setLuoId(t.getLuoId());
            productionProcedureConfirm.setPmOutBillNo(t.getPmOutBillNo());
            productionProcedureConfirm.setProductionNo(t.getProductionNo());
            productionProcedureConfirm.setOrderNo(t.getOrderNo());
            productionProcedureConfirm.setProductNo(t.getProductNo());
            productionProcedureConfirm.setNum(BigDecimal.valueOf(t.getNum()));
            productionProcedureConfirm.setEmpId(t.getEmpId());
            productionProcedureConfirm.setIsChange(0);
            productionProcedureConfirm.setMoney(t.getMoney());
            productionProcedureConfirm.setOperator(wxEmpVo.getName());
            productionProcedureConfirm.setLastModifyTime(new Date());
            productionProcedureConfirm.setCreateTime(new Date());
            productionProcedureConfirm.setCompleteTime(t.getCreateTime());
            productionProcedureConfirm.setPrice(t.getPrice());
            productionProcedureConfirm.setWorkshop(wxEmpVo.getDeptName());
            productionProcedureConfirm.setProcedureId(t.getProcedureId());
            productionProcedureConfirm.setProcedureName(t.getProcedureName());
            productionProcedureConfirm.setInspectionor(isCreateInspectionorSalary.equals(false)?"系统生成":wxEmpVo.getName()); //质检员
            productionProcedureConfirmMapper.insertSelective(productionProcedureConfirm);
            t.setStatus(1);
            //扫码记录改成已质检
            productionProcedureScanService.updateStatue(t);
        });

        if (isCreateInspectionorSalary) {
            //新增质检工资记录
            ProductionProcedureScan productionProcedureScan = productionProcedureScans.get(0);
            //获取质检    //获取负责车间质检工序的价格
            ProductProcedureWorkshop productProcedureWorkshop = productWorkshopService.getInspactPriceByWorkShapAndProductNo(wxEmpVo.getDeptName(), productNo);
            if (productProcedureWorkshop == null) throw new BusinessException("请再您所在车间设置好质检工序之后重试");
            ProductionProcedureConfirm productionProcedureConfirm = new ProductionProcedureConfirm();
            productionProcedureConfirm.setOrderNo(productionProcedureScan.getOrderNo());
            productionProcedureConfirm.setType(3);
            productionProcedureConfirm.setIsDelete(0);
            productionProcedureConfirm.setEmpId(wxEmpVo.getId());
            productionProcedureConfirm.setNum(BigDecimal.valueOf(productionProcedureScan.getNum()));
            productionProcedureConfirm.setPrice(productProcedureWorkshop.getPrice());
            productionProcedureConfirm.setOperator(wxEmpVo.getName());
            productionProcedureConfirm.setIsChange(0);
            productionProcedureConfirm.setMoney(BigDecimal.valueOf(BigDecimalUtil.mul(Double.valueOf(productionProcedureScan.getNum()), productProcedureWorkshop.getPrice().doubleValue())));
            productionProcedureConfirm.setLastModifyTime(new Date());
            productionProcedureConfirm.setCreateTime(new Date());
            productionProcedureConfirm.setCompleteTime(productionProcedureScan.getCreateTime());
            productionProcedureConfirm.setProductNo(productNo);
            productionProcedureConfirm.setProcedureId(productProcedureWorkshop.getProcedureId());
            productionProcedureConfirm.setProcedureName(productProcedureWorkshop.getProcedureName());
            productionProcedureConfirm.setPmOutBillNo(billOutNo);
            productionProcedureConfirm.setLuoId(louId);
            productionProcedureConfirm.setProductionNo(productionNo);
            productionProcedureConfirm.setInspectionor(wxEmpVo.getName()); //质检员

            productionProcedureConfirmMapper.insertSelective(productionProcedureConfirm);
        }

        return productionProcedureScans.size();
    }

    public void change(String orderNo, String productNo, Integer procedureId, List<EmpProcedureDetailVo> list, WxEmpVo vo) {
        //把之前可能存在的 调整数据 删除
        productionProcedureConfirmMapper.deleteType2(orderNo, productNo, procedureId, vo.getDeptName());
        //把之前的质检数据  置为 已调整
        List<ProductionProcedureConfirm> clist = productionProcedureConfirmMapper.getCheckList(orderNo, productNo, procedureId, vo.getDeptName());
        productionProcedureConfirmMapper.updateChange(orderNo, productNo, procedureId, vo.getDeptName());
        //插入 调整后的数据
        ProductionProcedureConfirm t = clist.get(0);
        String inspectionor = null;
        for (int i = 0; i < clist.size(); i++) {
            if (inspectionor == null) {
                inspectionor = clist.get(i).getInspectionor();
                continue;
            }
            if (StringUtils.isNotEmpty(clist.get(i).getInspectionor()) && !inspectionor.contains(clist.get(i).getInspectionor())) {
                inspectionor = inspectionor + "," + clist.get(i).getInspectionor();
            }
        }
        for (int i = 0; i < list.size(); i++) {
            ProductionProcedureConfirm productionProcedureConfirm = new ProductionProcedureConfirm();
            productionProcedureConfirm.setOrderNo(t.getOrderNo());
            productionProcedureConfirm.setType(2);
            productionProcedureConfirm.setIsDelete(0);
            productionProcedureConfirm.setOrderNo(t.getOrderNo());
            productionProcedureConfirm.setProductNo(t.getProductNo());
            productionProcedureConfirm.setNum(BigDecimal.valueOf(list.get(i).getNum()));
            productionProcedureConfirm.setEmpId(list.get(i).getEmpId());
            productionProcedureConfirm.setIsChange(0);
            productionProcedureConfirm.setMoney(t.getPrice().multiply(BigDecimal.valueOf(list.get(i).getNum())));
            productionProcedureConfirm.setOperator(vo.getName());
            productionProcedureConfirm.setLastModifyTime(new Date());
            productionProcedureConfirm.setCreateTime(new Date());
            productionProcedureConfirm.setCompleteTime(new Date());
            productionProcedureConfirm.setPrice(t.getPrice());
            productionProcedureConfirm.setWorkshop(vo.getDeptName());
            productionProcedureConfirm.setProcedureId(procedureId);
            productionProcedureConfirm.setProcedureName(t.getProcedureName());
            productionProcedureConfirm.setInspectionor(inspectionor);
            productionProcedureConfirmMapper.insertSelective(productionProcedureConfirm);
        }
    }

    public Double getChangeNum(String orderNo, String productNo, Integer procedureId, String deptName) {
        return productionProcedureConfirmMapper.getChangeNum(orderNo, productNo, procedureId, deptName);
    }

    public List<EmpDayWorkVo> analyseForDay(String date, Integer empId) {
        return productionProcedureConfirmMapper.analyseForDay(date, empId);
    }

    public List<EmpDayWorkDetailVo> getWorkForDay(String date, Integer empId) {
        return productionProcedureConfirmMapper.getWorkForDay(date, empId);
    }

    public List<ProcedureInfoVo> getWorkProcedureInfo(String date, Integer empId, String orderNo, String productNo,
                                                      String billNo, Integer luoId, Integer type) {
        return productionProcedureConfirmMapper.getWorkProcedureInfo(date, empId, orderNo, productNo, billNo, luoId, type);
    }

    public Page<ProcedureYieldVo> yieldList(String name, Integer deptId, Integer workId, String orderNo,
                                            String productNo, String procedureName, String yearMonth, String startDate, String endDate, Page page) {
        PageHelper.startPage(page.getPage(), page.getRp());
        List<ProcedureYieldVo> pmList = productionProcedureConfirmMapper.yieldList(name, deptId, workId,
                orderNo, productNo, procedureName, yearMonth, startDate, endDate);
        PageInfo pageInfo = new PageInfo(pmList);
        pageInfo.setList(pmList);
        return new Page<>(pageInfo);
    }


    public List<ProductionProcedureConfirm> getUnSettlement(String yearMonth, Integer empId) {
        if (StringUtils.isEmpty(yearMonth) || empId == null) {
            return null;
        }
        return productionProcedureConfirmMapper.getUnSettlement(yearMonth, empId);
    }

    public void updateSettlement(List<Integer> ids) {
        productionProcedureConfirmMapper.updateSettlement(ids);
    }
}
