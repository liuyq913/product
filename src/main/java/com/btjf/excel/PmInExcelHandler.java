package com.btjf.excel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by yj on 2019/7/31.
 */
@Component
public class PmInExcelHandler extends BaseExcelHandler{

    private final static List<String> fields = Stream.of("物料编号", "物料名称", "物料类别",
            "供应单位", "初始库或入库数量", "物料单位", "备注").collect(Collectors.toList());

    @Override
    public List<String> execute(MultipartFile file)throws Exception {
        return checkLayout(file, fields);
    }

    @Override
    protected void create(HSSFRow row) throws Exception{
        for(int i=0; i< fields.size(); i++){

        }
    }


}
