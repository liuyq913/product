package com.btjf.controller.pm;

import com.btjf.application.components.page.AppPageHelper;
import com.btjf.application.components.xaresult.AppXaResultHelper;
import com.btjf.application.util.XaResult;
import com.btjf.common.page.Page;
import com.btjf.model.pm.Pm;
import com.btjf.service.pm.PmService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * Created by liuyq on 2019/7/28.
 */
@RestController
@RequestMapping(value = "/pm")
@Api(value = "PmController", description = "材料管理", position = 1)
public class PmController {
    @Resource
    private PmService pmService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public XaResult<List<Pm>> findList(@ApiParam("编号") String pmNo, @ApiParam("名称") String name
            , @ApiParam("类型") String type, Integer pageSize, Integer currentPage) {

        Page<Pm> listPage = pmService.findListPage(pmNo, name, type, AppPageHelper.appInit(currentPage, pageSize));
        XaResult<List<Pm>> result = AppXaResultHelper.success(listPage, listPage.getRows());
        return result;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public XaResult<Integer> deleteByID(@ApiParam("id数组") Integer[] id) {
        if (null == id || Arrays.asList(id).size() <= 0) {
            return XaResult.error("请选择要删除的记录");
        }
        int rows = pmService.deleteByID(Arrays.asList(id));
        return XaResult.success(rows);
    }

    //@RequestMapping(value="addOrUpdate" method= "")

}
