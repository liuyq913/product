package com.btjf.service.pm;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.btjf.business.common.exception.BusinessException;
import com.btjf.common.page.Page;
import com.btjf.factory.ExcelImportFactory;
import com.btjf.mapper.pm.PmMapper;
import com.btjf.model.pm.Pm;
import com.btjf.model.pm.PmRequstPojo;
import com.btjf.service.dictionary.DictionaryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.List;

/**
 * Created by liuyq on 2019/7/28.
 */
@Service
public class PmService {

    @Resource
    private PmMapper pmMapper;
    @Resource
    private DictionaryService dictionaryService;
    @Autowired
    private ExcelImportFactory excelImportFactory;

    public Page<Pm> findListPage(PmRequstPojo pmRequstPojo, Page page) {
        PageHelper.startPage(page.getPage(), page.getRp());
        List<Pm> pmList = pmMapper.findList(pmRequstPojo);
        PageInfo pageInfo = new PageInfo(pmList);
        pageInfo.setList(pmList);
        return new Page<>(pageInfo);
    }

    public Integer deleteByID(List<Integer> integers) {
        return pmMapper.deleteByID(integers);
    }

    public Integer deleteByNo(List<String> nos) {
        if (CollectionUtils.isEmpty(nos)) {
            return 0;
        }
        return pmMapper.deleteByNo(nos);
    }

    public Pm getByID(Integer id) {
        if (id == null) return null;
        Pm pm = pmMapper.selectByPrimaryKey(id);
        return pm;
    }

    public List<Pm> findList(PmRequstPojo pmRequstPojo) {
        List<Pm> pmList = pmMapper.findList(pmRequstPojo);
        return pmList;
    }

    public Integer updateByID(Pm pm) {
        pmMapper.updateByPrimaryKeySelective(pm);
        return pm.getId();
    }

    public Integer insert(Pm pm) {
        pmMapper.insertSelective(pm);
        return pm.getId();
    }

    public Integer saveList(List<Pm> pmList, Boolean isCover) {
        if (CollectionUtils.isEmpty(pmList)) {
            return 0;
        }
        if (isCover) {
            List<String> stringList = Lists.newArrayList();
            pmList.stream().forEach(t -> stringList.add(t.getPmNo()));
            this.deleteByNo(stringList);
        }
        return pmMapper.saveList(pmList);
    }


    public Pm getByNo(String no) {
        if (no == null) return null;
        return pmMapper.getByNO(no);
    }

