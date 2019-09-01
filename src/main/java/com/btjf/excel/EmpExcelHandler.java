package com.btjf.excel;

import com.btjf.business.common.exception.BusinessException;
import com.btjf.common.utils.DateUtil;
import com.btjf.common.utils.MD5Utils;
import com.btjf.model.emp.Emp;
import com.btjf.model.emp.EmpWork;
import com.btjf.model.pm.Pm;
import com.btjf.model.pm.PmIn;
import com.btjf.model.sys.Sysdept;
import com.btjf.service.emp.EmpService;
import com.btjf.service.emp.EmpWorkService;
import com.btjf.service.pm.PmInService;
import com.btjf.service.pm.PmService;
import com.btjf.service.sys.SysDeptService;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by yj on 2019/7/31.
 */
@Component
//@Transactional(readOnly = false, rollbackFor = Exception.class)
public class EmpExcelHandler extends BaseExcelHandler{

    //姓名	性别	出生日期	学历	部门	人员类别	工种	固定工资	身份证号	家庭地址	进场日期	手机	开水费	夜餐费	备注

    public final static List<String> fields = Stream.of("姓名", "性别", "出生日期",
            "学历", "部门", "人员类别", "工种","固定工资","身份证号","家庭地址",
            "进场日期","手机","开水费","夜餐费","备注").collect(Collectors.toList());

    @Resource
    private EmpService empService;
    @Resource
    private SysDeptService sysDeptService;
    @Resource
    private EmpWorkService empWorkService;

    @Override
    public List<String> execute(MultipartFile file, Boolean isCover, String operator)throws Exception {
        return checkLayout(file, fields, operator);
    }

    @Override
    protected void insert(List emps, String operator) {
        if(emps != null && emps.size() >0){
            for(int i=0; i< emps.size(); i++){
                Emp emp = (Emp) emps.get(i);
                Emp emp1 = empService.getByName(emp.getName());
                Emp emp2 = null;
                if(StringUtils.isNotEmpty(emp.getIdCard())){
                    emp2 = empService.getByIdCard(emp.getIdCard());
                }

                if (emp1 != null || emp2 != null){
                    continue;
                }else{
                    empService.create(emp);
                }

            }
        }
        emps.clear();
    }

