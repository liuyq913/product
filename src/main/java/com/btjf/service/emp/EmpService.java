package com.btjf.service.emp;

import com.btjf.controller.weixin.vo.WxEmpVo;
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

    public WxEmpVo login(String phone, String password) {
        WxEmpVo wxEmpVo = empMapper.selectByPhone(phone, password);
        return wxEmpVo;
    }

    public Emp getByPhoneOrIdCard(String phone, String idCard) {
        Emp emp = empMapper.getByPhoneOrIdCard(phone, idCard);
        return emp;
    }

    public Emp getByID(Integer id) {
        Emp emp = empMapper.selectByPrimaryKey(id);
        return emp;
    }

    public Integer update(Emp emp) {
        if (emp == null) return 0;
        return empMapper.updateByPrimaryKey(emp);
    }
}
