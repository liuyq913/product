package com.btjf.service.emp;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.btjf.common.page.Page;
import com.btjf.constant.SubsidyTypeEnum;
import com.btjf.mapper.emp.EmpSalaryMonthlyMapper;
import com.btjf.model.emp.Emp;
import com.btjf.model.emp.EmpSalaryMonthly;
import com.btjf.model.emp.SummarySalaryMonthly;
import com.btjf.model.order.ProductionProcedureConfirm;
import com.btjf.service.order.ProductionProcedureConfirmService;
import com.btjf.util.BigDecimalUtil;
import com.btjf.util.ThreadPoolExecutorUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

/**
 * Created by liuyq on 2019/9/8.
 */
@Service
public class EmpSalaryMonthlyService {

    @Resource
    private EmpSalaryMonthlyMapper empSalaryMonthlyMapper;

    @Resource
    private EmpService empService;

    @Resource
    private EmpTimeSalaryService empTimeSalaryService;

    @Resource
    private EmpSubsibyMonthlyService empSubsibyMonthlyService;

    @Resource
    private SummarySalaryMonthlyService summarySalaryMonthlyService;

    @Resource
    private ProductionProcedureConfirmService productionProcedureConfirmService;


    public Integer save(EmpSalaryMonthly empSalaryMonthly) {
        return Optional.ofNullable(empSalaryMonthly).map(t -> {
            //多次导入删除之前的数据
            empSalaryMonthlyMapper.deleteByYearMonthAndName(t.getYearMonth(), t.getEmpName());
            empSalaryMonthlyMapper.insertSelective(t);
            return t.getId();
        }).orElse(0);
    }

    public EmpSalaryMonthly getById(Integer id) {
        return Optional.ofNullable(id).map(t -> {
            return empSalaryMonthlyMapper.selectByPrimaryKey(t);
        }).orElse(null);
    }

    public Page<EmpSalaryMonthly> getPage(String yearMonth, String empName, String deptName, Page page) {
        PageHelper.startPage(page.getPage(), page.getRp());
        List<EmpSalaryMonthly> empSalaryMonthlies = this.getList(yearMonth, empName, deptName);
        PageInfo pageInfo = new PageInfo(empSalaryMonthlies);
        pageInfo.setList(empSalaryMonthlies);
        return new Page<>(pageInfo);
    }

    public EmpSalaryMonthly getByYearMonthAndName(String yearMonth, String name) {
        return empSalaryMonthlyMapper.getByYearMonthAndName(yearMonth, name);
    }

    public List<EmpSalaryMonthly> getList(String yearMonth, String empName, String deptName) {
        return empSalaryMonthlyMapper.getList(yearMonth, empName, deptName);
    }

    public List<String> getYearMonth() {
        return empSalaryMonthlyMapper.getYearMonth();
    }


    public Integer update(EmpSalaryMonthly empSalaryMonthly) {
        if (empSalaryMonthly == null) return 0;
        return empSalaryMonthlyMapper.updateByPrimaryKeySelective(empSalaryMonthly);
    }

    public Integer calculation(String yearMonth, String deptName, String empName) {
        List<EmpSalaryMonthly> empSalaryMonthlies = this.getList(yearMonth, deptName, empName);
        ThreadPoolExecutor executor = ThreadPoolExecutorUtil.getPool();
        executor.execute(() -> {
            if (!CollectionUtils.isEmpty(empSalaryMonthlies)) {
                for (EmpSalaryMonthly empSalaryMonthly : empSalaryMonthlies) {
                    if (null == empSalaryMonthly) continue;
                    if (1 == empSalaryMonthly.getType()) {
                        //todo 计件工
                        //获取计件工资
                        List<ProductionProcedureConfirm> list = productionProcedureConfirmService.getUnSettlement(
                                empSalaryMonthly.getYearMonth(),empSalaryMonthly.getEmpId());
                        BigDecimal sum = BigDecimal.valueOf(0.0);
                        if(CollectionUtils.isNotEmpty(list)){
                            sum = list.stream().map(ProductionProcedureConfirm::getMoney).reduce(BigDecimal.ZERO, BigDecimal::add);
                        }



                        //将记录改为已结算
                        if(CollectionUtils.isNotEmpty(list)){
                            List<Integer> ids=list.stream().map(ProductionProcedureConfirm::getId).collect(Collectors.toList());
                            productionProcedureConfirmService.updateSettlement(ids);
                        }
                    } else if (2 == empSalaryMonthly.getType()) {
                        //固定工
                        this.calculationFixed(empSalaryMonthly);
                    }
                }
            }

        });
        return CollectionUtils.isEmpty(empSalaryMonthlies) ? 0 : empSalaryMonthlies.size();
    }


