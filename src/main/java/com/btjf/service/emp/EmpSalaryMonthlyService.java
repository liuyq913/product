package com.btjf.service.emp;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.btjf.common.page.Page;
import com.btjf.mapper.emp.EmpSalaryMonthlyMapper;
import com.btjf.model.emp.Emp;
import com.btjf.model.emp.EmpSalaryMonthly;
import com.btjf.model.emp.SummarySalaryMonthly;
import com.btjf.model.order.ProductionProcedureConfirm;
import com.btjf.service.order.ProductionProcedureConfirmService;
import com.btjf.util.BigDecimalUtil;
import com.btjf.util.ThreadPoolExecutorUtil;
import com.btjf.vo.weixin.EmpDayWorkVo;
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
     *
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
        //总工时  白班天数*8，正常加班工时*1.5*3，假日加班工时*2，法定加班工时*3  假日 法定 晚班不计算
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
        summarySalaryMonthly.setBasicSalary(BigDecimal.valueOf(BigDecimalUtil.mul(BigDecimalUtil.div(emp.getDhbt().doubleValue(),summarySalaryMonthly.getWorkDay().doubleValue()), summarySalaryMonthly.getSumDay().doubleValue())));
        //计时工资
        //summarySalaryMonthly.setTimeSalary()
    }
}
