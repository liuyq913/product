package com.btjf.controller.emp;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.btjf.application.components.xaresult.AppXaResultHelper;
import com.btjf.application.util.XaResult;
import com.btjf.business.common.exception.BusinessException;
import com.btjf.common.page.Page;
import com.btjf.controller.base.ProductBaseController;
import com.btjf.model.emp.EmpSalaryMonthly;
import com.btjf.service.emp.EmpSalaryMonthlyService;
import com.btjf.vo.EmpSubsidyVo;
import com.google.common.collect.Lists;
import com.wordnik.swagger.annotations.Api;
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
import java.util.List;

/**
 * Created by liuyq on 2019/9/8.
 */
@Api(value = "EmpController", description = "固定工资管理", position = 1)
@RequestMapping(value = "/empSalaryMothly")
@RestController("empSalaryMothlyController")
public class EmpSalaryMothlyController extends ProductBaseController {

    private static final Logger LOGGER = Logger
            .getLogger(EmpSalaryMothlyController.class);

    @Resource
    private EmpSalaryMonthlyService empSalaryMothlyService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public XaResult<List<EmpSalaryMonthly>> getList(Integer pageSize, Integer currentPage, String yearMonth, String empName, String deptName) {
        if (currentPage == null || currentPage < 1) {
            currentPage = 1;
        }
        if (pageSize == null || pageSize < 1) {
            pageSize = 25;
        }
        Page page = new Page(pageSize, currentPage);
        Page<EmpSalaryMonthly> empPage = empSalaryMothlyService.getPage(yearMonth, empName, deptName, null, page);
        XaResult<List<EmpSalaryMonthly>> result = AppXaResultHelper.success(empPage, empPage.getRows());
        return result;
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public XaResult<EmpSalaryMonthly> detail(Integer id) {
        if (id == null) return XaResult.error("id不能为空");

        EmpSalaryMonthly empSalaryMonthly = empSalaryMothlyService.getById(id);
        return XaResult.success(empSalaryMonthly);
    }

    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public void export(String yearMonth, String empName, String deptName, HttpServletResponse response) {
        List<EmpSalaryMonthly> empSalaryMonthlies = empSalaryMothlyService.getList(yearMonth, empName, deptName, null);

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
        sheet.setColumnWidth(7, (int) ((15 + 0.72) * 256));
        sheet.setColumnWidth(8, (int) ((15 + 0.72) * 256));
        sheet.setColumnWidth(8, (int) ((15 + 0.72) * 256));
        sheet.setColumnWidth(7, (int) ((15 + 0.72) * 256));
        sheet.setColumnWidth(8, (int) ((15 + 0.72) * 256));
        sheet.setColumnWidth(8, (int) ((15 + 0.72) * 256));
        sheet.setColumnWidth(8, (int) ((15 + 0.72) * 256));
        int j = 0;
        header.createCell(j++).setCellValue("序号");
        header.createCell(j++).setCellValue("月份");
        header.createCell(j++).setCellValue("姓名");
        header.createCell(j++).setCellValue("部门");
        header.createCell(j++).setCellValue("工种");
        header.createCell(j++).setCellValue("月工资");
        header.createCell(j++).setCellValue("工作日");
        header.createCell(j++).setCellValue("白班");
        header.createCell(j++).setCellValue("晚班");
        header.createCell(j++).setCellValue("考核分");
        header.createCell(j++).setCellValue("公休");
        header.createCell(j++).setCellValue("总天数");
        header.createCell(j++).setCellValue("日工资");
        header.createCell(j++).setCellValue("应发工资");
        EmpSalaryMonthly empSalaryMonthly = null;
        if (empSalaryMonthlies != null && empSalaryMonthlies.size() >= 1) {
            for (int i = 0; i < empSalaryMonthlies.size(); i++) {
                empSalaryMonthly = empSalaryMonthlies.get(i);
                Row row = sheet.createRow(i + 1);
                j = 0;
                row.createCell(j++).setCellValue(empSalaryMonthly.getId());
                row.createCell(j++).setCellValue(empSalaryMonthly.getYearMonth());
                row.createCell(j++).setCellValue(empSalaryMonthly.getEmpName());
                row.createCell(j++).setCellValue(empSalaryMonthly.getDeptName());
                row.createCell(j++).setCellValue(empSalaryMonthly.getWorkName());
                row.createCell(j++).setCellValue(empSalaryMonthly.getSalary().toString());
                row.createCell(j++).setCellValue(empSalaryMonthly.getWorkDay().toString());
                row.createCell(j++).setCellValue(empSalaryMonthly.getDayWork().toString());
                row.createCell(j++).setCellValue(empSalaryMonthly.getNightWork().toString());
                row.createCell(j++).setCellValue(empSalaryMonthly.getScore().toString());
                row.createCell(j++).setCellValue(empSalaryMonthly.getRestDay().toString());
                row.createCell(j++).setCellValue(empSalaryMonthly.getSumDay().toString());
                row.createCell(j++).setCellValue(empSalaryMonthly.getDaySalary().toString());
                row.createCell(j++).setCellValue(empSalaryMonthly.getRealSalary().toString());
            }
        }
        try {
            sheet.setForceFormulaRecalculation(true);
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(yearMonth + "月份员工数据.xlsx", "UTF-8"));
            response.setDateHeader("Expires", 0);
            response.setHeader("Connection", "close");
            response.setHeader("Content-Type", "application/vnd.ms-excel");
            wb.write(response.getOutputStream());
            wb.close();
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("导出本月员工数据excel异常");
        }
    }

