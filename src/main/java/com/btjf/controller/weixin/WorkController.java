package com.btjf.controller.weixin;

import com.alibaba.druid.util.StringUtils;
import com.btjf.application.util.XaResult;
import com.btjf.controller.base.ProductBaseController;
import com.btjf.controller.order.vo.WorkShopVo;
import com.btjf.controller.weixin.vo.WorkListVo;
import com.btjf.controller.weixin.vo.WxEmpVo;
import com.btjf.model.order.ProductionOrder;
import com.btjf.model.pm.PmOutBill;
import com.btjf.service.order.ProductionOrderService;
import com.btjf.service.order.ProductionProcedureService;
import com.btjf.service.pm.PmOutService;
import com.btjf.service.productpm.ProductWorkshopService;
import com.heige.aikajinrong.base.exception.BusinessException;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiParam;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * Created by liuyq on 2019/8/19.
 */
@RestController
@Api(value = "WorkController", description = "小程序 工作模块", position = 1)
@RequestMapping(value = "/wx/work")
public class WorkController extends ProductBaseController {

    private static final Logger LOGGER = Logger
            .getLogger(WorkController.class);

    @Resource
    private ProductWorkshopService productWorkshopService;

    @Resource
    private ProductionProcedureService productionProcedureService;

    @Resource
    private ProductionOrderService productionOrderService;

    @Resource
    private PmOutService pmOutService;

    @RequestMapping(value = "getConfigList", method = RequestMethod.GET)
    public XaResult<WorkListVo> getConfigList(@ApiParam("订单id") Integer orderId, @ApiParam("订单编号") String orderNo,
                                              @ApiParam("产品编号") String productNo, @ApiParam("生产单编号") String productionNo,
                                              @ApiParam("罗id") Integer louId, @ApiParam("领料单编号") String billNo) throws BusinessException {
        WxEmpVo wxEmpVo = getWxLoginUser();
        if(orderId == null || orderNo == null || productNo == null) return XaResult.error("无效二维码");

        WorkListVo workListVo = new WorkListVo();
        workListVo.setOrderID(orderId);
        workListVo.setOrderNo(orderNo);
        workListVo.setProductNo(productNo);
        String deptName = wxEmpVo.getDeptName();
        //生产单
        if(!StringUtils.isEmpty(productionNo)){
            ProductionOrder productionOrder = productionOrderService.getByNo(productionNo);
            if(productionOrder == null) return XaResult.error("没有您所需处理的工序。(如有疑问，请咨询客服)");
            if(!deptName.equals(productionOrder.getWorkshop())) return XaResult.error("无效的二维码");
            List<WorkShopVo.Procedure> list = productionProcedureService.getConfigProcedure(deptName, productionNo);
            workListVo.setLouId(louId);
            workListVo.setProcedures(list);
         //领料单
        }else if(!StringUtils.isEmpty(billNo)){
            PmOutBill bill = pmOutService.getByBillNo(billNo);
            if(bill == null) return XaResult.error("没有您所需处理的工序。(如有疑问，请咨询客服)");
            if(!deptName.equals(bill.getWorkshop())) return XaResult.error("无效的二维码");
            List<WorkShopVo.Procedure> list = productWorkshopService.getBySort(Arrays.asList(0,1,2,3));
        }else{
            return XaResult.error("没有您所需处理的工序。(如有疑问，请咨询客服)");
        }
        return null;
    }
}
