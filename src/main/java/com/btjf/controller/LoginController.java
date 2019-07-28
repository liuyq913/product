package com.btjf.controller;


import com.btjf.application.util.XaResult;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by Administrator on 2018/7/3 0003.
 */
@Api(value = "LoginController", description = "登录", position = 1)
@RequestMapping(value = "/api/evaluate/")
@RestController("loginController")
public class LoginController {

    /**
     * 登录
     *
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public XaResult<String> login(@ApiParam("登录名") String name,
                                  @ApiParam("密码") String pwd) {
        if(StringUtils.isEmpty(name)){
            return XaResult.error("用户名不能为空");
        }
        if(StringUtils.isEmpty(pwd)){
            return XaResult.error("密码不能为空");
        }
        return XaResult.error("用户名或密码错误");
    }




}
