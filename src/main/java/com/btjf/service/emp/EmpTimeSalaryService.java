package com.btjf.service.emp;

import com.btjf.common.page.Page;
import com.btjf.mapper.emp.EmpTimesalaryMonthlyMapper;
import com.btjf.model.emp.EmpTimesalaryMonthly;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class EmpTimeSalaryService {

    @Resource
    private EmpTimesalaryMonthlyMapper empTimesalaryMonthlyMapper;


    public EmpTimesalaryMonthly findByBillNo(String billNo) {
        List<EmpTimesalaryMonthly> list = empTimesalaryMonthlyMapper.findByBillNo(billNo);
        if(list == null || list.size() <1){
            return null;
        }else{
            return list.get(0);
        }
    }

    public void create(EmpTimesalaryMonthly empTimesalaryMonthly) {
        empTimesalaryMonthlyMapper.insert(empTimesalaryMonthly);
    }

    public void confirm(Integer[] ids) {
        List<Integer> list = new ArrayList<>(Arrays.asList(ids));
        empTimesalaryMonthlyMapper.confirm(list);
    }

    public Page<EmpTimesalaryMonthly> findList(String yearMonth, String empName, String deptName, String billNo,
                                               Integer isConfirm, Page page) {
        PageHelper.startPage(page.getPage(), page.getRp());
        List<EmpTimesalaryMonthly> pmList = empTimesalaryMonthlyMapper.findList(yearMonth,empName,deptName,billNo,isConfirm);
        PageInfo pageInfo = new PageInfo(pmList);
        pageInfo.setList(pmList);
        return new Page<>(pageInfo);
    }
}
