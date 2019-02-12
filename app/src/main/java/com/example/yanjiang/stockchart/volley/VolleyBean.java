package com.example.yanjiang.stockchart.volley;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by Administrator on 2014/10/6.
 */
public class VolleyBean implements Serializable {
    private boolean isSuccess = true; //是否正确返回
    private String content; //结果内容
    private String message;
    private int status;
    private Bitmap bitmap;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
//        content = "{\"msg\":" + content + "}";
        this.content = content;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Bitmap getBitmap() {
		return bitmap;
	}

	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}
    
    
}
