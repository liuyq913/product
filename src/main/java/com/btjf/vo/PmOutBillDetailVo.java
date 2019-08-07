package com.btjf.vo;

import com.btjf.common.utils.DateUtil;
import com.btjf.model.order.OrderProduct;
import com.btjf.model.pm.PmOutBill;
import com.btjf.model.pm.PmOutBillDetail;

import java.util.List;

public class PmOutBillDetailVo {

    private Integer id;//领料单ID
    private String orderNo;
    private String productNo;
    private String billNo;
    private String groupNmae;//领用组
    private String pmType;//材料类别
    private String pmCheckItem;//检测项目
    private String operator;//开票人
    private Integer sum;//总数
    private String billDate;
    private String workshop;//车间
    private Integer num;//分配数
    private Integer maxNum;//上限数
    private List<BillPmVo> list;

    public PmOutBillDetailVo() {
    }

    public PmOutBillDetailVo(PmOutBill pmOutBill, OrderProduct orderProduct, List<PmOutBillDetail> plist) {
        id = pmOutBill.getId();
        orderNo = pmOutBill.getOrderNo();
        productNo = pmOutBill.getProductNo();
        billNo = pmOutBill.getBillNo();
        groupNmae = pmOutBill.getGroupNmae();
        pmType = pmOutBill.getPmType();
        pmCheckItem = pmOutBill.getPmCheckItem();
        operator = pmOutBill.getOperator();
        workshop = pmOutBill.getWorkshop();
        sum = orderProduct.getNum();
        maxNum = orderProduct.getMaxNum();
        billDate = DateUtil.dateToString(pmOutBill.getCreateTime(), DateUtil.ymdFormat);
        if(plist != null && plist.size() >0){
            for (int i=0; i< plist.size(); i++){
                //TODO  未完待续
            }
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getGroupNmae() {
        return groupNmae;
    }

    public void setGroupNmae(String groupNmae) {
        this.groupNmae = groupNmae;
    }

    public String getPmType() {
        return pmType;
    }

    public void setPmType(String pmType) {
        this.pmType = pmType;
    }

    public String getPmCheckItem() {
        return pmCheckItem;
    }

    public void setPmCheckItem(String pmCheckItem) {
        this.pmCheckItem = pmCheckItem;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public String getWorkshop() {
        return workshop;
    }

    public void setWorkshop(String workshop) {
        this.workshop = workshop;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(Integer maxNum) {
        this.maxNum = maxNum;
    }

    public List<BillPmVo> getList() {
        return list;
    }

    public void setList(List<BillPmVo> list) {
        this.list = list;
    }
}
