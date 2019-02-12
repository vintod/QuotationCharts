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

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2014/10/27.
 */
public class VolleyPostManage {
	
    private static class VolleyToolBuilder {
        static VolleyPostManage _instance = new VolleyPostManage();
    }

    public static VolleyPostManage getInstance() {
        return VolleyToolBuilder._instance;
    }

    public VolleyPostManage() {

    }

    private LoadingDialog loadingDialog = null;
    private RequestQueue requestQueue = null;

    public Request methodPost(final Context context, String content, String head, Map<String, String> mapParam,String fileName, File file, VolleyInterface volleyInterface,boolean isShowToast) {
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
//        long currTime = PreferenceUtil.getLong(Constant.USER_LOGIN_LOG_KEY);
//        if (System.currentTimeMillis() - currTime > 1000 * 60 * 60 * 24) {
//            Intent intent = new Intent("com.haofengsoft.yxcps.action.login.loginloggerreceiver");
//            context.sendBroadcast(intent);
//            PreferenceUtil.putLong(Constant.USER_LOGIN_LOG_KEY, System.currentTimeMillis());
//        }

        return method(context, content, Request.Method.POST, head, mapParam,fileName,file, volleyInterface);
    }

    public Request methodPostFullUrl(final Context context, String content, String head, Map<String, String> mapParam,String fileName, File file, VolleyInterface volleyInterface,boolean isShowToast) {
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
//        long currTime = PreferenceUtil.getLong(Constant.USER_LOGIN_LOG_KEY);
//        if (System.currentTimeMillis() - currTime > 1000 * 60 * 60 * 24) {
//            Intent intent = new Intent("com.haofengsoft.yxcps.action.login.loginloggerreceiver");
//            context.sendBroadcast(intent);
//            PreferenceUtil.putLong(Constant.USER_LOGIN_LOG_KEY, System.currentTimeMillis());
//        }

        return methodFullUrl(context, content, Request.Method.POST, head, mapParam,fileName,file, volleyInterface);
    }

    private Request method(Context context, String content, int method, String url, Map<String, String> mapParam, String fileName,File file,VolleyInterface volleyInterface) {

        try {
            if (requestQueue == null) {
                requestQueue = Volley.newRequestQueue(context.getApplicationContext());
            }
            mapParam.put("clientType", "3");
            mapParam.put("appId", "180666");
            mapParam.put("version", EquipmentUtil.getVersionName(context));
            mapParam.put("deviceNo", EquipmentUtil.deviceId(context));
            url = Constant.httpbaseurl+url;
            String sign = Tools.genMd5Sign(mapParam);
            mapParam.put("sign", sign);
            final String tagId = url;
            VolleyPostClient request = new VolleyPostClient<BackResult>(method, url, mapParam, BackResult.class, fileName, file, new SuccessListener(tagId, content, volleyInterface), new WrongListener(tagId, content, volleyInterface));
            request.setTag(tagId);
            requestQueue.add(request);
            return request;
        } catch (Exception e) {
        	e.printStackTrace();
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

    private Request methodFullUrl(Context context, String content, int method, String url, Map<String, String> mapParam, String fileName,File file,VolleyInterface volleyInterface) {

        try {
            if (requestQueue == null) {
                requestQueue = Volley.newRequestQueue(context.getApplicationContext());
            }
            mapParam.put("clientType", "3");
            mapParam.put("appId", "180666");
            mapParam.put("version", EquipmentUtil.getVersionName(context));
            mapParam.put("deviceNo", EquipmentUtil.deviceId(context));
            String sign = Tools.genMd5Sign(mapParam);
            mapParam.put("sign", sign);

            Log.e("--->>>>>", JSON.toJSONString(mapParam));

            final String tagId = url;
            VolleyPostClient request = new VolleyPostClient<BackResult>(method, url, mapParam, BackResult.class, fileName, file, new SuccessListener(tagId, content, volleyInterface), new WrongListener(tagId, content, volleyInterface));
            request.setTag(tagId);
            requestQueue.add(request);
            return request;
        } catch (Exception e) {
            e.printStackTrace();
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

    class SuccessListener implements Response.Listener<BackResult> {
        String tagId;
        String content;
        VolleyInterface volleyInterface;

        SuccessListener(String tagId, String content, VolleyInterface volleyInterface) {
            this.tagId = tagId;
            this.content = content;
            this.volleyInterface = volleyInterface;
        }

        @Override
        public void onResponse(BackResult backInfo) {
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
                if (backInfo.getStatus() == 1) {
                    bean.setSuccess(true);
                    bean.setContent(backInfo.getResult());
                    Log.d("----->>>>","正确返回结果" + backInfo.getResult());
                    bean.setMessage("成功");
                } else if(backInfo.getStatus() == 999){
                    //强制下线
//                    Tools.forceOffLine();

                    bean.setSuccess(false);
                    bean.setStatus(backInfo.getStatus());
                    bean.setMessage(backInfo.getMsg());
                } else {
                    bean.setSuccess(false);
                    bean.setStatus(backInfo.getStatus());
                    bean.setMessage(backInfo.getMsg());
                }
            }
            volleyInterface.gainData(bean);
        }
    }

    class WrongListener implements Response.ErrorListener {
        String tagId;
        String content;
        VolleyInterface volleyInterface;

        WrongListener(String tagId, String content, VolleyInterface volleyInterface) {
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
    
}
