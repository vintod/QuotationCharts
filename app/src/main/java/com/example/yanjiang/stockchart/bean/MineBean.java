package com.example.yanjiang.stockchart.bean;

import java.io.Serializable;

/**
 * Created by zhangxiaohang on 2016/11/24.
 */
public class MineBean implements Serializable{
    private long iPublish;
    private String sDtInfoUrl;
    private int index;

    public long getIPublish() {
        return iPublish;
    }

    public void setIPublish(long iPublish) {
        this.iPublish = iPublish;
    }

    public String getSDtInfoUrl() {
        return sDtInfoUrl;
    }

    public void setSDtInfoUrl(String sDtInfoUrl) {
        this.sDtInfoUrl = sDtInfoUrl;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
