package com.btjf.controller.labor;


import com.btjf.application.util.XaResult;
import com.btjf.common.utils.JSONUtils;
import com.btjf.common.utils.MD5Utils;
import com.btjf.interceptor.LoginInfoCache;
import com.btjf.model.salary.SalaryMonthly;
import com.btjf.model.sys.SysRole;
import com.btjf.model.sys.SysUser;
import com.btjf.model.sys.Sysdept;
import com.btjf.service.sys.SysDeptService;
import com.btjf.service.sys.SysRoleService;
import com.btjf.service.sys.SysUserService;
import com.btjf.vo.UserInfoVo;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * Created by Administrator on 2018/7/3 0003.
 */
@Api(value = "LaborBasicController", description = "劳资管理基础接口", position = 1)
@RequestMapping(value = "/labor/")
@RestController("laborBasicController")
public class LaborBasicController {

    @Resource
    private SysUserService sysUserService;
    @Resource
    private SysDeptService sysDeptService;
    @Resource
    private SysRoleService sysRoleService;
    @Resource
    private LoginInfoCache loginInfoCache;

    /**
     * 工资月度 新增 修改
     *
     * @return
     */
    @RequestMapping(value = "/salary/addOrUpdate", method = RequestMethod.POST)
    public XaResult<String> addOrUpdate(Integer id, String yearMonth, Integer expectWorkDay,
                                      Integer realityWorkDay, Double basicSalary, Double hourlyWage) {
        if(StringUtils.isEmpty(yearMonth)){
            return XaResult.error("年月不能为空");
        }
        if(expectWorkDay == null || expectWorkDay <= 0){
            return XaResult.error("工作日必须大于0");
        }
        if(realityWorkDay == null || realityWorkDay <= 0){
            return XaResult.error("正常工作天数必须大于0");
        }
        if(basicSalary == null || basicSalary <= 0){
            return XaResult.error("基本工资必须大于0");
        }
        if(hourlyWage == null || hourlyWage <= 0){
            return XaResult.error("时薪必须大于0");
        }



        return XaResult.success();
    }


    /**
     * 工资月度 列表
     *
     * @return
     */
    @RequestMapping(value = "/salary/list", method = RequestMethod.GET)
    public XaResult<SalaryMonthly> salaryList() {

        return XaResult.success();
    }


    /**
     * 工资月度 结算
     *
     * @return
     */
    @RequestMapping(value = "/salary/settlement", method = RequestMethod.POST)
    public XaResult<String> settlement() {

        return XaResult.success();
    }
}
