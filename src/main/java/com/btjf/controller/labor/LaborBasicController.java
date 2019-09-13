package com.btjf.controller.labor;


import com.btjf.application.components.xaresult.AppXaResultHelper;
import com.btjf.application.util.XaResult;
import com.btjf.common.page.Page;
import com.btjf.common.utils.DateUtil;
import com.btjf.common.utils.JSONUtils;
import com.btjf.common.utils.MD5Utils;
import com.btjf.controller.base.ProductBaseController;
import com.btjf.excel.BaseExcelHandler;
import com.btjf.interceptor.LoginInfoCache;
import com.btjf.model.emp.Emp;
import com.btjf.model.emp.EmpTimesalaryMonthly;
import com.btjf.model.emp.EmpWork;
import com.btjf.model.salary.SalaryMonthly;
import com.btjf.model.sys.SysRole;
import com.btjf.model.sys.SysUser;
import com.btjf.model.sys.Sysdept;
import com.btjf.service.emp.EmpService;
import com.btjf.service.emp.EmpTimeSalaryService;
import com.btjf.service.emp.EmpWorkService;
import com.btjf.service.order.ProductionProcedureConfirmService;
import com.btjf.service.salary.SalaryMonthlyService;
import com.btjf.service.sys.SysDeptService;
import com.btjf.service.sys.SysRoleService;
import com.btjf.service.sys.SysUserService;
import com.btjf.vo.EmpSubsidyVo;
import com.btjf.vo.ProcedureYieldVo;
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
public class LaborBasicController extends ProductBaseController{

    @Resource
    private SalaryMonthlyService salaryMonthlyService;
    @Resource
    private EmpService empService;
    @Resource
    private ProductionProcedureConfirmService productionProcedureConfirmService;
    @Resource
    private EmpTimeSalaryService empTimeSalaryService;
    @Resource
    private SysDeptService sysDeptService;
    @Resource
    private EmpWorkService empWorkService;

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

    /**
     * 话费补贴 列表
     *
     * @return
     */
    @RequestMapping(value = "/phoneSubsidy/list", method = RequestMethod.GET)
    public XaResult<List<EmpSubsidyVo>> phoneSubsidyList(String name, Integer deptId,
                                                         Integer pageSize, Integer currentPage) {
        if(currentPage == null || currentPage < 1){
            currentPage =1;
        }
        if(pageSize == null || pageSize < 1){
            pageSize = 25;
        }
        Page page = new Page(pageSize, currentPage);
        Page<EmpSubsidyVo> listPage = empService.phoneSubsidyList(name, deptId, page);
        XaResult<List<EmpSubsidyVo>> result = AppXaResultHelper.success(listPage, listPage.getRows());
        return result;
    }

    /**
     * 社保补贴 列表
     *
     * @return
     */
    @RequestMapping(value = "/socialSubsidy/list", method = RequestMethod.GET)
    public XaResult<List<EmpSubsidyVo>> socialSubsidyList(String name, Integer deptId,
                                                         Integer pageSize, Integer currentPage) {
        if(currentPage == null || currentPage < 1){
            currentPage =1;
        }
        if(pageSize == null || pageSize < 1){
            pageSize = 25;
        }
        Page page = new Page(pageSize, currentPage);
        Page<EmpSubsidyVo> listPage = empService.socialSubsidyList(name, deptId, page);
        XaResult<List<EmpSubsidyVo>> result = AppXaResultHelper.success(listPage, listPage.getRows());
        return result;
    }


    /**
     * 工序 产量 列表
     *
     * @return
     */
    @RequestMapping(value = "/yield/list", method = RequestMethod.GET)
    public XaResult<List<ProcedureYieldVo>> yieldList(String name, Integer deptId,Integer workId,
        String orderNo, String productNo, String procedureName, String yearMonth,
        String startDate, String endDate, Integer pageSize, Integer currentPage) {
        if(currentPage == null || currentPage < 1){
            currentPage =1;
        }
        if(pageSize == null || pageSize < 1){
            pageSize = 25;
        }
        Page page = new Page(pageSize, currentPage);
        Page<ProcedureYieldVo> listPage = productionProcedureConfirmService.yieldList(name, deptId, workId,
                orderNo, productNo, procedureName, yearMonth, startDate, endDate, page);
        XaResult<List<ProcedureYieldVo>> result = AppXaResultHelper.success(listPage, listPage.getRows());
        return result;
    }

