package com.btjf.service.salary;

import com.btjf.common.page.Page;
import com.btjf.mapper.salary.SalaryMonthlyMapper;
import com.btjf.model.salary.SalaryMonthly;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SalaryMonthlyService {

    @Resource
    private SalaryMonthlyMapper salaryMonthlyMapper;

    public SalaryMonthly get(Integer id) {
        return salaryMonthlyMapper.selectByPrimaryKey(id);
    }


    public void create(SalaryMonthly salaryMonthly) {
        salaryMonthlyMapper.insertSelective(salaryMonthly);
    }

    public void update(SalaryMonthly salaryMonthly) {
        salaryMonthlyMapper.updateByPrimaryKeySelective(salaryMonthly);
    }

    public Page<SalaryMonthly> list(Page page) {
        PageHelper.startPage(page.getPage(), page.getRp());
        List<SalaryMonthly> pmList = salaryMonthlyMapper.findList();
        PageInfo pageInfo = new PageInfo(pmList);
        pageInfo.setList(pmList);
        return new Page<>(pageInfo);
    }
}