    @Override
    protected List create(XSSFRow row) throws BusinessException {
        List<Emp> emps = new ArrayList<>();
        Emp emp = new Emp();
        String errMsg = "";
        for(int i=0; i< fields.size(); i++){
            switch (i){
                case 0:
                    Emp emp1 = empService.getByName(getCellValue(row.getCell(i), i));
                    if(emp1 != null){
                        errMsg = errMsg + "第" + 1 +"列" + fields.get(0) + " 填写错误,";
                    }
                    emp.setName(getCellValue(row.getCell(i), i));
                    break;
                case 1:
                    String sex = getCellValue(row.getCell(i), i);
                    if(!StringUtils.isEmpty(sex)){
                        if("男".equals(sex)){
                            emp.setSex(1);
                        }else if("女".equals(sex)){
                            emp.setSex(2);
                        }
                    }

                    break;
                case 2:
                    //YYYYMMDD
                    if(isRightDateStr(getCellValue(row.getCell(i), i),"yyyyMMdd")){
                        emp.setBirthday(getCellValue(row.getCell(i), i));
                    }else{
                        errMsg = errMsg + "第" + 3 +"列" + fields.get(2) + " 填写错误,";
                    }

                    break;
                case 3:
                    emp.setEducation(getCellValue(row.getCell(i), i));
                    break;
                case 4:
                    Sysdept dept = sysDeptService.getByName(getCellValue(row.getCell(i), i));
                    if (dept == null){
                        errMsg = errMsg + "第" + 5 +"列" + fields.get(4) + " 填写错误,";
                    }else{
                        emp.setDeptId(dept.getId());
                    }
                    break;
                case 5:
                    String type = getCellValue(row.getCell(i), i);
                    if("计件工".equals(type)){
                        emp.setType(1);
                    }else if("合同工".equals(type)){
                        emp.setType(2);
                    }else{
                        errMsg = errMsg + "第" + 6 +"列" + fields.get(5) + " 填写错误,";
                    }
                    break;
                case 6:
                    EmpWork empWork = empWorkService.getByName(getCellValue(row.getCell(i), i));
                    if (empWork == null){
                        errMsg = errMsg + "第" + 7 +"列" + fields.get(6) + " 填写错误,";
                    }else{
                        emp.setWorkId(empWork.getId());
                    }
                    break;
                case 7:
                    String salary = getCellValue(row.getCell(i), i);
                    if (StringUtils.isEmpty(salary)){
                        emp.setSalary(BigDecimal.valueOf(0.0));
                    }else{
                        emp.setSalary(BigDecimal.valueOf(Double.parseDouble(salary)));
                    }
                    break;
                case 8:
                    String idCard = getCellValue(row.getCell(i), i);
                    if(StringUtils.isNotEmpty(idCard)) {
                        Emp emp2 = empService.getByIdCard(idCard);
                        if (emp2 != null) {
                            errMsg = errMsg + "第" + 9 + "列" + fields.get(8) + " 填写错误,";
                        }
                    }
                    emp.setIdCard(getCellValue(row.getCell(i), i));
                    break;
                case 9:
                    emp.setAddress(getCellValue(row.getCell(i), i));
                    break;
                case 10:
                    if(isRightDateStr(getCellValue(row.getCell(i), i),"yyyyMMdd")){
                        emp.setEntryDate(getCellValue(row.getCell(i), i));
                    }else{
                        errMsg = errMsg + "第" + 11 +"列" + fields.get(10) + " 填写错误,";
                    }
                    break;
                case 11:
                    emp.setPhone(getCellValue(row.getCell(i), i));
                    break;
                case 12:
                    String water = getCellValue(row.getCell(i), i);
                    if (StringUtils.isEmpty(water)){
                        emp.setWaterSubsidy(BigDecimal.valueOf(0.0));
                    }else{
                        emp.setWaterSubsidy(BigDecimal.valueOf(Double.parseDouble(water)));
                    }
                    break;
                case 13:
                    String food = getCellValue(row.getCell(i), i);
                    if (StringUtils.isEmpty(food)){
                        emp.setFoodSubsidy(BigDecimal.valueOf(0.0));
                    }else{
                        emp.setFoodSubsidy(BigDecimal.valueOf(Double.parseDouble(food)));
                    }
                    break;
                case 14:
                    emp.setRemark(getCellValue(row.getCell(i), i));
                    break;

                default:
                        break;
            }
        }
        if (StringUtils.isNotEmpty(errMsg)){
            throw new BusinessException(errMsg);
        }
        if(emp.getWorkId() == 1){
            emp.setIsLeader(1);
        }else{
            emp.setIsLeader(2);
        }
        emp.setYlbx(BigDecimal.valueOf(0.0));
        emp.setSybx(BigDecimal.valueOf(0.0));
        emp.setYiliaobx(BigDecimal.valueOf(0.0));
        emp.setGjj(BigDecimal.valueOf(0.0));
        emp.setPhoneSubsidy(BigDecimal.valueOf(0.0));
        emp.setSocialSubsidy(BigDecimal.valueOf(0.0));
        emp.setPassword(MD5Utils.ecodeByMD5("123456"));
        emp.setIsDelete(0);
        emp.setCreateTime(new Date());
        emp.setLastModifyTime(new Date());
        emps.add(emp);
        return emps;
    }

    private String getCellValue(XSSFCell cell, int i) {
        String value = null;
        if(cell == null && (i == 1 || i == 3 || i == 7 || i ==8 || i == 9 || i == 11
                || i == 12 || i == 13 || i == 14)){
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
