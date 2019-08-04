package com.btjf.vo;

import java.util.List;

public class PmOutStockDetailListVo {

    private String orderNo;

    private String productNo;

    private List<PmOutStockDetailVo> list;

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

    public List<PmOutStockDetailVo> getList() {
        return list;
    }

    public void setList(List<PmOutStockDetailVo> list) {
        this.list = list;
    }
}
