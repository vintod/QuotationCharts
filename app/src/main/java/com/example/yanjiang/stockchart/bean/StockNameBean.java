package com.example.yanjiang.stockchart.bean;

import java.io.Serializable;

/**
 * Created by zhangxiaohang on 2016/11/21.
 */
public class StockNameBean implements Serializable {
    private String StockId;
    private String StockName;
    private String Spell;
    private String updateTime;
    private String macketNo;
    private String stockNo;
    private String pinyin;
    private String sellsId;

    public String getMacketNo() {
        return macketNo;
    }
    public void setMacketNo(String macketNo) {
        this.macketNo = macketNo;
    }
    public String getStockNo() {
        return stockNo;
    }
    public void setStockNo(String stockNo) {
        this.stockNo = stockNo;
    }
    public String getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
    public String getStockId() {
        return StockId;
    }
    public void setStockId(String stockId) {
        StockId = stockId;
    }
    public String getStockName() {
        return StockName;
    }
    public void setStockName(String stockName) {
        StockName = stockName;
    }
    public String getPinyin() {
        return pinyin;
    }
    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getSpell() {
        return Spell;
    }

    public void setSpell(String spell) {
        Spell = spell;
    }

    public String getSellsId() {
        return sellsId;
    }

    public void setSellsId(String sellsId) {
        this.sellsId = sellsId;
    }

    @Override
    public boolean equals(Object o) {
        StockNameBean bean = (StockNameBean) o;
        return StockId.equals(bean.getStockId());
    }

    @Override
    public int hashCode() {
        return StockId.hashCode();
    }
}
