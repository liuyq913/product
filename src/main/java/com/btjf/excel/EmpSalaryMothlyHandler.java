package com.btjf.excel;

import com.btjf.model.emp.Emp;
import com.btjf.model.emp.EmpSalaryMonthly;
import com.btjf.model.emp.EmpSalaryMothlyPojo;
import com.btjf.service.emp.EmpService;
import com.btjf.service.emp.EmpWorkService;
import com.btjf.service.sys.SysDeptService;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by liuyq on 2019/9/7.
 */
public class EmpSalaryMothlyHandler extends BaseExcelHandler {

    @Resource
    private EmpService empService;

    @Resource
    private SysDeptService sysDeptService;

    @Resource
    private EmpWorkService empWorkService;

    @Override
    public List<String> checkLayout(MultipartFile file, List<String> fields, String operator) throws Exception {

        InputStream is = file.getInputStream();
        Workbook wb = WorkbookFactory.create(is);
        Sheet sheet = wb.getSheetAt(0);

        List<String> response = new ArrayList<>();
        List<String> errResponse = new ArrayList<>();
        // 日期
        XSSFRow dateRow = (XSSFRow) sheet.getRow(0);
        //星期
        XSSFRow webRow = (XSSFRow) sheet.getRow(1);
        //白班
        XSSFRow blackRow = (XSSFRow) sheet.getRow(2);
        //晚班
        XSSFRow nightRow = (XSSFRow) sheet.getRow(3);


        List<EmpSalaryMothlyPojo> result = new ArrayList<>();

        String name = null;
        for (int i = 4; i < sheet.getLastRowNum(); i++) {
            XSSFRow row = (XSSFRow) sheet.getRow(i);

            if (row == null) break;
            if (row.getCell(0) == null) continue;

            if (!StringUtils.isEmpty(getCellValue(row.getCell(0)))) {
                name = getCellValue(row.getCell(0));//名称
                Emp emp = empService.getByName(name);
                if (emp == null) {
                    errResponse.add("第" + i + "行，名称为：" + name + "的员工不存在");
                }
            }


            for (int index = 1; index < dateRow.getPhysicalNumberOfCells(); index++) {

                EmpSalaryMothlyPojo model = new EmpSalaryMothlyPojo();
                model.setDate(getCellValue(dateRow.getCell(index)));
                model.setName(name);
                model.setNum(new Double(row.getCell(index) == null || StringUtils.isEmpty(getCellValue(row.getCell(index))) ? "0" : getCellValue(row.getCell(index))));
                model.setIsLegal("法假".equals(getCellValue(blackRow.getCell(index))));
                model.setIsHoliday("节假".equals(getCellValue(blackRow.getCell(index))));
                //白班
                if (i % 2 == 0) {
                    model.setIsBlack(Boolean.TRUE);
                    //晚班
                } else {
                    model.setIsBlack(Boolean.FALSE);
                }
                result.add(model);

            }
        }
        //检验要导入的数据重复问题
        if (errResponse.size() > 0) {
            int sum = sheet.getLastRowNum() - errResponse.size();
            response.add("导入失败，以下数据请修改后再重新上传");
            response.addAll(errResponse);
        } else {
            insert(result, operator);
            response.add("提交成功！新增导入" + result.size() + "条数据！");
        }
        wb.close();
        return response;
    }

    @Override
    public List<String> execute(MultipartFile file, Boolean isCover, String operator) throws Exception {
        return null;
    }

    @Override
    protected void insert(List list, String operator) {
        if (!CollectionUtils.isEmpty(list)) {
            //按名称分组
            Map<String, List<EmpSalaryMothlyPojo>> modelMap = (Map<String, List<EmpSalaryMothlyPojo>>) list.stream().filter(t -> t != null).
                    collect(Collectors.groupingBy(EmpSalaryMothlyPojo::getName));
            for (Map.Entry<String, List<EmpSalaryMothlyPojo>> entry : modelMap.entrySet()) {
                EmpSalaryMonthly monthly = new EmpSalaryMonthly();
                //员工基本信息
                Emp emp = empService.getByName(entry.getKey());
                monthly.setEmpName(emp.getName());
                monthly.setDeptId(emp.getDeptId());
                monthly.setIsDelete(0);
                monthly.setEmpId(emp.getId());
                monthly.setDeptName(sysDeptService.get(emp.getDeptId()).getDeptName());
                monthly.setWorkName(empWorkService.getByID(emp.getWorkId()).getName());

            }

        }
    }

    @Override
    protected List<T> create(XSSFRow row) throws Exception {
        return null;
    }
}
