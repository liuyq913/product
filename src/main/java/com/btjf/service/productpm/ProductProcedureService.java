package com.btjf.service.productpm;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.btjf.common.page.Page;
import com.btjf.common.utils.BeanUtil;
import com.btjf.constant.WorkShopProductionMapEnum;
import com.btjf.controller.order.vo.WorkShopVo;
import com.btjf.mapper.product.ProductProcedureMapper;
import com.btjf.mapper.product.ProductProcedureWorkshopMapper;
import com.btjf.model.product.Product;
import com.btjf.model.product.ProductProcedure;
import com.btjf.model.product.ProductProcedureWorkshop;
import com.btjf.model.sys.SysUser;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

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

    @Resource
    private ProductService productService;


    public Integer addOrUpdate(ProductProcedure productProcedure) {

        if (productProcedure.getId() == null) {
            productProcedureMapper.insertSelective(productProcedure);
        } else {
            productProcedureWorkshopMapper.deleteByProcedureId(productProcedure.getId());
            productProcedureMapper.updateByPrimaryKeySelective(productProcedure);
        }
        productProcedureWorkshopMapper.insertSelective(build(productProcedure));
        return productProcedure.getId();
    }

    public Integer add(ProductProcedure productProcedure) {
        productProcedureMapper.insertSelective(productProcedure);
        return productProcedure.getId();
    }


    public ProductProcedureWorkshop build(ProductProcedure productProcedure) {
        ProductProcedureWorkshop productProcedureWorkshop = new ProductProcedureWorkshop();
        WorkShopProductionMapEnum workShopProductionMapEnum = WorkShopProductionMapEnum.get(productProcedure.getSort());
        productProcedureWorkshop.setOperator("系统");
        productProcedureWorkshop.setIsDelete(0);
        productProcedureWorkshop.setCreateTime(new Date());
        productProcedureWorkshop.setWorkshop(workShopProductionMapEnum == null ? null : workShopProductionMapEnum.getContent());
        productProcedureWorkshop.setProcedureName(productProcedure.getProcedureName());
        productProcedureWorkshop.setProcedureId(productProcedure.getId());
        productProcedureWorkshop.setLastModifyTime(new Date());
        productProcedureWorkshop.setPrice(productProcedure.getPrice());
        productProcedureWorkshop.setProductId(productProcedure.getProductId());
        productProcedureWorkshop.setProductNo(productProcedure.getProductNo());
        productProcedureWorkshop.setSort(productProcedure.getSort());
        return productProcedureWorkshop;
    }

    public ProductProcedure getById(Integer id) {
        return productProcedureMapper.selectByPrimaryKey(id);
    }

    public Integer update(ProductProcedure productProcedure) {
        return productProcedureMapper.updateByPrimaryKeySelective(productProcedure);
    }

    public List<WorkShopVo.Procedure> getByWorkShopAndProductNo(String workShop, String productNo) {
        List<ProductProcedureWorkshop> productProcedures = productProcedureWorkshopMapper.getByWorkShopAndProductNo(workShop, productNo);
        return BeanUtil.convertList(productProcedures, WorkShopVo.Procedure.class);
    }

    public synchronized Integer getSort() {
        return productProcedureMapper.getMaxSort() + 1;
    }

    public Page<ProductProcedure> listPage(String productNo, String procedureName, String price, Page page) {
        PageHelper.startPage(page.getPage(), page.getRp());
        List<ProductProcedure> productProcedures = productProcedureMapper.findList(procedureName, price, productNo);
        PageInfo pageInfo = new PageInfo(productProcedures);
        return new Page<>(pageInfo);
    }

    public List<ProductProcedure> list(String productNo, String procedureName, String price) {
        List<ProductProcedure> productProcedures = productProcedureMapper.findList(procedureName, price, productNo);
        return productProcedures;
    }

    public Integer sameProductNoAdd(String oldProductNo, String newProduct, SysUser sysUser) {
        Product product = productService.getByNO(newProduct);
        List<ProductProcedure> productProcedures = productProcedureMapper.getByProductNo(oldProductNo);
        if (!CollectionUtils.isEmpty(productProcedures)) {
            for (ProductProcedure productProcedure : productProcedures){
                if(productProcedure == null) continue;
                List<ProductProcedureWorkshop>  productProcedureWorkshops = productProcedureWorkshopMapper.getWorkShop(productProcedure.getProductNo(), productProcedure.getId());
                if(CollectionUtils.isEmpty(productProcedureWorkshops)) continue;
                ProductProcedureWorkshop productProcedureWorkshop = productProcedureWorkshops.get(0);

                //新增工序
                productProcedure.setOperator(sysUser.getUserName());
                productProcedure.setCreateTime(new Date());
                productProcedure.setLastModifyTime(new Date());
                productProcedure.setProductNo(newProduct);
                productProcedure.setProductId(product.getId());
                Integer productProcedureId = this.add(productProcedure);

                //新增型号工序
                productProcedureWorkshop.setProcedureId(productProcedureId);
                productProcedureWorkshop.setCreateTime(new Date());
                productProcedureWorkshop.setOperator(sysUser.getUserName());
                productProcedureWorkshop.setProductId(product.getId());
                productProcedureWorkshop.setProductNo(newProduct);
                productProcedureWorkshop.setLastModifyTime(new Date());

                productProcedureWorkshopMapper.insertSelective(productProcedureWorkshop);
            }
        }
        return productProcedures.size();
    }
}
