package com.btjf.controller.emp;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.btjf.application.components.xaresult.AppXaResultHelper;
import com.btjf.application.util.XaResult;
import com.btjf.common.page.Page;
import com.btjf.common.utils.BeanUtil;
import com.btjf.common.utils.DateUtil;
import com.btjf.controller.base.ProductBaseController;
import com.btjf.controller.emp.vo.EmpSalaryVo;
import com.btjf.excel.BaseExcelHandler;
import com.btjf.model.emp.EmpSalaryMonthly;
import com.btjf.model.emp.Score;
import com.btjf.model.emp.SummarySalaryMonthly;
import com.btjf.model.salary.SalaryMonthly;
import com.btjf.service.emp.EmpSalaryMonthlyService;
import com.btjf.service.emp.EmpService;
import com.btjf.service.emp.ScoreService;
import com.btjf.service.emp.SummarySalaryMonthlyService;
import com.btjf.service.salary.SalaryMonthlyService;
import com.btjf.util.BigDecimalUtil;
import com.wordnik.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by liuyq on 2019/9/17.
 */
@Api(value = "EmpController", description = "工资管理", position = 1)
@RequestMapping(value = "/empSalary")
@RestController("empSalaryControler")
public class EmpSalaryController extends ProductBaseController {

    private static final Logger LOGGER = Logger
            .getLogger(EmpSalaryController.class);

    @Resource
    private EmpSalaryMonthlyService empSalaryMothlyService;

    @Resource
    private SummarySalaryMonthlyService summarySalaryMonthlyService;

    @Resource
    private EmpService empService;

    @Resource
    private SalaryMonthlyService salaryMonthlyService;
    @Resource
    private ScoreService scoreService;

