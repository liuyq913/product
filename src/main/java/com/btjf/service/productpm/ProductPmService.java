package com.btjf.service.productpm;

import com.btjf.common.page.Page;
import com.btjf.mapper.product.ProductpmMapper;
import com.btjf.model.product.Productpm;
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
    private ProductpmMapper productpmMapper;


    public Page<Productpm> findListPage(String productNo, String pmNo, int status, Page page) {
        PageHelper.startPage(page.getPage(), page.getRp());
        List<Productpm> productpms = productpmMapper.findList(productNo, pmNo, status);
        PageInfo pageInfo = new PageInfo(productpms);
        pageInfo.setList(productpms);
        return new Page<>(pageInfo);
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Integer updateStatue(List<Integer> ids){
        try {
            ids.stream().forEach(t ->{
                Productpm productpm = new Productpm();
                productpm.setId(t);
                productpm.setLastModifyTime(new Date());
                productpm.setStatus(1);
                productpmMapper.updateByPrimaryKey(productpm);
            });
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
        return ids.size();
    }



    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Integer delete(List<Integer> ids){
        try {
            ids.stream().forEach(t ->{
                Productpm productpm = new Productpm();
                productpm.setId(t);
                productpm.setLastModifyTime(new Date());
                productpm.setIsDelete(1);
                productpmMapper.updateByPrimaryKey(productpm);
            });
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
        return ids.size();
    }

    public Productpm getByID(Integer id) {
        return productpmMapper.selectByPrimaryKey(id);
    }
}
