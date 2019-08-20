package com.btjf.service.order;

import com.btjf.mapper.order.ProductionProcedureConfirmMapper;
import com.btjf.mapper.order.ProductionProcedureScanMapper;
import com.btjf.model.order.ProductionProcedureConfirm;
import com.btjf.service.productpm.ProductProcedureService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by liuyq on 2019/8/18.
 */

@Service
@Transactional(readOnly = false, rollbackFor = Exception.class)
public class ProductionProcedureConfirmService {

    @Resource
    private ProductionProcedureConfirmMapper productionProcedureConfirmMapper;

    @Resource
    private ProductionProcedureScanMapper productionProcedureScanMapper;

    @Resource
    private ProductionOrderService productionOrderService;

    @Resource
    private ProductionLuoService productionLuoService;

    @Resource
    private ProductProcedureService productProcedureService;

    public List<ProductionProcedureConfirm> select(ProductionProcedureConfirm productionProcedureConfirm) {
        if (productionProcedureConfirm == null) return null;
        return productionProcedureConfirmMapper.select(productionProcedureConfirm);
    }
}
