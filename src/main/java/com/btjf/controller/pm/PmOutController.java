package com.btjf.controller.pm;

import com.btjf.application.components.xaresult.AppXaResultHelper;
import com.btjf.application.util.XaResult;
import com.btjf.common.page.Page;
import com.btjf.controller.base.ProductBaseController;
import com.btjf.model.order.OrderProduct;
import com.btjf.model.pm.PmOutBill;
import com.btjf.model.pm.PmOutBillDetail;
import com.btjf.model.product.ProductPm;
import com.btjf.service.order.OrderProductService;
import com.btjf.service.pm.PmOutService;
import com.btjf.service.productpm.ProductPmService;
import com.btjf.vo.*;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by liuyq on 2019/7/28.
 */
@RestController
@RequestMapping(value = "/pm/out")
@Api(value = "PmOutController", description = "出库", position = 1)
public class PmOutController extends ProductBaseController {

    private static final Logger LOGGER = Logger
            .getLogger(PmOutController.class);

    @Resource
    private OrderProductService orderProductService;
    @Resource
    private ProductPmService productPmService;
    @Resource
    private PmOutService pmOutService;


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public XaResult<List<PmInVo>> findList(@ApiParam("订单号") String orderNo, @ApiParam("型号") String productNo
            , @ApiParam("是否已录入") Integer isInput, Integer pageSize, Integer currentPage) {
        LOGGER.info(getRequestParamsAndUrl());
        if(currentPage == null || currentPage < 1){
            currentPage =1;
        }
        if(pageSize == null || pageSize < 1){
            pageSize = 25;
        }
        Page page = new Page(pageSize, currentPage);

        //是否确认   型号、耗料  是否确认

        return null;
    }

    /**
     *库存料明细
     * @param orderNo
     * @param productNo
     * @return
     */
    @RequestMapping(value = "/stock/detail", method = RequestMethod.GET)
    public XaResult<PmOutStockDetailListVo> stockDetail(@ApiParam("订单号") String orderNo, @ApiParam("型号") String productNo) {
        LOGGER.info(getRequestParamsAndUrl());
        if(StringUtils.isEmpty(orderNo)){
            XaResult.error("订单号不能为空");
        }
        if(StringUtils.isEmpty(productNo)){
            XaResult.error("型号不能为空");
        }
        //查询订单 获取订单 产品数量
        OrderProduct orderProduct = orderProductService.getByOrderNoAndProductNo(orderNo, productNo);

        PmOutStockDetailListVo vo = new PmOutStockDetailListVo();
        vo.setOrderNo(orderNo);
        vo.setProductNo(productNo);
        if(orderProduct != null){
            //查询 产品 耗料  获取所需耗料数量
            List<ProductPm> list = productPmService.findList(productNo, null, null);
            if (list != null && list.size() >0){
                List<PmOutStockDetailVo> plist = new ArrayList<>();
                for (int i=0; i< list.size(); i++){
                    PmOutStockDetailVo pVo = new PmOutStockDetailVo(orderProduct, list.get(i), Boolean.FALSE);
                    plist.add(pVo);
                }
                vo.setList(plist);
            }
        }else {
            return XaResult.error("订单不存在或者该订单"+ orderNo +"没有该型号" + productNo);
        }


        return XaResult.success(vo);

    }

