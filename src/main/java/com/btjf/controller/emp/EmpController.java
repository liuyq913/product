package com.btjf.controller.emp;


import com.btjf.application.util.XaResult;
import com.btjf.common.utils.JSONUtils;
import com.btjf.common.utils.MD5Utils;
import com.btjf.interceptor.LoginInfoCache;
import com.btjf.model.emp.Emp;
import com.btjf.model.sys.SysRole;
import com.btjf.model.sys.SysUser;
import com.btjf.model.sys.Sysdept;
import com.btjf.service.emp.EmpService;
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
import java.math.BigDecimal;
import java.util.Date;


/**
 * Created by Administrator on 2018/7/3 0003.
 */
@Api(value = "EmpController", description = "用户管理", position = 1)
@RequestMapping(value = "/emp/")
@RestController("empController")
public class EmpController {

    @Resource
    private EmpService empService;


    /**
     * 详情
     *
     * @return
     */
    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    public XaResult<Emp> detail(Integer id){
        Emp emp = empService.getByID(id);
        return XaResult.success(emp);
    }


    /**
     * 添加、修改
     *
     * @return
     */
    @RequestMapping(value = "/addOrUpdate", method = RequestMethod.POST)
    public XaResult<String> addOrUpdate(Integer id,String name,String phone,
                                  Integer deptId,Integer sex,Integer workId,Integer type,
                                  String nativeSource,String nation,String birthday,
                                  String idCard,String entryDate,String school,
                                  String subject,String education,String linkMan,
                                  String tel,String address,Double salary,
                                  Double ylbx,Double sybx,Double yiliaobx,
                                  Double gjj,Double phoneSubsidy,Double waterSubsidy,
                                  Double foodSubsidy,Double socialSubsidy,
                                  String remark
                                  ) {
        if(StringUtils.isEmpty(name)){
            return XaResult.error("姓名不能为空");
        }
        if(deptId == null){
            return XaResult.error("部门不能为空");
        }
        if(workId == null){
            return XaResult.error("工种不能为空");
        }
        if(type == null || type < 1 || type > 2){
            return XaResult.error("人员类别错误");
        }
        if(StringUtils.isEmpty(birthday)){
            return XaResult.error("出生年月不能为空");
        }
        if(StringUtils.isEmpty(entryDate)){
            return XaResult.error("进场日期不能为空");
        }
        Emp emp1 = empService.getByName(name);
        if (emp1 !=null){
            return XaResult.error("系统中该姓名："+ name + " 已存在");
        }
        if(salary == null) salary = 0.0;
        if(ylbx == null) ylbx = 0.0;
        if(sybx == null) sybx = 0.0;
        if(yiliaobx == null) yiliaobx = 0.0;
        if(gjj == null) gjj = 0.0;
        if(phoneSubsidy == null) phoneSubsidy = 0.0;
        if(waterSubsidy == null) waterSubsidy = 0.0;
        if(foodSubsidy == null) foodSubsidy = 0.0;
        if(socialSubsidy == null) socialSubsidy = 0.0;


        Emp emp = new Emp();
        emp.setId(id);
        emp.setName(name);;
        emp.setPhone(phone);
        emp.setDeptId(deptId);
        emp.setWorkId(workId);
        emp.setSex(sex);
        emp.setType(type);
        if(workId == 1){
            emp.setIsLeader(1);
        }else{
            emp.setIsLeader(2);
        }
        emp.setNativeSource(nativeSource);
        emp.setNation(nation);
        emp.setBirthday(birthday);
        emp.setIdCard(idCard);
        emp.setEntryDate(entryDate);
        emp.setSchool(school);
        emp.setSubject(subject);
        emp.setEducation(education);
        emp.setLinkMan(linkMan);
        emp.setTel(tel);
        emp.setAddress(address);
        emp.setSalary(BigDecimal.valueOf(salary));
        emp.setYlbx(BigDecimal.valueOf(ylbx));
        emp.setSybx(BigDecimal.valueOf(sybx));
        emp.setYiliaobx(BigDecimal.valueOf(yiliaobx));
        emp.setGjj(BigDecimal.valueOf(gjj));
        emp.setWaterSubsidy(BigDecimal.valueOf(waterSubsidy));
        emp.setFoodSubsidy(BigDecimal.valueOf(foodSubsidy));
        emp.setPhoneSubsidy(BigDecimal.valueOf(phoneSubsidy));
        emp.setSocialSubsidy(BigDecimal.valueOf(socialSubsidy));
        emp.setRemark(remark);

        if(id == null){
            emp.setPassword(MD5Utils.ecodeByMD5("123456"));
            emp.setIsDelete(0);
            emp.setCreateTime(new Date());
            emp.setLastModifyTime(new Date());
            empService.create(emp);
        }else{
            emp.setLastModifyTime(new Date());
            empService.update(emp);
        }

        return XaResult.success();
    }




}
