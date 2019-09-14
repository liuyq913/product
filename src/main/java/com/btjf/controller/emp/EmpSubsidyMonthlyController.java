package com.btjf.controller.emp;

import com.alibaba.druid.util.StringUtils;
import com.btjf.application.util.XaResult;
import com.btjf.constant.SubsidyTypeEnum;
import com.btjf.controller.base.ProductBaseController;
import com.btjf.model.emp.EmpSubsibyMonthly;
import com.btjf.service.emp.EmpSubsibyMonthlyService;
import com.wordnik.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by liuyq on 2019/9/14.
 */
@Api(value = "EmpController", description = "补贴，扣款", position = 1)
@RequestMapping(value = "/empSubsidyMonthly/")
@RestController("empSubsidyMonthlyController")
public class EmpSubsidyMonthlyController extends ProductBaseController {

    @Resource
    private EmpSubsibyMonthlyService empSubsibyMonthlyService;

    public XaResult<Integer> addOrUpdate(Integer id, String yearMonth, String billNo, String empName,
                                         Integer empId, Integer deptId, String deptName, String workName,
                                         String remark, Double money, String reason, String drawer, Integer type) {
        if (StringUtils.isEmpty(yearMonth)) {
            return XaResult.error("月份不能为空");
        }
        if (empId == null || deptId == null || StringUtils.isEmpty(empName)
                || StringUtils.isEmpty(deptName) || StringUtils.isEmpty(workName)) {
            return XaResult.error("扣款人/补贴人信息不完整");
        }

        if (StringUtils.isEmpty(billNo)) {
            return XaResult.error("票据编号为空");
        }

        if (StringUtils.isEmpty(reason)) {
            return XaResult.error("扣款原因/补贴事项为空");
        }

        if (StringUtils.isEmpty(drawer)) {
            return XaResult.error("开票人为空");
        }

        if (type == null) return XaResult.error("类型不能为空");

        if (!type.equals(4) || !type.equals(9)) {
            return XaResult.error("类型不支持");
        }

        EmpSubsibyMonthly empSubsibyMonthly = new EmpSubsibyMonthly();
        empSubsibyMonthly.setDeptName(deptName);
        empSubsibyMonthly.setId(id);
        empSubsibyMonthly.setMoney(money == null ? BigDecimal.ZERO : BigDecimal.valueOf(money));
        empSubsibyMonthly.setIsDelete(0);
        empSubsibyMonthly.setCreateTime(new Date());
        empSubsibyMonthly.setEmpId(empId);
        empSubsibyMonthly.setEmpName(empName);
        empSubsibyMonthly.setBillNo(billNo);
        empSubsibyMonthly.setType(SubsidyTypeEnum.getByValue(type).getValue());
        empSubsibyMonthly.setWorkName(workName);
        empSubsibyMonthly.setDeptId(deptId);
        empSubsibyMonthly.setDrawer(drawer);
        empSubsibyMonthly.setDrawTime(new Date());
        empSubsibyMonthly.setRemark(remark);
        empSubsibyMonthly.setReason(reason);

        //todo
        return null;
    }

}