    /**
     * 新增\修改 计时工资
     *
     * @return
     */
    @RequestMapping(value = "/hourlywage/addOrUpdate", method = RequestMethod.POST)
    public XaResult<String> hourlywageAdd(Integer id, String yearMonth, Integer empId,
                                          String billNo, String content, Double price,
                                          Double num, String remark) {
        if(StringUtils.isEmpty(yearMonth)){
            return XaResult.error("工资年月不能为空");
        }
        if(!BaseExcelHandler.isRightDateStr(yearMonth,"yyyy-MM")){
            return XaResult.error("年月格式不符，请更正为yyyy-MM");
        }
        if(empId == null){
            return XaResult.error("员工ID不能为空");
        }
        if(StringUtils.isEmpty(billNo)){
            return XaResult.error("单据编号不能为空");
        }
        if(StringUtils.isEmpty(content)){
            return XaResult.error("工作内容不能为空");
        }
        if(price == null || price <= 0){
            return XaResult.error("单价必须大于0");
        }
        if(num == null || num <= 0){
            return XaResult.error("数量必须大于0");
        }
        if(id != null){
            EmpTimesalaryMonthly empTimesalaryMonthly = empTimeSalaryService.get(id);
            Emp emp = empService.getByID(empId);
            if (emp == null){
                return XaResult.error("该员工不存在");
            }
            Sysdept sysdept = sysDeptService.get(emp.getDeptId());
            EmpWork empWork = empWorkService.getByID(emp.getWorkId());
            empTimesalaryMonthly.setYearMonth(yearMonth);
            empTimesalaryMonthly.setEmpId(empId);
            empTimesalaryMonthly.setEmpName(emp.getName());
            empTimesalaryMonthly.setBillNo(billNo);
            empTimesalaryMonthly.setContent(content);
            empTimesalaryMonthly.setPrice(BigDecimal.valueOf(price));
            empTimesalaryMonthly.setNum(BigDecimal.valueOf(num));
            empTimesalaryMonthly.setMoney(empTimesalaryMonthly.getPrice().multiply(empTimesalaryMonthly.getNum()));
            empTimesalaryMonthly.setDeptId(emp.getDeptId());
            empTimesalaryMonthly.setDeptName(sysdept == null ? "" : sysdept.getDeptName());
            empTimesalaryMonthly.setDrawer(getLoginUser().getUserName());
            empTimesalaryMonthly.setDrawTime(new Date());
            empTimesalaryMonthly.setLastModifyTime(new Date());
            empTimesalaryMonthly.setRemark(remark);
            empTimesalaryMonthly.setWorkName(empWork == null ? "" : empWork.getName());
            empTimeSalaryService.update(empTimesalaryMonthly);
        }else {
            EmpTimesalaryMonthly empTimesalaryMonthly = empTimeSalaryService.findByBillNo(billNo);
            if (empTimesalaryMonthly != null) {
                return XaResult.error("单据编号重复，请修改");
            }
            Emp emp = empService.getByID(empId);
            if (emp == null) {
                return XaResult.error("该员工不存在");
            }
            Sysdept sysdept = sysDeptService.get(emp.getDeptId());
            EmpWork empWork = empWorkService.getByID(emp.getWorkId());

            empTimesalaryMonthly = new EmpTimesalaryMonthly();
            empTimesalaryMonthly.setYearMonth(yearMonth);
            empTimesalaryMonthly.setEmpId(empId);
            empTimesalaryMonthly.setEmpName(emp.getName());
            empTimesalaryMonthly.setBillNo(billNo);
            empTimesalaryMonthly.setContent(content);
            empTimesalaryMonthly.setPrice(BigDecimal.valueOf(price));
            empTimesalaryMonthly.setNum(BigDecimal.valueOf(num));
            empTimesalaryMonthly.setMoney(empTimesalaryMonthly.getPrice().multiply(empTimesalaryMonthly.getNum()));
            empTimesalaryMonthly.setDeptId(emp.getDeptId());
            empTimesalaryMonthly.setDeptName(sysdept == null ? "" : sysdept.getDeptName());
            empTimesalaryMonthly.setDrawer(getLoginUser().getUserName());
            empTimesalaryMonthly.setDrawTime(new Date());
            empTimesalaryMonthly.setCreateTime(new Date());
            empTimesalaryMonthly.setLastModifyTime(new Date());
            empTimesalaryMonthly.setRemark(remark);
            empTimesalaryMonthly.setIsDelete(0);
            empTimesalaryMonthly.setIsConfirm(0);
            empTimesalaryMonthly.setWorkName(empWork == null ? "" : empWork.getName());
            empTimeSalaryService.create(empTimesalaryMonthly);
        }
        return XaResult.success();
    }

    /**
     * 确认 计时工资
     *
     * @return
     */
    @RequestMapping(value = "/hourlywage/confirm", method = RequestMethod.POST)
    public XaResult<String> hourlywageAdd(Integer[] ids) {
        if(ids == null || ids.length < 1){
            return XaResult.error("ID不能为空");
        }
        empTimeSalaryService.confirm(ids);
        return XaResult.success();
    }


    /**
     * 计时工资 列表
     * @return
     */
    @RequestMapping(value = "/hourlywage/list", method = RequestMethod.GET)
    public XaResult<List<EmpTimesalaryMonthly>> hourlywageList(String yearMonth,String empName, String deptName,String billNo,
                                                      Integer isConfirm, Integer pageSize, Integer currentPage) {
        if(yearMonth!= null && !BaseExcelHandler.isRightDateStr(yearMonth,"yyyy-MM")){
            return XaResult.error("年月格式不符，请更正为yyyy-MM");
        }
        if(currentPage == null || currentPage < 1){
            currentPage =1;
        }
        if(pageSize == null || pageSize < 1){
            pageSize = 25;
        }
        Page page = new Page(pageSize, currentPage);
        Page<EmpTimesalaryMonthly> listPage = empTimeSalaryService.findList(yearMonth,empName,deptName,billNo,isConfirm,page);
        XaResult<List<EmpTimesalaryMonthly>> result = AppXaResultHelper.success(listPage, listPage.getRows());
        return result;
    }

}
