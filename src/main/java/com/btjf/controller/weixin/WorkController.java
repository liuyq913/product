package com.btjf.controller.weixin;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.btjf.application.util.XaResult;
import com.btjf.business.common.exception.BusinessException;
import com.btjf.common.utils.DateUtil;
import com.btjf.controller.base.ProductBaseController;
import com.btjf.controller.order.vo.WorkShopVo;
import com.btjf.controller.weixin.vo.WorkListVo;
import com.btjf.controller.weixin.vo.WxEmpVo;
import com.btjf.model.order.OrderProduct;
import com.btjf.model.order.ProductionLuo;
import com.btjf.model.order.ProductionOrder;
import com.btjf.model.order.ProductionProcedureConfirm;
import com.btjf.model.pm.PmOutBill;
import com.btjf.service.order.*;
import com.btjf.service.pm.PmOutService;
import com.btjf.service.productpm.ProductWorkshopService;
import com.google.common.collect.Maps;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiParam;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

    @Resource
    private ProductionProcedureConfirmService productionProcedureConfirmService;

    @Resource
    private ProductionProcedureScanService productionProcedureScanService;

    @Resource
    private ProductionLuoService productionLuoService;

    @Resource
    private OrderProductService orderProductService;

    @RequestMapping(value = "getConfirmList", method = RequestMethod.GET)
    public XaResult<WorkListVo> getConfigList(@ApiParam("订单id") Integer orderId, @ApiParam("订单编号") String orderNo,
                                              @ApiParam("产品编号") String productNo, @ApiParam("生产单编号") String productionNo,
                                              @ApiParam("罗id") Integer louId, @ApiParam("领料单编号") String billNo) throws BusinessException {
        WxEmpVo wxEmpVo = getWxLoginUser();
        if (orderId == null || orderNo == null || productNo == null) return XaResult.error("无效二维码");

        WorkListVo workListVo = new WorkListVo();
        workListVo.setOrderId(orderId);
        workListVo.setOrderNo(orderNo);
        workListVo.setProductNo(productNo);
        String deptName = wxEmpVo.getDeptName();

        OrderProduct orderProduct = orderProductService.getByID(orderId);
        if (orderProduct == null) return XaResult.error("订单不存在");
        Integer assignNum = 0;
        String unit = orderProduct.getUnit();
        //生产单
        if (!StringUtils.isEmpty(productionNo)) {
            ProductionOrder productionOrder = productionOrderService.getByNo(productionNo);
            if (productionOrder == null) {
                LOGGER.info(wxEmpVo.getName() + "扫码生产单:" + productionNo + "没有您所需处理的工序。(如有疑问，请咨询客服");
                return XaResult.error("没有您所需处理的工序。(如有疑问，请咨询客服)");
            }
            if (!deptName.equals(productionOrder.getWorkshop())) {
                LOGGER.info(wxEmpVo.getName() + "扫码生产单:" + productionNo + "无效的二维码");
                return XaResult.error("无效的二维码");
            }
            assignNum = productionOrder.getAssignNum();
            List<WorkShopVo.Procedure> list = productionProcedureService.getConfigProcedure(null, productionNo);
            if (louId != null) {
                ProductionLuo productionLuo = productionLuoService.getById(louId);
                if (productionLuo != null) {
                    assignNum = productionLuo.getNum();
                }
            }
            workListVo.setLouId(louId);
            workListVo.setProcedures(list);
            workListVo.setProductionNo(productionNo);
            //领料单
        } else if (!StringUtils.isEmpty(billNo)) {
            PmOutBill bill = pmOutService.getByBillNo(billNo);
            if (bill == null) {
                LOGGER.info(wxEmpVo.getName() + "扫码领料单:" + billNo + "没有您所需处理的工序。(如有疑问，请咨询客服");
                return XaResult.error("没有您所需处理的工序。(如有疑问，请咨询客服)");
            }
            if (!deptName.equals(bill.getWorkshop())) {
                LOGGER.info(wxEmpVo.getName() + "扫码领料单:" + billNo + "无效的二维码");
                return XaResult.error("无效的二维码");
            }
            assignNum = bill.getDistributionNum();
            List<WorkShopVo.Procedure> list = productWorkshopService.getBySort(Arrays.asList(0, 1, 2, 3));
            workListVo.setProcedures(list);
            workListVo.setBillNo(billNo);
        } else {
            return XaResult.error("没有您所需处理的工序。(如有疑问，请咨询客服)");
        }
        XaResult result = XaResult.success(workListVo);
        if (wxEmpVo.getWorkName().equals("检验")) {
            Map map = Maps.newHashMap();
            map.put("assignNum", assignNum);
            map.put("unit", unit);
            result.setMap(map);
        }
        return result;
    }

    @RequestMapping(value = "/checkConfirm", method = RequestMethod.GET)
    public XaResult<String> checkConfirm(@ApiParam("订单id") Integer orderId, @ApiParam("订单编号") String orderNo,
                                         @ApiParam("产品编号") String productNo, @ApiParam("生产单编号") String productionNo,
                                         @ApiParam("罗id") Integer louId, @ApiParam("领料单编号") String billOutNo, String proceduresJosn) {
        if (orderId == null || orderNo == null || productNo == null || StringUtils.isEmpty(proceduresJosn))
            return XaResult.error("orderId，orderNo， productNo，proceduresJosn必填");

        List<WorkShopVo.Procedure> procedures = JSONObject.parseArray(proceduresJosn, WorkShopVo.Procedure.class);
        if ((StringUtils.isEmpty(productionNo) && StringUtils.isEmpty(billOutNo)) || (!StringUtils.isEmpty(productionNo) && !StringUtils.isEmpty(billOutNo)))
            throw new com.btjf.business.common.exception.BusinessException("生成单和领料单不能存在，且不能同时未空");
        StringBuffer stringBuffer = new StringBuffer();

        for (WorkShopVo.Procedure procedure : procedures) {
            if (procedure == null) continue;
            if (!CollectionUtils.isEmpty(productionProcedureScanService.select(orderNo, productNo, productionNo, louId, billOutNo, procedure.getProcedureId()))) {
                stringBuffer.append(procedure.getProcedureName() + "、");
            }
        }
        if (StringUtils.isEmpty(stringBuffer)) return XaResult.success();
        String result = stringBuffer.toString();
        if (result.length() - 1 == result.lastIndexOf("、")) {
            result = result.substring(0, result.length() - 1);
        }
        return XaResult.success(result);
    }


    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @RequestMapping(value = "confirm", method = RequestMethod.POST)
    public XaResult confirm(@ApiParam("订单id") Integer orderId, @ApiParam("订单编号") String orderNo,
                            @ApiParam("产品编号") String productNo, @ApiParam("生产单编号") String productionNo,
                            @ApiParam("罗id") Integer louId, @ApiParam("领料单编号") String billOutNo, String proceduresJosn) throws BusinessException {

        if (orderId == null || orderNo == null || productNo == null || StringUtils.isEmpty(proceduresJosn))
            return XaResult.error("orderId，orderNo， productNo，proceduresJosn必填");

        List<WorkShopVo.Procedure> procedures = JSONObject.parseArray(proceduresJosn, WorkShopVo.Procedure.class);

        WxEmpVo wxEmpVo = getWxLoginUser();
        if(wxEmpVo.getWorkName().equals("检验")){
            return XaResult.error("您无权限确认工序");
        }
        //生产单
        if (!StringUtils.isEmpty(productionNo)) {
            try {
                checkComfig(orderNo, productNo, productionNo, louId, billOutNo, procedures);
            } catch (BusinessException e) {
                LOGGER.info(wxEmpVo.getName() + "扫码生成单:" + productionNo + "无效的二维码");
                throw new BusinessException("生成单：" + productionNo + "中的" + e.getMessage());
            }
            //领料单
        } else if (!StringUtils.isEmpty(billOutNo)) {
            try {
                checkComfig(orderNo, productNo, productionNo, louId, billOutNo, procedures);
            } catch (BusinessException e) {
                throw new BusinessException("领料单：" + billOutNo + "中的" + e.getMessage());
            }
        }
        Integer num = productionProcedureScanService.deleteAndInsert(orderNo, productNo, productionNo, louId, billOutNo, procedures, wxEmpVo);

        return XaResult.success(num);
    }


    public void checkComfig(String orderNo, String productNo, String productionNo,
                            Integer louId, String billOutNo, List<WorkShopVo.Procedure> procedures) throws BusinessException {

        ProductionProcedureConfirm productionProcedureConfirm = new ProductionProcedureConfirm();
        productionProcedureConfirm.setOrderNo(orderNo);
        productionProcedureConfirm.setProductNo(productNo);
        productionProcedureConfirm.setType(1);
        if (!StringUtils.isEmpty(productionNo)) {
            productionProcedureConfirm.setProductionNo(productionNo);
            if (louId != null) {
                productionProcedureConfirm.setLuoId(louId);
            }
        }
        if (!StringUtils.isEmpty(billOutNo)) {
            productionProcedureConfirm.setPmOutBillNo(billOutNo);
        }

        for (WorkShopVo.Procedure procedure : procedures) {
            productionProcedureConfirm.setProcedureId(procedure.getProcedureId());
            productionProcedureConfirm.setIsChange(1);
            //是否调整
            if (!CollectionUtils.isEmpty(productionProcedureConfirmService.select(productionProcedureConfirm))) {
                throw new BusinessException("工序：(" + procedure.getProcedureName() + ")车间主任已经调整过了，无法再确认该工序");
            }
            //未调整   工序上个月最新一条有质检信息
            productionProcedureConfirm.setCreateTime(DateUtil.string2Date(DateUtil.dateToString(new Date(), DateUtil.ymdFormat), DateUtil.ymdFormat));
            productionProcedureConfirm.setIsChange(0);
            if (productionProcedureScanService.selectLastMonthIsPass(productionProcedureConfirm)) {
                throw new BusinessException("工序：(" + procedure.getProcedureName() + ")上月已经质检过，无法再确认该工序");
            }
        }

    }

    @RequestMapping(value = "inspectionConfirm", method = RequestMethod.POST)
    public XaResult inspectionConfig(@ApiParam("订单id") Integer orderId, @ApiParam("订单编号") String orderNo,
                                     @ApiParam("产品编号") String productNo, @ApiParam("生产单编号") String productionNo,
                                     @ApiParam("罗id") Integer louId, @ApiParam("领料单编号") String billOutNo) throws BusinessException {

        WxEmpVo wxEmpVo = getWxLoginUser();

        if (!wxEmpVo.getWorkName().equals("检验")) {
            return XaResult.error("身份错误");
        }

        if (orderId == null || orderNo == null || productNo == null)
            return XaResult.error("orderId，orderNo， productNo，必填");

        if (productionNo == null && billOutNo == null) return XaResult.error("生产单号和领料单号不能同时为空");
        if (productionNo != null && billOutNo != null) return XaResult.error("生产单号和领料单号不能同时存在");

        if(productionNo != null){
            ProductionOrder productionOrder =  productionOrderService.getByNo(productionNo);
            if(productionOrder == null) return XaResult.error("生产单号不存在");
            if(!wxEmpVo.getDeptName().equals(productionOrder.getWorkshop())) return XaResult.error("您无法质检不属于自己部门的单子");
        }

        if(billOutNo != null){
            PmOutBill pmOutBill =  pmOutService.getByBillNo(billOutNo);
            if(pmOutBill == null) return XaResult.error("生产单不存在");
            if(!wxEmpVo.getDeptName().equals(pmOutBill.getWorkshop())) return XaResult.error("您无法质检不属于自己部门的单子");
        }


        ProductionProcedureConfirm productionProcedureConfirm = new ProductionProcedureConfirm();
        productionProcedureConfirm.setOrderNo(orderNo);
        productionProcedureConfirm.setProductNo(productNo);
        productionProcedureConfirm.setType(1);
        productionProcedureConfirm.setIsChange(1);
        productionProcedureConfirm.setIsDelete(0);
        if (!StringUtils.isEmpty(productionNo)) {
            productionProcedureConfirm.setProductionNo(productionNo);
            if (louId != null) {
                productionProcedureConfirm.setLuoId(louId);
            }
        }
        if (!StringUtils.isEmpty(billOutNo)) {
            productionProcedureConfirm.setPmOutBillNo(billOutNo);
        }
        if (!CollectionUtils.isEmpty(productionProcedureConfirmService.select(productionProcedureConfirm))) {
            return XaResult.error("车间主任已经调整过了，无法再质检");
        }
        Integer row = productionProcedureConfirmService.add(orderId, orderNo, louId, billOutNo, productNo, productionNo, wxEmpVo);
        return XaResult.success(row);
    }
}
