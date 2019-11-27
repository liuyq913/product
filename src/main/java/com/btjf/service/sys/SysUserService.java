package com.btjf.service.sys;

import com.btjf.mapper.sys.SysUserMapper;
import com.btjf.model.sys.SysUser;
import com.btjf.model.sys.SysUserExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;


    public SysUser login(String loginName, String loginPwd) {
        SysUserExample sysUserExample = new SysUserExample();
        SysUserExample.Criteria criteria = sysUserExample.createCriteria();
        criteria.andLoginNameEqualTo(loginName);
        criteria.andLoginPwdEqualTo(loginPwd);
        List<SysUser> list = sysUserMapper.selectByExample(sysUserExample);
        return (list == null || list.size() <= 0)? null:list.get(0);
    }

}
