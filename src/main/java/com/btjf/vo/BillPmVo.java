package com.btjf.vo;

public class BillPmVo {

    private String pmNo;

    private String pmName;

    private Integer sum;//总数

    private String unit;
    //以下 返回参数  件/双耗料/需用料数量  接口专用

    private Integer allowNum;//可领用
    private String pmBatchNo;//材料批次号
    private String remark;

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

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getAllowNum() {
        return allowNum;
    }

    public void setAllowNum(Integer allowNum) {
        this.allowNum = allowNum;
    }

    public String getPmBatchNo() {
        return pmBatchNo;
    }

    public void setPmBatchNo(String pmBatchNo) {
        this.pmBatchNo = pmBatchNo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
