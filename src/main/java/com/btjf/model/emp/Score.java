package com.btjf.model.emp;

import java.io.Serializable;
import java.math.BigDecimal;

public class Score implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_score.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_score.yearMonth
     *
     * @mbg.generated
     */
    private String yearMonth;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_score.empId
     *
     * @mbg.generated
     */
    private Integer empId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_score.empName
     *
     * @mbg.generated
     */
    private String empName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_score.fiveScore
     *
     * @mbg.generated
     */
    private BigDecimal fiveScore;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_score.coordinationScore
     *
     * @mbg.generated
     */
    private BigDecimal coordinationScore;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_score.qualityScore
     *
     * @mbg.generated
     */
    private BigDecimal qualityScore;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_score.score
     *
     * @mbg.generated
     */
    private BigDecimal score;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_score.isDelete
     *
     * @mbg.generated
     */
    private Integer isDelete;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_score.checkworkScore
     *
     * @mbg.generated
     */
    private BigDecimal checkworkScore;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_score
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_score.id
     *
     * @return the value of t_score.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_score.id
     *
     * @param id the value for t_score.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_score.yearMonth
     *
     * @return the value of t_score.yearMonth
     *
     * @mbg.generated
     */
    public String getYearMonth() {
        return yearMonth;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_score.yearMonth
     *
     * @param yearMonth the value for t_score.yearMonth
     *
     * @mbg.generated
     */
    public void setYearMonth(String yearMonth) {
        this.yearMonth = yearMonth == null ? null : yearMonth.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_score.empId
     *
     * @return the value of t_score.empId
     *
     * @mbg.generated
     */
    public Integer getEmpId() {
        return empId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_score.empId
     *
     * @param empId the value for t_score.empId
     *
     * @mbg.generated
     */
    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_score.empName
     *
     * @return the value of t_score.empName
     *
     * @mbg.generated
     */
    public String getEmpName() {
        return empName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_score.empName
     *
     * @param empName the value for t_score.empName
     *
     * @mbg.generated
     */
    public void setEmpName(String empName) {
        this.empName = empName == null ? null : empName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_score.fiveScore
     *
     * @return the value of t_score.fiveScore
     *
     * @mbg.generated
     */
    public BigDecimal getFiveScore() {
        return fiveScore;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_score.fiveScore
     *
     * @param fiveScore the value for t_score.fiveScore
     *
     * @mbg.generated
     */
    public void setFiveScore(BigDecimal fiveScore) {
        this.fiveScore = fiveScore;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_score.coordinationScore
     *
     * @return the value of t_score.coordinationScore
     *
     * @mbg.generated
     */
    public BigDecimal getCoordinationScore() {
        return coordinationScore;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_score.coordinationScore
     *
     * @param coordinationScore the value for t_score.coordinationScore
     *
     * @mbg.generated
     */
    public void setCoordinationScore(BigDecimal coordinationScore) {
        this.coordinationScore = coordinationScore;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_score.qualityScore
     *
     * @return the value of t_score.qualityScore
     *
     * @mbg.generated
     */
    public BigDecimal getQualityScore() {
        return qualityScore;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_score.qualityScore
     *
     * @param qualityScore the value for t_score.qualityScore
     *
     * @mbg.generated
     */
    public void setQualityScore(BigDecimal qualityScore) {
        this.qualityScore = qualityScore;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_score.score
     *
     * @return the value of t_score.score
     *
     * @mbg.generated
     */
    public BigDecimal getScore() {
        return score;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_score.score
     *
     * @param score the value for t_score.score
     *
     * @mbg.generated
     */
    public void setScore(BigDecimal score) {
        this.score = score;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_score.isDelete
     *
     * @return the value of t_score.isDelete
     *
     * @mbg.generated
     */
    public Integer getIsDelete() {
        return isDelete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_score.isDelete
     *
     * @param isDelete the value for t_score.isDelete
     *
     * @mbg.generated
     */
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_score.checkworkScore
     *
     * @return the value of t_score.checkworkScore
     *
     * @mbg.generated
     */
    public BigDecimal getCheckworkScore() {
        return checkworkScore;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_score.checkworkScore
     *
     * @param checkworkScore the value for t_score.checkworkScore
     *
     * @mbg.generated
     */
    public void setCheckworkScore(BigDecimal checkworkScore) {
        this.checkworkScore = checkworkScore;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_score
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
        Score other = (Score) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getYearMonth() == null ? other.getYearMonth() == null : this.getYearMonth().equals(other.getYearMonth()))
            && (this.getEmpId() == null ? other.getEmpId() == null : this.getEmpId().equals(other.getEmpId()))
            && (this.getEmpName() == null ? other.getEmpName() == null : this.getEmpName().equals(other.getEmpName()))
            && (this.getFiveScore() == null ? other.getFiveScore() == null : this.getFiveScore().equals(other.getFiveScore()))
            && (this.getCoordinationScore() == null ? other.getCoordinationScore() == null : this.getCoordinationScore().equals(other.getCoordinationScore()))
            && (this.getQualityScore() == null ? other.getQualityScore() == null : this.getQualityScore().equals(other.getQualityScore()))
            && (this.getScore() == null ? other.getScore() == null : this.getScore().equals(other.getScore()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()))
            && (this.getCheckworkScore() == null ? other.getCheckworkScore() == null : this.getCheckworkScore().equals(other.getCheckworkScore()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_score
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
        result = prime * result + ((getFiveScore() == null) ? 0 : getFiveScore().hashCode());
        result = prime * result + ((getCoordinationScore() == null) ? 0 : getCoordinationScore().hashCode());
        result = prime * result + ((getQualityScore() == null) ? 0 : getQualityScore().hashCode());
        result = prime * result + ((getScore() == null) ? 0 : getScore().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        result = prime * result + ((getCheckworkScore() == null) ? 0 : getCheckworkScore().hashCode());
        return result;
    }
}