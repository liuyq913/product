package com.btjf.excel;

import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by liuyq on 2019/8/12.
 */
@Component
public class ProductWorkshopExcelHandler extends BaseExcelHandler {

    public final static List<String> fields = Stream.of("型号", "工序号", "单价", "序号").collect(Collectors.toList());

    @Override
    public List<String> execute(MultipartFile file, Boolean isCover, String operator) throws Exception {
        return checkLayout(file, fields);
    }

    @Override
    protected void insert(List<T> list) {

    }

    @Override
    protected List<T> create(XSSFRow row) throws Exception {
        for (int i = 0; i < fields.size(); i++) {
            switch (i) {
                case 0:


            }
        }
        return null;
    }
}
