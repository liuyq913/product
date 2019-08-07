package com.btjf.service.order;

import com.btjf.mapper.order.OrderMapper;
import com.btjf.model.order.Order;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by liuyq on 2019/8/4.
 */
@Service
public class OrderService {
    @Resource
    private OrderMapper orderMapper;

    public Order getByNo(String orderNo) {
        return orderMapper.getByNo(orderNo);
    }

    public Integer insert(Order order) {
        if(null == order) return 0;
        orderMapper.insertSelective(order);
        return order.getId();
    }
}
