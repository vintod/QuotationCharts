package com.example.yanjiang.stockchart.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhangxiaohang on 2016/10/19.
 */
public class QuotationStockInfoBean implements Serializable {
    private int state;
    private double Open;
    private double High;
    private double Low;
    private double New;
    private double Volume;
    private double Amount;
    private double LastClose;
    private long time;
    private List<QuotationStockPointBean> BS5;
    public void setState(int state) {
        this.state = state;
    }
    public int getState() {
        return state;
    }

    public void setOpen(float Open) {
        this.Open = Open;
    }

    public void setOpen(double open) {
        Open = open;
    }

    public double getHigh() {
        return High;
    }

    public void setHigh(double high) {
        High = high;
    }

    public double getLow() {
        return Low;
    }

    public void setLow(double low) {
        Low = low;
    }

    public double getNew() {
        return New;
    }

    public void setNew(double aNew) {
        New = aNew;
    }

    public double getVolume() {
        return Volume;
    }

    public void setVolume(double volume) {
        Volume = volume;
    }

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double amount) {
        Amount = amount;
    }

    public double getLastClose() {
        return LastClose;
    }

    public void setLastClose(double lastClose) {
        LastClose = lastClose;
    }

    public List<QuotationStockPointBean> getBS5() {
        return BS5;
    }

    public void setBS5(List<QuotationStockPointBean> BS5) {
        this.BS5 = BS5;
    }

    public double getOpen() {
        return Open;
    }



    public void setTime(long time) {
        this.time = time;
    }
    public long getTime() {
        return time;
    }


}
