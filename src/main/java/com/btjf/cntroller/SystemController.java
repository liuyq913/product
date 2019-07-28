package com.btjf.cntroller;

import com.btjf.application.util.XaResult;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * Created by Administrator on 2018/7/3 0003.
 */
@Api(value = "SystemController", description = "系统", position = 1)
@RequestMapping(value = "/system")
@RestController("systemController")
public class SystemController {

    /**
     * 获取数据
     *
     * @return
     */
    @RequestMapping(value = "/data", method = RequestMethod.POST)
    public XaResult<List<String>> data(@ApiParam("数据类型 1 材料类别  2材料单位  3产品类型 4工种") Integer type) {


        return XaResult.success();
    }




}
