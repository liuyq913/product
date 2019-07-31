package com.btjf.excel;

import com.btjf.business.common.exception.BusinessException;
import com.btjf.model.pm.Pm;
import com.btjf.model.pm.PmIn;
import com.btjf.service.pm.PmInService;
import com.btjf.service.pm.PmService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
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


    @Resource
    private PmInService pmInService;
    @Resource
    private PmService pmService;

    @Override
    public List<String> execute(MultipartFile file)throws Exception {
        return checkLayout(file, fields);
    }

    @Override
    protected void create(XSSFRow row) throws BusinessException {
        PmIn pmIn = new PmIn();
        for(int i=0; i< fields.size(); i++){
            switch (i){
                case 0:
                    pmIn.setPmNo(getCellValue(row.getCell(i), i));
                    break;
                case 1:
                    pmIn.setPmName(getCellValue(row.getCell(i), i));
                    break;
                case 2:
                    pmIn.setType(getCellValue(row.getCell(i), i));
                    break;
                case 3:
                    pmIn.setSupplier(getCellValue(row.getCell(i), i));
                    break;
                case 4:
                    pmIn.setNum(Integer.valueOf(getCellValue(row.getCell(i), i)));
                    break;
                case 5:
                    pmIn.setUnit(getCellValue(row.getCell(i), i));
                    break;
                case 6:
                    pmIn.setRemark(getCellValue(row.getCell(i), i));
                    break;
                default:
                        break;
            }
        }
        Pm pm = pmService.getByNo(pmIn.getPmNo());
        pmIn.setPmId(pm.getId());
        pmIn.setPerNum(pm.getNum());
        pmIn.setBackNum(pm.getNum() + pmIn.getNum());
        pmIn.setOperator("系统导入");
        pmIn.setCreateTime(new Date());
        pmIn.setIsDelete(0);
        pmInService.create(pmIn);
        Pm pm1 = new Pm();
        pm1.setId(pm.getId());
        pm1.setNum(pm.getNum() + pmIn.getNum());
        pm.setLastModifyTime(new Date());
        pmService.updateByID(pm1);
    }

    private String getCellValue(XSSFCell cell, int i) {
        String value = null;
        try{
            value = getCellValue(cell);
        }catch (Exception e){
            throw new BusinessException("第" + (i+1) +"列" + fields.get(i) + " 填写错误");
        }
        if(value.equals("非法字符") || value.equals("未知类型")){
            throw new BusinessException("第" + (i+1) +"列" + fields.get(i) + " 填写错误");
        }
        return value;
    }


}
