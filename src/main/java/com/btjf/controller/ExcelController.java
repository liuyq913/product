package com.btjf.controller;

import com.btjf.application.util.XaResult;
import com.btjf.excel.ExcelHandlerHelper;
import com.btjf.service.dictionary.DictionaryService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by Administrator on 2018/7/3 0003.
 */
@Api(value = "ExcelController", description = "Excel文件导入", position = 1)
@RequestMapping(value = "/excel")
@RestController("excelController")
public class ExcelController {

    @Resource
    private ExcelHandlerHelper excelHandlerHelper;


    /**
     * 获取数据
     *
     * @return
     */
    @RequestMapping(value = "/import", method = RequestMethod.POST)
    public XaResult<List<String>> importExcel(MultipartFile file, Integer fileType) {
        if(fileType == null){
            return XaResult.error("数据类型为空");
        }
        if (file == null || file.isEmpty()) {
            return XaResult.error("请选择上传的文件");
        } else {
            excelHandlerHelper.getHandler(fileType).execute(file);
        }
        return XaResult.success();
    }






}