    @RequestMapping(value = "/calculation", method = RequestMethod.POST)
    public XaResult<Integer> calculation(String yearMonth, String deptName, String empName) {
        if(StringUtils.isEmpty(yearMonth)){
            return XaResult.error("年月不能为空，格式为yyyy-MM");
        }
        if (yearMonth != null && !BaseExcelHandler.isRightDateStr(yearMonth, "yyyy-MM")) {
            return XaResult.error("年月格式不符，请更正为yyyy-MM");
        }
        SalaryMonthly salaryMonthly = salaryMonthlyService.getByYearMonth(yearMonth);
        if(salaryMonthly == null){
            return XaResult.error("记录不存在");
        }
        if(salaryMonthly.getIsMore() == null){
            return XaResult.error("该月份产值未设置");
        }
        if(!DateUtil.isAfter(DateUtil.string2Date(salaryMonthly.getYearMonth(),DateUtil.ymFormat),
                DateUtil.dateBefore(new Date(), 5,3))){
            return XaResult.error("三个月前的信息不允许再度结算");
        }
        List<Score> scoreList = scoreService.getList(salaryMonthly.getYearMonth(), null, null);
        if(scoreList == null || scoreList.size() <1){
            return XaResult.error("该月份考勤分或3个分未导入");
        }
        List<EmpSalaryMonthly> empSalaryMonthlyList = empSalaryMothlyService.getList(salaryMonthly.getYearMonth(), null, null);
        if(empSalaryMonthlyList == null || empSalaryMonthlyList.size() <1){
            return XaResult.error("该月份考勤信息未导入");
        }

        Integer row = empSalaryMothlyService.calculation(yearMonth, deptName, empName);
        return XaResult.success(row);
    }


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public XaResult<List<EmpSalaryVo>> list(String yearMonth, String deptName, String empName,
                                            Integer type, Integer currentPage, Integer pageSize) {

        if (yearMonth != null && !BaseExcelHandler.isRightDateStr(yearMonth, "yyyy-MM")) {
            return XaResult.error("年月格式不符，请更正为yyyy-MM");
        }

        if (type != null && (type != 1 && type != 2)) {
            return XaResult.error("type有误");
        }

        if (currentPage == null || currentPage < 1) {
            currentPage = 1;
        }
        if (pageSize == null || pageSize < 1) {
            pageSize = 25;
        }
        Page page = new Page(pageSize, currentPage);

        Page<SummarySalaryMonthly> summarySalaryMonthlyPage = summarySalaryMonthlyService.getPage(yearMonth, deptName, empName, type, page);

        Page<EmpSalaryVo> empSalaryVoPage = BeanUtil.convertPage(summarySalaryMonthlyPage, EmpSalaryVo.class);
        List<EmpSalaryVo> empSalaryVos = empSalaryVoPage.getRows();
        if (!CollectionUtils.isEmpty(empSalaryVos)) {
            for (EmpSalaryVo empSalaryVo : empSalaryVos) {
                if (empSalaryVo == null) continue;
                if (type == null) {
                    //基本工资工时
                    empSalaryVo.setBaseWorkHour(BigDecimal.valueOf(BigDecimalUtil.mul(empSalaryVo.getDayWork().doubleValue(), 8.0)));
                    SalaryMonthly salaryMonthly = salaryMonthlyService.getByYearMonth(empSalaryVo.getYearMonth());
                    if (salaryMonthly == null) continue;
                    //基本工资/(正常上班天数*8)*(白天上班天数*8)
                    empSalaryVo.setBaseWorkHourSalary(BigDecimal.valueOf(BigDecimalUtil.mul(empSalaryVo.getWorkDay().doubleValue() * 8, BigDecimalUtil.div(empSalaryVo.getDhbt().doubleValue(), BigDecimalUtil.mul(salaryMonthly.getExpectWorkDay(), 8)))));
                    empSalaryVo.setHourSalary(salaryMonthly.getHourlyWage());
                    //正常加班工时金额
                    empSalaryVo.setNormalOvertimeSalary(BigDecimal.valueOf(BigDecimalUtil.mul(empSalaryVo.getNormalOvertime() == null ? 0 : empSalaryVo.getNormalOvertime().doubleValue(), 17.325)));

                    //假日白班天数*8+假日晚班天数*3 * 23.1= 金额
                    empSalaryVo.setHoliayOvertimeSalary(BigDecimal.valueOf(BigDecimalUtil.mul(empSalaryVo.getHoliayOvertime() == null ? 0 : empSalaryVo.getHoliayOvertime().doubleValue(), 23.1)));
                    //法定假日（白班天数*8+晚班*3） * 3 * 11.55
                    empSalaryVo.setLegalOvertimeSalary(BigDecimal.valueOf(BigDecimalUtil.mul(empSalaryVo.getLegalOvertime().doubleValue(), 3 * 11.55)));
                    //汇总表覆盖计时工资
                    empSalaryVo.setTimeSalary(BigDecimal.valueOf(BigDecimalUtil.add(empSalaryVo.getBaseWorkHourSalary() == null ? 0 : empSalaryVo.getBaseWorkHourSalary().doubleValue(),
                            empSalaryVo.getNormalOvertimeSalary() == null ? 0 : empSalaryVo.getNormalOvertimeSalary().doubleValue(),
                            empSalaryVo.getHoliayOvertimeSalary() == null ? 0 : empSalaryVo.getHoliayOvertimeSalary().doubleValue(),
                            empSalaryVo.getLegalOvertimeSalary() == null ? 0 : empSalaryVo.getLegalOvertimeSalary().doubleValue())));

                    //绩效 应发工资-补贴合计-计时工资
                    empSalaryVo.setAchievements(BigDecimal.valueOf(BigDecimalUtil.sub(empSalaryVo.getRealSalary() == null ? 0 : empSalaryVo.getRealSalary().doubleValue(),
                            empSalaryVo.getSumSusbsidy() == null ? 0 : empSalaryVo.getSumSusbsidy().doubleValue(),
                            empSalaryVo.getTimeSalary().doubleValue())));
                }
            }
        }
        XaResult<List<EmpSalaryVo>> result = AppXaResultHelper.success(empSalaryVoPage, empSalaryVos);
        return result;
    }
}
