package com.btjf.service.order;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.dubbo.common.utils.StringUtils;
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

        productionOrder.setProductionNo("P"+billNoService.getNo(4));
        productionOrderMapper.insertSelective(productionOrder);
        //更新  订单 型号表 分配数量信息
        OrderProduct orderProduct = orderProductService.getByID(productionOrder.getOrderProductId());
        OrderProduct orderProduct1 = new OrderProduct();
        orderProduct1.setId(productionOrder.getOrderProductId());
        orderProduct1.setAssignedNum(orderProduct.getAssignedNum() + productionOrder.getAssignNum());
        orderProduct1.setNotAssignNum(orderProduct.getNotAssignNum() - productionOrder.getAssignNum());
        orderProduct1.setLastModifyTime(new Date());
        orderProductService.update(orderProduct1);

        //工序
        if (!CollectionUtils.isEmpty(procedures)) {
            procedures.stream().filter(t -> t != null).forEach(t -> {
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
                productionProcedureService.insert(productionProcedure);
            });
        }

        //分萝
        List<ProductionLuo> productionLuos = distribution(productionOrder);
        if (!CollectionUtils.isEmpty(productionLuos)) {
            productionLuos.forEach(t -> productionLuoService.insert(t));
        }

        return productionOrder.getId();
    }

    private List<ProductionLuo> distribution(ProductionOrder productionOrder) {
        List<ProductionLuo> productionLuos = Lists.newArrayList();
        Integer assignNum = productionOrder.getAssignNum();
        if (null != productionOrder && productionOrder.getIsLuo() == 1) {
            do {
                ProductionLuo productionLuo = new ProductionLuo();
                productionLuo.setIsDelete(0);
                productionLuo.setOrderNo(productionOrder.getOrderNo());
                productionLuo.setMaxNum(productionOrder.getMaxNum());
                productionLuo.setNum(productionOrder.getLuoNum());
                productionLuo.setCreateTime(new Date());
                productionLuo.setProductNo(productionOrder.getProductNo());
                productionLuo.setOrderId(productionOrder.getOrderId());
                productionLuo.setProductionNo(productionOrder.getProductionNo());
                productionLuos.add(productionLuo);
                assignNum -= productionOrder.getLuoNum();
            } while (assignNum > productionOrder.getLuoNum() * 1.5);

            //最后不足1.5倍的方一框
            ProductionLuo productionLuo = new ProductionLuo();
            productionLuo.setIsDelete(0);
            productionLuo.setOrderNo(productionOrder.getOrderNo());
            productionLuo.setMaxNum(productionOrder.getMaxNum());
            productionLuo.setNum(assignNum);
            productionLuo.setProductionNo(productionOrder.getProductionNo());
            productionLuo.setCreateTime(new Date());
            productionLuo.setProductNo(productionOrder.getProductNo());
            productionLuo.setOrderId(productionOrder.getOrderId());
            productionLuos.add(productionLuo);

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
}
