package com.example.yanjiang.stockchart.bean;

import java.io.Serializable;

/**
 * Created by houwentao on 2017/5/26.
 */

public class QuotationMABean implements Serializable {
    static final long serialVersionUID = 236553769354460498L;

    private int maIndex;
    /**
     * 均线值
     */
    private int maValue;


    public int getMaIndex() {
        return maIndex;
    }

    public void setMaIndex(int maIndex) {
        this.maIndex = maIndex;
    }

    public int getMaValue() {
        return maValue;
    }

    public void setMaValue(int maValue) {
        this.maValue = maValue;
    }
}
