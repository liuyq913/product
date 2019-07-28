package com.btjf.model.pm;

import java.io.Serializable;
import java.util.Date;

public class PmIn implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pmin.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pmin.pmId
     *
     * @mbg.generated
     */
    private Integer pmId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pmin.pmNo
     *
     * @mbg.generated
     */
    private String pmNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pmin.pmName
     *
     * @mbg.generated
     */
    private String pmName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pmin.type
     *
     * @mbg.generated
     */
    private String type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pmin.unit
     *
     * @mbg.generated
     */
    private String unit;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pmin.remark
     *
     * @mbg.generated
     */
    private String remark;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pmin.supplier
     *
     * @mbg.generated
     */
    private String supplier;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pmin.num
     *
     * @mbg.generated
     */
    private Integer num;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pmin.perNum
     *
     * @mbg.generated
     */
    private Integer perNum;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pmin.backNum
     *
     * @mbg.generated
     */
    private Integer backNum;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pmin.operator
     *
     * @mbg.generated
     */
    private String operator;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pmin.createTime
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pmin.lastModifyTime
     *
     * @mbg.generated
     */
    private Date lastModifyTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pmin.isDelete
     *
     * @mbg.generated
     */
    private Integer isDelete;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_pmin
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pmin.id
     *
     * @return the value of t_pmin.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pmin.id
     *
     * @param id the value for t_pmin.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pmin.pmId
     *
     * @return the value of t_pmin.pmId
     *
     * @mbg.generated
     */
    public Integer getPmId() {
        return pmId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pmin.pmId
     *
     * @param pmId the value for t_pmin.pmId
     *
     * @mbg.generated
     */
    public void setPmId(Integer pmId) {
        this.pmId = pmId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pmin.pmNo
     *
     * @return the value of t_pmin.pmNo
     *
     * @mbg.generated
     */
    public String getPmNo() {
        return pmNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pmin.pmNo
     *
     * @param pmNo the value for t_pmin.pmNo
     *
     * @mbg.generated
     */
    public void setPmNo(String pmNo) {
        this.pmNo = pmNo == null ? null : pmNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pmin.pmName
     *
     * @return the value of t_pmin.pmName
     *
     * @mbg.generated
     */
    public String getPmName() {
        return pmName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pmin.pmName
     *
     * @param pmName the value for t_pmin.pmName
     *
     * @mbg.generated
     */
    public void setPmName(String pmName) {
        this.pmName = pmName == null ? null : pmName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pmin.type
     *
     * @return the value of t_pmin.type
     *
     * @mbg.generated
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pmin.type
     *
     * @param type the value for t_pmin.type
     *
     * @mbg.generated
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pmin.unit
     *
     * @return the value of t_pmin.unit
     *
     * @mbg.generated
     */
    public String getUnit() {
        return unit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pmin.unit
     *
     * @param unit the value for t_pmin.unit
     *
     * @mbg.generated
     */
    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pmin.remark
     *
     * @return the value of t_pmin.remark
     *
     * @mbg.generated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pmin.remark
     *
     * @param remark the value for t_pmin.remark
     *
     * @mbg.generated
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pmin.supplier
     *
     * @return the value of t_pmin.supplier
     *
     * @mbg.generated
     */
    public String getSupplier() {
        return supplier;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pmin.supplier
     *
     * @param supplier the value for t_pmin.supplier
     *
     * @mbg.generated
     */
    public void setSupplier(String supplier) {
        this.supplier = supplier == null ? null : supplier.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pmin.num
     *
     * @return the value of t_pmin.num
     *
     * @mbg.generated
     */
    public Integer getNum() {
        return num;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pmin.num
     *
     * @param num the value for t_pmin.num
     *
     * @mbg.generated
     */
    public void setNum(Integer num) {
        this.num = num;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pmin.perNum
     *
     * @return the value of t_pmin.perNum
     *
     * @mbg.generated
     */
    public Integer getPerNum() {
        return perNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pmin.perNum
     *
     * @param perNum the value for t_pmin.perNum
     *
     * @mbg.generated
     */
    public void setPerNum(Integer perNum) {
        this.perNum = perNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pmin.backNum
     *
     * @return the value of t_pmin.backNum
     *
     * @mbg.generated
     */
    public Integer getBackNum() {
        return backNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pmin.backNum
     *
     * @param backNum the value for t_pmin.backNum
     *
     * @mbg.generated
     */
    public void setBackNum(Integer backNum) {
        this.backNum = backNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pmin.operator
     *
     * @return the value of t_pmin.operator
     *
     * @mbg.generated
     */
    public String getOperator() {
        return operator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pmin.operator
     *
     * @param operator the value for t_pmin.operator
     *
     * @mbg.generated
     */
    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pmin.createTime
     *
     * @return the value of t_pmin.createTime
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pmin.createTime
     *
     * @param createTime the value for t_pmin.createTime
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pmin.lastModifyTime
     *
     * @return the value of t_pmin.lastModifyTime
     *
     * @mbg.generated
     */
    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pmin.lastModifyTime
     *
     * @param lastModifyTime the value for t_pmin.lastModifyTime
     *
     * @mbg.generated
     */
    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pmin.isDelete
     *
     * @return the value of t_pmin.isDelete
     *
     * @mbg.generated
     */
    public Integer getIsDelete() {
        return isDelete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pmin.isDelete
     *
     * @param isDelete the value for t_pmin.isDelete
     *
     * @mbg.generated
     */
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pmin
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
        PmIn other = (PmIn) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getPmId() == null ? other.getPmId() == null : this.getPmId().equals(other.getPmId()))
            && (this.getPmNo() == null ? other.getPmNo() == null : this.getPmNo().equals(other.getPmNo()))
            && (this.getPmName() == null ? other.getPmName() == null : this.getPmName().equals(other.getPmName()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getUnit() == null ? other.getUnit() == null : this.getUnit().equals(other.getUnit()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getSupplier() == null ? other.getSupplier() == null : this.getSupplier().equals(other.getSupplier()))
            && (this.getNum() == null ? other.getNum() == null : this.getNum().equals(other.getNum()))
            && (this.getPerNum() == null ? other.getPerNum() == null : this.getPerNum().equals(other.getPerNum()))
            && (this.getBackNum() == null ? other.getBackNum() == null : this.getBackNum().equals(other.getBackNum()))
            && (this.getOperator() == null ? other.getOperator() == null : this.getOperator().equals(other.getOperator()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getLastModifyTime() == null ? other.getLastModifyTime() == null : this.getLastModifyTime().equals(other.getLastModifyTime()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pmin
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPmId() == null) ? 0 : getPmId().hashCode());
        result = prime * result + ((getPmNo() == null) ? 0 : getPmNo().hashCode());
        result = prime * result + ((getPmName() == null) ? 0 : getPmName().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getUnit() == null) ? 0 : getUnit().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getSupplier() == null) ? 0 : getSupplier().hashCode());
        result = prime * result + ((getNum() == null) ? 0 : getNum().hashCode());
        result = prime * result + ((getPerNum() == null) ? 0 : getPerNum().hashCode());
        result = prime * result + ((getBackNum() == null) ? 0 : getBackNum().hashCode());
        result = prime * result + ((getOperator() == null) ? 0 : getOperator().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getLastModifyTime() == null) ? 0 : getLastModifyTime().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        return result;
    }
}