package com.btjf.controller.productpm;

import com.btjf.controller.base.ProductBaseController;
import com.btjf.service.productpm.ProductProcedureService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by liuyq on 2019/8/5.
 */
@RestController
@RequestMapping(value = "/productpmprocedure")
public class ProductProcedureController extends ProductBaseController {

    @Resource
    private ProductProcedureService productProcedureService;

}