    @RequestMapping(value = "/getYearMonth", method = RequestMethod.GET)
    public XaResult<List<String>> getYearMonth() {
        return XaResult.success(empSalaryMothlyService.getYearMonth());
    }


    /**
     * 住房补贴
     *
     * @param yearMonth
     * @param empName
     * @param deptName
     * @param type
     * @param pageSize
     * @param currentPage
     * @return
     */
    @RequestMapping(value = "/houseSubsidy/list", method = RequestMethod.GET)
    public XaResult<List<EmpSubsidyVo>> houseSubsidy(String yearMonth, String empName, String deptName, Integer type, Integer pageSize, Integer currentPage) {
        if (currentPage == null || currentPage < 1) {
            currentPage = 1;
        }
        if (pageSize == null || pageSize < 1) {
            pageSize = 25;
        }
        if (type == null) {
            return XaResult.error("type 不能为null");
        }
        Page page = new Page(pageSize, currentPage);
        Page<EmpSalaryMonthly> empPage = empSalaryMothlyService.getPage(yearMonth, empName, deptName, type, page);
        List<EmpSalaryMonthly> empSalaryMonthlies = empPage.getRows();
        List<EmpSubsidyVo> empSubsidyVos = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(empSalaryMonthlies)) {
            empSalaryMonthlies.stream().filter(t -> t != null).forEach(t -> {
                empSubsidyVos.add(new EmpSubsidyVo(t));
            });
        }
        XaResult<List<EmpSubsidyVo>> result = AppXaResultHelper.success(empPage, empSubsidyVos);
        return result;
    }

    @RequestMapping(value = "/houseSubsidy/export", method = RequestMethod.GET)
    public void houseSubsidyExport(String yearMonth, String empName, String deptName, Integer type, HttpServletResponse response) throws BusinessException {

        if (type == null) {
            throw new BusinessException("type 不能为null");
        }

        List<EmpSalaryMonthly> empSalaryMonthlies = empSalaryMothlyService.getList(yearMonth, empName, deptName, type);

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
        int j = 0;
        header.createCell(j++).setCellValue("序号");
        header.createCell(j++).setCellValue("工资月份");
        header.createCell(j++).setCellValue("人员姓名");
        header.createCell(j++).setCellValue("所在部门");
        header.createCell(j++).setCellValue("工种");
        header.createCell(j++).setCellValue("考核分");
        header.createCell(j++).setCellValue("金额");
        EmpSalaryMonthly empSalaryMonthly = null;
        if (empSalaryMonthlies != null && empSalaryMonthlies.size() >= 1) {
            for (int i = 0; i < empSalaryMonthlies.size(); i++) {
                empSalaryMonthly = empSalaryMonthlies.get(i);
                Row row = sheet.createRow(i + 1);
                j = 0;
                row.createCell(j++).setCellValue(empSalaryMonthly.getId());
                row.createCell(j++).setCellValue(empSalaryMonthly.getYearMonth());
                row.createCell(j++).setCellValue(empSalaryMonthly.getEmpName());
                row.createCell(j++).setCellValue(empSalaryMonthly.getDeptName());
                row.createCell(j++).setCellValue(empSalaryMonthly.getWorkName());
                row.createCell(j++).setCellValue(empSalaryMonthly.getScore() == null ? "0" : empSalaryMonthly.getScore().toString());
                row.createCell(j++).setCellValue(empSalaryMonthly.getScore() == null ? "0" : empSalaryMonthly.getScore().toString());
            }
        }

        try {
            sheet.setForceFormulaRecalculation(true);
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(yearMonth == null ? "" : yearMonth + (type == 1 ? "计件工" : "固定工") + "月份住房补贴数据.xlsx", "UTF-8"));
            response.setDateHeader("Expires", 0);
            response.setHeader("Connection", "close");
            response.setHeader("Content-Type", "application/vnd.ms-excel");
            wb.write(response.getOutputStream());
            wb.close();
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("月份住房补贴数据excel异常");
        }
    }
}
