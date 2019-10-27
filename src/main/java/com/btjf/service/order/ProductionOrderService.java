package com.btjf.service.order;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.btjf.business.common.exception.BusinessException;
import com.btjf.common.page.Page;
import com.btjf.controller.order.vo.WorkShopVo;
import com.btjf.controller.productionorder.vo.ProductionOrderVo;
import com.btjf.mapper.order.ProductionOrderMapper;
import com.btjf.model.order.OrderProduct;
import com.btjf.model.order.ProductionLuo;
import com.btjf.model.order.ProductionOrder;
import com.btjf.model.order.ProductionProcedure;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * Created by liuyq on 2019/8/8.
 * <p>
 * 生产单
 */
@Service
@Transactional(readOnly = false, rollbackFor = Exception.class)
public class ProductionOrderService {
    @Resource
    private ProductionOrderMapper productionOrderMapper;

    @Resource
    private ProductionProcedureService productionProcedureService;

    @Resource
    private ProductionLuoService productionLuoService;

    @Resource
    private OrderProductService orderProductService;

    @Resource
    private BillNoService billNoService;


    public ProductionOrder getByOrderProductID(Integer orderProductID) {
        if (orderProductID == null) return null;

        return productionOrderMapper.getByOrderProductID(orderProductID);
    }

    public Integer assign(ProductionOrder productionOrder, List<WorkShopVo.Procedure> procedures) {
        if (null == productionOrder) return 0;

        productionOrder.setProductionNo("P" + billNoService.getNo(4));
        productionOrder.setCodeUrl("/wx/work/getConfirmList?orderId=" + productionOrder.getOrderProductId() + "&orderNo=" + productionOrder.getOrderNo()
                + "&productNo=" + productionOrder.getProductNo() + "&productionNo=" + productionOrder.getProductionNo());
        productionOrderMapper.insertSelective(productionOrder);
        //更新  订单 型号表 分配数量信息
//        OrderProduct orderProduct = orderProductService.getByID(productionOrder.getOrderProductId());
//        OrderProduct orderProduct1 = new OrderProduct();
//        orderProduct1.setId(productionOrder.getOrderProductId());
//        orderProduct1.setAssignedNum(orderProduct.getAssignedNum() + productionOrder.getAssignNum());
//        orderProduct1.setNotAssignNum(orderProduct.getNotAssignNum() - productionOrder.getAssignNum());
//        orderProduct1.setLastModifyTime(new Date());
//        orderProductService.update(orderProduct1);

        //工序
        if (!CollectionUtils.isEmpty(procedures)) {
            for (WorkShopVo.Procedure t : procedures) {
                ProductionProcedure productionProcedure = new ProductionProcedure();
                productionProcedure.setProductionNo(productionOrder.getProductionNo());
                productionProcedure.setOrderId(productionOrder.getOrderId());
                productionProcedure.setProductNo(productionOrder.getProductNo());
                productionProcedure.setCreateTime(new Date());
                productionProcedure.setIsDelete(0);
                productionProcedure.setProcedureId(t.getProcedureId());
                productionProcedure.setProcedureName(t.getProcedureName());
                productionProcedure.setOrderNo(productionOrder.getOrderNo());
                productionProcedure.setSort(t.getSort());
                if (productionOrder.getAssignNum() > productionProcedureService.procedureCanAssignNum(productionOrder.getOrderNo(), productionOrder.getProductNo(), t.getProcedureId())) {
                    throw new BusinessException("工序:" + t.getProcedureName() + "可分配数量不足");
                }
                productionProcedure.setAssignNum(productionOrder.getAssignNum()); //工序数量
                productionProcedureService.insert(productionProcedure);
            }
        }

        //分萝
        List<ProductionLuo> productionLuos = distribution(productionOrder);
        if (!CollectionUtils.isEmpty(productionLuos)) {
            productionLuos.stream().filter(t -> t != null).forEach(t -> {
                Integer id = productionLuoService.insert(t);
                ProductionLuo productionLuo = new ProductionLuo();
                productionLuo.setId(id);
                productionLuo.setCodeUrl(productionOrder.getCodeUrl() + "&louId=" + id);
                productionLuoService.update(productionLuo);
            });
        }

        return productionOrder.getId();
    }

