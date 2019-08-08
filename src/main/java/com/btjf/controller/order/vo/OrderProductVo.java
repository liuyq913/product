package com.btjf.controller.order.vo;

import com.btjf.model.order.Order;
import com.btjf.model.order.OrderProduct;

import java.io.Serializable;
import java.util.List;

/**
 * Created by liuyq on 2019/8/8.
 */
public class OrderProductVo implements Serializable {

    private Integer orderId;

    private String orderNo;

    private List<OrderProduct> products;

    private static final long serialVersionUID = 1L;

    public OrderProductVo(Order order, List<OrderProduct> orderProducts) {
        if (order != null) {
            this.orderId = order.getId();
            this.orderNo = order.getOrderNo();
            products = orderProducts;
        }
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public List<OrderProduct> getProducts() {
        return products;
    }

    public void setProducts(List<OrderProduct> products) {
        this.products = products;
    }
}
