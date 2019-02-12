package com.example.yanjiang.stockchart.bean;

import java.io.Serializable;

/**
 * Created by zhangxiaohang on 2017/3/9.
 */
public class QuotationMineBean implements Serializable{
    private long publish;
    private String htmlUrl;

    public long getPublish() {
        return publish;
    }

    public void setPublish(long publish) {
        this.publish = publish;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }
}
