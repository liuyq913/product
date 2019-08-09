package com.btjf.controller.productionorder;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSONObject;
import com.btjf.application.components.xaresult.AppXaResultHelper;
import com.btjf.application.util.XaResult;
import com.btjf.common.page.Page;
import com.btjf.controller.base.ProductBaseController;
import com.btjf.controller.order.vo.WorkShopVo;
import com.btjf.controller.productionorder.vo.ProductionOrderDetailVo;
import com.btjf.controller.productionorder.vo.ProductionOrderVo;
import com.btjf.model.order.OrderProduct;
import com.btjf.model.order.ProductionLuo;
import com.btjf.model.order.ProductionOrder;
import com.btjf.model.order.ProductionProcedure;
import com.btjf.model.sys.SysUser;
import com.btjf.service.order.OrderProductService;
import com.btjf.service.order.ProductionLuoService;
import com.btjf.service.order.ProductionOrderService;
import com.btjf.service.order.ProductionProcedureService;
import com.google.common.collect.Lists;
import com.heige.aikajinrong.base.exception.BusinessException;
import com.wordnik.swagger.annotations.Api;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by liuyq on 2019/8/8.
 * <p>
 * 生产单
 */
@RestController
@RequestMapping(value = "/ProductionOrder")
@Api(value = "ProductionOrderController", description = "生产单管理", position = 1)
public class ProductionOrderController extends ProductBaseController {

    @Resource
    private OrderProductService orderProductService;

    @Resource
    private ProductionOrderService productionOrderService;

    @Resource
    private ProductionProcedureService productionProcedureService;

    @Resource
    private ProductionLuoService productionLuoService;


    @RequestMapping(value = "/assign")
    public XaResult<Integer> assign(Integer orderProductId, Integer assignNum, String workshop, String workshopDirector,
                                    Integer isLuo, Integer luoNum, String procedure) throws BusinessException {

        OrderProduct orderProduct = orderProductService.getByID(orderProductId);
        if (null == orderProduct) return XaResult.error("订单不存在");
        if (null == assignNum) return XaResult.error("请输入分配数额");
        if (null == isLuo || isLuo != 1 || isLuo != 0) return XaResult.error("请选择是否分萝");
        if (isLuo == 1 && (null == luoNum || luoNum < 0)) return XaResult.error("请输入一萝数量");
        if (orderProduct.getNotAssignNum() < assignNum) return XaResult.error("可分配数额不足");
        List<WorkShopVo.Procedure> procedures = Lists.newArrayList();
        if (!StringUtils.isEmpty(procedure)) {
            procedures = JSONObject.parseArray(procedure, WorkShopVo.Procedure.class);
        }
        ProductionOrder productionOrder = new ProductionOrder();
        productionOrder.setIsDelete(0);
        productionOrder.setOrderProductId(orderProductId);
        productionOrder.setCreateTime(new Date());
        productionOrder.setAssignNum(assignNum);
        productionOrder.setLastModifyTime(new Date());
        productionOrder.setIsLuo(isLuo);
        productionOrder.setOrderId(orderProduct.getOrderId());
        productionOrder.setProductNo(orderProduct.getProductNo());
        productionOrder.setMaxNum(orderProduct.getMaxNum());
        productionOrder.setWorkshop(workshop);
        productionOrder.setWorkshopDirector(workshopDirector);

        Integer id = productionOrderService.assign(productionOrder, procedures);
        return XaResult.success(id);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public XaResult<List<ProductionOrderVo>> getList(String orderNo, String productionNo, String customerName, String productNo,
                                                     String productType, String workshop, String workshopDirector, String createStartTime,
                                                     String createEndTime, Integer pageSize, Integer currentPage) throws BusinessException {
        if (currentPage == null || currentPage < 1) {
            currentPage = 1;
        }
        if (pageSize == null || pageSize < 1) {
            pageSize = 25;
        }
        Page page = new Page(pageSize, currentPage);
        ProductionOrderVo productionOrderVo = new ProductionOrderVo();
        productionOrderVo.setOrderNo(orderNo);
        productionOrderVo.setProductionNo(productionNo);
        productionOrderVo.setCustomerName(customerName);
        productionOrderVo.setProductNo(productNo);
        productionOrderVo.setProductType(productType);
        productionOrderVo.setWorkshop(workshop);
        productionOrderVo.setWorkshopDirector(workshopDirector);
        productionOrderVo.setCreateEndTime(createEndTime);
        productionOrderVo.setCreateStartTime(createStartTime);
        Page<ProductionOrderVo> productionOrderVoPage = productionOrderService.getPage(productionOrderVo, page);
        XaResult<List<ProductionOrderVo>> result = AppXaResultHelper.success(productionOrderVoPage, productionOrderVoPage.getRows());
        return result;
    }

    @RequestMapping(value = "/print", method = RequestMethod.GET)
    public XaResult<List<ProductionOrderDetailVo>> getDetailByProductionNo(String productionNo) throws BusinessException {
        SysUser sysUser = getLoginUser();

        if (null == productionNo) return XaResult.error("请输入要打印是生成单号");

        List<ProductionOrderDetailVo> productionOrderDetailVos = Lists.newArrayList();
        ProductionOrder productionOrder = productionOrderService.getByNo(productionNo);
        if (productionOrder == null) return XaResult.error("该生成单不存在");
        //订单
        OrderProduct orderProduct = orderProductService.getByID(productionOrder.getOrderId());
        //工序
        List<ProductionProcedure> productionProcedures = productionProcedureService.findByProductionNo(productionOrder.getProductionNo());

        ProductionOrderDetailVo productionOrderDetailVo = new ProductionOrderDetailVo(productionOrder, productionProcedures, orderProduct);
        productionOrderDetailVo.setPrintTime(new Date());
        productionOrderDetailVo.setPrinter(sysUser.getUserName());
        //未分
        if (productionOrder.getIsLuo() == 1) {
            List<ProductionLuo> productionLuos = productionLuoService.getByProductionNo(productionOrder.getProductionNo());
            if (!CollectionUtils.isEmpty(productionLuos)) {
                productionLuos.stream().forEach(t -> {
                    try {
                        ProductionOrderDetailVo productionOrderDetailVo1 = (ProductionOrderDetailVo) BeanUtils.cloneBean(productionOrderDetailVo);
                        productionOrderDetailVo1.setAssignNum(t.getNum());
                        productionOrderDetailVo1.setCodeUrl(t.getCodeUrl());
                        productionOrderDetailVos.add(productionOrderDetailVo1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
        } else {
            productionOrderDetailVos.add(productionOrderDetailVo);
        }
        return XaResult.success(productionOrderDetailVos);
    }

}

