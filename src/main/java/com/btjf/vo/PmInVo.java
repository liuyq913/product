package com.btjf.vo;

import com.btjf.common.utils.DateUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PmInVo {

    private Integer id;

    private String pmNo;

    private String name;

    private String type;

    private Integer num;

    private String unit;

    private String remark;

    private String inDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPmNo() {
        return pmNo;
    }

    public void setPmNo(String pmNo) {
        this.pmNo = pmNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getInDate() {
        return inDate;
    }

    public void setInDate(Date inDate) {
        this.inDate =  DateUtil.dateToString(inDate, new SimpleDateFormat("yyyy/MM/dd"));
    }
}
