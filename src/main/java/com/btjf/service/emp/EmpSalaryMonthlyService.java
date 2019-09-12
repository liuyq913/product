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
            //多次导入删除之前的数据
            empSalaryMonthlyMapper.deleteByYearMonthAndName(t.getYearMonth(), t.getEmpName());
            empSalaryMonthlyMapper.insertSelective(t);
            return t.getId();
        }).orElse(0);
    }

    public EmpSalaryMonthly getById(Integer id) {
        return Optional.ofNullable(id).map(t -> {
            return empSalaryMonthlyMapper.selectByPrimaryKey(t);
        }).orElse(null);
    }

    public Page<EmpSalaryMonthly> getPage(String yearMonth, String empName, String deptName, Page page) {
        PageHelper.startPage(page.getPage(), page.getRp());
        List<EmpSalaryMonthly> empSalaryMonthlies = this.getList(yearMonth, empName, deptName);
        PageInfo pageInfo = new PageInfo(empSalaryMonthlies);
        pageInfo.setList(empSalaryMonthlies);
        return new Page<>(pageInfo);
    }

    public EmpSalaryMonthly getByYearMonthAndName(String yearMonth, String name){
        return empSalaryMonthlyMapper.getByYearMonthAndName(yearMonth, name);
    }

    public List<EmpSalaryMonthly> getList(String yearMonth, String empName, String deptName) {
        return empSalaryMonthlyMapper.getList(yearMonth, empName, deptName);
    }

    public List<String> getYearMonth(){
        return empSalaryMonthlyMapper.getYearMonth();
    }


    public Integer update(EmpSalaryMonthly empSalaryMonthly){
        if(empSalaryMonthly == null) return 0;
        return empSalaryMonthlyMapper.updateByPrimaryKeySelective(empSalaryMonthly);
    }
}
