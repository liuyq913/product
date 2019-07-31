package com.btjf.excel;


import com.btjf.business.common.exception.BusinessException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
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
        HSSFRow firstRow = (HSSFRow) sheet.getRow(0);
        int celllength = firstRow.getLastCellNum();

        List<String> response = new ArrayList<>();
        // 获取Excel中的列名
        for (int f = 0; f < celllength; f++) {
            HSSFCell cell = firstRow.getCell(f);
            String field = cell.getStringCellValue().trim();
            if(!fields.get(f).equals(field)){
                wb.close();
                response.add("Excel格式出错,第" + (f+1) +"列表头应该为:" + fields.get(f));
                return response;
            }
        }
        // 将sheet转换为list
        for (int j = 1; j <= sheet.getLastRowNum(); j++) {
            HSSFRow row = (HSSFRow) sheet.getRow(j);
            try {
                create(row);
            }catch (Exception e){
                response.add("第" + j + "行数据录入出错。" );
            }
        }
        wb.close();
        return response;
    }

    protected abstract void create(HSSFRow row)throws Exception;

}
