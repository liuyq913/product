package com.btjf.model.pm;

import java.io.Serializable;
import java.util.Date;

public class Pm implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pm.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pm.pmNo
     *
     * @mbg.generated
     */
    private String pmNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pm.name
     *
     * @mbg.generated
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pm.type
     *
     * @mbg.generated
     */
    private String type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pm.num
     *
     * @mbg.generated
     */
    private Integer num;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pm.unit
     *
     * @mbg.generated
     */
    private String unit;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pm.remark
     *
     * @mbg.generated
     */
    private String remark;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pm.operator
     *
     * @mbg.generated
     */
    private String operator;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pm.createTime
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pm.lastModifyTime
     *
     * @mbg.generated
     */
    private Date lastModifyTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pm.isDelete
     *
     * @mbg.generated
     */
    private Integer isDelete;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pm.colour
     *
     * @mbg.generated
     */
    private String colour;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pm.norms
     *
     * @mbg.generated
     */
    private String norms;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pm.material
     *
     * @mbg.generated
     */
    private String material;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pm.call
     *
     * @mbg.generated
     */
    private String call;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_pm
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pm.id
     *
     * @return the value of t_pm.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pm.id
     *
     * @param id the value for t_pm.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pm.pmNo
     *
     * @return the value of t_pm.pmNo
     *
     * @mbg.generated
     */
    public String getPmNo() {
        return pmNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pm.pmNo
     *
     * @param pmNo the value for t_pm.pmNo
     *
     * @mbg.generated
     */
    public void setPmNo(String pmNo) {
        this.pmNo = pmNo == null ? null : pmNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pm.name
     *
     * @return the value of t_pm.name
     *
     * @mbg.generated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pm.name
     *
     * @param name the value for t_pm.name
     *
     * @mbg.generated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pm.type
     *
     * @return the value of t_pm.type
     *
     * @mbg.generated
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pm.type
     *
     * @param type the value for t_pm.type
     *
     * @mbg.generated
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pm.num
     *
     * @return the value of t_pm.num
     *
     * @mbg.generated
     */
    public Integer getNum() {
        return num;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pm.num
     *
     * @param num the value for t_pm.num
     *
     * @mbg.generated
     */
    public void setNum(Integer num) {
        this.num = num;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pm.unit
     *
     * @return the value of t_pm.unit
     *
     * @mbg.generated
     */
    public String getUnit() {
        return unit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pm.unit
     *
     * @param unit the value for t_pm.unit
     *
     * @mbg.generated
     */
    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pm.remark
     *
     * @return the value of t_pm.remark
     *
     * @mbg.generated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pm.remark
     *
     * @param remark the value for t_pm.remark
     *
     * @mbg.generated
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pm.operator
     *
     * @return the value of t_pm.operator
     *
     * @mbg.generated
     */
    public String getOperator() {
        return operator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pm.operator
     *
     * @param operator the value for t_pm.operator
     *
     * @mbg.generated
     */
    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pm.createTime
     *
     * @return the value of t_pm.createTime
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pm.createTime
     *
     * @param createTime the value for t_pm.createTime
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pm.lastModifyTime
     *
     * @return the value of t_pm.lastModifyTime
     *
     * @mbg.generated
     */
    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pm.lastModifyTime
     *
     * @param lastModifyTime the value for t_pm.lastModifyTime
     *
     * @mbg.generated
     */
    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pm.isDelete
     *
     * @return the value of t_pm.isDelete
     *
     * @mbg.generated
     */
    public Integer getIsDelete() {
        return isDelete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pm.isDelete
     *
     * @param isDelete the value for t_pm.isDelete
     *
     * @mbg.generated
     */
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pm.colour
     *
     * @return the value of t_pm.colour
     *
     * @mbg.generated
     */
    public String getColour() {
        return colour;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pm.colour
     *
     * @param colour the value for t_pm.colour
     *
     * @mbg.generated
     */
    public void setColour(String colour) {
        this.colour = colour == null ? null : colour.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pm.norms
     *
     * @return the value of t_pm.norms
     *
     * @mbg.generated
     */
    public String getNorms() {
        return norms;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pm.norms
     *
     * @param norms the value for t_pm.norms
     *
     * @mbg.generated
     */
    public void setNorms(String norms) {
        this.norms = norms == null ? null : norms.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pm.material
     *
     * @return the value of t_pm.material
     *
     * @mbg.generated
     */
    public String getMaterial() {
        return material;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pm.material
     *
     * @param material the value for t_pm.material
     *
     * @mbg.generated
     */
    public void setMaterial(String material) {
        this.material = material == null ? null : material.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pm.call
     *
     * @return the value of t_pm.call
     *
     * @mbg.generated
     */
    public String getCall() {
        return call;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pm.call
     *
     * @param call the value for t_pm.call
     *
     * @mbg.generated
     */
    public void setCall(String call) {
        this.call = call == null ? null : call.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pm
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
        Pm other = (Pm) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getPmNo() == null ? other.getPmNo() == null : this.getPmNo().equals(other.getPmNo()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getNum() == null ? other.getNum() == null : this.getNum().equals(other.getNum()))
            && (this.getUnit() == null ? other.getUnit() == null : this.getUnit().equals(other.getUnit()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getOperator() == null ? other.getOperator() == null : this.getOperator().equals(other.getOperator()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getLastModifyTime() == null ? other.getLastModifyTime() == null : this.getLastModifyTime().equals(other.getLastModifyTime()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()))
            && (this.getColour() == null ? other.getColour() == null : this.getColour().equals(other.getColour()))
            && (this.getNorms() == null ? other.getNorms() == null : this.getNorms().equals(other.getNorms()))
            && (this.getMaterial() == null ? other.getMaterial() == null : this.getMaterial().equals(other.getMaterial()))
            && (this.getCall() == null ? other.getCall() == null : this.getCall().equals(other.getCall()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pm
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPmNo() == null) ? 0 : getPmNo().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getNum() == null) ? 0 : getNum().hashCode());
        result = prime * result + ((getUnit() == null) ? 0 : getUnit().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getOperator() == null) ? 0 : getOperator().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getLastModifyTime() == null) ? 0 : getLastModifyTime().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        result = prime * result + ((getColour() == null) ? 0 : getColour().hashCode());
        result = prime * result + ((getNorms() == null) ? 0 : getNorms().hashCode());
        result = prime * result + ((getMaterial() == null) ? 0 : getMaterial().hashCode());
        result = prime * result + ((getCall() == null) ? 0 : getCall().hashCode());
        return result;
    }
}