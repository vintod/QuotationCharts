package com.example.yanjiang.stockchart.volley;

import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;

import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.protocol.HTTP;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2014/10/27.
 */
@SuppressWarnings("deprecation")
public class VolleyPostClient<T> extends Request<T> {
    private Response.Listener<T> responseListener = null;
    private Class<T> mClass;
    private MultipartEntity entity = new MultipartEntity();
    private final Map<String, String> params;
    private List<File> mFiles;
    private String mFileName;
    private List<String> mFileNames;


    public VolleyPostClient(int method, String url, Map<String, String> params, Class<T> clazz, String fileName, File file, Response.Listener<T> listener, Response.ErrorListener errorListener) {

        super(method, url, errorListener);
        mClass = clazz;
        responseListener = listener;
        this.params = params;
        mFiles = new ArrayList<File>();
        if (file != null) {
            mFiles.add(file);
        }
        if (!TextUtils.isEmpty(fileName)) {
            mFileName = fileName;
        }
        setShouldCache(false);
        setRetryPolicy(new DefaultRetryPolicy(10000, 0, 0));
        buildMultipartEntity();
    }

    public VolleyPostClient(int method, String url, Map<String, String> params, Class<T> clazz, List<String> fileNames, List<File> files, Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        mClass = clazz;
        responseListener = listener;
        this.params = params;
        this.mFiles = files;
        this.mFileNames = fileNames;
        setShouldCache(false);
        setRetryPolicy(new DefaultRetryPolicy(30000, 0, 0));
        buildMultipartEntitys();
    }

    @SuppressWarnings("deprecation")
    private void buildMultipartEntity() {
        if (mFiles != null && mFiles.size() > 0) {
            for (File file : mFiles) {
                entity.addPart(mFileName, new FileBody(file));
            }
        }
        try {
            for (String key : params.keySet()) {
                entity.addPart(key, new StringBody(params.get(key), Charset.forName("UTF-8")));
//            	entity.addPart(key, new StringBody(params.get(key)));
//            	entity.addPart(key, new StringBody(URLEncoder.encode(params.get(key), "utf-8")));
            }
        } catch (UnsupportedEncodingException e) {
            VolleyLog.e("UnsupportedEncodingException");
        }
    }

    @SuppressWarnings("deprecation")
    private void buildMultipartEntitys() {
        if (mFiles != null && mFiles.size() > 0 && mFileNames != null && mFileNames.size() > 0 && mFiles.size() == mFileNames.size()) {
            for (int i = 0; i < mFiles.size(); i++) {
                File file = mFiles.get(i);
                entity.addPart(mFileNames.get(i), new FileBody(file));
            }
        }
        try {
            for (String key : params.keySet()) {
                entity.addPart(key, new StringBody(params.get(key), Charset.forName("UTF-8")));
            }
        } catch (UnsupportedEncodingException e) {
            VolleyLog.e("UnsupportedEncodingException");
        }
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
    protected Map<String, String> getParams() throws AuthFailureError {
//        Map<String, String> headers = new HashMap<String, String>();
//        headers.put("Charset", "UTF-8");
////        headers.put("Content-Type", "application/x-javascript; charset=UTF-8");
////        headers.put("Content-Type", "application/octet-stream; charset=utf-8");
//        headers.put("Content-Type", "multipart/form-data; charset=utf-8");
//        headers.put("Accept-Encoding", "gzip,deflate");
//        return headers;
        return params;
    }

    @Override
    public String getBodyContentType() {
//        return "application/x-www-form-urlencoded; charset=utf-8";
//        return "application/octet-stream; charset=utf-8";
//    	return "multipart/form-data;charset=utf-8;boundary=mHJCmTOazp1_WIxi41Nfbcp-B-a39N";
        return entity.getContentType().getValue();
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
            Log.d("----->>>>", "服务段：" + result);
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
////        super.getRetryPolicy();
//        // Volley设置请求超时时间
//        //1、最大超时时间
//        //2、The maximum number of attempts 最大尝试次数
//        //3、注意最后一个参数，它允许你指定一个退避乘数可以用来实现“指数退避”来从RESTful服务器请求数据
//        RetryPolicy retryPolicy = new DefaultRetryPolicy(15000, 0, 0);
//        return retryPolicy;
//    }

    //    查看文本打印
    @Override
    public byte[] getBody() throws AuthFailureError {

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            entity.writeTo(bos);
            Log.i("---->>>>>", bos.toString());
        } catch (IOException e) {
            VolleyLog.e("IOException writing to ByteArrayOutputStream");
        }
        return bos.toByteArray();
    }
}
