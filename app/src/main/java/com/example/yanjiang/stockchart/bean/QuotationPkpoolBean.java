package com.example.yanjiang.stockchart.bean;

import java.io.Serializable;

/**
 * Created by kinger on 2016/11/23.
 */
public class QuotationPkpoolBean implements Serializable {
    private String StokId;
    private double New;
    private double LastClose;
    private String hs;
    private String speed;
    private String stockName;

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

    public String getHs() {
        return hs;
    }

    public void setHs(String hs) {
        this.hs = hs;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }
}
