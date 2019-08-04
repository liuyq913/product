package com.btjf.controller.pm;

import com.btjf.application.components.xaresult.AppXaResultHelper;
import com.btjf.application.util.XaResult;
import com.btjf.common.page.Page;
import com.btjf.common.utils.DateUtil;
import com.btjf.controller.base.ProductBaseController;
import com.btjf.excel.PmInExcelHandler;
import com.btjf.model.pm.Pm;
import com.btjf.model.pm.PmIn;
import com.btjf.model.sys.SysUser;
import com.btjf.service.pm.PmInService;
import com.btjf.service.pm.PmService;
import com.btjf.vo.PmInVo;
import com.btjf.vo.PmOutBillListVo;
import com.btjf.vo.PmOutStockDetailListVo;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

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
    private PmInService pmInService;
    @Resource
    private PmService pmService;

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
     *
     * @param orderNo
     * @param productNo
     * @return
     */
    @RequestMapping(value = "/stock/detail", method = RequestMethod.GET)
    public XaResult<PmOutStockDetailListVo> stockDetail(@ApiParam("订单号") String orderNo, @ApiParam("型号") String productNo) {
        LOGGER.info(getRequestParamsAndUrl());
        //查询订单 获取订单 产品数量
        //查询 产品 耗料  获取所需耗料数量

        return XaResult.success();

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
    public XaResult<List<PmOutBillListVo>> detail(@ApiParam("票据编号") String billNo,
                                                  @ApiParam("订单号") String orderNo, @ApiParam("型号") String productNo,
                                                  Integer pageSize, Integer currentPage) {
        LOGGER.info(getRequestParamsAndUrl());


        return null;
    }

    @RequestMapping(value = "export", method = RequestMethod.GET)
    public void export(@ApiParam("编号") String pmNo, @ApiParam("名称") String name
            , @ApiParam("类型") String type, @ApiParam("起始时间") String startDate, @ApiParam("截止时间") String endDate,
                               HttpServletResponse response) {
        LOGGER.info(getRequestParamsAndUrl());
        List<PmInVo> list = pmInService.findList(pmNo, name, type, startDate, endDate);

        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet();
        Row header = sheet.createRow(0);

        sheet.setColumnWidth(0, (int) ((20 + 0.72) * 256));
        sheet.setColumnWidth(1, (int) ((20 + 0.72) * 256));
        sheet.setColumnWidth(2, (int) ((10 + 0.72) * 256));
        sheet.setColumnWidth(3, (int) ((20 + 0.72) * 256));
        sheet.setColumnWidth(4, (int) ((20 + 0.72) * 256));
        sheet.setColumnWidth(5, (int) ((15 + 0.72) * 256));
        sheet.setColumnWidth(6, (int) ((15 + 0.72) * 256));

        for(int i=0; i< PmInExcelHandler.fields.size(); i++){
            header.createCell(i).setCellValue(PmInExcelHandler.fields.get(i));
        }

        PmInVo pm = null;
        if (list != null && list.size() >= 1) {
            for (int i = 0; i < list.size(); i++) {
                pm = list.get(i);
                Row row = sheet.createRow(i + 1);
                int j = 0;
                row.createCell(j++).setCellValue(pm.getPmNo());
                row.createCell(j++).setCellValue(pm.getName());
                row.createCell(j++).setCellValue(pm.getType());
                row.createCell(j++).setCellValue(pm.getSupplier());
                row.createCell(j++).setCellValue(pm.getNum());
                row.createCell(j++).setCellValue(pm.getUnit());
                row.createCell(j++).setCellValue(pm.getRemark());
            }
        }
        try {
            sheet.setForceFormulaRecalculation(true);
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode("入库记录.xlsx", "UTF-8"));
            response.setDateHeader("Expires", 0);
            response.setHeader("Connection", "close");
            response.setHeader("Content-Type", "application/vnd.ms-excel");
            wb.write(response.getOutputStream());
            wb.close();
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("入库记录导出excel异常");
        }

    }

}
