package com.example.yanjiang.stockchart.volley;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.yanjiang.stockchart.api.Constant;
import com.example.yanjiang.stockchart.util.EquipmentUtil;
import com.example.yanjiang.stockchart.util.LoadingDialog;
import com.example.yanjiang.stockchart.util.NetWorkUtil;
import com.example.yanjiang.stockchart.util.ToastUtil;
import com.example.yanjiang.stockchart.util.Tools;

import java.util.Map;


/**
 * Created by zhangxiaohang on 2016/11/1.
 */
public class VolleyQuotationGetManage {
    private static class VolleyToolBuilder {
        static VolleyQuotationGetManage _instance = new VolleyQuotationGetManage();
    }

    public static VolleyQuotationGetManage getInstance() {
        return VolleyToolBuilder._instance;
    }
    private LoadingDialog loadingDialog = null;
    private RequestQueue requestQueue = null;
    public VolleyQuotationGetManage() {

    }
    public Request methodGetQuotation(final Context context, String content, Map<String, String> mapParam, VolleyInterface volleyInterface, boolean isShowToast){
        if (!NetWorkUtil.isNetworkConnected(context)) {
            volleyInterface.onNetworkError();
            if(isShowToast){
                ((Activity)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        ToastUtil.TextToast(context, "网络未连接，请先设置网络", 200);
                    }
                });
            }
            return null;
        }
        if (null != content) {
            loadingDialog = new LoadingDialog(context, content);
            loadingDialog.show();
        }
        return methodGetQuotation(context, content, Request.Method.GET, mapParam, volleyInterface);
    }

    public Request methodGetQuotationWithFullUrl(final Context context, String content,String url, Map<String, String> mapParam, VolleyInterface volleyInterface, boolean isShowToast){
        if (!NetWorkUtil.isNetworkConnected(context)) {
            volleyInterface.onNetworkError();
            if(isShowToast){
                ((Activity)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        ToastUtil.TextToast(context, "网络未连接，请先设置网络", 200);
                    }
                });
            }
            return null;
        }
        if (null != content) {
            loadingDialog = new LoadingDialog(context, content);
            loadingDialog.show();
        }
        try {
            if (requestQueue == null) {
                requestQueue = Volley.newRequestQueue(context.getApplicationContext());
            }
//            url = Constant.httpbaseurl+url;
            mapParam.put("deviceNo", EquipmentUtil.deviceId(context));
            url = Tools.getUrlParamsByMap(url, mapParam);
            String tagId = url;
            Log.d("----->>>>volley","参数值：" + url);
            VolleyGetClient request = new VolleyGetClient<QuotationBackResult>(Request.Method.GET, url, QuotationBackResult.class, new QuotationSuccessListener(tagId, content, volleyInterface), new QuotationWrongListener(tagId, content, volleyInterface));
            request.setTag(tagId);
            requestQueue.add(request);
            return request;
        } catch (Exception e) {
            if (null != content) {
                loadingDialog.dismiss();
            }
            VolleyBean bean = new VolleyBean();
            bean.setSuccess(false);
            bean.setMessage("服务器忙！请您稍后再试");
            volleyInterface.gainData(bean);
        }
        return null;
    }

    public Request methodGetStreamQuotation(final Context context, String content, Map<String, String> mapParam, VolleyInterface volleyInterface, boolean isShowToast){
        if (!NetWorkUtil.isNetworkConnected(context)) {
            volleyInterface.onNetworkError();
            if(isShowToast){
                ((Activity)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        ToastUtil.TextToast(context, "网络未连接，请先设置网络", 200);
                    }
                });
            }
            return null;
        }
        if (null != content) {
            loadingDialog = new LoadingDialog(context, content);
            loadingDialog.show();
        }
        return methodGetStreamQuotation(context, content, Request.Method.GET, mapParam, volleyInterface);
    }

    private Request methodGetQuotation(Context context, String content, int method, Map<String, String> mapParam, VolleyInterface volleyInterface) {

        try {
            if (requestQueue == null) {
                requestQueue = Volley.newRequestQueue(context.getApplicationContext());
            }
//            url = Constant.httpbaseurl+url;
            String url = Constant.quotationBasicUrl;
            mapParam.put("deviceNo", EquipmentUtil.deviceId(context));
            url = Tools.getUrlParamsByMap(url, mapParam);
            String tagId = url;
            Log.d("----->>>>volley","参数值：" + url);
            VolleyGetClient request = new VolleyGetClient<QuotationBackResult>(method, url, QuotationBackResult.class, new QuotationSuccessListener(tagId, content, volleyInterface), new QuotationWrongListener(tagId, content, volleyInterface));
            request.setTag(tagId);
            requestQueue.add(request);
            return request;
        } catch (Exception e) {
            if (null != content) {
                loadingDialog.dismiss();
            }
            VolleyBean bean = new VolleyBean();
            bean.setSuccess(false);
            bean.setMessage("服务器忙！请您稍后再试");
            volleyInterface.gainData(bean);
        }
        return null;
    }

    private Request methodGetStreamQuotation(Context context, String content, int method, Map<String, String> mapParam, VolleyInterface volleyInterface) {

        try {
            if (requestQueue == null) {
                requestQueue = Volley.newRequestQueue(context.getApplicationContext());
            }
//            url = Constant.httpbaseurl+url;
            String url = Constant.quotationBasicUrl;
            mapParam.put("deviceNo", EquipmentUtil.deviceId(context));
            url = Tools.getUrlParamsByMap(url, mapParam);
            String tagId = url;
            Log.d("----->>>>volley","参数值：" + url);
            VolleyQuotationGetClient request = new VolleyQuotationGetClient<QuotationBackResult>(method, url, QuotationBackResult.class, new QuotationSuccessListener(tagId, content, volleyInterface), new QuotationWrongListener(tagId, content, volleyInterface));
            request.setTag(tagId);
            requestQueue.add(request);
            return request;
        } catch (Exception e) {
            if (null != content) {
                loadingDialog.dismiss();
            }
            VolleyBean bean = new VolleyBean();
            bean.setSuccess(false);
            bean.setMessage("服务器忙！请您稍后再试");
            volleyInterface.gainData(bean);
        }
        return null;
    }

    class QuotationSuccessListener implements Response.Listener<QuotationBackResult> {
        String tagId;
        String content;
        VolleyInterface volleyInterface;

        QuotationSuccessListener(String tagId, String content, VolleyInterface volleyInterface) {
            this.tagId = tagId;
            this.content = content;
            this.volleyInterface = volleyInterface;
        }

        @Override
        public void onResponse(QuotationBackResult backInfo) {
            if (null != content) {
                loadingDialog.dismiss();
            }
            if (null != requestQueue) {
                requestQueue.cancelAll(tagId);
            }
            VolleyBean bean = new VolleyBean();
            if (null == backInfo) {
                // 返回值异常
                bean.setSuccess(false);
                bean.setMessage("服务器忙！请您稍后再试");
            } else {
                if (backInfo.getState() == 0) {
                    bean.setSuccess(true);
                    bean.setContent(JSON.toJSONString(backInfo));
                    Log.d("----->>>>","正确返回结果" + JSON.toJSONString(backInfo));
                    bean.setMessage("成功");
                } else {
                    bean.setSuccess(false);
                    bean.setStatus(backInfo.getState());
                    bean.setMessage(backInfo.getError());
                }
            }
            volleyInterface.gainData(bean);
        }
    }
    class QuotationWrongListener implements Response.ErrorListener {
        String tagId;
        String content;
        VolleyInterface volleyInterface;

        QuotationWrongListener(String tagId, String content, VolleyInterface volleyInterface) {
            this.tagId = tagId;
            this.content = content;
            this.volleyInterface = volleyInterface;
        }

        @Override
        public void onErrorResponse(VolleyError volleyError) {
            if (null != content) {
                loadingDialog.dismiss();
            }
            if (null != requestQueue) {
                requestQueue.cancelAll(tagId);
            }
            String error = volleyError.toString();
            Log.d("----->>>>","错误返回结果" + error);
            VolleyBean bean = new VolleyBean();
            bean.setSuccess(false);
            bean.setMessage("服务器忙！请您稍后再试");
            volleyInterface.gainData(bean);
        }
    }
    public Request methodGetQuotationInfo(final Context context, String content, Map<String, String> mapParam, VolleyInterface volleyInterface, boolean isShowToast){
        if (!NetWorkUtil.isNetworkConnected(context)) {
            volleyInterface.onNetworkError();
            if(isShowToast){
                ((Activity)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        ToastUtil.TextToast(context, "网络未连接，请先设置网络", 200);
                    }
                });
            }
            return null;
        }
        if (null != content) {
            loadingDialog = new LoadingDialog(context, content);
            loadingDialog.show();
        }
        return methodGetQuotationInfo(context, content, Request.Method.GET, mapParam, volleyInterface);
    }
    private Request methodGetQuotationInfo(Context context, String content, int method, Map<String, String> mapParam, VolleyInterface volleyInterface) {

        try {
            if (requestQueue == null) {
                requestQueue = Volley.newRequestQueue(context.getApplicationContext());
            }
//            url = Constant.httpbaseurl+url;
            String url = Constant.quotationBasicUrl;
            mapParam.put("deviceNo", EquipmentUtil.deviceId(context));
            url = Tools.getUrlParamsByMap(url, mapParam);
            String tagId = url;
            Log.d("----->>>>volley","参数值：" + url);
            VolleyGetClient request = new VolleyGetClient<QuotationInfoBackResult>(method, url, QuotationInfoBackResult.class, new QuotationInfoSuccessListener(tagId, content, volleyInterface), new QuotationWrongListener(tagId, content, volleyInterface));
            request.setTag(tagId);
            requestQueue.add(request);
            return request;
        } catch (Exception e) {
            if (null != content) {
                loadingDialog.dismiss();
            }
            VolleyBean bean = new VolleyBean();
            bean.setSuccess(false);
            bean.setMessage("服务器忙！请您稍后再试");
            volleyInterface.gainData(bean);
        }
        return null;
    }

    class QuotationInfoSuccessListener implements Response.Listener<QuotationInfoBackResult> {
        String tagId;
        String content;
        VolleyInterface volleyInterface;

        QuotationInfoSuccessListener(String tagId, String content, VolleyInterface volleyInterface) {
            this.tagId = tagId;
            this.content = content;
            this.volleyInterface = volleyInterface;
        }

        @Override
        public void onResponse(QuotationInfoBackResult backInfo) {
            if (null != content) {
                loadingDialog.dismiss();
            }
            if (null != requestQueue) {
                requestQueue.cancelAll(tagId);
            }
            VolleyBean bean = new VolleyBean();
            if (null == backInfo) {
                // 返回值异常
                bean.setSuccess(false);
                bean.setMessage("服务器忙！请您稍后再试");
            } else {
                if (backInfo.getState() == 0) {
                    bean.setSuccess(true);
                    bean.setContent(JSON.toJSONString(backInfo));
                    Log.d("----->>>>","正确返回结果" + JSON.toJSONString(backInfo));
                    bean.setMessage("成功");
                } else {
                    bean.setSuccess(false);
                    bean.setStatus(backInfo.getState());
                    bean.setMessage(backInfo.getError());
                }
            }
            volleyInterface.gainData(bean);
        }
    }
}