    /**
     *每件/双耗料/需用料数量
     * @param orderNo
     * @param productNo
     * @return
     */
    @RequestMapping(value = "/productpm/detail", method = RequestMethod.GET)
    public XaResult<PmOutStockDetailListVo> pmDetail(@ApiParam("订单号") String orderNo,
                   @ApiParam("型号") String productNo, @ApiParam("类型") String type) {
        LOGGER.info(getRequestParamsAndUrl());
        if(StringUtils.isEmpty(orderNo)){
            XaResult.error("订单号不能为空");
        }
        if(StringUtils.isEmpty(productNo)){
            XaResult.error("型号不能为空");
        }
        //查询订单 获取订单 产品数量
        OrderProduct orderProduct = orderProductService.getByOrderNoAndProductNo(orderNo, productNo);
        PmOutStockDetailListVo vo = new PmOutStockDetailListVo();
        vo.setOrderNo(orderNo);
        vo.setProductNo(productNo);
        vo.setNum(orderProduct.getNum());
        if(orderProduct != null){
            //查询 产品 耗料  获取所需耗料数量
            List<ProductPm> list = productPmService.findListByProductNoAndType(productNo, type);
            if (list != null && list.size() >0){
                List<PmOutStockDetailVo> plist = new ArrayList<>();
                for (int i=0; i< list.size(); i++){
                    PmOutStockDetailVo pVo = new PmOutStockDetailVo(orderProduct, list.get(i), Boolean.TRUE);
                    plist.add(pVo);
                }
                vo.setList(plist);
            }
        }else {
            return XaResult.error("订单不存在或者该订单"+ orderNo +"没有该型号" + productNo);
        }

        return XaResult.success(vo);

    }

    /**
     * 领料单查询
     * @param billNo
     * @param orderNo
     * @param productNo
     * @param pageSize
     * @param currentPage
     * @return
     */
    @RequestMapping(value = "bill/list", method = RequestMethod.GET)
    public XaResult<List<PmOutBillListVo>> billList(@ApiParam("票据编号") String billNo,
                                                  @ApiParam("订单号") String orderNo, @ApiParam("型号") String productNo,
                                                  Integer pageSize, Integer currentPage) {
        LOGGER.info(getRequestParamsAndUrl());
        if(currentPage == null || currentPage < 1){
            currentPage =1;
        }
        if(pageSize == null || pageSize < 1){
            pageSize = 25;
        }
        Page page = new Page(pageSize, currentPage);
        Page<PmOutBillListVo> listPage = pmOutService.findListPage(billNo,orderNo,productNo,page);

        return AppXaResultHelper.success(listPage, listPage.getRows());
    }

    /**
     * 领料单详情
     * @param billNo
     * @return
     */
    @RequestMapping(value = "bill/detail", method = RequestMethod.GET)
    public XaResult<PmOutBillDetailVo> billDetail(@ApiParam("票据编号") String billNo) {
        LOGGER.info(getRequestParamsAndUrl());
        if(StringUtils.isEmpty(billNo)){
            XaResult.error("票据编号不能为空");
        }
        //总用量=订单型号数量*型号耗材数
        PmOutBill pmOutBill = pmOutService.getByBillNo(billNo);
        if(pmOutBill == null){
            XaResult.error("领料单不存在");
        }
        OrderProduct orderProduct = orderProductService.getByOrderNoAndProductNo(pmOutBill.getOrderNo(), pmOutBill.getProductNo());
        if(orderProduct == null){
            XaResult.error("订单不存在或者该订单"+ pmOutBill.getOrderNo() +"没有该型号" + pmOutBill.getProductNo());
        }
        List<PmOutBillDetail> list = pmOutService.getListDetailByBillId(pmOutBill.getId());
        List<ProductPm> pplist = productPmService.findListByProductNoAndType(pmOutBill.getProductNo(), null);
        PmOutBillDetailVo vo = new PmOutBillDetailVo(pmOutBill, orderProduct, list, pplist);

        return XaResult.success(vo);
    }

