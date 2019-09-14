package com.btjf.service.emp;

import com.btjf.mapper.emp.EmpSubsibyMonthlyMapper;
import com.btjf.model.emp.EmpSubsibyMonthly;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * Created by liuyq on 2019/9/11.
 */
@Service
public class EmpSubsibyMonthlyService {

    @Resource
    private EmpSubsibyMonthlyMapper empSubsibyMonthlyMapper;

    public Integer save(EmpSubsibyMonthly empSubsibyMonthly) {
        return Optional.ofNullable(empSubsibyMonthly).map(t -> {
            empSubsibyMonthlyMapper.insertSelective(t);
            return t.getId();
        }).orElse(0);
    }

    public EmpSubsibyMonthly getByNo(String billNo) {
        return Optional.ofNullable(billNo).map(t -> {
            EmpSubsibyMonthly empSubsibyMonthly = empSubsibyMonthlyMapper.getByNo(t);
            return empSubsibyMonthly;
        }).orElse(null);
    }
}
