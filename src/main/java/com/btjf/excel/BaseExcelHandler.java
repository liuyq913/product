package com.btjf.excel;


import com.btjf.business.common.exception.BusinessException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yj on 2019/7/31.
 */
public abstract class BaseExcelHandler {

    public abstract  List<String> execute(MultipartFile file)throws Exception;

    public  List<String> checkLayout(MultipartFile file, List<String> fields) throws Exception{

        InputStream is = file.getInputStream();
        Workbook wb = WorkbookFactory.create(is);
        Sheet sheet = wb.getSheetAt(0);
        // 每页中的第一行为标题行，对标题行的特殊处理
        XSSFRow firstRow = (XSSFRow) sheet.getRow(0);
        int celllength = firstRow.getLastCellNum();

        List<String> response = new ArrayList<>();
        // 获取Excel中的列名
        for (int f = 0; f < celllength; f++) {
            XSSFCell cell = firstRow.getCell(f);
            String field = cell.getStringCellValue().trim();
            if(!fields.get(f).equals(field)){
                wb.close();
                response.add("Excel格式出错,第" + (f+1) +"列表头应该为:" + fields.get(f));
                return response;
            }
        }
        // 将sheet转换为list
        for (int j = 1; j <= sheet.getLastRowNum(); j++) {
            XSSFRow row = (XSSFRow) sheet.getRow(j);
            try {
                create(row);
            }catch (Exception e){
                e.printStackTrace();
                response.add("第" + (j +1) + "行数据录入出错。" );
            }
        }
        response.add("提交成功！新增导入" + sheet.getLastRowNum() + "条数据！" );
        wb.close();
        return response;
    }

    protected abstract void create(XSSFRow row)throws Exception;


    public String getCellValue(Cell cell) {
        String cellValue = "";
        // 以下是判断数据的类型
        switch (cell.getCellType()) {
            case 0: // 数字
                if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell)) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    cellValue = sdf.format(org.apache.poi.ss.usermodel.DateUtil.getJavaDate(cell.getNumericCellValue())).toString();
                } else {
                    cellValue = NumberToTextConverter.toText(cell.getNumericCellValue());
                }
                break;
            case 1: // 字符串
                cellValue = cell.getStringCellValue();
                break;
            case 4: // Boolean
                cellValue = cell.getBooleanCellValue() + "";
                break;
            case 2: // 公式
                cellValue = cell.getCellFormula() + "";
                break;
            case 3: // 空值
                cellValue = "";
                break;
            case 5: // 故障
                cellValue = "非法字符";
                break;
            default:
                cellValue = "未知类型";
                break;
        }
        return cellValue.trim();
    }


}
