package com.btjf.service.productpm;

import com.btjf.common.page.Page;
import com.btjf.mapper.product.ProductPmMapper;
import com.btjf.model.product.ProductPm;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by liuyq on 2019/7/29.
 */
@Service
public class ProductPmService {

    @Resource
    private ProductPmMapper productpmMapper;


    public Page<ProductPm> findListPage(String productNo, String pmNo, Integer status, Page page) {
        PageHelper.startPage(page.getPage(), page.getRp());
        List<ProductPm> productpms = productpmMapper.findList(productNo, pmNo, status);
        PageInfo pageInfo = new PageInfo(productpms);
        pageInfo.setList(productpms);
        return new Page<>(pageInfo);
    }

    public List<ProductPm> findList(String productNo, String pmNo, int status) {
        List<ProductPm> productpms = productpmMapper.findList(productNo, pmNo, status);
        return productpms;
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Integer updateStatue(List<Integer> ids) {
        try {
            ids.stream().forEach(t -> {
                ProductPm productpm = new ProductPm();
                productpm.setId(t);
                productpm.setLastModifyTime(new Date());
                productpm.setStatus(1);
                productpmMapper.updateByPrimaryKeySelective(productpm);
            });
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return ids.size();
    }


    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Integer delete(List<Integer> ids) {
        try {
            ids.stream().forEach(t -> {
                ProductPm productpm = new ProductPm();
                productpm.setId(t);
                productpm.setLastModifyTime(new Date());
                productpm.setIsDelete(1);
                productpmMapper.updateByPrimaryKeySelective(productpm);
            });
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return ids.size();
    }

    public ProductPm getByID(Integer id) {
        return productpmMapper.selectByPrimaryKey(id);
    }

    public ProductPm getByNo(String productNo) {
        return productpmMapper.selectByNo(productNo);
    }

    public Integer update(ProductPm productPm) {
        return productpmMapper.updateByPrimaryKeySelective(productPm);
    }

    public Integer add(ProductPm productPm) {
        productpmMapper.insertSelective(productPm);
        return productPm.getId();
    }


    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Integer addBySameModel(String oldModel, String newModel) {
        List<ProductPm> productPms = productpmMapper.findListByProductNo(oldModel);
        Integer row = 0;
        if (productPms != null && productPms.size() > 0) {
            productPms.stream().filter(t -> t != null).forEach(t -> {
                t.setProductNo(newModel);
                t.setId(null);
                productpmMapper.insertSelective(t);
            });
            row = productPms.size();
        }
        return row;
    }
}
