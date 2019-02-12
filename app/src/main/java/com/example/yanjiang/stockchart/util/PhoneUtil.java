package com.example.yanjiang.stockchart.util;

import android.text.TextUtils;

/**
 * Created by Administrator on 15-3-11.
 */
public class PhoneUtil {
    /**
     * 验证手机格式
     * */
    public static boolean isMobileNo(String mobiles){
        String telReges="[1][3578]\\d{9}";
        if(TextUtils.isEmpty(mobiles)) return false;
        else return mobiles.matches(telReges);
    }

}
