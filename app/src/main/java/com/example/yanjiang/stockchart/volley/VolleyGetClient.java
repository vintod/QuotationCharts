package com.example.yanjiang.stockchart.volley;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import org.apache.http.protocol.HTTP;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class VolleyGetClient<T> extends Request<T> {

    private final Response.Listener<T> responseListener;
    private Class<T> mClass;


    public  VolleyGetClient(int method, String url, Class<T> clazz, Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        mClass = clazz;
        responseListener = listener;
        setRetryPolicy(new DefaultRetryPolicy(10000,0,0));
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> headers = new HashMap<String, String>();
        return headers;
    }

    @Override
    protected String getParamsEncoding() {
        return "UTF-8";
    }



    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        String jsonString = null;
        try {
//            response.headers.put(HTTP.CONTENT_TYPE, "application/json;charset=GBK");
//            response.headers.put(HTTP.CONTENT_TYPE, "text/html;charset=GBK");
//            response.headers.put(HTTP.CONTENT_TYPE, "application/octet-stream;charset=UTF-8");
        	response.headers.put(HTTP.CONTENT_TYPE, "application/json;charset=UTF-8");

            String result = new String(response.data, HttpHeaderParser.parseCharset(response.headers));

//            String result = new String(response.data,"UTF-8");
//            result = URLDecoder.decode(result, "utf-8");
//            BufferedReader br = new BufferedReader(new InputStreamReader(response., "UTF-8"));
            Log.d("------>>>>>","服务段：" + result);
            return Response.success(JSON.parseObject(result, mClass), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            jsonString = "{\"status\":\"0\",\"msg\":\"请求超时\"}";
        }
        return Response.success(JSON.parseObject(jsonString, mClass), HttpHeaderParser.parseCacheHeaders(response));
    }

    @Override
    protected void deliverResponse(T t) {
        responseListener.onResponse(t);
    }

//    @Override
//    public RetryPolicy getRetryPolicy() {
//        super.getRetryPolicy();
//        // Volley设置请求超时时间
//        //1、最大超时时间
//        //2、The maximum number of attempts 最大尝试次数
//        //3、注意最后一个参数，它允许你指定一个退避乘数可以用来实现“指数退避”来从RESTful服务器请求数据
//        RetryPolicy retryPolicy = new DefaultRetryPolicy(10000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
//        return retryPolicy;
//    }


}
