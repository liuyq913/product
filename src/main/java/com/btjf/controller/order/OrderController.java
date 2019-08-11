package com.btjf.controller.order;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.btjf.application.components.xaresult.AppXaResultHelper;
import com.btjf.application.util.XaResult;
import com.btjf.common.page.Page;
import com.btjf.common.utils.DateUtil;
import com.btjf.controller.base.ProductBaseController;
import com.btjf.controller.order.vo.OrderProductVo;
import com.btjf.controller.order.vo.OrderVo;
import com.btjf.model.order.Order;
import com.btjf.model.order.OrderProduct;
import com.btjf.model.product.Product;
import com.btjf.model.product.ProductProcedureWorkshop;
import com.btjf.service.order.OrderProductService;
import com.btjf.service.order.OrderService;
import com.btjf.service.productpm.ProductService;
import com.btjf.service.productpm.ProductWorkshopService;
import com.google.common.collect.Lists;
import com.heige.aikajinrong.base.exception.BusinessException;
import com.wordnik.swagger.annotations.Api;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by liuyq on 2019/8/4.
 */
@RestController
@RequestMapping(value = "/order")
@Api(value = "OrderController", description = "订单管理", position = 1)
public class OrderController extends ProductBaseController {

    @Resource
    private OrderService orderService;

    @Resource
    private OrderProductService orderProductService;

    @Resource
    private ProductService productService;

    @Resource
    private ProductWorkshopService productWorkshopService;


    private static final Logger LOGGER = Logger
            .getLogger(OrderController.class);

    @RequestMapping(value = "/updateOrAdd", method = RequestMethod.POST)
    public XaResult<Integer> updateOrAdd(Integer id, String orderNo, String productNo, Integer num,
                                         String type, String unit, Integer maxNum, String completeDate,
                                         String customerName, Integer customerId, Integer isMore, Integer urgentLevel) {

        if (StringUtils.isEmpty(orderNo)) {
            return XaResult.error("订单No为空");
        }

        if (StringUtils.isEmpty(productNo)) {
            return XaResult.error("产品编号为空");
        }

        if (num == null) {
            return XaResult.error("数量为空");
        }
        if (num <= 0) {
            return XaResult.error("数量有误");
        }

        if (StringUtils.isEmpty(unit)) {
            return XaResult.error("单位为空");
        }
        if (StringUtils.isEmpty(type)) {
            return XaResult.error("类型为空");
        }
        if (maxNum == null) {
            return XaResult.error("上限数量为空");
        }

        if (maxNum < num) {
            return XaResult.error("上限数量有误");
        }
        if (customerId == null) {
            return XaResult.error("客户信息有误");
        }

        if (completeDate == null) {
            return XaResult.error("完成时间为空");
        }

        Integer orderID = null;
        Order order = orderService.getByNo(orderNo);
        if (null == order) {
            orderID = orderService.insert(new Order(orderNo, new Date(), new Date(), 0));
        } else {
            orderID = order.getId();
        }
        Product product = productService.getByNO(productNo);
        if (product == null) {
            return XaResult.error("该型号不存在");
        }

        OrderProduct orderProduct1 = new OrderProduct(orderID, orderNo, product.getId(),
                productNo, type, num, maxNum, unit, DateUtil.string2Date(completeDate, DateUtil.ymdFormat), customerId, customerName, null, null,
                null, null, null, new Date(), new Date(), 0);
        if (id != null) {
            return XaResult.error("暂时不支持更新");
        } else {
            OrderProduct orderProduct = orderProductService.getByOrderNoAndProductNo(orderNo, productNo);
            if (null != orderProduct) {
                return XaResult.error("该型号的订单已经存在");
            }
            orderProduct1.setNotAssignNum(maxNum);
            orderProduct1.setAssignedNum(0);
            orderProduct1.setIsMore(isMore);
            orderProduct1.setUrgentLevel(urgentLevel);
            orderID = orderProductService.insert(orderProduct1);
        }
        return XaResult.success(orderID);

    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public XaResult<List<OrderVo>> list(String orderNo, String pmNo, String type, Integer customerId, String completeStartDate,
                                        String completeStartEnd, String createStartDate, String createEndDate, Integer pageSize, Integer currentPage) {

        LOGGER.info(getRequestParamsAndUrl());
        if (currentPage == null || currentPage < 1) {
            currentPage = 1;
        }
        if (pageSize == null || pageSize < 1) {
            pageSize = 25;
        }
        Page page = new Page(pageSize, currentPage);

        Page<OrderVo> listPage = orderProductService.listPage(customerId, orderNo, pmNo, type, completeStartDate, completeStartEnd, createStartDate, createEndDate, page);
        XaResult<List<OrderVo>> result = AppXaResultHelper.success(listPage, listPage.getRows());
        return result;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public XaResult<Integer> delete(String[] orderIds) throws BusinessException {
        if (null == orderIds || Arrays.asList(orderIds).size() <= 0) {
            return XaResult.error("请选择要删除的记录");
        }
        Integer num = orderService.delete(Arrays.asList(orderIds));
        return XaResult.success(num);
    }


    /**
     * 获取所有未分配订单
     *
     * @return
     */
    @RequestMapping(value = "/notAssignOrder", method = RequestMethod.GET)
    public XaResult<List<Order>> orderProductVos(String orderNo) {
        List<Order> orders = orderService.notAssignOrder(orderNo);
        return XaResult.success(orders);
    }

    @RequestMapping(value = "/getOrderProductAndProcedure", method = RequestMethod.GET)
    public XaResult<List<OrderProductVo>> getOrderProductAndProcedure(Integer orderId) {
        if (orderId == null) XaResult.error("请输入订单id");

        List<OrderProduct> products = orderProductService.findByOrderId(orderId);
        List<OrderProductVo> productVos = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(products)) {
            products.stream().filter(t -> t != null).forEach(t -> {
                List<ProductProcedureWorkshop> productProcedureWorkshops =
                        productWorkshopService.getWorkShop(t.getProductNo());
                OrderProductVo orderProductVo = new OrderProductVo(t, productProcedureWorkshops);
                productVos.add(orderProductVo);
            });
        }
        return XaResult.success(productVos);
    }


}
