package com.btjf.service.productpm;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.btjf.common.page.Page;
import com.btjf.controller.productpm.vo.ProductWorkShopVo;
import com.btjf.mapper.product.ProductMapper;
import com.btjf.mapper.product.ProductProcedureWorkshopMapper;
import com.btjf.model.product.Product;
import com.btjf.model.product.ProductProcedure;
import com.btjf.model.product.ProductProcedureWorkshop;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
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
    @Resource
    private ProductProcedureService productProcedureService;

    public Page<ProductWorkShopVo> getListPage(Page page, String type, String productNo) {
        PageHelper.startPage(page.getPage(), page.getRp());
        List<Product> pmList = productMapper.getList(type, productNo);
        List<ProductWorkShopVo> productWorkShopVos = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(pmList)) {
            pmList.stream().forEach(t -> {
                ProductWorkShopVo productWorkShopVo = new ProductWorkShopVo(t);
                productWorkShopVo.setAssist(productProcedureWorkshopMapper.getNumByWorkShopName("外协质检"));
                productWorkShopVo.setBlanking(productProcedureWorkshopMapper.getNumByWorkShopName("下料车间"));
                productWorkShopVo.setGroundFloor(productProcedureWorkshopMapper.getNumByWorkShopName("一车间"));
                productWorkShopVo.setBackAssist(productProcedureWorkshopMapper.getNumByWorkShopName("后道车间-车工"));
                productWorkShopVo.setBackCenterAssist(productProcedureWorkshopMapper.getNumByWorkShopName("后道车间-中辅工"));
                productWorkShopVo.setBackBigAssist(productProcedureWorkshopMapper.getNumByWorkShopName("后道车间-大辅工"));
                productWorkShopVo.setInspection(productProcedureWorkshopMapper.getNumByWorkShopName("质检部-成品质检"));
                productWorkShopVo.setPacking(productProcedureWorkshopMapper.getNumByWorkShopName("包装车间"));
                productWorkShopVo.setProductNo(t.getProductNo());
                productWorkShopVo.setType(t.getType());
                productWorkShopVos.add(productWorkShopVo);
            });
        }

        PageInfo pageInfo = new PageInfo(productWorkShopVos);
        pageInfo.setList(productWorkShopVos);

        return new Page<>(pageInfo);
    }


    public List<ProductProcedureWorkshop> findByWorkshopName(String name) {
        if (StringUtils.isEmpty(name)) return null;

        return productProcedureWorkshopMapper.findByWorkshopName(name);
    }

    public List<ProductProcedureWorkshop> getWorkShop(String productNo) {
        return productProcedureWorkshopMapper.getWorkShop(productNo);
    }

    public ProductProcedureWorkshop getById(Integer id) {
        return productProcedureWorkshopMapper.selectByPrimaryKey(id);
    }

    public Integer add(ProductProcedureWorkshop productProcedureWorkshop) {
        ProductProcedure productProcedure = new ProductProcedure();
        productProcedure.setCreateTime(new Date());
        productProcedure.setIsDelete(0);
        productProcedure.setOperator(productProcedureWorkshop.getOperator());
        productProcedure.setLastModifyTime(new Date());
        productProcedure.setProcedureName(productProcedureWorkshop.getProcedureName());
        productProcedure.setProductNo(productProcedureWorkshop.getProductNo());
        productProcedure.setPrice(productProcedureWorkshop.getPrice());
        productProcedure.setSort(productProcedureWorkshop.getSort());
        Integer productId = productProcedureService.addOrUpdate(productProcedure);
        productProcedureWorkshop.setProcedureId(productId);
        return productProcedureWorkshopMapper.insertSelective(productProcedureWorkshop);
    }

    public Integer udpate(ProductProcedureWorkshop productProcedureWorkshop) {
        ProductProcedureWorkshop productProcedureWorkshop1 = productProcedureWorkshopMapper.selectByPrimaryKey(productProcedureWorkshop.getId());
        productProcedureWorkshopMapper.updateByPrimaryKeySelective(productProcedureWorkshop);
        ProductProcedure productProcedure = productProcedureService.getById(productProcedureWorkshop1.getProcedureId());
        if (productProcedure != null) {
            productProcedure.setSort(productProcedure.getSort());
            productProcedure.setPrice(productProcedure.getPrice());
            productProcedure.setLastModifyTime(productProcedure.getLastModifyTime());
            productProcedure.setProcedureName(productProcedureWorkshop.getProcedureName());
            productProcedureService.update(productProcedure);
        }

        return productProcedureWorkshop.getId();
    }
}
