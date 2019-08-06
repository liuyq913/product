package com.btjf.service.order;

import com.btjf.mapper.order.OrderProductMapper;
import com.btjf.model.order.OrderProduct;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by liuyq on 2019/8/4.
 */
@Service
public class OrderProductService {

    @Resource
    private OrderProductMapper mapper;

    public OrderProduct findByOrderNoAndProductNo(String orderNo, String productNo) {
        return mapper.findByOrderNoAndProductNo(orderNo,productNo);
    }
}
