package com.example.yanjiang.stockchart.volley;



import com.example.yanjiang.stockchart.bean.BuySoldPointBean;
import com.example.yanjiang.stockchart.bean.QuotationDetailBean;
import com.example.yanjiang.stockchart.bean.QuotationPkBean;
import com.example.yanjiang.stockchart.bean.QuotationPkpoolBean;
import com.example.yanjiang.stockchart.bean.QuotationStockBean;
import com.example.yanjiang.stockchart.bean.QuotationStockPointBean;
import com.example.yanjiang.stockchart.bean.StockNameBean;

import java.io.Serializable;
import java.util.List;



/**
 * Created by zhangxiaohang on 2016/11/1.
 */
public class QuotationBackResult implements Serializable {
    private int state;
    private double NewSZ;
    private double LastCloseSZ;
    private double NewSS;
    private double LastCloseSS;
    private double NewCY;
    private double LastCloseCY;
    private double Open;
    private double High;
    private double Low;
    private double New;
    private int Volume;
    private double Amount;
    private double LastClose;
    private long totalv;
    private List<QuotationPkBean> pk;

    private List<QuotationPkpoolBean> pkpool;

    private List<QuotationStockBean> Klineresult;
    private long time;
    private List<QuotationStockPointBean> BS5;
    private String error;
    private boolean IsCompress;
    private long CompressSize;
    private long UnCompressSize;
    private String StringStock;
    private List<StockNameBean> StockInfo;
    private List<BuySoldPointBean> bspoint;
    private List<QuotationDetailBean> Cjmxresult;
    private long up;
    private long equal;
    private long down;


    public boolean isCompress() {
        return IsCompress;
    }

    public void setCompress(boolean compress) {
        IsCompress = compress;
    }

    public long getCompressSize() {
        return CompressSize;
    }

    public void setCompressSize(long compressSize) {
        CompressSize = compressSize;
    }

    public long getUnCompressSize() {
        return UnCompressSize;
    }

    public void setUnCompressSize(long unCompressSize) {
        UnCompressSize = unCompressSize;
    }

    public String getStringStock() {
        return StringStock;
    }

    public void setStringStock(String stringStock) {
        StringStock = stringStock;
    }

    public double getOpen() {
        return Open;
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

    public int getVolume() {
        return Volume;
    }

    public void setVolume(int volume) {
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

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public List<QuotationStockPointBean> getBS5() {
        return BS5;
    }

    public void setBS5(List<QuotationStockPointBean> BS5) {
        this.BS5 = BS5;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<QuotationDetailBean> getCjmxresult() {
        return Cjmxresult;
    }

    public void setCjmxresult(List<QuotationDetailBean> cjmxresult) {
        Cjmxresult = cjmxresult;
    }

    public List<QuotationStockBean> getKlineresult() {
        return Klineresult;
    }

    public void setKlineresult(List<QuotationStockBean> klineresult) {
        Klineresult = klineresult;
    }

    public List<StockNameBean> getStockInfo() {
        return StockInfo;
    }

    public void setStockInfo(List<StockNameBean> stockInfo) {
        StockInfo = stockInfo;
    }

    public List<QuotationPkBean> getPk() {
        return pk;
    }

    public void setPk(List<QuotationPkBean> pk) {
        this.pk = pk;
    }


    public List<BuySoldPointBean> getBspoint() {
        return bspoint;
    }

    public void setBspoint(List<BuySoldPointBean> bspoint) {
        this.bspoint = bspoint;
    }

    public long getTotalv() {
        return totalv;
    }

    public void setTotalv(long totalv) {
        this.totalv = totalv;
    }

    public long getUp() {
        return up;
    }

    public void setUp(long up) {
        this.up = up;
    }

    public long getEqual() {
        return equal;
    }

    public void setEqual(long equal) {
        this.equal = equal;
    }

    public long getDown() {
        return down;
    }

    public void setDown(long down) {
        this.down = down;
    }

    public List<QuotationPkpoolBean> getPkpool() {
        return pkpool;
    }

    public void setPkpool(List<QuotationPkpoolBean> pkpool) {
        this.pkpool = pkpool;
    }

    public double getNewSZ() {
        return NewSZ;
    }

    public void setNewSZ(double newSZ) {
        NewSZ = newSZ;
    }

    public double getLastCloseSZ() {
        return LastCloseSZ;
    }

    public void setLastCloseSZ(double lastCloseSZ) {
        LastCloseSZ = lastCloseSZ;
    }

    public double getNewSS() {
        return NewSS;
    }

    public void setNewSS(double newSS) {
        NewSS = newSS;
    }

    public double getLastCloseSS() {
        return LastCloseSS;
    }

    public void setLastCloseSS(double lastCloseSS) {
        LastCloseSS = lastCloseSS;
    }

    public double getNewCY() {
        return NewCY;
    }

    public void setNewCY(double newCY) {
        NewCY = newCY;
    }

    public double getLastCloseCY() {
        return LastCloseCY;
    }

    public void setLastCloseCY(double lastCloseCY) {
        LastCloseCY = lastCloseCY;
    }
}
