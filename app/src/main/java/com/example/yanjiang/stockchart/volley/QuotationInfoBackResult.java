package com.example.yanjiang.stockchart.volley;

import java.io.Serializable;

/**
 * Created by zhangxiaohang on 2017/3/14.
 */
public class QuotationInfoBackResult implements Serializable {
    private int state;
    private double zgb;
    private double ltgb;
    private long upnum;
    private long downnum;
    private long equalnum;
    private double shijing;
    private double shiying;
    private double lb;
    private double out;
    private double in;
    private double zsz;
    private double ltsz;
    private double hs;
    private double wbuy;
    private double wsell;
    private double wb;
    private double zf;
    private double zt;
    private double zs;
    private double dt;
    private double avgprice;
    private String error;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public double getZgb() {
        return zgb;
    }

    public void setZgb(double zgb) {
        this.zgb = zgb;
    }

    public double getLtgb() {
        return ltgb;
    }

    public void setLtgb(double ltgb) {
        this.ltgb = ltgb;
    }

    public long getUpnum() {
        return upnum;
    }

    public void setUpnum(long upnum) {
        this.upnum = upnum;
    }

    public long getDownnum() {
        return downnum;
    }

    public void setDownnum(long downnum) {
        this.downnum = downnum;
    }

    public long getEqualnum() {
        return equalnum;
    }

    public void setEqualnum(long equalnum) {
        this.equalnum = equalnum;
    }

    public double getShijing() {
        return shijing;
    }

    public void setShijing(double shijing) {
        this.shijing = shijing;
    }

    public double getShiying() {
        return shiying;
    }

    public void setShiying(double shiying) {
        this.shiying = shiying;
    }

    public double getLb() {
        return lb;
    }

    public void setLb(double lb) {
        this.lb = lb;
    }

    public double getOut() {
        return out;
    }

    public void setOut(double out) {
        this.out = out;
    }

    public double getIn() {
        return in;
    }

    public void setIn(double in) {
        this.in = in;
    }

    public double getZsz() {
        return zsz;
    }

    public void setZsz(double zsz) {
        this.zsz = zsz;
    }

    public double getLtsz() {
        return ltsz;
    }

    public void setLtsz(double ltsz) {
        this.ltsz = ltsz;
    }

    public double getHs() {
        return hs;
    }

    public void setHs(double hs) {
        this.hs = hs;
    }

    public double getWbuy() {
        return wbuy;
    }

    public void setWbuy(double wbuy) {
        this.wbuy = wbuy;
    }

    public double getWsell() {
        return wsell;
    }

    public void setWsell(double wsell) {
        this.wsell = wsell;
    }

    public double getWb() {
        return wb;
    }

    public void setWb(double wb) {
        this.wb = wb;
    }

    public double getZf() {
        return zf;
    }

    public void setZf(double zf) {
        this.zf = zf;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public double getZt() {
        return zt;
    }

    public void setZt(double zt) {
        this.zt = zt;
    }

    public double getZs() {
        return zs;
    }

    public void setZs(double zs) {
        this.zs = zs;
    }

    public double getDt() {
        return dt;
    }

    public void setDt(double dt) {
        this.dt = dt;
    }

    public double getAvgprice() {
        return avgprice;
    }

    public void setAvgprice(double avgprice) {
        this.avgprice = avgprice;
    }
}
