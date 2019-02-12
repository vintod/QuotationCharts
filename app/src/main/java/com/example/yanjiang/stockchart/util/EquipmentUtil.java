package com.example.yanjiang.stockchart.util;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.util.List;
import java.util.UUID;

/**
 * Created by weiwei on 15/3/14.
 * 设备相关信息
 */
public class EquipmentUtil {
    // 获取ID号
    public static String deviceId(Context context) {
        try {
            final TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            final String tmDevice, tmSerial, tmPhone, androidId;
            tmDevice = "" + tm.getDeviceId();
            tmSerial = "" + tm.getSimSerialNumber();
            androidId = "" + android.provider.Settings.Secure.getString(context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
            UUID deviceUuid = new UUID(androidId.hashCode(), ((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());
            return deviceUuid.toString();
        } catch (Exception e) {
            //Serial Number
            return android.os.Build.SERIAL;
        }
    }

    // 获取手机型号
    public static String phoneModule() {
        return android.os.Build.MODEL;
    }

    // 获取系统版本
    public static String systemVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

    //获取imei：
    public static String getImei(Context context){
        final TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String imei = tm.getDeviceId();
        if(StringUtil.isEmpty(imei)){
            imei = android.provider.Settings.Secure.getString(context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
        }
        return imei;
    }

    //获取mac地址
    public static String getMac(Context context){
        android.net.wifi.WifiManager wifi = (android.net.wifi.WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        String mac = wifi.getConnectionInfo().getMacAddress();
        return mac;
    }



    //获取当前应用的版本号：

    public static int getVersionCode(Context context) {
        try {
            // 获取packagemanager的实例
            PackageManager packageManager = context.getPackageManager();
            // getPackageName()是你当前类的包名，0代表是获取版本信息
            PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            int versionCode = packInfo.versionCode;
            return versionCode;
        } catch (Exception e) {
            return -1;
        }
    }
    //获取当前应用的版本号：

    public static String getVersionName(Context context) {
        try {
            // 获取packagemanager的实例
            PackageManager packageManager = context.getPackageManager();
            // getPackageName()是你当前类的包名，0代表是获取版本信息
            PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            String versionName = packInfo.versionName;
            return versionName;
        } catch (Exception e) {
            return "-1";
        }
    }

    
    /**
     * @param context
     * @return
     */
    public static boolean isRunning(Context context) {

    	boolean isAppRunning = false;
        ActivityManager am = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningTaskInfo> list = am.getRunningTasks(100);
        if(list == null || list.size() == 0){
        	return false;
        }
        
        RunningTaskInfo info = list.get(0);
        Log.e("isAppRunning", "----->>>>>>>info.topActivity.getPackageName() = " + info.topActivity.getPackageName() + ",info.baseActivity.getPackageName()=" + info.baseActivity.getPackageName());
        if (info.topActivity.getPackageName().equals("com.yxcps.trendpd") && info.baseActivity.getPackageName().equals("com.yxcps.trendpd")) {
        	isAppRunning = true;
        }
        return isAppRunning;
    	
    }
    
    
    /**
     * @param context
     * @return
     */
    public static boolean isBackground(Context context) {

//      ActivityManager activityManager = (ActivityManager) context
//              .getSystemService(Context.ACTIVITY_SERVICE);
//      List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager
//              .getRunningAppProcesses();
//      for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
//          if (appProcess.processName.equals(context.getPackageName())) {
//              if (appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_BACKGROUND) {
////                  Log.i(String.format("Background App:", appProcess.processName));
//                  //处于后台使用
//                  return true;
//              } else {
//                  //处于当前使用
////                  Log.i(String.format("Foreground App:", appProcess.processName));
//                  return false;
//              }
//          }
//      }
//      return false;
    	boolean isbackground = false;
        ActivityManager am = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningTaskInfo> list = am.getRunningTasks(100);
        if(list == null || list.size() == 0){
        	return false;
        }        
        for (int i=1;i<list.size();i++ ) {
        	RunningTaskInfo info = list.get(i);
        	Log.e("isAppRunning", "----->>>>>>>info.topActivity.getPackageName() = " + info.topActivity.getPackageName() + ",info.baseActivity.getPackageName()=" + info.baseActivity.getPackageName());
            if (info.topActivity.getPackageName().equals("com.yxcps.trendpd") && info.baseActivity.getPackageName().equals("com.yxcps.trendpd")) {
            	isbackground = true;
                break;
            }
        }
        return isbackground;
    	
    }      
}
