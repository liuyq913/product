package com.btjf.service.productpm;

import com.btjf.mapper.product.ProductMapper;
import com.btjf.model.product.Product;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * Created by liuyq on 2019/8/6.
 */
@Service
public class ProductService {

    @Resource
    private ProductMapper productMapper;

    public Product getByNO(String productNo) {
        return productMapper.getByNo(productNo);
    }

    public Integer add(Product product) {
        return Optional.ofNullable(product).map(v -> {
            productMapper.insertSelective(product);
            return product.getId();
        }).orElse(0);
    }

    public Integer update(Product product) {
        return Optional.ofNullable(product).map(v -> {
            return productMapper.updateByPrimaryKeySelective(product);
        }).orElse(0);
    }
}
