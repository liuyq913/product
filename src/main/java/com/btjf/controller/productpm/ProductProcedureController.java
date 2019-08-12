package com.btjf.controller.productpm;

import com.btjf.application.util.XaResult;
import com.btjf.constant.WorkShopProductionMapEnum;
import com.btjf.controller.base.ProductBaseController;
import com.btjf.model.product.ProductProcedure;
import com.btjf.model.sys.SysUser;
import com.btjf.service.productpm.ProductProcedureService;
import com.btjf.service.productpm.ProductService;
import com.heige.aikajinrong.base.exception.BusinessException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by liuyq on 2019/8/5.
 *
 * 产品 工序 价格
 */
@RestController
@RequestMapping(value = "/productpmprocedure")
public class ProductProcedureController extends ProductBaseController {

    @Resource
    private ProductProcedureService productProcedureService;
    @Resource
    private ProductService productProcedure;


    @RequestMapping(value = "/updateoradd", method = RequestMethod.POST)
    public XaResult<Integer> add(Integer id , String productNo, Double price, String procedureName, Integer sort) throws BusinessException {
        SysUser sysUser = getLoginUser();

        if (productNo == null || price == null || procedureName == null || sort == null) return XaResult.error("请填写完整信息");


        if(null == productProcedure.getByNO(productNo)){
            return XaResult.error("型号不存在");
        }

        if(null == WorkShopProductionMapEnum.get(sort)) return XaResult.error("请输入正确的序号");

        ProductProcedure productProcedure = new ProductProcedure();
        productProcedure.setCreateTime(new Date());
        productProcedure.setProductNo(productNo);
        productProcedure.setPrice(BigDecimal.valueOf(price));
        productProcedure.setIsDelete(0);
        productProcedure.setSort(sort);
        productProcedure.setLastModifyTime(new Date());
        productProcedure.setProcedureName(procedureName);
        productProcedure.setOperator(sysUser.getUserName());
        productProcedure.setId(id);

        return XaResult.success(productProcedureService.addOrUpdate(productProcedure));
    }
}
