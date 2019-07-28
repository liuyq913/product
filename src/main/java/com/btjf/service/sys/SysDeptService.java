package com.btjf.service.sys;

import com.btjf.mapper.sys.SysUserMapper;
import com.btjf.mapper.sys.SysdeptMapper;
import com.btjf.model.sys.SysUser;
import com.btjf.model.sys.SysUserExample;
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

}
