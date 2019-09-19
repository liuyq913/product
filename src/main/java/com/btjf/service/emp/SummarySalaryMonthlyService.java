package com.btjf.service.emp;

import com.btjf.common.page.Page;
import com.btjf.mapper.emp.SummarySalaryMonthlyMapper;
import com.btjf.model.emp.SummarySalaryMonthly;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by liuyq on 2019/9/18.
 */
@Service
public class SummarySalaryMonthlyService {

    @Resource
    private SummarySalaryMonthlyMapper summarySalaryMonthlyMapper;

    public Integer saveOrUpdate(SummarySalaryMonthly summarySalaryMonthly) {
        return Optional.ofNullable(summarySalaryMonthly).map(v -> {
            SummarySalaryMonthly summarySalaryMonthly1 = summarySalaryMonthlyMapper.getByEmpIDAndYearMonth(summarySalaryMonthly.getEmpId(), summarySalaryMonthly.getYearMonth());
            if (null == summarySalaryMonthly1) {
                summarySalaryMonthly.setIsDelete(0);
                summarySalaryMonthly.setCreateTime(new Date());
                summarySalaryMonthlyMapper.insertSelective(summarySalaryMonthly);
                return summarySalaryMonthly.getId();
            } else {
                summarySalaryMonthly.setId(summarySalaryMonthly1.getId());
                summarySalaryMonthlyMapper.updateByPrimaryKeySelective(summarySalaryMonthly);
                return summarySalaryMonthly1.getId();
            }
        }).orElse(0);
    }

    public List<SummarySalaryMonthly> getList(String yearMonth, String deptName, String empName,
                                              Integer type) {
        List<SummarySalaryMonthly> summarySalaryMonthlies = summarySalaryMonthlyMapper.getList(yearMonth, deptName, empName, type);
        return summarySalaryMonthlies;
    }


    public Page<SummarySalaryMonthly> getPage(String yearMonth, String deptName, String empName,
                                              Integer type, Page page) {
        PageHelper.startPage(page.getPage(), page.getRp());
        List<SummarySalaryMonthly> summarySalaryMonthlies = this.getList(yearMonth, deptName, empName, type);
        PageInfo pageInfo = new PageInfo(summarySalaryMonthlies);
        pageInfo.setList(summarySalaryMonthlies);
        return new Page<>(pageInfo);
    }
}
