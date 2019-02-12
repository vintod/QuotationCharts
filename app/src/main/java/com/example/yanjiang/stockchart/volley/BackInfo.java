package com.example.yanjiang.stockchart.volley;

import java.io.Serializable;

/**
 * Created by Administrator on 2014/10/6.
 */
public class BackInfo implements Serializable {
    private String result;//返回状态(true,false )
    private String msg;//返回内容(true,返回结果内容，false 返回错误提示信息)

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
