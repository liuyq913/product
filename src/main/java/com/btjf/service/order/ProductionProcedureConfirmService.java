package com.btjf.service.order;

import com.btjf.business.common.exception.BusinessException;
import com.btjf.controller.weixin.vo.WxEmpVo;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.btjf.business.common.exception.BusinessException;
import com.btjf.controller.weixin.vo.WxEmpVo;
import com.btjf.mapper.order.ProductionProcedureConfirmMapper;
import com.btjf.model.order.Order;
import com.btjf.model.order.ProductionProcedureConfirm;
import com.btjf.model.order.ProductionProcedureScan;
import com.btjf.model.product.ProductProcedure;
import com.btjf.vo.weixin.*;
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

    public List<Order> getOrderByMouth(String date,String deptName) {
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

    public Integer add(Integer orderId, String orderNo, Integer louId, String billOutNo, String productNo, String productionNo, WxEmpVo wxEmpVo) {


        Integer row = productionProcedureConfirmMapper.delete(orderNo, productNo, productionNo, louId, billOutNo, null);
        if (row > 0) LOGGER.info("质检员确认工序 删除之前质检的工序条数：" + row);


        List<ProductionProcedureScan> productionProcedureScans = productionProcedureScanService.select(orderNo, productNo, productionNo, louId, billOutNo, null);
        if(CollectionUtils.isEmpty(productionProcedureScans)) throw new BusinessException("该订单工序还没有员工处理");
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
            productionProcedureConfirm.setNum(t.getNum());
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
            productionProcedureConfirmMapper.insertSelective(productionProcedureConfirm);
        });
        return productionProcedureScans.size();
    }

    public void change(String orderNo, String productNo, Integer procedureId, List<EmpProcedureDetailVo> list, WxEmpVo vo){
        //把之前可能存在的 调整数据 删除
        productionProcedureConfirmMapper.deleteType2(orderNo, productNo, procedureId, vo.getDeptName());
        //把之前的质检数据  置为 已调整
        List<ProductionProcedureConfirm> clist = productionProcedureConfirmMapper.getCheckList(orderNo, productNo, procedureId, vo.getDeptName());
        productionProcedureConfirmMapper.updateChange(orderNo, productNo, procedureId, vo.getDeptName());
        //插入 调整后的数据
        ProductionProcedureConfirm t = clist.get(0);
        for (int i = 0; i < list.size(); i++){
            ProductionProcedureConfirm productionProcedureConfirm = new ProductionProcedureConfirm();
            productionProcedureConfirm.setOrderNo(t.getOrderNo());
            productionProcedureConfirm.setType(2);
            productionProcedureConfirm.setIsDelete(0);
            productionProcedureConfirm.setOrderNo(t.getOrderNo());
            productionProcedureConfirm.setProductNo(t.getProductNo());
            productionProcedureConfirm.setNum(list.get(i).getNum());
            productionProcedureConfirm.setEmpId(list.get(i).getEmpId());
            productionProcedureConfirm.setIsChange(0);
            productionProcedureConfirm.setMoney(t.getPrice().multiply(BigDecimal.valueOf(list.get(i).getNum())));
            productionProcedureConfirm.setOperator(vo.getName());
            productionProcedureConfirm.setLastModifyTime(new Date());
            productionProcedureConfirm.setCreateTime(new Date());
            productionProcedureConfirm.setPrice(t.getPrice());
            productionProcedureConfirm.setWorkshop(vo.getDeptName());
            productionProcedureConfirm.setProcedureId(procedureId);
            productionProcedureConfirm.setProcedureName(t.getProcedureName());
            productionProcedureConfirmMapper.insertSelective(productionProcedureConfirm);
        }
    }

    public Integer getChangeNum(String orderNo, String productNo, Integer procedureId, String deptName) {
        return productionProcedureConfirmMapper.getChangeNum(orderNo, productNo, procedureId, deptName);
    }

    public List<EmpDayWorkVo> analyseForDay(String date, Integer id) {
        return null;
    }

    public List<EmpDayWorkDetailVo> getWorkForDay(String date, Integer id) {
        return null;
    }
}
