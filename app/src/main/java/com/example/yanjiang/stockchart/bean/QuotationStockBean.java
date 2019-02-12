package com.example.yanjiang.stockchart.bean;

import java.io.Serializable;

/**
 * Created by zhangxiaohang on 2016/10/17.
 */
public class QuotationStockBean implements Serializable{
    private float Open;
    private float High;
    private float Low;
    private float Volume;
    private float Amount;
    private long time;
    private float head;
    private float Close;
    private float indexclose;

    public float getOpen() {
        return Open;
    }

    public void setOpen(float open) {
        Open = open;
    }

    public float getHigh() {
        return High;
    }

    public void setHigh(float high) {
        High = high;
    }

    public float getLow() {
        return Low;
    }

    public void setLow(float low) {
        Low = low;
    }

    public float getVolume() {
        return Volume;
    }

    public void setVolume(float volume) {
        Volume = volume;
    }

    public float getAmount() {
        return Amount;
    }

    public void setAmount(float amount) {
        Amount = amount;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public float getHead() {
        return head;
    }

    public void setHead(float head) {
        this.head = head;
    }

    public float getClose() {
        return Close;
    }

    public void setClose(float close) {
        Close = close;
    }

    public float getIndexclose() {
        return indexclose;
    }

    public void setIndexclose(float indexclose) {
        this.indexclose = indexclose;
    }
}