    /**
     * 固定工工资计算
     * @param empSalaryMonthly
     */
    public void calculationFixed(EmpSalaryMonthly empSalaryMonthly) {
        SummarySalaryMonthly summarySalaryMonthly = new SummarySalaryMonthly();
        summarySalaryMonthly.setType(empSalaryMonthly.getType());
        summarySalaryMonthly.setDeptName(empSalaryMonthly.getDeptName());
        summarySalaryMonthly.setDeptId(empSalaryMonthly.getDeptId());
        summarySalaryMonthly.setEmpName(empSalaryMonthly.getEmpName());
        summarySalaryMonthly.setLastModifyTime(new Date());
        summarySalaryMonthly.setScore(empSalaryMonthly.getScore());
        summarySalaryMonthly.setWorkName(empSalaryMonthly.getWorkName());
        summarySalaryMonthly.setEmpId(empSalaryMonthly.getEmpId());
        Emp emp = empService.getByID(empSalaryMonthly.getEmpId());
        if (emp != null) {
            //月工资
            summarySalaryMonthly.setDhbt(emp.getDhbt());
        }
        //工作日
        summarySalaryMonthly.setWorkDay(empSalaryMonthly.getWorkDay());
        //白班
        summarySalaryMonthly.setDayWork(empSalaryMonthly.getDayWork());
        //晚班
        summarySalaryMonthly.setNightWork(empSalaryMonthly.getNightWork());
        //考核分
        summarySalaryMonthly.setScore(empSalaryMonthly.getScore());
        //总工时（固定汇总表里用）  白班天数*8，正常加班工时*1.5*3，假日加班工时*2，法定加班工时*3  假日 法定 晚班不计算
        summarySalaryMonthly.setSumWorkHour(BigDecimal.valueOf(BigDecimalUtil.add(
                BigDecimalUtil.mul(BigDecimalUtil.add(empSalaryMonthly.getDayWork().doubleValue(),
                        empSalaryMonthly.getDayWorkHoliday().doubleValue(), empSalaryMonthly.getDayWorkLegal().
                                doubleValue()), 8),  //白班工时 包含正常+法假+节假 白班
                BigDecimalUtil.mul(empSalaryMonthly.getNightWork().doubleValue(), 1.5, 3))));//正常加班工时
        //BigDecimalUtil.mul(empSalaryMonthly.getNightWorkHoliay().doubleValue(), 2), //假日加班工时
        //BigDecimalUtil.mul(empSalaryMonthly.getNigthWorkLegal().doubleValue(), 2))));//法定加班工时
        //公休
        summarySalaryMonthly.setRestDay(empSalaryMonthly.getRestDay());
        //总天数
        summarySalaryMonthly.setSumDay(empSalaryMonthly.getSumDay());
        //日工资  取固定工资/总天数，保留2位小数，四舍五入
        summarySalaryMonthly.setDaySalary(BigDecimal.valueOf(BigDecimalUtil.div(emp.getDhbt().doubleValue(), summarySalaryMonthly.getSumDay().doubleValue(), 2)));
        //基本工资  月工资/工作日*总天数
        summarySalaryMonthly.setBasicSalary(BigDecimal.valueOf(BigDecimalUtil.mul(BigDecimalUtil.div(emp.getDhbt().doubleValue(), summarySalaryMonthly.getWorkDay().doubleValue()), summarySalaryMonthly.getSumDay().doubleValue())));
        //计时工资
        summarySalaryMonthly.setTimeSalary(empTimeSalaryService.getTimeSalary(empSalaryMonthly.getYearMonth(), emp.getId()));
        summarySalaryMonthly.setNigthSnack(empSalaryMonthly.getNightWork() == null ? BigDecimal.ZERO : empSalaryMonthly.getNightWork());
        summarySalaryMonthly.setPhoneSubsidy(emp.getPhoneSubsidy() == null ? BigDecimal.ZERO : emp.getPhoneSubsidy());
        if (empSalaryMonthly.getScore() != null && !empSalaryMonthly.getScore().equals(BigDecimal.ZERO)) {
            //社保补贴
            summarySalaryMonthly.setSocialSubsidy(BigDecimal.valueOf(BigDecimalUtil.mul(BigDecimalUtil.div(empSalaryMonthly.getScore().doubleValue(), 100), emp.getSocialSubsidy() != null ? emp.getSocialSubsidy().doubleValue() : 0)));
            summarySalaryMonthly.setHourSubsidy(empSalaryMonthly.getScore());
        } else {
            summarySalaryMonthly.setSocialSubsidy(BigDecimal.valueOf(0));
            summarySalaryMonthly.setHourSubsidy(BigDecimal.valueOf(0));
        }
        //其他补贴
        summarySalaryMonthly.setOtherSubsidy(empSubsibyMonthlyService.getSumSubsiby(empSalaryMonthly.getYearMonth(), emp.getId(), SubsidyTypeEnum.SALARY.getValue()));
        //用餐补贴
        summarySalaryMonthly.setMealSubsidy(BigDecimal.valueOf(BigDecimalUtil.mul(empSalaryMonthly.getDayWork().doubleValue(), 6.0)));
        //补贴合计 夜餐+电话补贴+用餐补贴+社保补贴+住房补贴+其他补贴
        summarySalaryMonthly.setSumSusbsidy(BigDecimal.valueOf(BigDecimalUtil.add(summarySalaryMonthly.getNigthSnack().doubleValue(),
                summarySalaryMonthly.getPhoneSubsidy().doubleValue(), summarySalaryMonthly.getMealSubsidy().doubleValue(),
                summarySalaryMonthly.getSocialSubsidy().doubleValue(), summarySalaryMonthly.getHourSubsidy().doubleValue(),
                summarySalaryMonthly.getOtherSubsidy().doubleValue())));
        //基本工资+计时工资+补贴合计
        summarySalaryMonthly.setRealSalary(BigDecimal.valueOf(BigDecimalUtil.add(summarySalaryMonthly.getBasicSalary().doubleValue(), summarySalaryMonthly.getTimeSalary().doubleValue(),
                summarySalaryMonthly.getSumSusbsidy().doubleValue())));
        summarySalaryMonthly.setYlbx(emp.getYlbx() == null ? BigDecimal.ZERO : emp.getYlbx());
        summarySalaryMonthly.setYiliaobx(emp.getYiliaobx() == null ? BigDecimal.ZERO : emp.getYiliaobx());
        summarySalaryMonthly.setSybx(emp.getSybx() == null ? BigDecimal.ZERO : emp.getSybx());
        summarySalaryMonthly.setGjj(emp.getGjj() == null ? BigDecimal.ZERO : emp.getGjj());
        summarySalaryMonthly.setOtherDeduction(empSubsibyMonthlyService.getSumSubsiby(empSalaryMonthly.getYearMonth(), emp.getId(), SubsidyTypeEnum.OTHER.getValue()));
        summarySalaryMonthly.setSumDeduction(summarySalaryMonthly.getOtherDeduction().add(summarySalaryMonthly.getMealSubsidy()));

        //应发工资-养老金-医疗险-失业金-公积金-用餐扣款-其他扣款
        summarySalaryMonthly.setTrueSalary(BigDecimal.valueOf(BigDecimalUtil.sub(summarySalaryMonthly.getRealSalary().doubleValue(),
                summarySalaryMonthly.getYlbx().doubleValue(), summarySalaryMonthly.getYiliaobx().doubleValue(),
                summarySalaryMonthly.getSybx().doubleValue(), summarySalaryMonthly.getGjj().doubleValue(),
                summarySalaryMonthly.getMealSubsidy().doubleValue(), summarySalaryMonthly.getOtherDeduction().doubleValue())));

        //汇总表里面要用  正常加班工时  假日加班工时  法假加班工时
        summarySalaryMonthly.setNormalOvertime(BigDecimal.valueOf(BigDecimalUtil.mul(empSalaryMonthly.getNightWork().doubleValue(), 3.0)));
        summarySalaryMonthly.setHoliayOvertime(BigDecimal.valueOf(BigDecimalUtil.add(BigDecimalUtil.mul(empSalaryMonthly.getNightWorkHoliay().doubleValue(), 3.0),
                BigDecimalUtil.mul(empSalaryMonthly.getDayWorkHoliday().doubleValue(), 8.0))));
        summarySalaryMonthly.setLegalOvertime(BigDecimal.valueOf(BigDecimalUtil.add(BigDecimalUtil.mul(empSalaryMonthly.getNigthWorkLegal().doubleValue(), 3.0),
                BigDecimalUtil.mul(empSalaryMonthly.getDayWorkLegal().doubleValue(), 8.0))));

        summarySalaryMonthlyService.saveOrUpdate(summarySalaryMonthly);
    }
}
