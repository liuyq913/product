package com.btjf.controller.pm;

import com.btjf.application.components.page.AppPageHelper;
import com.btjf.application.components.xaresult.AppXaResultHelper;
import com.btjf.application.util.XaResult;
import com.btjf.common.page.Page;
import com.btjf.controller.base.ProductBaseController;
import com.btjf.model.pm.Pm;
import com.btjf.model.sys.SysUser;
import com.btjf.service.pm.PmService;
import com.btjf.vo.PmInVo;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiParam;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by liuyq on 2019/7/28.
 */
@RestController
@RequestMapping(value = "/pm/in")
@Api(value = "PmInControllerProduct", description = "材料管理", position = 1)
public class PmInControllerProduct extends ProductBaseController {

    private static final Logger LOGGER = Logger
            .getLogger(PmInControllerProduct.class);

    @Resource
    private PmService pmService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public XaResult<List<PmInVo>> findList(@ApiParam("编号") String pmNo, @ApiParam("名称") String name
            , @ApiParam("类型") String type, @ApiParam("起始时间") String startDate, @ApiParam("截止时间") String endDate,
                                           Integer pageSize, Integer currentPage) {
        LOGGER.info(getRequestParamsAndUrl());

        Page<Pm> listPage = pmService.findListPage(null, AppPageHelper.appInit(currentPage, pageSize));
        XaResult<List<Pm>> result = AppXaResultHelper.success(listPage, listPage.getRows());
        return null;
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public XaResult<Integer> add(@ApiParam("id") Integer id, @ApiParam("入库数量") Integer num, @ApiParam("供应单位")
            String supplier, @ApiParam("入库日期") String date,@ApiParam("备注") String remark) {
        LOGGER.info(getRequestParamsAndUrl());

        SysUser sysUser = getLoginUser();


        return XaResult.success(id);

    }

    @RequestMapping(value = "query", method = RequestMethod.GET)
    public XaResult<Pm> query(@ApiParam("材料编号") Integer pmNo) {
        LOGGER.info(getRequestParamsAndUrl());



        return XaResult.success();

    }

    @RequestMapping(value = "export", method = RequestMethod.GET)
    public XaResult<Pm> export(@ApiParam("编号") String pmNo, @ApiParam("名称") String name
            , @ApiParam("类型") String type, @ApiParam("起始时间") String startDate, @ApiParam("截止时间") String endDate) {
        LOGGER.info(getRequestParamsAndUrl());



        return XaResult.success();

    }

}
