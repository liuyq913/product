package com.btjf.service.sys;

import com.btjf.mapper.sys.SysdeptMapper;
import com.btjf.model.sys.Sysdept;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysDeptService {

    @Resource
    private SysdeptMapper sysdeptMapper;


    public Sysdept get(Integer id) {
        return sysdeptMapper.selectByPrimaryKey(id);
    }


    public List<Sysdept> getList() {

        return sysdeptMapper.getList();
    }

    public List<Sysdept> getWorkshopList() {
        return sysdeptMapper.getWorkshopList();
    }
}
