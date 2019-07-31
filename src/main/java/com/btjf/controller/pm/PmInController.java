package com.btjf.controller.pm;

import com.btjf.application.components.page.AppPageHelper;
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
@RequestMapping(value = "/pm/in")
@Api(value = "PmInController", description = "材料管理", position = 1)
public class PmInController extends ProductBaseController {

    private static final Logger LOGGER = Logger
            .getLogger(PmInController.class);

    @Resource
    private PmInService pmInService;
    @Resource
    private PmService pmService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public XaResult<List<PmInVo>> findList(@ApiParam("编号") String pmNo, @ApiParam("名称") String name
            , @ApiParam("类型") String type, @ApiParam("起始时间") String startDate, @ApiParam("截止时间") String endDate,
                                           Integer pageSize, Integer currentPage) {
        LOGGER.info(getRequestParamsAndUrl());

        Page<PmInVo> listPage = pmInService.findListPage(pmNo, name, type,startDate,endDate, AppPageHelper.appInit(currentPage, pageSize));
        XaResult<List<PmInVo>> result = AppXaResultHelper.success(listPage, listPage.getRows());
        return result;
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public XaResult<Integer> add(@ApiParam("id") Integer id, @ApiParam("入库数量") Integer num, @ApiParam("供应单位")
            String supplier, @ApiParam("入库日期") String date,@ApiParam("备注") String remark) {
        LOGGER.info(getRequestParamsAndUrl());
        if(id == null){
            return XaResult.error("材料ID不可为空");
        }
        if(num == null){
            return XaResult.error("材料数量不可为空");
        }
        SysUser sysUser = getLoginUser();
        Pm pm = pmService.getByID(id);
        if (pm == null){
            return XaResult.error("该材料不存在");
        }
        PmIn pmIn = new PmIn();
        pmIn.setPmId(pm.getId());
        pmIn.setPmNo(pm.getPmNo());
        pmIn.setPmName(pm.getName());
        pmIn.setType(pm.getType());
        pmIn.setUnit(pm.getUnit());
        pmIn.setRemark(remark);
        pmIn.setSupplier(supplier);
        if(StringUtils.isEmpty(date)){
            pmIn.setInDate(new Date());
        }else {
            pmIn.setInDate(DateUtil.string2Date(date, DateUtil.ymdFormat));
        }
        pmIn.setNum(num);
        pmIn.setPerNum(pm.getNum());
        pmIn.setBackNum(pm.getNum() + num);
        pmIn.setOperator(sysUser.getLoginName());
        pmIn.setCreateTime(new Date());
        pmIn.setIsDelete(0);
        pmInService.create(pmIn);
        Pm pm1 = new Pm();
        pm1.setId(pm.getId());
        pm1.setNum(pm.getNum() + num);
        pm.setLastModifyTime(new Date());
        pmService.updateByID(pm1);
        return XaResult.success();

    }

    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public XaResult<Pm> detail(@ApiParam("材料编号") String pmNo) {
        LOGGER.info(getRequestParamsAndUrl());
        Pm pm = pmService.getByNo(pmNo);
        return XaResult.success(pm);

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
