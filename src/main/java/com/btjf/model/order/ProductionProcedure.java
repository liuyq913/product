package com.btjf.model.order;

import java.io.Serializable;
import java.util.Date;

public class ProductionProcedure implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_production_procedure.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_production_procedure.productionNo
     *
     * @mbg.generated
     */
    private String productionNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_production_procedure.orderId
     *
     * @mbg.generated
     */
    private Integer orderId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_production_procedure.orderNo
     *
     * @mbg.generated
     */
    private String orderNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_production_procedure.productNo
     *
     * @mbg.generated
     */
    private String productNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_production_procedure.procedureId
     *
     * @mbg.generated
     */
    private Integer procedureId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_production_procedure.procedureName
     *
     * @mbg.generated
     */
    private String procedureName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_production_procedure.createTime
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_production_procedure.isDelete
     *
     * @mbg.generated
     */
    private Integer isDelete;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_production_procedure
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_production_procedure.id
     *
     * @return the value of t_production_procedure.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_production_procedure.id
     *
     * @param id the value for t_production_procedure.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_production_procedure.productionNo
     *
     * @return the value of t_production_procedure.productionNo
     *
     * @mbg.generated
     */
    public String getProductionNo() {
        return productionNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_production_procedure.productionNo
     *
     * @param productionNo the value for t_production_procedure.productionNo
     *
     * @mbg.generated
     */
    public void setProductionNo(String productionNo) {
        this.productionNo = productionNo == null ? null : productionNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_production_procedure.orderId
     *
     * @return the value of t_production_procedure.orderId
     *
     * @mbg.generated
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_production_procedure.orderId
     *
     * @param orderId the value for t_production_procedure.orderId
     *
     * @mbg.generated
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_production_procedure.orderNo
     *
     * @return the value of t_production_procedure.orderNo
     *
     * @mbg.generated
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_production_procedure.orderNo
     *
     * @param orderNo the value for t_production_procedure.orderNo
     *
     * @mbg.generated
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_production_procedure.productNo
     *
     * @return the value of t_production_procedure.productNo
     *
     * @mbg.generated
     */
    public String getProductNo() {
        return productNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_production_procedure.productNo
     *
     * @param productNo the value for t_production_procedure.productNo
     *
     * @mbg.generated
     */
    public void setProductNo(String productNo) {
        this.productNo = productNo == null ? null : productNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_production_procedure.procedureId
     *
     * @return the value of t_production_procedure.procedureId
     *
     * @mbg.generated
     */
    public Integer getProcedureId() {
        return procedureId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_production_procedure.procedureId
     *
     * @param procedureId the value for t_production_procedure.procedureId
     *
     * @mbg.generated
     */
    public void setProcedureId(Integer procedureId) {
        this.procedureId = procedureId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_production_procedure.procedureName
     *
     * @return the value of t_production_procedure.procedureName
     *
     * @mbg.generated
     */
    public String getProcedureName() {
        return procedureName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_production_procedure.procedureName
     *
     * @param procedureName the value for t_production_procedure.procedureName
     *
     * @mbg.generated
     */
    public void setProcedureName(String procedureName) {
        this.procedureName = procedureName == null ? null : procedureName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_production_procedure.createTime
     *
     * @return the value of t_production_procedure.createTime
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_production_procedure.createTime
     *
     * @param createTime the value for t_production_procedure.createTime
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_production_procedure.isDelete
     *
     * @return the value of t_production_procedure.isDelete
     *
     * @mbg.generated
     */
    public Integer getIsDelete() {
        return isDelete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_production_procedure.isDelete
     *
     * @param isDelete the value for t_production_procedure.isDelete
     *
     * @mbg.generated
     */
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_production_procedure
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
        ProductionProcedure other = (ProductionProcedure) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getProductionNo() == null ? other.getProductionNo() == null : this.getProductionNo().equals(other.getProductionNo()))
            && (this.getOrderId() == null ? other.getOrderId() == null : this.getOrderId().equals(other.getOrderId()))
            && (this.getOrderNo() == null ? other.getOrderNo() == null : this.getOrderNo().equals(other.getOrderNo()))
            && (this.getProductNo() == null ? other.getProductNo() == null : this.getProductNo().equals(other.getProductNo()))
            && (this.getProcedureId() == null ? other.getProcedureId() == null : this.getProcedureId().equals(other.getProcedureId()))
            && (this.getProcedureName() == null ? other.getProcedureName() == null : this.getProcedureName().equals(other.getProcedureName()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_production_procedure
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getProductionNo() == null) ? 0 : getProductionNo().hashCode());
        result = prime * result + ((getOrderId() == null) ? 0 : getOrderId().hashCode());
        result = prime * result + ((getOrderNo() == null) ? 0 : getOrderNo().hashCode());
        result = prime * result + ((getProductNo() == null) ? 0 : getProductNo().hashCode());
        result = prime * result + ((getProcedureId() == null) ? 0 : getProcedureId().hashCode());
        result = prime * result + ((getProcedureName() == null) ? 0 : getProcedureName().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        return result;
    }
}