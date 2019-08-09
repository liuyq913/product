package com.btjf.service.productpm;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.btjf.common.page.Page;
import com.btjf.controller.productpm.vo.ProductWorkShopVo;
import com.btjf.mapper.product.ProductMapper;
import com.btjf.mapper.product.ProductProcedureWorkshopMapper;
import com.btjf.model.product.Product;
import com.btjf.model.product.ProductProcedureWorkshop;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by liuyq on 2019/8/5.
 * 产品工序车间
 */
@Service
public class ProductWorkshopService {

    @Resource
    private ProductProcedureWorkshopMapper productProcedureWorkshopMapper;
    @Resource
    private ProductMapper productMapper;

    public Page<ProductWorkShopVo> getListPage(Page page, String type, String productNo) {
        PageHelper.startPage(page.getPage(), page.getRp());
        List<Product> pmList = productMapper.getList(type, productNo);
        List<ProductWorkShopVo> productWorkShopVos = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(pmList)) {
            pmList.stream().forEach(t -> {
                ProductWorkShopVo productWorkShopVo = new ProductWorkShopVo(t);
                productWorkShopVo.setAssist(productProcedureWorkshopMapper.getNumByWorkShopName("外协质检"));
                productWorkShopVo.setBlanking(productProcedureWorkshopMapper.getNumByWorkShopName("下料车间"));
                productWorkShopVo.setBlanking(productProcedureWorkshopMapper.getNumByWorkShopName("下料车间"));
            });
        }

        PageInfo pageInfo = new PageInfo(pmList);
        pageInfo.setList(pmList);

        return new Page<>(pageInfo);
    }


    public List<ProductProcedureWorkshop> findByWorkshopName(String name) {
        if (StringUtils.isEmpty(name)) return null;

        return productProcedureWorkshopMapper.findByWorkshopName(name);
    }

    public List<ProductProcedureWorkshop> getWorkShop(String productNo) {
        return productProcedureWorkshopMapper.getWorkShop(productNo);
    }
}
