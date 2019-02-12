package com.example.yanjiang.stockchart.bean;

import java.io.Serializable;

/**
 * Created by zhangxiaohang on 2016/10/17.
 */
public class QuotationStockPointBean implements Serializable{
    private double BuyPrice;

    private int BuyVolume;

    private double SellPrice;

    private int SellVolume;

    public void setBuyPrice(double BuyPrice){
        this.BuyPrice = BuyPrice;
    }
    public double getBuyPrice(){
        return this.BuyPrice;
    }
    public void setBuyVolume(int BuyVolume){
        this.BuyVolume = BuyVolume;
    }
    public int getBuyVolume(){
        return this.BuyVolume;
    }
    public void setSellPrice(double SellPrice){
        this.SellPrice = SellPrice;
    }
    public double getSellPrice(){
        return this.SellPrice;
    }
    public void setSellVolume(int SellVolume){
        this.SellVolume = SellVolume;
    }
    public int getSellVolume(){
        return this.SellVolume;
    }
}
