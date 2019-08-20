package com.btjf.controller.weixin;

import com.btjf.application.util.XaResult;
import com.btjf.common.utils.DateUtil;
import com.btjf.controller.base.ProductBaseController;
import com.btjf.controller.weixin.vo.WxEmpVo;
import com.btjf.model.emp.Emp;
import com.btjf.model.order.Order;
import com.btjf.model.sys.Sysdept;
import com.btjf.service.emp.EmpService;
import com.btjf.service.order.ProductionProcedureConfirmService;
import com.btjf.service.sys.SysDeptService;
import com.btjf.vo.weixin.*;
import com.wordnik.swagger.annotations.Api;
import org.apache.commons.collections.list.AbstractLinkedList;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Api(value = "MineController", description = "小程序 个人中心", position = 1)
@RequestMapping(value = "/wx/mine")
@RestController("mineController")
public class MineController  extends ProductBaseController {

    @Resource
    private EmpService empService;
    @Resource
    private SysDeptService sysDeptService;
    @Resource
    private ProductionProcedureConfirmService productionProcedureConfirmService;


    /**
     * 个人中心主页
     *
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public XaResult<MineIndexVo> importExcel(){
        WxEmpVo vo = getWXLoginUser();
        MineIndexVo mineIndexVo = null;
        if(vo != null){
            mineIndexVo = new MineIndexVo();
            mineIndexVo.setName(vo.getName());
            mineIndexVo.setDeptName(vo.getDeptName());
            if (vo.getIsLeader() == 1){
                mineIndexVo.setIsShowMenu(1);
                mineIndexVo.setPosition("车间主任");
            }
        }
        return XaResult.success(mineIndexVo);
    }


    /**
     * 计件上报-订单列表
     *获取当月 质检通过的订单
     * @return
     */
    @RequestMapping(value = "/order/list", method = RequestMethod.GET)
    public XaResult<List<OrderVo>> list(String date){
        //2019-08
        if (StringUtils.isEmpty(date)){
            return XaResult.error("月份不能为空");
        }

        List<Order> list = productionProcedureConfirmService.getOrderByMouth(date);
        List<OrderVo> voList = null;
        if(list != null && list.size() >0){
            voList = new ArrayList<>();
            for (Order o: list){
                OrderVo orderVo = new OrderVo();
                orderVo.setDate(DateUtil.dateToString(o.getCreateTime(),new SimpleDateFormat("yyyy/MM/dd")));
                orderVo.setOrderNo(o.getOrderNo());
                List<OrderProductVo> ops = productionProcedureConfirmService.getOrderProductByMouth(o.getOrderNo());
                orderVo.setList(ops);
                voList.add(orderVo);
            }

        }
        return XaResult.success(voList);
    }


    /**
     * 计件上报-订单产品详情
     *获取当月 质检通过的订单
     * @return
     */
    @RequestMapping(value = "/order/detail", method = RequestMethod.GET)
    public XaResult<List<EmpProcedureListVo>> detail(String orderNo, String productNo){
        if (StringUtils.isEmpty(orderNo)){
            return XaResult.error("订单号不能为空");
        }
        if (StringUtils.isEmpty(productNo)){
            return XaResult.error("产品型号不能为空");
        }

        List<EmpProcedureListVo> list = productionProcedureConfirmService.getEmpNum(orderNo, productNo);

        return XaResult.success(list);
    }

    /**
     * 计件上报-添加员工
     *查询员工 是否存在
     * 返回 员工姓名 部门名字
     * @return
     */
    @RequestMapping(value = "/emp/query", method = RequestMethod.GET)
    public XaResult<MineIndexVo> query(String empName){
        if (StringUtils.isEmpty(empName)){
            return XaResult.error("员工名字不能为空");
        }
         Emp emp =  empService.getByName(empName);
        if(emp == null){
            return XaResult.error("查无此员工，请修改");
        }
        Sysdept dept = sysDeptService.get(emp.getDeptId());
        MineIndexVo vo = new MineIndexVo();
        vo.setName(empName);
        vo.setDeptName(dept==null?null:dept.getDeptName());
        return XaResult.success();
    }

    /**
     * 计件上报-订单产品详情-上传工序计件
     *获取当月 质检通过的订单
     * @return
     */
    @RequestMapping(value = "/order/report", method = RequestMethod.POST)
    public XaResult<String> report(String orderNo, String productNo, Integer procedureId,
                                                     String[] content){
        if (StringUtils.isEmpty(orderNo)){
            return XaResult.error("订单号不能为空");
        }
        if (StringUtils.isEmpty(productNo)){
            return XaResult.error("产品型号不能为空");
        }
        if (procedureId == null){
            return XaResult.error("工序ID不能为空");
        }
        if (content == null || content.length <1){
            return XaResult.error("上报内容不能为空");
        }

        List<EmpProcedureDetailVo> list = new ArrayList<>();
        Integer num = 0;//上报的总数
        for (int i=0; i<content.length; i++){
            String ep = content[i];
            String[] s = ep.split("\\|");
            if(s.length <2){
                return XaResult.error("上报信息有误");
            }
            EmpProcedureDetailVo vo = new EmpProcedureDetailVo();
            vo.setEmpId(Integer.valueOf(s[0]));
            vo.setNum(Integer.valueOf(s[1]));
            num = num + vo.getNum();
            list.add(vo);
        }

        WxEmpVo vo = getWXLoginUser();

        return null;
    }

    /**
     * 工作产出
     * @return
     */
    @RequestMapping(value = "/work", method = RequestMethod.GET)
    public XaResult<List<EmpProcedureListVo>> detail(String date){
        WxEmpVo vo = getWXLoginUser();



        return null;
    }

}
