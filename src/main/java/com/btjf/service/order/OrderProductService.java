package com.btjf.service.order;

import com.btjf.common.page.Page;
import com.btjf.controller.order.vo.OrderVo;
import com.btjf.mapper.order.OrderProductMapper;
import com.btjf.model.order.OrderProduct;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by liuyq on 2019/8/4.
 */
@Transactional(readOnly = false, rollbackFor = Exception.class)
@Service
public class OrderProductService {

    @Resource
    private OrderProductMapper orderProductMapper;

    public OrderProduct getByOrderNoAndProductNo(String orderNo, String productNo) {
        return orderProductMapper.getByOrderNoAndProductNo(orderNo, productNo);
    }

    public Integer insert(OrderProduct orderProduct) {
        orderProductMapper.insertSelective(orderProduct);
        return orderProduct.getId();
    }

    public Page<OrderVo> listPage(Integer customerId, String orderNo, String pmNo, String type, String completeStartDate, String completeStartEnd, String createStartDate, String createEndDate, Page page) {

        PageHelper.startPage(page.getPage(), page.getRp());
        List<OrderVo> orderVos = orderProductMapper.findList(customerId, orderNo, pmNo, type, completeStartDate, completeStartEnd, createStartDate, createEndDate);
        PageInfo pageInfo = new PageInfo(orderVos);
        pageInfo.setList(orderVos);
        return new Page<>(pageInfo);
    }

    public OrderProduct getByID(Integer ID) {
        return orderProductMapper.selectByPrimaryKey(ID);
    }

    public Integer deleteById(Integer Id) {
        return orderProductMapper.deleteById(Id);
    }

    public List<OrderProduct> findByOrderId(Integer orderId) {
        return orderProductMapper.findByOrderID(orderId);
    }

    public Integer update(OrderProduct orderProduct1) {
        if (null == orderProduct1) return 0;
        return orderProductMapper.updateByPrimaryKeySelective(orderProduct1);
    }
}
