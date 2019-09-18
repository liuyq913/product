package com.btjf.controller.emp.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by liuyq on 2019/9/18.
 */
public class EmpSalaryVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String yearMonth;
    private Integer empId;
    private String empName;
    private Integer deptId;
    private String deptName;
    private String workName;
    private BigDecimal dayWork;
    private BigDecimal nightWork;
    private BigDecimal score;
    private BigDecimal sumWorkHour;
    private BigDecimal hourSalary;
    private BigDecimal restDay;
    private BigDecimal sumDay;
    private BigDecimal daySalary;
    private BigDecimal dhbt;
    private Integer type;
    private BigDecimal timeSalary;
    private BigDecimal nigthSnack;
    private BigDecimal phoneSubsidy;
    private BigDecimal mealSubsidy;
    private BigDecimal socialSubsidy;
    private BigDecimal hourSubsidy;
    private BigDecimal otherSubsidy;
    private BigDecimal sumSusbsidy;
    private BigDecimal realSalary;
    private BigDecimal ylbx;
    private BigDecimal sybx;
    private BigDecimal yiliaobx;
    private BigDecimal gjj;
    private BigDecimal otherDeduction;
    private BigDecimal sumDeduction;
    private BigDecimal trueSalary;
    private BigDecimal normalOvertime; //正常加班工时
    private BigDecimal normalOvertimeSalary;// 金额1 正常加班工时*17.325
    private BigDecimal holiayOvertime; //假日加班工时
    private BigDecimal holiayOvertimeSalary;// 金额2 假日加班工时*23.1
    private BigDecimal legalOvertime; //法假加班工时 法定假日（白班天数*8+晚班*3）
    private BigDecimal legalOvertimeSalary;// 法假日加班工时(白班天数*8+晚班*3)*3*基本时薪(11.55)
    private Date createTime;
    private Date lastModifyTime;
    private Integer isDelete;
    private BigDecimal workDay;
    private BigDecimal basicSalary;

    //计件工特有
    private BigDecimal workYearSubsidy; // 工龄
    private BigDecimal percentSubsidy;  //计件10%
    private BigDecimal latheWorkerSubsidy;  //车工
    private BigDecimal newLatheWorkerSubsidy; // 新车工
    private BigDecimal twoSideSubsidy; //复面补贴

    //汇总表 特有
    private BigDecimal baseWorkHour;// 基本工资工时  正常白班天数*8
    private BigDecimal baseWorkHourSalary;// 基本工资工时金额


}
