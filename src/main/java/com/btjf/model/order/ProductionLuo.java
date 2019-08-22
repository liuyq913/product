package com.btjf.model.order;

import java.io.Serializable;
import java.util.Date;

public class ProductionLuo implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_production_luo.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_production_luo.productionNo
     *
     * @mbg.generated
     */
    private String productionNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_production_luo.orderId
     *
     * @mbg.generated
     */
    private Integer orderId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_production_luo.orderNo
     *
     * @mbg.generated
     */
    private String orderNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_production_luo.productNo
     *
     * @mbg.generated
     */
    private String productNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_production_luo.num
     *
     * @mbg.generated
     */
    private Integer num;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_production_luo.maxNum
     *
     * @mbg.generated
     */
    private Integer maxNum;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_production_luo.createTime
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_production_luo.isDelete
     *
     * @mbg.generated
     */
    private Integer isDelete;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_production_luo.codeUrl
     *
     * @mbg.generated
     */
    private String codeUrl;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_production_luo.sort
     *
     * @mbg.generated
     */
    private Integer sort;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_production_luo
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_production_luo.id
     *
     * @return the value of t_production_luo.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_production_luo.id
     *
     * @param id the value for t_production_luo.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_production_luo.productionNo
     *
     * @return the value of t_production_luo.productionNo
     *
     * @mbg.generated
     */
    public String getProductionNo() {
        return productionNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_production_luo.productionNo
     *
     * @param productionNo the value for t_production_luo.productionNo
     *
     * @mbg.generated
     */
    public void setProductionNo(String productionNo) {
        this.productionNo = productionNo == null ? null : productionNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_production_luo.orderId
     *
     * @return the value of t_production_luo.orderId
     *
     * @mbg.generated
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_production_luo.orderId
     *
     * @param orderId the value for t_production_luo.orderId
     *
     * @mbg.generated
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_production_luo.orderNo
     *
     * @return the value of t_production_luo.orderNo
     *
     * @mbg.generated
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_production_luo.orderNo
     *
     * @param orderNo the value for t_production_luo.orderNo
     *
     * @mbg.generated
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_production_luo.productNo
     *
     * @return the value of t_production_luo.productNo
     *
     * @mbg.generated
     */
    public String getProductNo() {
        return productNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_production_luo.productNo
     *
     * @param productNo the value for t_production_luo.productNo
     *
     * @mbg.generated
     */
    public void setProductNo(String productNo) {
        this.productNo = productNo == null ? null : productNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_production_luo.num
     *
     * @return the value of t_production_luo.num
     *
     * @mbg.generated
     */
    public Integer getNum() {
        return num;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_production_luo.num
     *
     * @param num the value for t_production_luo.num
     *
     * @mbg.generated
     */
    public void setNum(Integer num) {
        this.num = num;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_production_luo.maxNum
     *
     * @return the value of t_production_luo.maxNum
     *
     * @mbg.generated
     */
    public Integer getMaxNum() {
        return maxNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_production_luo.maxNum
     *
     * @param maxNum the value for t_production_luo.maxNum
     *
     * @mbg.generated
     */
    public void setMaxNum(Integer maxNum) {
        this.maxNum = maxNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_production_luo.createTime
     *
     * @return the value of t_production_luo.createTime
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_production_luo.createTime
     *
     * @param createTime the value for t_production_luo.createTime
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_production_luo.isDelete
     *
     * @return the value of t_production_luo.isDelete
     *
     * @mbg.generated
     */
    public Integer getIsDelete() {
        return isDelete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_production_luo.isDelete
     *
     * @param isDelete the value for t_production_luo.isDelete
     *
     * @mbg.generated
     */
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_production_luo.codeUrl
     *
     * @return the value of t_production_luo.codeUrl
     *
     * @mbg.generated
     */
    public String getCodeUrl() {
        return codeUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_production_luo.codeUrl
     *
     * @param codeUrl the value for t_production_luo.codeUrl
     *
     * @mbg.generated
     */
    public void setCodeUrl(String codeUrl) {
        this.codeUrl = codeUrl == null ? null : codeUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_production_luo.sort
     *
     * @return the value of t_production_luo.sort
     *
     * @mbg.generated
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_production_luo.sort
     *
     * @param sort the value for t_production_luo.sort
     *
     * @mbg.generated
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_production_luo
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
        ProductionLuo other = (ProductionLuo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getProductionNo() == null ? other.getProductionNo() == null : this.getProductionNo().equals(other.getProductionNo()))
            && (this.getOrderId() == null ? other.getOrderId() == null : this.getOrderId().equals(other.getOrderId()))
            && (this.getOrderNo() == null ? other.getOrderNo() == null : this.getOrderNo().equals(other.getOrderNo()))
            && (this.getProductNo() == null ? other.getProductNo() == null : this.getProductNo().equals(other.getProductNo()))
            && (this.getNum() == null ? other.getNum() == null : this.getNum().equals(other.getNum()))
            && (this.getMaxNum() == null ? other.getMaxNum() == null : this.getMaxNum().equals(other.getMaxNum()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()))
            && (this.getCodeUrl() == null ? other.getCodeUrl() == null : this.getCodeUrl().equals(other.getCodeUrl()))
            && (this.getSort() == null ? other.getSort() == null : this.getSort().equals(other.getSort()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_production_luo
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
        result = prime * result + ((getNum() == null) ? 0 : getNum().hashCode());
        result = prime * result + ((getMaxNum() == null) ? 0 : getMaxNum().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        result = prime * result + ((getCodeUrl() == null) ? 0 : getCodeUrl().hashCode());
        result = prime * result + ((getSort() == null) ? 0 : getSort().hashCode());
        return result;
    }
}