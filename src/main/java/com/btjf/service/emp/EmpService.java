package com.btjf.service.emp;

import com.btjf.mapper.emp.EmpMapper;
import com.btjf.model.emp.Emp;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by liuyq on 2019/8/8.
 */
@Service
public class EmpService {

    @Resource
    private EmpMapper empMapper;


    public List<Emp> getLeaderByDeptID(Integer id) {
        return empMapper.getLeaderByDeptID(id);
    }

    public Emp getByName(String empName) {
        List<Emp> list = empMapper.getByName(empName);
        return (list == null || list.size() ==0)? null:list.get(0);
    }
}
