package com.btjf.mapper.emp;

import com.btjf.model.emp.EmpTimesalaryMonthly;
import com.btjf.model.emp.EmpTimesalaryMonthlyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EmpTimesalaryMonthlyMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_emp_timesalary_monthly
     *
     * @mbg.generated
     */
    long countByExample(EmpTimesalaryMonthlyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_emp_timesalary_monthly
     *
     * @mbg.generated
     */
    int deleteByExample(EmpTimesalaryMonthlyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_emp_timesalary_monthly
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_emp_timesalary_monthly
     *
     * @mbg.generated
     */
    int insert(EmpTimesalaryMonthly record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_emp_timesalary_monthly
     *
     * @mbg.generated
     */
    int insertSelective(EmpTimesalaryMonthly record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_emp_timesalary_monthly
     *
     * @mbg.generated
     */
    List<EmpTimesalaryMonthly> selectByExample(EmpTimesalaryMonthlyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_emp_timesalary_monthly
     *
     * @mbg.generated
     */
    EmpTimesalaryMonthly selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_emp_timesalary_monthly
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") EmpTimesalaryMonthly record, @Param("example") EmpTimesalaryMonthlyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_emp_timesalary_monthly
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") EmpTimesalaryMonthly record, @Param("example") EmpTimesalaryMonthlyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_emp_timesalary_monthly
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(EmpTimesalaryMonthly record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_emp_timesalary_monthly
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(EmpTimesalaryMonthly record);

    List<EmpTimesalaryMonthly> findByBillNo(@Param("billNo")String billNo);

    void confirm(@Param("list")List<Integer> list);

    List<EmpTimesalaryMonthly> findList(@Param("yearMonth")String yearMonth, @Param("empName")String empName,
                                        @Param("deptName")String deptName, @Param("billNo")String billNo,
                                        @Param("isConfirm")Integer isConfirm);
}