    private List<ProductionLuo> distribution(ProductionOrder productionOrder) {
        List<ProductionLuo> productionLuos = Lists.newArrayList();
        Integer assignNum = productionOrder.getAssignNum();
        Integer sort = 0;
        if (null != productionOrder && productionOrder.getIsLuo() == 1) {
            if (assignNum % productionOrder.getLuoNum() > 1) throw new BusinessException("分萝错误，请修改");
            do {
                ProductionLuo productionLuo = new ProductionLuo();
                productionLuo.setIsDelete(0);
                productionLuo.setOrderNo(productionOrder.getOrderNo());
                productionLuo.setMaxNum(productionOrder.getMaxNum());
                productionLuo.setNum(productionOrder.getLuoNum()); //生产单对应工序的num 罗的工序和 生产单的工序一致
                productionLuo.setCreateTime(new Date());
                productionLuo.setProductNo(productionOrder.getProductNo());
                productionLuo.setOrderId(productionOrder.getOrderId());
                productionLuo.setProductionNo(productionOrder.getProductionNo());
                productionLuo.setSort(++sort);
                productionLuos.add(productionLuo);
                assignNum -= productionOrder.getLuoNum();
            } while (assignNum > productionOrder.getLuoNum() + 1);

            if (assignNum > 0) {
                ProductionLuo productionLuo = new ProductionLuo();
                productionLuo.setIsDelete(0);
                productionLuo.setOrderNo(productionOrder.getOrderNo());
                productionLuo.setMaxNum(productionOrder.getMaxNum());
                productionLuo.setNum(assignNum);
                productionLuo.setProductionNo(productionOrder.getProductionNo());
                productionLuo.setCreateTime(new Date());
                productionLuo.setProductNo(productionOrder.getProductNo());
                productionLuo.setOrderId(productionOrder.getOrderId());
                productionLuo.setSort(++sort);
                productionLuos.add(productionLuo);

            }
            return productionLuos;
        } else {
            return null;
        }

    }

    public Page<ProductionOrderVo> getPage(ProductionOrderVo productionOrderVo, Page page) {
        PageHelper.startPage(page.getPage(), page.getRp());
        List<ProductionOrderVo> pmList = productionOrderMapper.findList(productionOrderVo);
        PageInfo pageInfo = new PageInfo(pmList);
        pageInfo.setList(pmList);
        return new Page<>(pageInfo);
    }

    public List<ProductionOrderVo> getList(ProductionOrderVo productionOrderVo) {
        List<ProductionOrderVo> pmList = productionOrderMapper.findList(productionOrderVo);
        return pmList;
    }

    public ProductionOrder getByNo(String productionNo) {
        if (StringUtils.isEmpty(productionNo)) return null;

        return productionOrderMapper.getByNo(productionNo);
    }

    private static String getDateTime() {
        DateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(new Date());
    }

    private String getOrderIdByUUId() {
        int first = new Random(10).nextInt(8) + 1;
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if (hashCodeV < 0) {//有可能是负数
            hashCodeV = -hashCodeV;
        }
        // 0 代表前面补充0
        // 4 代表长度为4
        // d 代表参数为正数型
        return first + String.format("%015d", hashCodeV);
    }

    public Integer update(ProductionOrder productionOrder) {
        return productionOrderMapper.updateByPrimaryKeySelective(productionOrder);
    }

    public Integer delete(String productionNo, String orderNo) {
        ProductionOrder productionOrder = productionOrderMapper.getByNo(productionNo);
        if (productionOrder == null) throw new BusinessException("生成单不存在");

        //删除生成单
        productionOrder.setIsDelete(1);
        productionOrderMapper.updateByPrimaryKeySelective(productionOrder);

        //分配数据返回
       /* Integer orderProductId = productionOrder.getOrderProductId();
        OrderProduct orderProduct = orderProductService.getByID(orderProductId);
        OrderProduct orderProduct1 = new OrderProduct();
        orderProduct1.setAssignedNum(orderProduct.getAssignedNum() - productionOrder.getAssignNum());
        orderProduct1.setNotAssignNum(orderProduct.getNotAssignNum() + productionOrder.getAssignNum());
        orderProduct1.setLastModifyTime(new Date());
        orderProductService.update(orderProduct1);*/

        //删除罗信息
        productionLuoService.deleteByProductionNo(productionNo);

        //删除工序信息
        productionProcedureService.deleteByProductionNo(productionNo);

        return 1;
    }

    public static void main(String[] ars) {
        Integer num = 41;
        Integer luoNum = 4;
        if (num % luoNum > 1) throw new BusinessException("分萝错误，请修改");
        do {
            System.out.println(luoNum);
            num -= luoNum;
        } while (num > luoNum + 1);
        System.out.println(num);

    }
}
