package com.btjf.service.sys;

import com.btjf.mapper.sys.SysRoleMapper;
import com.btjf.mapper.sys.SysUserMapper;
import com.btjf.model.sys.SysRole;
import com.btjf.model.sys.SysUser;
import com.btjf.model.sys.SysUserExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysRoleService {

    @Resource
    private SysRoleMapper sysRoleMapper;


    public SysRole get(Integer id) {
        return sysRoleMapper.selectByPrimaryKey(id);
    }


}
