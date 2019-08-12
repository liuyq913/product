package com.btjf.service.productpm;

import com.btjf.constant.WorkShopProductionMapEnum;
import com.btjf.mapper.product.ProductProcedureMapper;
import com.btjf.mapper.product.ProductProcedureWorkshopMapper;
import com.btjf.model.product.ProductProcedure;
import com.btjf.model.product.ProductProcedureWorkshop;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by liuyq on 2019/8/5.
 * 产品工序
 */
@Service
public class ProductProcedureService {

    @Resource
    private ProductProcedureMapper productProcedureMapper;

    @Resource
    private ProductProcedureWorkshopMapper productProcedureWorkshopMapper;


    public Integer addOrUpdate(ProductProcedure productProcedure) {
        if(productProcedure.getId() == null){
            productProcedureMapper.insertSelective(productProcedure);
        }else{
            productProcedureWorkshopMapper.deleteByProcedureId(productProcedure.getId());
        }
        productProcedureWorkshopMapper.insertSelective(build(productProcedure));
        return productProcedure.getId();
    }


    public ProductProcedureWorkshop build(ProductProcedure productProcedure) {
        ProductProcedureWorkshop productProcedureWorkshop = new ProductProcedureWorkshop();
        WorkShopProductionMapEnum workShopProductionMapEnum = WorkShopProductionMapEnum.get(productProcedure.getSort());
        productProcedureWorkshop.setOperator("系统");
        productProcedureWorkshop.setIsDelete(0);
        productProcedureWorkshop.setCreateTime(new Date());
        productProcedureWorkshop.setWorkshop(workShopProductionMapEnum.getContent());
        productProcedureWorkshop.setProcedureName(productProcedure.getProcedureName());
        productProcedureWorkshop.setProcedureId(productProcedure.getId());
        productProcedureWorkshop.setLastModifyTime(new Date());
        productProcedureWorkshop.setPrice(productProcedure.getPrice());
        productProcedureWorkshop.setProductId(productProcedure.getProductId());
        productProcedureWorkshop.setProductNo(productProcedure.getProductNo());
        productProcedureWorkshop.setSort(productProcedure.getSort());
        return productProcedureWorkshop;
    }

    public ProductProcedure getById(Integer id){
        return productProcedureMapper.selectByPrimaryKey(id);
    }

    public Integer update(ProductProcedure productProcedure){
        return productProcedureMapper.updateByPrimaryKeySelective(productProcedure);
    }
}
