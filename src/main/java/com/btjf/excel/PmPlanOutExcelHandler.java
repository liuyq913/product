package com.btjf.excel;

import com.btjf.business.common.exception.BusinessException;
import com.btjf.model.pm.Pm;
import com.btjf.model.pm.PmOutBill;
import com.btjf.model.pm.PmOutBillDetail;
import com.btjf.service.pm.PmOutService;
import com.btjf.service.pm.PmService;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by yj on 2019/7/31.
 */
@Component
//@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
public class PmPlanOutExcelHandler extends BaseExcelHandler{

    public final static List<String> fields = Stream.of("物料编号", "物料名称", "出库数量",
            "出库说明").collect(Collectors.toList());

    @Resource
    private PmOutService pmOutService;
    @Resource
    private PmService pmService;

    @Override
    public List<String> execute(MultipartFile file, Boolean isCover, String operator)throws Exception {
        return checkLayout(file, fields, operator);
    }

    @Override
    protected void insert(List pmOutList, String operator) {
        if(pmOutList != null && pmOutList.size() >0){
            for(int i=0; i< pmOutList.size(); i++){
                PmOutBillDetail pmOutBillDetail = (PmOutBillDetail) pmOutList.get(i);
                Pm pm = pmService.getByNo(pmOutBillDetail.getPmNo());

                PmOutBill pmOutBill = new PmOutBill();
                pmOutBill.setBillNo("0");
                pmOutBill.setType(3);
                pmOutBill.setOperator(operator);
                pmOutBill.setCreateTime(new Date());
                pmOutBill.setLastModifyTime(new Date());
                pmOutBill.setIsDelete(0);
                //pmOutBillDetail.setBillId(pmOutBill.getId());
                pmOutBillDetail.setPmId(pm.getId());
                pmOutBillDetail.setPerNum(pm.getNum());
                pmOutBillDetail.setBackNum(pm.getNum().subtract(pmOutBillDetail.getNum()));
                pmOutBillDetail.setUnit(pm.getUnit());
                pmOutBillDetail.setOperator(operator);
                pmOutBillDetail.setCreateTime(new Date());
                pmOutBillDetail.setIsDelete(0);
                pmOutBillDetail.setCreateTime(new Date());
                pmOutBillDetail.setLastModifyTime(new Date());
                pmOutBillDetail.setIsDelete(0);
                pmOutService.insert(pmOutBill, pmOutBillDetail);


                Pm pm1 = new Pm();
                pm1.setId(pm.getId());
                pm1.setNum(pmOutBillDetail.getBackNum());
                pm.setLastModifyTime(new Date());
                pmService.updateByID(pm1);
            }
        }
        pmOutList.clear();
    }

    @Override
    protected List create(XSSFRow row) throws BusinessException {
        List<PmOutBillDetail> list = new ArrayList<>();
        PmOutBillDetail pmOut = new PmOutBillDetail();
        for(int i=0; i< fields.size(); i++){
            switch (i){
                case 0:
                    pmOut.setPmNo(getCellValue(row.getCell(i), i));
                    break;
                case 1:
                    pmOut.setPmName(getCellValue(row.getCell(i), i));
                    break;
                case 2:
                    pmOut.setNum(BigDecimal.valueOf(Double.parseDouble(getCellValue(row.getCell(i), i))));
                    break;
                case 3:
                    pmOut.setRemark(getCellValue(row.getCell(i), i));
                    break;
                default:
                        break;
            }
        }
        Pm pm = pmService.getByNo(pmOut.getPmNo());
        if(pm == null){
            throw new BusinessException("第" + 1 +"列" + fields.get(0) + " 填写错误");
        }else if(!pm.getName().equals(pmOut.getPmName())){
            throw new BusinessException("第" + 2 +"列" + fields.get(1) + " 填写错误");
        }else if(pmOut.getNum().doubleValue() > pm.getNum().doubleValue()){
            throw new BusinessException("第" + 3 +"列" + fields.get(2) + " 填写错误: 材料库存数不足");
        }
        list.add(pmOut);
        return list;
    }

    private String getCellValue(XSSFCell cell, int i) {
        String value = null;
        if(cell == null && i == 3){
            //备注列 允许为空
            return null;
        }
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
