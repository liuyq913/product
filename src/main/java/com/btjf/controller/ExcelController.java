package com.btjf.controller;

import com.btjf.application.util.XaResult;
import com.btjf.controller.base.ProductBaseController;
import com.btjf.excel.ExcelHandlerHelper;
import com.wordnik.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
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
public class ExcelController extends ProductBaseController{

    @Resource
    private ExcelHandlerHelper excelHandlerHelper;


    /**
     * 获取数据
     *
     * @return
     */
    @RequestMapping(value = "/import", method = RequestMethod.POST)
    public XaResult<List<String>> importExcel(MultipartFile file, Integer fileType, Boolean isCover) throws Exception {
        if(fileType == null){
            return XaResult.error("数据类型为空");
        }
        if (file == null || file.isEmpty()) {
            return XaResult.error("请选择上传的文件");
        } else {
            return XaResult.success(excelHandlerHelper.getHandler(fileType).execute(file, isCover, getLoginUser().getUserName()));
        }
    }


    /**
     * 下载模板文件
     *
     * @return
     */
    @RequestMapping(value = "/download", method = RequestMethod.POST)
    public XaResult<String> importExcel(Integer fileType) throws Exception {
        if(fileType == null){
            return XaResult.error("数据类型为空");
        }
        String filename = null;
        switch (fileType){
            case 1:
                filename = "批量新增仓库材料.xlsx";
                break;
            case 2:
                filename = "仓库材料批量入库.xlsx";
                break;
            case 3:
                filename = "批量新增型号耗料.xlsx";
                break;
        }
        if (StringUtils.isEmpty(filename)){
            return XaResult.error("模板不存在");
        }
        String url = "/download/" + filename;
        return XaResult.success(url);

    }


}
