package com.example.hsm.beans;

import java.sql.Date;

public class Book {
    private Integer Bid;
    private Integer Rid;
    private String UserCode;
    private Date StartTime;
    private Date EndTime;
    private Float Price;
    private String Checked;
    private String Paid;

    public Integer getBid() {
        return Bid;
    }

    public void setBid(Integer bid) {
        Bid = bid;
    }

    public Integer getRid() {
        return Rid;
    }

    public void setRid(Integer rid) {
        Rid = rid;
    }

    public String getUserCode() {
        return UserCode;
    }

    public void setUserCode(String userCode) {
        UserCode = userCode;
    }

    public Date getStartTime() {
        return StartTime;
    }

    public void setStartTime(Date startTime) {
        StartTime = startTime;
    }

    public Date getEndTime() {
        return EndTime;
    }

    public void setEndTime(Date endTime) {
        EndTime = endTime;
    }

    public Float getPrice() {
        return Price;
    }

    public void setPrice(Float price) {
        Price = price;
    }

    public String getChecked() {
        return Checked;
    }

    public void setChecked(String checked) {
        Checked = checked;
    }

    public String getPaid() {
        return Paid;
    }

    public void setPaid(String paid) {
        Paid = paid;
    }
}