    public Integer Import(String filePath, Boolean isCover, String operator) throws BusinessException, IOException, InvalidFormatException {
        if (!filePath.endsWith(".xlsx") && !filePath.endsWith(".xls")) {
            throw new BusinessException("请上传excel文件");
        }
        URL url = new URL(filePath);
        URLConnection conn = url.openConnection();
        InputStream inputStream = conn.getInputStream();
        //错误信息
        List<String> error = Lists.newArrayList();

        List<Pm> pmList = Lists.newArrayList();
        // Workbook 通用
        Workbook workbook = WorkbookFactory.create(inputStream);
        Sheet sheet = workbook.getSheetAt(0);
        // 总行数
        int rows = sheet.getPhysicalNumberOfRows();
        int k = 0;//所在行数
        for (int i = 1; i <= rows; i++) {
            Row row = sheet.getRow(i);
            if (row == null) {
                continue;
            }
            k = i + 1;
            Cell cell0 = row.getCell(0);
            if (cell0 == null) {//cell0==null作为检查改row是否无数据的触发条件
                if (cellIsNull(cell0) && cellIsNull(row.getCell(1)) && cellIsNull(row.getCell(2)) && cellIsNull(row.getCell(3)) && cellIsNull(row.getCell(4)) && cellIsNull(row.getCell(5)) && cellIsNull(row.getCell(6)) && cellIsNull(row.getCell(7))) {
                    continue;
                }
            }

            Pm pm = new Pm();
            pm.setOperator(operator);
            pm.setCreateTime(new Date());
            //excel就只有 8 列数据;  从1开始循环取值取到8
            StringBuffer name = new StringBuffer();
            for (int j = 0; j < 8; j++) {
                String stringCellValue;
                Cell cell = row.getCell(j);
                switch (j) {
                    //材料
                    case 0:
                        if (cell != null) {
                            cell.setCellType(CellType.STRING);
                            //去空格
                            stringCellValue = cell.getStringCellValue().replaceAll("\u00A0", "").trim();
                            if (StringUtils.isEmpty(stringCellValue)) {
                                error.add("第" + k + "行的材料编号为空");
                            } else if (null != this.getByNo(stringCellValue) && !Boolean.TRUE.equals(isCover)) {
                                error.add("第" + k + "行的材料编号已经存在");
                            } else {
                                pm.setPmNo(stringCellValue);

                            }
                        } else {
                            error.add("第" + k + "行的材料编号为空");
                        }
                        break;
                    //颜色
                    case 1:
                        cell.setCellType(CellType.STRING);
                        //去空格
                        stringCellValue = cell.getStringCellValue().replaceAll("\u00A0", "").trim();
                        name.append(stringCellValue).append("-");
                        break;
                    //规格
                    case 2:
                        cell.setCellType(CellType.STRING);
                        //去空格
                        stringCellValue = cell.getStringCellValue().replaceAll("\u00A0", "").trim();
                        name.append(stringCellValue);
                        if (!stringCellValue.endsWith("-")) {
                            name.append("-");
                        }
                        break;
                    //材质
                    case 3:
                        cell.setCellType(CellType.STRING);
                        //去空格
                        stringCellValue = cell.getStringCellValue().replaceAll("\u00A0", "").trim();
                        name.append(stringCellValue);
                        if (!stringCellValue.endsWith("-")) {
                            name.append("-");
                        }
                        break;
                    //称呼
                    case 4:
                        cell.setCellType(CellType.STRING);
                        //去空格
                        stringCellValue = cell.getStringCellValue().replaceAll("\u00A0", "").trim();
                        name.append(stringCellValue);
                        if (StringUtils.isEmpty(name.toString())) {
                            error.add("第" + k + "行的材料名称为空");
                        }
                        pm.setName(name.toString());
                        break;

                    //类别
                    case 5:
                        cell.setCellType(CellType.STRING);
                        //去空格
                        stringCellValue = cell.getStringCellValue().replaceAll("\u00A0", "").trim();
                        if (CollectionUtils.isEmpty(dictionaryService.getListByNameAndType(stringCellValue, 1))) {
                            error.add("第" + k + "行的材料类别有误为空");
                        } else {
                            pm.setType(stringCellValue);
                        }
                        break;
                    //单位
                    case 6:
                        cell.setCellType(CellType.STRING);
                        //去空格
                        stringCellValue = cell.getStringCellValue().replaceAll("\u00A0", "").trim();
                        if (CollectionUtils.isEmpty(dictionaryService.getListByNameAndType(stringCellValue, 2))) {
                            error.add("第" + k + "行的材料单位有误");
                        } else {
                            pm.setUnit(stringCellValue);
                        }
                        break;
                    //备注
                    case 7:
                        cell.setCellType(CellType.STRING);
                        //去空格
                        stringCellValue = cell.getStringCellValue().replaceAll("\u00A0", "").trim();
                        pm.setRemark(stringCellValue);
                        break;


                }
                pmList.add(pm);
            }
        }

        if (error.size() > 0) {
            StringBuffer errorString = new StringBuffer();
            error.stream().forEach(t -> errorString.append(t).append(","));
            throw new BusinessException(errorString.toString());
        } else {
            //insert
            return excelImportFactory.savePm(pmList, isCover);
        }
    }


    /**
     * 判断excel单元格是否为空
     */
    private Boolean cellIsNull(Cell cell) {
        Boolean falg = false;
        if (cell == null) {
            falg = true;
        } else {
            cell.setCellType(CellType.STRING);
            String cellValue = cell.getStringCellValue().replaceAll("\u00A0", "").trim();
            if (StringUtils.isEmpty(cellValue)) {
                falg = true;
            }
        }
        return falg;
    }
}
