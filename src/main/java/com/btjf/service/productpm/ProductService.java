package com.btjf.service.productpm;

import com.btjf.mapper.product.ProductMapper;
import com.btjf.model.product.Product;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by liuyq on 2019/8/6.
 */
@Service
public class ProductService {

    @Resource
    private ProductMapper productMapper;

    public Product getByNO(String productNo) {
       return  productMapper.getByNo(productNo);
    }

}
