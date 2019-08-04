package com.btjf.service.customer;

import com.btjf.common.page.Page;
import com.btjf.mapper.customer.CustomerMapper;
import com.btjf.model.customer.Customer;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by liuyq on 2019/8/4.
 */
@Service
public class CustomerService {
    @Resource
    private CustomerMapper customerMapper;

    public Page<Customer> findListPage(String name, String startDate, String endDate,  Page page) {
        PageHelper.startPage(page.getPage(), page.getRp());
        List<Customer> pmList = customerMapper.findList(name, startDate, endDate);
        PageInfo pageInfo = new PageInfo(pmList);
        pageInfo.setList(pmList);
        return new Page<>(pageInfo);
    }

    public Customer getByID(Integer id) {
        if(id == null) return null;

        return customerMapper.selectByPrimaryKey(id);
    }
}
