package com.btjf.controller;

import com.btjf.application.util.XaResult;
import com.btjf.service.dictionary.DictionaryService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by Administrator on 2018/7/3 0003.
 */
@Api(value = "SystemController", description = "系统", position = 1)
@RequestMapping(value = "/system")
@RestController("systemController")
public class SystemController {

    @Resource
    private DictionaryService dictionaryService;

    /**
     * 获取数据
     *
     * @return
     */
    @RequestMapping(value = "/data", method = RequestMethod.GET)
    public XaResult<List<String>> data(@ApiParam("数据类型 1 材料类别  2材料单位  3产品类型") Integer type) {
        if(type == null){
            return XaResult.error("数据类型为空");
        }
        List<String> list = dictionaryService.getList(type);
        return XaResult.success(list);
    }






}
