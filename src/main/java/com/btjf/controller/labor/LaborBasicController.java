package com.btjf.controller.labor;


import com.btjf.application.components.xaresult.AppXaResultHelper;
import com.btjf.application.util.XaResult;
import com.btjf.common.page.Page;
import com.btjf.common.utils.DateUtil;
import com.btjf.common.utils.JSONUtils;
import com.btjf.common.utils.MD5Utils;
import com.btjf.excel.BaseExcelHandler;
import com.btjf.interceptor.LoginInfoCache;
import com.btjf.model.salary.SalaryMonthly;
import com.btjf.model.sys.SysRole;
import com.btjf.model.sys.SysUser;
import com.btjf.model.sys.Sysdept;
import com.btjf.service.salary.SalaryMonthlyService;
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
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * Created by Administrator on 2018/7/3 0003.
 */
@Api(value = "LaborBasicController", description = "劳资管理基础接口", position = 1)
@RequestMapping(value = "/labor/")
@RestController("laborBasicController")
public class LaborBasicController {

    @Resource
    private SalaryMonthlyService salaryMonthlyService;
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
        if(!BaseExcelHandler.isRightDateStr(yearMonth,"yyyy-MM")){
            return XaResult.error("年月格式不符，请更正为yyyy-MM");
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
        if(id == null){
            SalaryMonthly salaryMonthly = new SalaryMonthly();
            salaryMonthly.setBasicSalary(BigDecimal.valueOf(basicSalary));
            salaryMonthly.setExpectWorkDay(expectWorkDay);
            salaryMonthly.setRealityWorkDay(realityWorkDay);
            salaryMonthly.setHourlyWage(BigDecimal.valueOf(hourlyWage));
            salaryMonthly.setYearMonth(yearMonth);
            salaryMonthly.setCreateTime(new Date());
            salaryMonthly.setLastModifyTime(new Date());
            salaryMonthly.setIsDelete(0);
            salaryMonthlyService.create(salaryMonthly);
        }else{
            SalaryMonthly salaryMonthly = salaryMonthlyService.get(id);
            salaryMonthly.setBasicSalary(BigDecimal.valueOf(basicSalary));
            salaryMonthly.setExpectWorkDay(expectWorkDay);
            salaryMonthly.setRealityWorkDay(realityWorkDay);
            salaryMonthly.setHourlyWage(BigDecimal.valueOf(hourlyWage));
            salaryMonthly.setYearMonth(yearMonth);
            salaryMonthly.setLastModifyTime(new Date());
            salaryMonthlyService.update(salaryMonthly);
        }


        return XaResult.success();
    }


    /**
     * 工资月度 列表
     *
     * @return
     */
    @RequestMapping(value = "/salary/list", method = RequestMethod.GET)
    public XaResult<List<SalaryMonthly>> salaryList(Integer pageSize, Integer currentPage) {
        if(currentPage == null || currentPage < 1){
            currentPage =1;
        }
        if(pageSize == null || pageSize < 1){
            pageSize = 25;
        }
        Page page = new Page(pageSize, currentPage);
        Page<SalaryMonthly> listPage = salaryMonthlyService.list(page);
        XaResult<List<SalaryMonthly>> result = AppXaResultHelper.success(listPage, listPage.getRows());
        return result;
    }


    /**
     * 工资月度 结算
     *
     * @return
     */
    @RequestMapping(value = "/salary/settlement", method = RequestMethod.POST)
    public XaResult<String> settlement(Integer id) {
        if(id == null || id <= 0){
            return XaResult.error("基本工资必须大于0");
        }
        SalaryMonthly salaryMonthly = salaryMonthlyService.get(id);
        if(salaryMonthly == null){
            return XaResult.error("记录不存在");
        }
        if(!DateUtil.isAfter(DateUtil.string2Date(salaryMonthly.getYearMonth(),DateUtil.ymFormat),
                DateUtil.dateBefore(new Date(), 5,3))){
            return XaResult.error("三个月前的信息不允许再度结算");
        }

        //TODO 判断salaryMonthly.getYearMonth()  这个月份 是否结算  如果已经结算  也不允许再改

        return XaResult.success();
    }
}
