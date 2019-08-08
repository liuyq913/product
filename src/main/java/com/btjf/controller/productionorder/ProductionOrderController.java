package com.btjf.controller.productionorder;

import com.btjf.application.util.XaResult;
import com.btjf.controller.base.ProductBaseController;
import com.wordnik.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liuyq on 2019/8/8.
 * <p>
 * 生产单
 */
@RestController
@RequestMapping(value = "/ProductionOrder")
@Api(value = "ProductionOrderController", description = "生产单管理", position = 1)
public class ProductionOrderController extends ProductBaseController {



    @RequestMapping(value="/assign")
    public XaResult<Integer> assign(){

    }

}
