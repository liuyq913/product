package com.btjf.controller.order;

import com.btjf.controller.base.ProductBaseController;
import com.wordnik.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liuyq on 2019/8/4.
 */
@RestController
@RequestMapping(value = "/order")
@Api(value = "OrderController", description = "订单管理", position = 1)
public class OrderController extends ProductBaseController {
}
