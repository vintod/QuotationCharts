package com.example.yanjiang.stockchart.bean;

import java.io.Serializable;

/**
 * Created by kinger on 2016/11/23.
 */
public class QuotationPkBean implements Serializable {
    private String StokId;
    private double New;
    private double LastClose;
    private double RiseSpeed;
    private double Hs;
    private double Lb;
    private double Amount;

    public double getRiseSpeed() {
        return RiseSpeed;
    }

    public void setRiseSpeed(double riseSpeed) {
        RiseSpeed = riseSpeed;
    }

    public double getHs() {
        return Hs;
    }

    public void setHs(double hs) {
        Hs = hs;
    }

    public double getLb() {
        return Lb;
    }

    public void setLb(double lb) {
        Lb = lb;
    }

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double amount) {
        Amount = amount;
    }

    public String getStokId() {
        return StokId;
    }

    public void setStokId(String stokId) {
        StokId = stokId;
    }

    public double getNew() {
        return New;
    }

    public void setNew(double aNew) {
        New = aNew;
    }

    public double getLastClose() {
        return LastClose;
    }

    public void setLastClose(double lastClose) {
        LastClose = lastClose;
    }

}