    /**
     * 计算核可领用
     * @param productNo
     * @param distributeNum
     * @return
     */
    @RequestMapping(value = "bill/calculate", method = RequestMethod.GET)
    public XaResult<List<BillPmVo>> calculate(@ApiParam("订单号")String orderNo,
                                              @ApiParam("型号") String productNo,
                                            @ApiParam("分配数量") Integer distributeNum,
                                              @ApiParam("材料类型")String pmType) {
        LOGGER.info(getRequestParamsAndUrl());
        if(StringUtils.isEmpty(productNo)){
            XaResult.error("型号不能为空");
        }
        if(StringUtils.isEmpty(orderNo)){
            return XaResult.error("订单号不能为空");
        }
        OrderProduct orderProduct = orderProductService.getByOrderNoAndProductNo(orderNo, productNo);
        if(orderProduct == null){
            XaResult.error("订单不存在或者该订单"+ orderNo +"没有该型号" + productNo);
        }
        List<ProductPm> list = productPmService.findListByProductNoAndType(productNo, pmType);

        List<BillPmVo> voList = null;
        if(list != null && list.size() >0){
            voList = new ArrayList<>();
            for (int i=0; i< list.size(); i++){
                ProductPm pm = list.get(i);
                BillPmVo billPmVo = new BillPmVo();
                billPmVo.setPmNo(pm.getPmNo());
                billPmVo.setPmName(pm.getPmName());
                billPmVo.setUnit(pm.getUnit());
                if(distributeNum != null) {
                    billPmVo.setAllowNum(pm.getNum().doubleValue() * distributeNum);
                }
                billPmVo.setSum(orderProduct.getMaxNum() * pm.getNum().doubleValue());
                voList.add(billPmVo);
            }
        }

        return XaResult.success(voList);
    }

    /**
     * 新增领料单、订单号开单
     */
    @RequestMapping(value = "bill/add", method = RequestMethod.POST)
    public XaResult<String> addBill(@ApiParam("1领料单 2订单号开单")Integer type, @ApiParam("订单号")String orderNo,@ApiParam("电子章")String stamp,
            @ApiParam("型号")String productNo,@ApiParam("单号")String billNo, @ApiParam("领用组")String groupNmae,@ApiParam("材料类型")String pmType,
            @ApiParam("材质检测")String pmCheckItem,@ApiParam("车间")String workshop,@ApiParam("分配数量")Integer assignedNum,
            @ApiParam("耗材信息")String[] pms) {
        //pms结构 材料编号|核可领用|批次
        if(pms ==null || pms.length <1){
            return XaResult.error("耗料信息为空");
        }
        if(type == null || (type >2) || (type < 1)){
            return XaResult.error("单据类型有误");
        }
        if(StringUtils.isEmpty(orderNo)){
            return XaResult.error("订单号为空");
        }
        if(StringUtils.isEmpty(productNo)){
            return XaResult.error("型号为空");
        }
        if(StringUtils.isEmpty(billNo)){
            return XaResult.error("订单号为空");
        }
        if(assignedNum == null){
            return XaResult.error("分配数量为空");
        }
        List<BillPmVo> list = new ArrayList<>();
        for (int i=0; i< pms.length; i++){
            String pm = pms[i];
            String[] s = pm.split("|");
            if(s.length <3){
                return XaResult.error("耗材信息有误");
            }
            BillPmVo vo = new BillPmVo();
            vo.setPmNo(s[0]);
            vo.setAllowNum(Double.valueOf(s[1]));
            vo.setPmBatchNo(s[2]);
            list.add(vo);
        }

        LOGGER.info(getRequestParamsAndUrl());
        PmOutBill pmOutBill = new PmOutBill();
        pmOutBill.setBillNo(billNo);
        pmOutBill.setOrderNo(orderNo);
        pmOutBill.setProductNo(productNo);
        pmOutBill.setType(type);
        pmOutBill.setPmCheckItem(pmCheckItem);
        pmOutBill.setPmType(pmType);
        pmOutBill.setGroupNmae(groupNmae);
        pmOutBill.setWorkshop(workshop);
        pmOutBill.setStamp(stamp);
        pmOutBill.setDistributionNum(assignedNum);
        pmOutBill.setCreateTime(new Date());
        pmOutBill.setOperator(getLoginUser().getUserName());
        pmOutBill.setIsDelete(0);
        pmOutBill.setLastModifyTime(new Date());
        pmOutService.createBill(pmOutBill, list);

        return XaResult.success();
    }




    /**
     * 计划外开单列表
     */
    @RequestMapping(value = "planout/list", method = RequestMethod.GET)
    public void planOutList() {
        LOGGER.info(getRequestParamsAndUrl());


    }

    /**
     * 出入库汇总
     */
    @RequestMapping(value = "collect", method = RequestMethod.GET)
    public void collect() {
        LOGGER.info(getRequestParamsAndUrl());


    }


}
