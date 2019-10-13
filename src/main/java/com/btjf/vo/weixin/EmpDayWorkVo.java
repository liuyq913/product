package com.btjf.vo.weixin;

import java.util.List;

public class EmpDayWorkVo {

    private String date;
    private Double sum;
    private List<EmpDayWorkDetailVo> dayWorkDetailVoList;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public List<EmpDayWorkDetailVo> getDayWorkDetailVoList() {
        return dayWorkDetailVoList;
    }

    public void setDayWorkDetailVoList(List<EmpDayWorkDetailVo> dayWorkDetailVoList) {
        this.dayWorkDetailVoList = dayWorkDetailVoList;
    }
}
