package com.btjf.controller.productpm;

import com.btjf.application.components.page.AppPageHelper;
import com.btjf.application.components.xaresult.AppXaResultHelper;
import com.btjf.application.util.XaResult;
import com.btjf.common.page.Page;
import com.btjf.controller.base.ProductBaseController;
import com.btjf.model.product.ProductPm;
import com.btjf.service.pm.PmService;
import com.btjf.service.productpm.ProductPmService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiParam;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * Created by liuyq on 2019/7/29.
 */
@RestController
@RequestMapping(value = "/productpm")
@Api(value = "ProductPmController", description = "耗料管理", position = 1)
public class ProductPmController extends ProductBaseController {

    private static final Logger LOGGER = Logger
            .getLogger(ProductPmController.class);

    @Resource
    private ProductPmService productPmService;

    @Resource
    private PmService pmService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public XaResult<List<ProductPm>> findList(@ApiParam("编号") String productNo, @ApiParam("名称") String pmNo
            , @ApiParam("1已确认  0 未确认") int status, Integer pageSize, Integer currentPage) {
        getLoginUser();
        LOGGER.info(getRequestParamsAndUrl());

        Page<ProductPm> listPage = productPmService.findListPage(productNo, pmNo, status, AppPageHelper.appInit(currentPage, pageSize));
        XaResult<List<ProductPm>> result = AppXaResultHelper.success(listPage, listPage.getRows());
        return result;
    }

    @RequestMapping(value = "/modelConfige", method = RequestMethod.POST)
    public XaResult<Integer> modelConfige(Integer[] ids) {
        getLoginUser();
        LOGGER.info(getRequestParamsAndUrl());

        if (null == ids || Arrays.asList(ids).size() <= 0) {
            return XaResult.error("请选择要确认的记录");
        }
        Integer row = productPmService.updateStatue(Arrays.asList(ids));
        return XaResult.success(row);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public XaResult<Integer> delete(Integer[] ids) {
        getLoginUser();
        LOGGER.info(getRequestParamsAndUrl());

        if (null == ids || Arrays.asList(ids).size() <= 0) {
            return XaResult.error("请选择要删除的记录");
        }
        Integer row = productPmService.delete(Arrays.asList(ids));
        return XaResult.success(row);
    }

    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    public XaResult<ProductPm> detail(Integer id) {
        getLoginUser();
        LOGGER.info(getRequestParamsAndUrl());

        if (id == null) {
            return XaResult.error("请选择要查看的记录");
        }
        ProductPm productpm = productPmService.getByID(id);
        if (null == productpm) {
            return XaResult.error("查看的记录不存在");
        }
        return XaResult.success(productpm);
    }


    @RequestMapping(value = "/addOrUpdate", method = RequestMethod.POST)
    public XaResult<ProductPm> updateOrAdd(Integer id, String productNo, String pmNo, String pmName, String num,
                                           String unit, String unitNum, String type, String remark, int status) {
        getLoginUser();
        LOGGER.info(getRequestParamsAndUrl());

        if (null == pmService.getByNo(pmNo)) {
            return XaResult.error("物料编号填写错误，请修改");
        }


        if (status != 0 || status != 1) {
            return XaResult.error("是否确认类型错误");
        }

        if (id != null) {

        } else {
            if (null != productPmService.getByNo(productNo)) {
                return XaResult.error("物料编号已经存在");
            }
        }
        return null;
    }


}
