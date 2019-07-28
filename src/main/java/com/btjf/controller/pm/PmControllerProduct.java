package com.btjf.controller.pm;

import com.btjf.application.components.page.AppPageHelper;
import com.btjf.application.components.xaresult.AppXaResultHelper;
import com.btjf.application.util.XaResult;
import com.btjf.common.page.Page;
import com.btjf.controller.base.ProductBaseController;
import com.btjf.model.pm.Pm;
import com.btjf.model.sys.SysUser;
import com.btjf.service.pm.PmService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiParam;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by liuyq on 2019/7/28.
 */
@RestController
@RequestMapping(value = "/pm")
@Api(value = "PmControllerProduct", description = "材料管理", position = 1)
public class PmControllerProduct extends ProductBaseController {

    private static final Logger LOGGER = Logger
            .getLogger(PmControllerProduct.class);

    @Resource
    private PmService pmService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public XaResult<List<Pm>> findList(@ApiParam("编号") String pmNo, @ApiParam("名称") String name
            , @ApiParam("类型") String type, Integer pageSize, Integer currentPage) {
        getLoginUser();
        LOGGER.info(getRequestParams());

        Page<Pm> listPage = pmService.findListPage(pmNo, name, type, AppPageHelper.appInit(currentPage, pageSize));
        XaResult<List<Pm>> result = AppXaResultHelper.success(listPage, listPage.getRows());
        return result;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public XaResult<Integer> deleteByID(@ApiParam("id数组") Integer[] id) {
        getLoginUser();
        LOGGER.info(getRequestParams());

        if (null == id || Arrays.asList(id).size() <= 0) {
            return XaResult.error("请选择要删除的记录");
        }
        int rows = pmService.deleteByID(Arrays.asList(id));
        return XaResult.success(rows);
    }

    @RequestMapping(value = "addOrUpdate", method = RequestMethod.GET)
    public XaResult<Integer> addOrUpdate(@ApiParam("id") Integer id, @ApiParam("编号") String pmNo, @ApiParam("名称")
            String name, @ApiParam("类型") String type, @ApiParam("单位") String unit,
                                         @ApiParam("备注") String remark) {
        LOGGER.info(getRequestParams());

        SysUser sysUser = getLoginUser();

        if(null != id){ //更新
            Pm pm = pmService.getByID(id);
            pm.setLastModifyTime(new Date());
            pm.setPmNo(pmNo);
            pm.setName(name);
            pm.setType(type);
            pm.setUnit(unit);
            pm.setRemark(remark);
            pm.setOperator(sysUser.getUserName());
            id = pmService.updateByID(pm);
        }else{
            Pm pm = new Pm();
            pm.setLastModifyTime(new Date());
            pm.setPmNo(pmNo);
            pm.setName(name);
            pm.setType(type);
            pm.setUnit(unit);
            pm.setRemark(remark);
            pm.setOperator(sysUser.getUserName());
            id = pmService.insert(pm);
        }

        return XaResult.success(id);

    }

}
