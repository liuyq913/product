package com.btjf.controller.productionorder;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.btjf.application.util.XaResult;
import com.btjf.controller.base.ProductBaseController;
import com.btjf.controller.order.vo.WorkShopVo;
import com.btjf.model.order.OrderProduct;
import com.btjf.model.order.ProductionOrder;
import com.btjf.service.order.OrderProductService;
import com.btjf.service.order.ProductionOrderService;
import com.google.common.collect.Lists;
import com.heige.aikajinrong.base.exception.BusinessException;
import com.wordnik.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
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

        Integer id = productionOrderService.assign(productionOrder, procedures);
        return XaResult.success(id);
    }

}
