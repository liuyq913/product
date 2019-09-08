package com.btjf.service.emp;

import com.btjf.common.page.Page;
import com.btjf.mapper.emp.EmpSalaryMonthlyMapper;
import com.btjf.model.emp.EmpSalaryMonthly;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * Created by liuyq on 2019/9/8.
 */
@Service
public class EmpSalaryMonthlyService {

    @Resource
    private EmpSalaryMonthlyMapper empSalaryMonthlyMapper;

    public Integer save(EmpSalaryMonthly empSalaryMonthly) {
        return Optional.ofNullable(empSalaryMonthly).map(t -> {
            empSalaryMonthlyMapper.insertSelective(t);
            return t.getId();
        }).orElse(0);
    }

    public Integer deleteByYearMonth(String yearMonth) {
        return Optional.ofNullable(yearMonth).map(t -> {
            return empSalaryMonthlyMapper.deleteByYearMonth(t);
        }).orElse(0);
    }

    public Page<EmpSalaryMonthly> getPage(String yearMonth, String empName, String deptName, Page page) {
        PageHelper.startPage(page.getPage(), page.getRp());
        List<EmpSalaryMonthly> empSalaryMonthlies = this.getList(yearMonth, empName, deptName);
        PageInfo pageInfo = new PageInfo(empSalaryMonthlies);
        pageInfo.setList(empSalaryMonthlies);
        return new Page<>(pageInfo);
    }

    public List<EmpSalaryMonthly> getList(String yearMonth, String empName, String deptName) {
        return empSalaryMonthlyMapper.getList(yearMonth, empName, deptName);
    }
}
