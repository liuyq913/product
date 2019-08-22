package com.btjf.vo.weixin;

import java.util.List;

public class EmpDayWorkDetailVo {

    private String orderNo;
    private String productNo;
    private String billNo;
    private String statusDesc;
    private List<ProcedureInfoVo> procedureInfoVoList;


    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public List<ProcedureInfoVo> getProcedureInfoVoList() {
        return procedureInfoVoList;
    }

    public void setProcedureInfoVoList(List<ProcedureInfoVo> procedureInfoVoList) {
        this.procedureInfoVoList = procedureInfoVoList;
    }
}
