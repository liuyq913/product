package com.btjf.model.order;

import java.io.Serializable;
import java.util.Date;

public class ProductionOrder implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_production_order.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_production_order.productionNo
     *
     * @mbg.generated
     */
    private String productionNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_production_order.orderId
     *
     * @mbg.generated
     */
    private Integer orderId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_production_order.orderNo
     *
     * @mbg.generated
     */
    private String orderNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_production_order.productNo
     *
     * @mbg.generated
     */
    private String productNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_production_order.maxNum
     *
     * @mbg.generated
     */
    private Integer maxNum;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_production_order.assignNum
     *
     * @mbg.generated
     */
    private Integer assignNum;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_production_order.workshop
     *
     * @mbg.generated
     */
    private String workshop;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_production_order.workshopDirector
     *
     * @mbg.generated
     */
    private String workshopDirector;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_production_order.isLuo
     *
     * @mbg.generated
     */
    private Integer isLuo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_production_order.luoNum
     *
     * @mbg.generated
     */
    private Integer luoNum;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_production_order.printer
     *
     * @mbg.generated
     */
    private String printer;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_production_order.printTime
     *
     * @mbg.generated
     */
    private Date printTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_production_order.printCount
     *
     * @mbg.generated
     */
    private Integer printCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_production_order.createTime
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_production_order.lastModifyTime
     *
     * @mbg.generated
     */
    private Date lastModifyTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_production_order.isDelete
     *
     * @mbg.generated
     */
    private Integer isDelete;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_production_order.codeUrl
     *
     * @mbg.generated
     */
    private String codeUrl;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_production_order
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_production_order.id
     *
     * @return the value of t_production_order.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_production_order.id
     *
     * @param id the value for t_production_order.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_production_order.productionNo
     *
     * @return the value of t_production_order.productionNo
     *
     * @mbg.generated
     */
    public String getProductionNo() {
        return productionNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_production_order.productionNo
     *
     * @param productionNo the value for t_production_order.productionNo
     *
     * @mbg.generated
     */
    public void setProductionNo(String productionNo) {
        this.productionNo = productionNo == null ? null : productionNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_production_order.orderId
     *
     * @return the value of t_production_order.orderId
     *
     * @mbg.generated
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_production_order.orderId
     *
     * @param orderId the value for t_production_order.orderId
     *
     * @mbg.generated
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_production_order.orderNo
     *
     * @return the value of t_production_order.orderNo
     *
     * @mbg.generated
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_production_order.orderNo
     *
     * @param orderNo the value for t_production_order.orderNo
     *
     * @mbg.generated
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_production_order.productNo
     *
     * @return the value of t_production_order.productNo
     *
     * @mbg.generated
     */
    public String getProductNo() {
        return productNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_production_order.productNo
     *
     * @param productNo the value for t_production_order.productNo
     *
     * @mbg.generated
     */
    public void setProductNo(String productNo) {
        this.productNo = productNo == null ? null : productNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_production_order.maxNum
     *
     * @return the value of t_production_order.maxNum
     *
     * @mbg.generated
     */
    public Integer getMaxNum() {
        return maxNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_production_order.maxNum
     *
     * @param maxNum the value for t_production_order.maxNum
     *
     * @mbg.generated
     */
    public void setMaxNum(Integer maxNum) {
        this.maxNum = maxNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_production_order.assignNum
     *
     * @return the value of t_production_order.assignNum
     *
     * @mbg.generated
     */
    public Integer getAssignNum() {
        return assignNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_production_order.assignNum
     *
     * @param assignNum the value for t_production_order.assignNum
     *
     * @mbg.generated
     */
    public void setAssignNum(Integer assignNum) {
        this.assignNum = assignNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_production_order.workshop
     *
     * @return the value of t_production_order.workshop
     *
     * @mbg.generated
     */
    public String getWorkshop() {
        return workshop;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_production_order.workshop
     *
     * @param workshop the value for t_production_order.workshop
     *
     * @mbg.generated
     */
    public void setWorkshop(String workshop) {
        this.workshop = workshop == null ? null : workshop.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_production_order.workshopDirector
     *
     * @return the value of t_production_order.workshopDirector
     *
     * @mbg.generated
     */
    public String getWorkshopDirector() {
        return workshopDirector;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_production_order.workshopDirector
     *
     * @param workshopDirector the value for t_production_order.workshopDirector
     *
     * @mbg.generated
     */
    public void setWorkshopDirector(String workshopDirector) {
        this.workshopDirector = workshopDirector == null ? null : workshopDirector.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_production_order.isLuo
     *
     * @return the value of t_production_order.isLuo
     *
     * @mbg.generated
     */
    public Integer getIsLuo() {
        return isLuo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_production_order.isLuo
     *
     * @param isLuo the value for t_production_order.isLuo
     *
     * @mbg.generated
     */
    public void setIsLuo(Integer isLuo) {
        this.isLuo = isLuo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_production_order.luoNum
     *
     * @return the value of t_production_order.luoNum
     *
     * @mbg.generated
     */
    public Integer getLuoNum() {
        return luoNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_production_order.luoNum
     *
     * @param luoNum the value for t_production_order.luoNum
     *
     * @mbg.generated
     */
    public void setLuoNum(Integer luoNum) {
        this.luoNum = luoNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_production_order.printer
     *
     * @return the value of t_production_order.printer
     *
     * @mbg.generated
     */
    public String getPrinter() {
        return printer;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_production_order.printer
     *
     * @param printer the value for t_production_order.printer
     *
     * @mbg.generated
     */
    public void setPrinter(String printer) {
        this.printer = printer == null ? null : printer.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_production_order.printTime
     *
     * @return the value of t_production_order.printTime
     *
     * @mbg.generated
     */
    public Date getPrintTime() {
        return printTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_production_order.printTime
     *
     * @param printTime the value for t_production_order.printTime
     *
     * @mbg.generated
     */
    public void setPrintTime(Date printTime) {
        this.printTime = printTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_production_order.printCount
     *
     * @return the value of t_production_order.printCount
     *
     * @mbg.generated
     */
    public Integer getPrintCount() {
        return printCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_production_order.printCount
     *
     * @param printCount the value for t_production_order.printCount
     *
     * @mbg.generated
     */
    public void setPrintCount(Integer printCount) {
        this.printCount = printCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_production_order.createTime
     *
     * @return the value of t_production_order.createTime
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_production_order.createTime
     *
     * @param createTime the value for t_production_order.createTime
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_production_order.lastModifyTime
     *
     * @return the value of t_production_order.lastModifyTime
     *
     * @mbg.generated
     */
    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_production_order.lastModifyTime
     *
     * @param lastModifyTime the value for t_production_order.lastModifyTime
     *
     * @mbg.generated
     */
    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_production_order.isDelete
     *
     * @return the value of t_production_order.isDelete
     *
     * @mbg.generated
     */
    public Integer getIsDelete() {
        return isDelete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_production_order.isDelete
     *
     * @param isDelete the value for t_production_order.isDelete
     *
     * @mbg.generated
     */
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_production_order.codeUrl
     *
     * @return the value of t_production_order.codeUrl
     *
     * @mbg.generated
     */
    public String getCodeUrl() {
        return codeUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_production_order.codeUrl
     *
     * @param codeUrl the value for t_production_order.codeUrl
     *
     * @mbg.generated
     */
    public void setCodeUrl(String codeUrl) {
        this.codeUrl = codeUrl == null ? null : codeUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_production_order
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
        ProductionOrder other = (ProductionOrder) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getProductionNo() == null ? other.getProductionNo() == null : this.getProductionNo().equals(other.getProductionNo()))
            && (this.getOrderId() == null ? other.getOrderId() == null : this.getOrderId().equals(other.getOrderId()))
            && (this.getOrderNo() == null ? other.getOrderNo() == null : this.getOrderNo().equals(other.getOrderNo()))
            && (this.getProductNo() == null ? other.getProductNo() == null : this.getProductNo().equals(other.getProductNo()))
            && (this.getMaxNum() == null ? other.getMaxNum() == null : this.getMaxNum().equals(other.getMaxNum()))
            && (this.getAssignNum() == null ? other.getAssignNum() == null : this.getAssignNum().equals(other.getAssignNum()))
            && (this.getWorkshop() == null ? other.getWorkshop() == null : this.getWorkshop().equals(other.getWorkshop()))
            && (this.getWorkshopDirector() == null ? other.getWorkshopDirector() == null : this.getWorkshopDirector().equals(other.getWorkshopDirector()))
            && (this.getIsLuo() == null ? other.getIsLuo() == null : this.getIsLuo().equals(other.getIsLuo()))
            && (this.getLuoNum() == null ? other.getLuoNum() == null : this.getLuoNum().equals(other.getLuoNum()))
            && (this.getPrinter() == null ? other.getPrinter() == null : this.getPrinter().equals(other.getPrinter()))
            && (this.getPrintTime() == null ? other.getPrintTime() == null : this.getPrintTime().equals(other.getPrintTime()))
            && (this.getPrintCount() == null ? other.getPrintCount() == null : this.getPrintCount().equals(other.getPrintCount()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getLastModifyTime() == null ? other.getLastModifyTime() == null : this.getLastModifyTime().equals(other.getLastModifyTime()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()))
            && (this.getCodeUrl() == null ? other.getCodeUrl() == null : this.getCodeUrl().equals(other.getCodeUrl()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_production_order
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
        result = prime * result + ((getMaxNum() == null) ? 0 : getMaxNum().hashCode());
        result = prime * result + ((getAssignNum() == null) ? 0 : getAssignNum().hashCode());
        result = prime * result + ((getWorkshop() == null) ? 0 : getWorkshop().hashCode());
        result = prime * result + ((getWorkshopDirector() == null) ? 0 : getWorkshopDirector().hashCode());
        result = prime * result + ((getIsLuo() == null) ? 0 : getIsLuo().hashCode());
        result = prime * result + ((getLuoNum() == null) ? 0 : getLuoNum().hashCode());
        result = prime * result + ((getPrinter() == null) ? 0 : getPrinter().hashCode());
        result = prime * result + ((getPrintTime() == null) ? 0 : getPrintTime().hashCode());
        result = prime * result + ((getPrintCount() == null) ? 0 : getPrintCount().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getLastModifyTime() == null) ? 0 : getLastModifyTime().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        result = prime * result + ((getCodeUrl() == null) ? 0 : getCodeUrl().hashCode());
        return result;
    }
}