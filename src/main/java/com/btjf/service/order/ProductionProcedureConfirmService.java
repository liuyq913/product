package com.btjf.service.order;

import com.btjf.mapper.order.ProductionProcedureConfirmMapper;
import com.btjf.model.order.Order;
import com.btjf.model.order.ProductionProcedureConfirm;
import com.btjf.model.product.ProductProcedure;
import com.btjf.vo.weixin.EmpProcedureDetailVo;
import com.btjf.vo.weixin.EmpProcedureListVo;
import com.btjf.vo.weixin.OrderProductVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuyq on 2019/8/18.
 */
@Service
public class ProductionProcedureConfirmService {

    @Resource
    private ProductionProcedureConfirmMapper productionProcedureConfirmMapper;

    public List<Order> getOrderByMouth(String date) {
        return productionProcedureConfirmMapper.getOrderByMouth(date);
    }

    public List<OrderProductVo> getOrderProductByMouth(String orderNo) {
        return productionProcedureConfirmMapper.getOrderProductByMouth(orderNo);
    }

    public List<EmpProcedureListVo> getEmpNum(String orderNo, String productNo) {
        List<ProductProcedure> list =
                productionProcedureConfirmMapper.getByOrderAndProduct(orderNo, productNo);
        List<EmpProcedureListVo> volist = null;
        if(list != null && list.size() >0){
            volist = new ArrayList<>();
            for (int i=0; i<list.size(); i++){
                EmpProcedureListVo vo = new EmpProcedureListVo();
                vo.setId(list.get(i).getId());
                vo.setName(list.get(i).getProcedureName());
                vo.setSort(list.get(i).getSort());
                List<EmpProcedureDetailVo> dlist = productionProcedureConfirmMapper.getEmpNum(orderNo,productNo,vo.getId());
                vo.setList(dlist);
                volist.add(vo);
            }
        }

        return volist;
    }
}
