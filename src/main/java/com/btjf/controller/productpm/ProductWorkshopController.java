package com.btjf.controller.productpm;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.btjf.application.components.xaresult.AppXaResultHelper;
import com.btjf.application.util.XaResult;
import com.btjf.common.page.Page;
import com.btjf.controller.base.ProductBaseController;
import com.btjf.controller.order.vo.WorkShopVo;
import com.btjf.controller.productpm.vo.ProductWorkShopVo;
import com.btjf.model.product.ProductProcedureWorkshop;
import com.btjf.service.productpm.ProductWorkshopService;
import com.google.common.collect.Lists;
import com.heige.aikajinrong.base.exception.BusinessException;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by liuyq on 2019/8/5.
 * 型号工序车间
 */
@RestController
@RequestMapping(value = "/productworkshop")
public class ProductWorkshopController extends ProductBaseController {


    private static final Logger LOGGER = Logger
            .getLogger(ProductWorkshopController.class);

    @Resource
    private ProductWorkshopService productWorkshopService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public XaResult<List<ProductWorkShopVo>> getList(Integer pageSize, Integer currentPage, String type, String productNo) {

        getLoginUser();
        LOGGER.info(getRequestParamsAndUrl());

        if (currentPage == null || currentPage < 1) {
            currentPage = 1;
        }
        if (pageSize == null || pageSize < 1) {
            pageSize = 25;
        }
        Page page = new Page(pageSize, currentPage);
        Page<ProductWorkShopVo> productWorkShopVoPage = productWorkshopService.getListPage(page, type, productNo);
        return AppXaResultHelper.success(productWorkShopVoPage, productWorkShopVoPage.getRows());
    }

    @RequestMapping(value = "/getByWorkshopName", method = RequestMethod.GET)
    public XaResult<List<WorkShopVo.Procedure>> getByWorkshopName(String workshopName) throws BusinessException {
        if (StringUtils.isEmpty(workshopName)) return XaResult.success();
        List<WorkShopVo.Procedure> result = Lists.newArrayList();
        List<ProductProcedureWorkshop> productProcedureWorkshops = productWorkshopService.findByWorkshopName(workshopName);
        if (!CollectionUtils.isEmpty(productProcedureWorkshops)) {
            for (ProductProcedureWorkshop productProcedureWorkshop : productProcedureWorkshops) {
                result.add(new WorkShopVo.Procedure(productProcedureWorkshop));
            }
            return XaResult.success(result);
        } else {
            return XaResult.success();
        }
    }
}
