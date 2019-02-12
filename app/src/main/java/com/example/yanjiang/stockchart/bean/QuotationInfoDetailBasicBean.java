package com.example.yanjiang.stockchart.bean;

import java.io.Serializable;

/**
 * Created by zhangxiaohang on 2017/3/14.
 */
public class QuotationInfoDetailBasicBean implements Serializable{
    private String sectionName;
    private double valve;
    private int type;
    private int color;
    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public double getValve() {
        return valve;
    }

    public void setValve(double valve) {
        this.valve = valve;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
