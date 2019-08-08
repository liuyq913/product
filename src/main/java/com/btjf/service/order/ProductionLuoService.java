package com.btjf.service.order;

import com.btjf.mapper.order.ProductionLuoMapper;
import com.btjf.model.order.ProductionLuo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by liuyq on 2019/8/8.
 * 分萝
 */

@Transactional(readOnly = false, rollbackFor = Exception.class)
@Service
public class ProductionLuoService {

    @Resource
    private ProductionLuoMapper productionLuoMapper;


    public Integer insert(ProductionLuo productionLuo) {
        if (productionLuo == null) return 0;
        productionLuoMapper.insertSelective(productionLuo);
        return productionLuo.getId();
    }

}
