package com.btjf.controller.weixin.vo;

import com.btjf.controller.order.vo.WorkShopVo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by liuyq on 2019/8/19.
 */
public class WorkListVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer orderID;

    private String productNo; //型号

    private String orderNo;//订单编号

    private Integer louId;//罗id

    //工序
    private List<WorkShopVo.Procedure> procedures;

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getLouId() {
        return louId;
    }

    public void setLouId(Integer louId) {
        this.louId = louId;
    }

    public List<WorkShopVo.Procedure> getProcedures() {
        return procedures;
    }

    public void setProcedures(List<WorkShopVo.Procedure> procedures) {
        this.procedures = procedures;
    }
}
