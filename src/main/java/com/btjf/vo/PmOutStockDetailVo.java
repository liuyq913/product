package com.btjf.vo;

import com.btjf.common.utils.DateUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PmOutStockDetailVo {

    private String pmNo;

    private String pmName;

    private Integer sum;//总数

    private String unit;
    //以下 返回参数  件/双耗料/需用料数量  接口专用
    private String type;
    private String remark;
    private Integer num;//单位数
    private Integer unitNum;//倒数

    public String getPmNo() {
        return pmNo;
    }

    public void setPmNo(String pmNo) {
        this.pmNo = pmNo;
    }

    public String getPmName() {
        return pmName;
    }

    public void setPmName(String pmName) {
        this.pmName = pmName;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getUnitNum() {
        return unitNum;
    }

    public void setUnitNum(Integer unitNum) {
        this.unitNum = unitNum;
    }
}
