package com.btjf.controller.weixin;

import com.btjf.application.util.XaResult;
import com.btjf.model.order.Order;
import com.btjf.vo.weixin.EmpProcedureListVo;
import com.btjf.vo.weixin.MineIndexVo;
import com.btjf.vo.weixin.OrderVo;
import com.wordnik.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "MineController", description = "小程序 个人中心", position = 1)
@RequestMapping(value = "/wx/mine")
@RestController("mineController")
public class MineController {


    /**
     * 个人中心主页
     *
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public XaResult<MineIndexVo> importExcel(){

        return null;
    }


    /**
     * 计件上报-订单列表
     *获取当月 质检通过的订单
     * @return
     */
    @RequestMapping(value = "/order/list", method = RequestMethod.GET)
    public XaResult<List<OrderVo>> list(String date){

        return null;
    }


    /**
     * 计件上报-订单产品详情
     *获取当月 质检通过的订单
     * @return
     */
    @RequestMapping(value = "/order/detail", method = RequestMethod.GET)
    public XaResult<List<EmpProcedureListVo>> detail(String orderNo, String productNo){

        return null;
    }

    /**
     * 计件上报-添加员工
     *查询员工 是否存在
     * 返回 员工姓名 部门名字
     * @return
     */
    @RequestMapping(value = "/emp/query", method = RequestMethod.GET)
    public XaResult<MineIndexVo> query(String empName){

        //return XaResult.error("查无此员工，请修改");
        return XaResult.success();
    }



}
