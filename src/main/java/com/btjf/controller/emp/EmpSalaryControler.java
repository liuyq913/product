package com.btjf.controller.emp;

import com.btjf.application.util.XaResult;
import com.btjf.controller.base.ProductBaseController;
import com.btjf.excel.BaseExcelHandler;
import com.btjf.service.emp.EmpSalaryMonthlyService;
import com.wordnik.swagger.annotations.Api;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by liuyq on 2019/9/17.
 */
@Api(value = "EmpController", description = "工资管理", position = 1)
@RequestMapping(value = "/empSalary")
@RestController("empSalaryControler")
public class EmpSalaryControler extends ProductBaseController {

    private static final Logger LOGGER = Logger
            .getLogger(EmpSalaryControler.class);

    @Resource
    private EmpSalaryMonthlyService empSalaryMothlyService;


    @RequestMapping(value = "/calculation", method = RequestMethod.POST)
    public XaResult<Integer> calculation(String yearMonth, String deptName, String empName) {

        if (yearMonth != null && !BaseExcelHandler.isRightDateStr(yearMonth, "yyyy-MM")) {
            return XaResult.error("年月格式不符，请更正为yyyy-MM");
        }
        Integer row = empSalaryMothlyService.calculation(yearMonth, deptName, empName);
        return XaResult.success(row);
    }

    public XaResult<>
}
