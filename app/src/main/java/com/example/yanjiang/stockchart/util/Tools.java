package com.example.yanjiang.stockchart.util;

import com.example.yanjiang.stockchart.api.Constant;

import java.security.MessageDigest;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public final class Tools {

    private static boolean isLog = true;
    private static String strLog = "YXCPS";

    public static String genSignLink(String url, Map<String, String> map) {
        String sign = genMd5Sign(map);
        map.put("sign", sign);
        return getUrlParamsByMap(url, map);
    }

    public static String getUrlParamsByMap(String url, Map<String, String> map) {
        if (!url.contains("?")) {
            url = url + "?";
        } else {
            if (!url.endsWith("?")) {
                url = url + "&";
            }
        }
        if (map == null) {
            return url;
        }
        StringBuffer sb = new StringBuffer();
        sb.append(url);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            sb.append(entry.getKey() + "=" + entry.getValue());
            sb.append("&");
        }
        if (sb.lastIndexOf("&") == sb.length() - 1) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    public static String genMd5Sign(Map<String, String> map) {
        StringBuffer bf = new StringBuffer();
        Map<String, String> resultMap = sortMapByKey(map);    //按Key进行排序
        for (Map.Entry<String, String> entry : resultMap.entrySet()) {
            bf.append(entry.getKey() + entry.getValue());
        }
        bf.append(Constant.VU_APP_KEY);
        return md5(bf.toString()).toUpperCase();
    }

    /**
     * 使用 Map按key进行排序
     *
     * @param map
     * @return
     */
    public static Map<String, String> sortMapByKey(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        Map<String, String> sortMap = new TreeMap<String, String>(new MapKeyComparator());
        sortMap.putAll(map);
        return sortMap;
    }

    public static String md5(String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = MessageDigest
                    .getInstance("MD5");
            digest.update(s.getBytes("GBK"));
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++) {
                if (Integer.toHexString(0xFF & messageDigest[i]).length() == 1)
                    hexString.append("0").append(
                            Integer.toHexString(0xFF & messageDigest[i]));
                else
                    hexString.append(Integer
                            .toHexString(0xFF & messageDigest[i]));
            }
            return hexString.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    static class MapKeyComparator implements Comparator<String> {
        public int compare(String str1, String str2) {
            return str1.compareTo(str2);
        }
    }
}
