package com.btjf.service.emp;

import com.btjf.mapper.emp.EmpWorkMapper;
import com.btjf.model.emp.EmpWork;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by liuyq on 2019/8/8.
 */
@Service
public class EmpWorkService {

    @Resource
    private EmpWorkMapper empWorkMapper;


    public List<EmpWork> getList() {
        return empWorkMapper.getList();
    }

    public EmpWork getByName(String name) {
        return empWorkMapper.getByName(name);
    }
}
