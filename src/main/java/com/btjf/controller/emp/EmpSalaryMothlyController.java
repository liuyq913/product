package com.btjf.controller.emp;

import com.btjf.application.components.xaresult.AppXaResultHelper;
import com.btjf.application.util.XaResult;
import com.btjf.common.page.Page;
import com.btjf.controller.base.ProductBaseController;
import com.btjf.model.emp.EmpSalaryMonthly;
import com.btjf.service.emp.EmpSalaryMonthlyService;
import com.wordnik.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by liuyq on 2019/9/8.
 */
@Api(value = "EmpController", description = "固定工资管理", position = 1)
@RequestMapping(value = "/empSalaryMothly/")
@RestController("empSalaryMothlyController")
public class EmpSalaryMothlyController extends ProductBaseController {

    @Resource
    private EmpSalaryMonthlyService empSalaryMothlyService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public XaResult<List<EmpSalaryMonthly>> getList(Integer pageSize, Integer currentPage, String yearMonth, String empName, String deptName) {
        if (currentPage == null || currentPage < 1) {
            currentPage = 1;
        }
        if (pageSize == null || pageSize < 1) {
            pageSize = 25;
        }
        Page page = new Page(pageSize, currentPage);
        Page<EmpSalaryMonthly> empPage = empSalaryMothlyService.getPage(yearMonth, empName, deptName, page);
        XaResult<List<EmpSalaryMonthly>> result = AppXaResultHelper.success(empPage, empPage.getRows());
        return result;
    }
}
