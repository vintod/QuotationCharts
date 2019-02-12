package com.example.yanjiang.stockchart.bean;

import java.io.Serializable;

/**
 * Created by zhangxiaohang on 2016/11/25.
 */
public class BuySoldPointBean implements Serializable {
    private String description;
    private String sellId;
    private String sellsName;
    private String descUrl;
    private String content;
    private String imgUrl;
    private String klineType;
    private long time;
    private double signalPrice;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSellId() {
        return sellId;
    }

    public void setSellId(String sellId) {
        this.sellId = sellId;
    }

    public String getSellsName() {
        return sellsName;
    }

    public void setSellsName(String sellsName) {
        this.sellsName = sellsName;
    }

    public String getDescUrl() {
        return descUrl;
    }

    public void setDescUrl(String descUrl) {
        this.descUrl = descUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getKlineType() {
        return klineType;
    }

    public void setKlineType(String klineType) {
        this.klineType = klineType;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public double getSignalPrice() {
        return signalPrice;
    }

    public void setSignalPrice(double signalPrice) {
        this.signalPrice = signalPrice;
    }
}
