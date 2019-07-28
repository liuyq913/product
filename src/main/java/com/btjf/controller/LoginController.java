package com.btjf.controller;


import com.btjf.application.util.XaResult;
import com.btjf.model.sys.SysRole;
import com.btjf.model.sys.SysUser;
import com.btjf.model.sys.Sysdept;
import com.btjf.service.sys.SysDeptService;
import com.btjf.service.sys.SysRoleService;
import com.btjf.service.sys.SysUserService;
import com.btjf.vo.UserInfoVo;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * Created by Administrator on 2018/7/3 0003.
 */
@Api(value = "LoginController", description = "登录", position = 1)
@RequestMapping(value = "/user/")
@RestController("loginController")
public class LoginController {

    @Resource
    private SysUserService sysUserService;
    @Resource
    private SysDeptService sysDeptService;
    @Resource
    private SysRoleService sysRoleService;


    /**
     * 登录
     *
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public XaResult<UserInfoVo> login(@ApiParam("登录名") String loginName,
                                      @ApiParam("密码") String loginPwd) {
        if(StringUtils.isEmpty(loginName)){
            return XaResult.error("用户名不能为空");
        }
        if(StringUtils.isEmpty(loginPwd)){
            return XaResult.error("密码不能为空");
        }
        SysUser sysUser = sysUserService.login(loginName,loginPwd);
        if(sysUser == null){
            XaResult.error("用户名或密码错误");
        }
        UserInfoVo userInfoVo = new UserInfoVo(sysUser);
        if(sysUser.getDeptId() !=null){
            Sysdept sysdept = sysDeptService.get(sysUser.getDeptId());
            userInfoVo.setDeptName(sysdept!= null?sysdept.getDeptName():null);
        }
        if(sysUser.getRoleId()!= null){
            SysRole sysRole = sysRoleService.get(sysUser.getRoleId());
            userInfoVo.setRoleName(sysRole!= null?sysRole.getName():null);
        }
        //TODO 缺一个 用户信息加密

        return XaResult.success(userInfoVo);
    }




}
