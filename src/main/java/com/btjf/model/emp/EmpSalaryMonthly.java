package com.btjf.model.emp;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class EmpSalaryMonthly implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_emp_salary_monthly.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_emp_salary_monthly.yearMonth
     *
     * @mbg.generated
     */
    private String yearMonth;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_emp_salary_monthly.empId
     *
     * @mbg.generated
     */
    private Integer empId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_emp_salary_monthly.empName
     *
     * @mbg.generated
     */
    private String empName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_emp_salary_monthly.deptId
     *
     * @mbg.generated
     */
    private Integer deptId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_emp_salary_monthly.deptName
     *
     * @mbg.generated
     */
    private String deptName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_emp_salary_monthly.workName
     *
     * @mbg.generated
     */
    private String workName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_emp_salary_monthly.salary
     *
     * @mbg.generated
     */
    private BigDecimal salary;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_emp_salary_monthly.workDay
     *
     * @mbg.generated
     */
    private BigDecimal workDay;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_emp_salary_monthly.dayWork
     *
     * @mbg.generated
     */
    private BigDecimal dayWork;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_emp_salary_monthly.nightWork
     *
     * @mbg.generated
     */
    private BigDecimal nightWork;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_emp_salary_monthly.score
     *
     * @mbg.generated
     */
    private BigDecimal score;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_emp_salary_monthly.fiveScore
     *
     * @mbg.generated
     */
    private BigDecimal fiveScore;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_emp_salary_monthly.coordinationScore
     *
     * @mbg.generated
     */
    private BigDecimal coordinationScore;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_emp_salary_monthly.qualityScore
     *
     * @mbg.generated
     */
    private BigDecimal qualityScore;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_emp_salary_monthly.checkWorkScore
     *
     * @mbg.generated
     */
    private BigDecimal checkWorkScore;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_emp_salary_monthly.restDay
     *
     * @mbg.generated
     */
    private BigDecimal restDay;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_emp_salary_monthly.sumDay
     *
     * @mbg.generated
     */
    private BigDecimal sumDay;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_emp_salary_monthly.daySalary
     *
     * @mbg.generated
     */
    private BigDecimal daySalary;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_emp_salary_monthly.realSalary
     *
     * @mbg.generated
     */
    private BigDecimal realSalary;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_emp_salary_monthly.createTime
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_emp_salary_monthly.lastModifyTime
     *
     * @mbg.generated
     */
    private Date lastModifyTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_emp_salary_monthly.dayWorkHoliday
     *
     * @mbg.generated
     */
    private BigDecimal dayWorkHoliday;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_emp_salary_monthly.nightWorkHoliay
     *
     * @mbg.generated
     */
    private BigDecimal nightWorkHoliay;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_emp_salary_monthly.dayWorkLegal
     *
     * @mbg.generated
     */
    private BigDecimal dayWorkLegal;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_emp_salary_monthly.nigthWorkLegal
     *
     * @mbg.generated
     */
    private BigDecimal nigthWorkLegal;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_emp_salary_monthly.isDelete
     *
     * @mbg.generated
     */
    private Integer isDelete;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_emp_salary_monthly
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_emp_salary_monthly.id
     *
     * @return the value of t_emp_salary_monthly.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_emp_salary_monthly.id
     *
     * @param id the value for t_emp_salary_monthly.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_emp_salary_monthly.yearMonth
     *
     * @return the value of t_emp_salary_monthly.yearMonth
     *
     * @mbg.generated
     */
    public String getYearMonth() {
        return yearMonth;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_emp_salary_monthly.yearMonth
     *
     * @param yearMonth the value for t_emp_salary_monthly.yearMonth
     *
     * @mbg.generated
     */
    public void setYearMonth(String yearMonth) {
        this.yearMonth = yearMonth == null ? null : yearMonth.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_emp_salary_monthly.empId
     *
     * @return the value of t_emp_salary_monthly.empId
     *
     * @mbg.generated
     */
    public Integer getEmpId() {
        return empId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_emp_salary_monthly.empId
     *
     * @param empId the value for t_emp_salary_monthly.empId
     *
     * @mbg.generated
     */
    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_emp_salary_monthly.empName
     *
     * @return the value of t_emp_salary_monthly.empName
     *
     * @mbg.generated
     */
    public String getEmpName() {
        return empName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_emp_salary_monthly.empName
     *
     * @param empName the value for t_emp_salary_monthly.empName
     *
     * @mbg.generated
     */
    public void setEmpName(String empName) {
        this.empName = empName == null ? null : empName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_emp_salary_monthly.deptId
     *
     * @return the value of t_emp_salary_monthly.deptId
     *
     * @mbg.generated
     */
    public Integer getDeptId() {
        return deptId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_emp_salary_monthly.deptId
     *
     * @param deptId the value for t_emp_salary_monthly.deptId
     *
     * @mbg.generated
     */
    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_emp_salary_monthly.deptName
     *
     * @return the value of t_emp_salary_monthly.deptName
     *
     * @mbg.generated
     */
    public String getDeptName() {
        return deptName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_emp_salary_monthly.deptName
     *
     * @param deptName the value for t_emp_salary_monthly.deptName
     *
     * @mbg.generated
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName == null ? null : deptName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_emp_salary_monthly.workName
     *
     * @return the value of t_emp_salary_monthly.workName
     *
     * @mbg.generated
     */
    public String getWorkName() {
        return workName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_emp_salary_monthly.workName
     *
     * @param workName the value for t_emp_salary_monthly.workName
     *
     * @mbg.generated
     */
    public void setWorkName(String workName) {
        this.workName = workName == null ? null : workName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_emp_salary_monthly.salary
     *
     * @return the value of t_emp_salary_monthly.salary
     *
     * @mbg.generated
     */
    public BigDecimal getSalary() {
        return salary;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_emp_salary_monthly.salary
     *
     * @param salary the value for t_emp_salary_monthly.salary
     *
     * @mbg.generated
     */
    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_emp_salary_monthly.workDay
     *
     * @return the value of t_emp_salary_monthly.workDay
     *
     * @mbg.generated
     */
    public BigDecimal getWorkDay() {
        return workDay;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_emp_salary_monthly.workDay
     *
     * @param workDay the value for t_emp_salary_monthly.workDay
     *
     * @mbg.generated
     */
    public void setWorkDay(BigDecimal workDay) {
        this.workDay = workDay;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_emp_salary_monthly.dayWork
     *
     * @return the value of t_emp_salary_monthly.dayWork
     *
     * @mbg.generated
     */
    public BigDecimal getDayWork() {
        return dayWork;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_emp_salary_monthly.dayWork
     *
     * @param dayWork the value for t_emp_salary_monthly.dayWork
     *
     * @mbg.generated
     */
    public void setDayWork(BigDecimal dayWork) {
        this.dayWork = dayWork;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_emp_salary_monthly.nightWork
     *
     * @return the value of t_emp_salary_monthly.nightWork
     *
     * @mbg.generated
     */
    public BigDecimal getNightWork() {
        return nightWork;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_emp_salary_monthly.nightWork
     *
     * @param nightWork the value for t_emp_salary_monthly.nightWork
     *
     * @mbg.generated
     */
    public void setNightWork(BigDecimal nightWork) {
        this.nightWork = nightWork;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_emp_salary_monthly.score
     *
     * @return the value of t_emp_salary_monthly.score
     *
     * @mbg.generated
     */
    public BigDecimal getScore() {
        return score;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_emp_salary_monthly.score
     *
     * @param score the value for t_emp_salary_monthly.score
     *
     * @mbg.generated
     */
    public void setScore(BigDecimal score) {
        this.score = score;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_emp_salary_monthly.fiveScore
     *
     * @return the value of t_emp_salary_monthly.fiveScore
     *
     * @mbg.generated
     */
    public BigDecimal getFiveScore() {
        return fiveScore;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_emp_salary_monthly.fiveScore
     *
     * @param fiveScore the value for t_emp_salary_monthly.fiveScore
     *
     * @mbg.generated
     */
    public void setFiveScore(BigDecimal fiveScore) {
        this.fiveScore = fiveScore;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_emp_salary_monthly.coordinationScore
     *
     * @return the value of t_emp_salary_monthly.coordinationScore
     *
     * @mbg.generated
     */
    public BigDecimal getCoordinationScore() {
        return coordinationScore;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_emp_salary_monthly.coordinationScore
     *
     * @param coordinationScore the value for t_emp_salary_monthly.coordinationScore
     *
     * @mbg.generated
     */
    public void setCoordinationScore(BigDecimal coordinationScore) {
        this.coordinationScore = coordinationScore;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_emp_salary_monthly.qualityScore
     *
     * @return the value of t_emp_salary_monthly.qualityScore
     *
     * @mbg.generated
     */
    public BigDecimal getQualityScore() {
        return qualityScore;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_emp_salary_monthly.qualityScore
     *
     * @param qualityScore the value for t_emp_salary_monthly.qualityScore
     *
     * @mbg.generated
     */
    public void setQualityScore(BigDecimal qualityScore) {
        this.qualityScore = qualityScore;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_emp_salary_monthly.checkWorkScore
     *
     * @return the value of t_emp_salary_monthly.checkWorkScore
     *
     * @mbg.generated
     */
    public BigDecimal getCheckWorkScore() {
        return checkWorkScore;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_emp_salary_monthly.checkWorkScore
     *
     * @param checkWorkScore the value for t_emp_salary_monthly.checkWorkScore
     *
     * @mbg.generated
     */
    public void setCheckWorkScore(BigDecimal checkWorkScore) {
        this.checkWorkScore = checkWorkScore;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_emp_salary_monthly.restDay
     *
     * @return the value of t_emp_salary_monthly.restDay
     *
     * @mbg.generated
     */
    public BigDecimal getRestDay() {
        return restDay;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_emp_salary_monthly.restDay
     *
     * @param restDay the value for t_emp_salary_monthly.restDay
     *
     * @mbg.generated
     */
    public void setRestDay(BigDecimal restDay) {
        this.restDay = restDay;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_emp_salary_monthly.sumDay
     *
     * @return the value of t_emp_salary_monthly.sumDay
     *
     * @mbg.generated
     */
    public BigDecimal getSumDay() {
        return sumDay;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_emp_salary_monthly.sumDay
     *
     * @param sumDay the value for t_emp_salary_monthly.sumDay
     *
     * @mbg.generated
     */
    public void setSumDay(BigDecimal sumDay) {
        this.sumDay = sumDay;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_emp_salary_monthly.daySalary
     *
     * @return the value of t_emp_salary_monthly.daySalary
     *
     * @mbg.generated
     */
    public BigDecimal getDaySalary() {
        return daySalary;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_emp_salary_monthly.daySalary
     *
     * @param daySalary the value for t_emp_salary_monthly.daySalary
     *
     * @mbg.generated
     */
    public void setDaySalary(BigDecimal daySalary) {
        this.daySalary = daySalary;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_emp_salary_monthly.realSalary
     *
     * @return the value of t_emp_salary_monthly.realSalary
     *
     * @mbg.generated
     */
    public BigDecimal getRealSalary() {
        return realSalary;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_emp_salary_monthly.realSalary
     *
     * @param realSalary the value for t_emp_salary_monthly.realSalary
     *
     * @mbg.generated
     */
    public void setRealSalary(BigDecimal realSalary) {
        this.realSalary = realSalary;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_emp_salary_monthly.createTime
     *
     * @return the value of t_emp_salary_monthly.createTime
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_emp_salary_monthly.createTime
     *
     * @param createTime the value for t_emp_salary_monthly.createTime
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_emp_salary_monthly.lastModifyTime
     *
     * @return the value of t_emp_salary_monthly.lastModifyTime
     *
     * @mbg.generated
     */
    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_emp_salary_monthly.lastModifyTime
     *
     * @param lastModifyTime the value for t_emp_salary_monthly.lastModifyTime
     *
     * @mbg.generated
     */
    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_emp_salary_monthly.dayWorkHoliday
     *
     * @return the value of t_emp_salary_monthly.dayWorkHoliday
     *
     * @mbg.generated
     */
    public BigDecimal getDayWorkHoliday() {
        return dayWorkHoliday;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_emp_salary_monthly.dayWorkHoliday
     *
     * @param dayWorkHoliday the value for t_emp_salary_monthly.dayWorkHoliday
     *
     * @mbg.generated
     */
    public void setDayWorkHoliday(BigDecimal dayWorkHoliday) {
        this.dayWorkHoliday = dayWorkHoliday;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_emp_salary_monthly.nightWorkHoliay
     *
     * @return the value of t_emp_salary_monthly.nightWorkHoliay
     *
     * @mbg.generated
     */
    public BigDecimal getNightWorkHoliay() {
        return nightWorkHoliay;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_emp_salary_monthly.nightWorkHoliay
     *
     * @param nightWorkHoliay the value for t_emp_salary_monthly.nightWorkHoliay
     *
     * @mbg.generated
     */
    public void setNightWorkHoliay(BigDecimal nightWorkHoliay) {
        this.nightWorkHoliay = nightWorkHoliay;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_emp_salary_monthly.dayWorkLegal
     *
     * @return the value of t_emp_salary_monthly.dayWorkLegal
     *
     * @mbg.generated
     */
    public BigDecimal getDayWorkLegal() {
        return dayWorkLegal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_emp_salary_monthly.dayWorkLegal
     *
     * @param dayWorkLegal the value for t_emp_salary_monthly.dayWorkLegal
     *
     * @mbg.generated
     */
    public void setDayWorkLegal(BigDecimal dayWorkLegal) {
        this.dayWorkLegal = dayWorkLegal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_emp_salary_monthly.nigthWorkLegal
     *
     * @return the value of t_emp_salary_monthly.nigthWorkLegal
     *
     * @mbg.generated
     */
    public BigDecimal getNigthWorkLegal() {
        return nigthWorkLegal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_emp_salary_monthly.nigthWorkLegal
     *
     * @param nigthWorkLegal the value for t_emp_salary_monthly.nigthWorkLegal
     *
     * @mbg.generated
     */
    public void setNigthWorkLegal(BigDecimal nigthWorkLegal) {
        this.nigthWorkLegal = nigthWorkLegal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_emp_salary_monthly.isDelete
     *
     * @return the value of t_emp_salary_monthly.isDelete
     *
     * @mbg.generated
     */
    public Integer getIsDelete() {
        return isDelete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_emp_salary_monthly.isDelete
     *
     * @param isDelete the value for t_emp_salary_monthly.isDelete
     *
     * @mbg.generated
     */
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_emp_salary_monthly
     *
     * @mbg.generated
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        EmpSalaryMonthly other = (EmpSalaryMonthly) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getYearMonth() == null ? other.getYearMonth() == null : this.getYearMonth().equals(other.getYearMonth()))
            && (this.getEmpId() == null ? other.getEmpId() == null : this.getEmpId().equals(other.getEmpId()))
            && (this.getEmpName() == null ? other.getEmpName() == null : this.getEmpName().equals(other.getEmpName()))
            && (this.getDeptId() == null ? other.getDeptId() == null : this.getDeptId().equals(other.getDeptId()))
            && (this.getDeptName() == null ? other.getDeptName() == null : this.getDeptName().equals(other.getDeptName()))
            && (this.getWorkName() == null ? other.getWorkName() == null : this.getWorkName().equals(other.getWorkName()))
            && (this.getSalary() == null ? other.getSalary() == null : this.getSalary().equals(other.getSalary()))
            && (this.getWorkDay() == null ? other.getWorkDay() == null : this.getWorkDay().equals(other.getWorkDay()))
            && (this.getDayWork() == null ? other.getDayWork() == null : this.getDayWork().equals(other.getDayWork()))
            && (this.getNightWork() == null ? other.getNightWork() == null : this.getNightWork().equals(other.getNightWork()))
            && (this.getScore() == null ? other.getScore() == null : this.getScore().equals(other.getScore()))
            && (this.getFiveScore() == null ? other.getFiveScore() == null : this.getFiveScore().equals(other.getFiveScore()))
            && (this.getCoordinationScore() == null ? other.getCoordinationScore() == null : this.getCoordinationScore().equals(other.getCoordinationScore()))
            && (this.getQualityScore() == null ? other.getQualityScore() == null : this.getQualityScore().equals(other.getQualityScore()))
            && (this.getCheckWorkScore() == null ? other.getCheckWorkScore() == null : this.getCheckWorkScore().equals(other.getCheckWorkScore()))
            && (this.getRestDay() == null ? other.getRestDay() == null : this.getRestDay().equals(other.getRestDay()))
            && (this.getSumDay() == null ? other.getSumDay() == null : this.getSumDay().equals(other.getSumDay()))
            && (this.getDaySalary() == null ? other.getDaySalary() == null : this.getDaySalary().equals(other.getDaySalary()))
            && (this.getRealSalary() == null ? other.getRealSalary() == null : this.getRealSalary().equals(other.getRealSalary()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getLastModifyTime() == null ? other.getLastModifyTime() == null : this.getLastModifyTime().equals(other.getLastModifyTime()))
            && (this.getDayWorkHoliday() == null ? other.getDayWorkHoliday() == null : this.getDayWorkHoliday().equals(other.getDayWorkHoliday()))
            && (this.getNightWorkHoliay() == null ? other.getNightWorkHoliay() == null : this.getNightWorkHoliay().equals(other.getNightWorkHoliay()))
            && (this.getDayWorkLegal() == null ? other.getDayWorkLegal() == null : this.getDayWorkLegal().equals(other.getDayWorkLegal()))
            && (this.getNigthWorkLegal() == null ? other.getNigthWorkLegal() == null : this.getNigthWorkLegal().equals(other.getNigthWorkLegal()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_emp_salary_monthly
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getYearMonth() == null) ? 0 : getYearMonth().hashCode());
        result = prime * result + ((getEmpId() == null) ? 0 : getEmpId().hashCode());
        result = prime * result + ((getEmpName() == null) ? 0 : getEmpName().hashCode());
        result = prime * result + ((getDeptId() == null) ? 0 : getDeptId().hashCode());
        result = prime * result + ((getDeptName() == null) ? 0 : getDeptName().hashCode());
        result = prime * result + ((getWorkName() == null) ? 0 : getWorkName().hashCode());
        result = prime * result + ((getSalary() == null) ? 0 : getSalary().hashCode());
        result = prime * result + ((getWorkDay() == null) ? 0 : getWorkDay().hashCode());
        result = prime * result + ((getDayWork() == null) ? 0 : getDayWork().hashCode());
        result = prime * result + ((getNightWork() == null) ? 0 : getNightWork().hashCode());
        result = prime * result + ((getScore() == null) ? 0 : getScore().hashCode());
        result = prime * result + ((getFiveScore() == null) ? 0 : getFiveScore().hashCode());
        result = prime * result + ((getCoordinationScore() == null) ? 0 : getCoordinationScore().hashCode());
        result = prime * result + ((getQualityScore() == null) ? 0 : getQualityScore().hashCode());
        result = prime * result + ((getCheckWorkScore() == null) ? 0 : getCheckWorkScore().hashCode());
        result = prime * result + ((getRestDay() == null) ? 0 : getRestDay().hashCode());
        result = prime * result + ((getSumDay() == null) ? 0 : getSumDay().hashCode());
        result = prime * result + ((getDaySalary() == null) ? 0 : getDaySalary().hashCode());
        result = prime * result + ((getRealSalary() == null) ? 0 : getRealSalary().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getLastModifyTime() == null) ? 0 : getLastModifyTime().hashCode());
        result = prime * result + ((getDayWorkHoliday() == null) ? 0 : getDayWorkHoliday().hashCode());
        result = prime * result + ((getNightWorkHoliay() == null) ? 0 : getNightWorkHoliay().hashCode());
        result = prime * result + ((getDayWorkLegal() == null) ? 0 : getDayWorkLegal().hashCode());
        result = prime * result + ((getNigthWorkLegal() == null) ? 0 : getNigthWorkLegal().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        return result;
    }
}