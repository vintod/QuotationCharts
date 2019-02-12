package com.example.yanjiang.stockchart.bean;

import java.io.Serializable;

/**
 * Created by zhangxiaohang on 2016/12/1.
 */
public class QuotationDetailBean implements Serializable{
    private double New;
    private double BuyPrice;
    private double SellPrice;
    private double Volume;
    private long time;

    public double getNew() {
        return New;
    }

    public void setNew(double aNew) {
        New = aNew;
    }

    public double getBuyPrice() {
        return BuyPrice;
    }

    public void setBuyPrice(double buyPrice) {
        BuyPrice = buyPrice;
    }

    public double getSellPrice() {
        return SellPrice;
    }

    public void setSellPrice(double sellPrice) {
        SellPrice = sellPrice;
    }

    public double getVolume() {
        return Volume;
    }

    public void setVolume(double volume) {
        Volume = volume;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
