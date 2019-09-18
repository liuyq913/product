package com.btjf.service.emp;

import com.btjf.mapper.emp.SummarySalaryMonthlyMapper;
import com.btjf.model.emp.SummarySalaryMonthly;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
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
}
