package com.btjf.vo;

import com.btjf.common.utils.DateUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PmOutStockDetailVo {

    private String pmNo;

    private String pmName;

    private Integer num;

    private String unit;

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
}
