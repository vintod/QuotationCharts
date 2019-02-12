/*
 * StringUtil.java        1.0     2009-10-27  9:27:42
 *
 * Copyright (c) 2004, 2005 Works Systems, Inc.
 * Copyright (c) 2004, 2005 Works Systems (Tianjin) Co., Ltd.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Works Systems, Inc and Works Systems (Tianjin) Co., Ltd. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Works Systems.
 */
package com.example.yanjiang.stockchart.util;

import java.io.UnsupportedEncodingException;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA. User: Chris.F Date: 2009-10-27 Time: 9:27:42
 */
public final class StringUtil {
    private static String encoding = "UTF-8";

    public static void setEncoding(String enc) throws UnsupportedEncodingException {
        byte[] b = "".getBytes(enc); // validate encoding, do not remove this
        // line
        assert b != null;
        encoding = enc;
    }

    public static String getEncoding() {
        return encoding;
    }

    public static byte[] getBytes(String str) {
        return getBytes(str, encoding);
    }

    public static String toUtf8(String str) {
        return changeCharset(str, encoding);
    }

    /**
     * 字符串编码转换的实现方法
     *
     * @param str        待转换编码的字符串
     * @param newCharset 目标编码
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String changeCharset(String str, String newCharset) {
        try {
            if (str != null) {
                // 用默认字符编码解码字符串。
                byte[] bs = str.getBytes();
                // 用新的字符编码生成字符串
                return new String(bs, newCharset);
            }

        } catch (UnsupportedEncodingException ex) {

        }
        return null;
    }

    public static byte[] getBytes(String str, String encoding) {
        if (isEmpty(str))
            return new byte[0];
        try {
            return isEmpty(encoding) ? str.getBytes() : str.getBytes(encoding);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("invalid encoding: " + encoding, e);
        }
    }

    public static String formString(byte[] bytes) {
        return formString(bytes, encoding);
    }

    public static String formString(byte[] bytes, String encoding) {
        if (bytes == null || bytes.length == 0)
            return "";
        try {
            return isEmpty(encoding) ? new String(bytes) : new String(bytes, encoding);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("invalid encoding: " + encoding, e);
        }
    }

    public static boolean isEmpty(String s) {
        return s == null || s.length() == 0;
    }

    public static boolean isNotEmpty(String s) {
        return !isEmpty(s);
    }

    public static boolean isBlank(String s) {
        return isEmpty(s) || isWhitespace(s);
    }

    public static boolean isNotBlank(String s) {
        return !isBlank(s);
    }

    public static String[] split(String str, String seperator) {
        if (isEmpty(str))
            return new String[0];
        return str.split(seperator);
    }

    public static boolean isWhitespace(String text) {
        if (text == null)
            return false;
        for (int i = 0; i < text.length(); i++) {
            if (!Character.isWhitespace(text.charAt(i)))
                return false;
        }
        return true;
    }

    public static String trim(String str) {
        if (str == null)
            return null;
        return str.trim();
    }

    public static String trimToEmpty(String str) {
        if (str == null)
            return "";
        return str.trim();
    }

    public static String trimToNull(String str) {
        if (isEmpty(str))
            return null;
        return str.trim();
    }

    public static int indexOf(String str, String sub) {
        if (str == null)
            return -1;
        return str.indexOf(sub);
    }

    public static String replaceAllSubstring(String source, String substring, String replacement) {
        return Pattern.compile(substring, Pattern.LITERAL).matcher(source).replaceAll(replacement);
    }

    /**
     * support Numeric format:<br>
     * "33" "+33" "033.30" "-.33" ".33" " 33." " 000.000 "
     *
     * @param str String
     * @return boolean
     */
    public static boolean isNumeric(String str) {
        if (str == null || str.trim().equals("")) {
            return false;
        }
        str = str.trim();
        String regExp = "-?[0-9]+(\\.[0-9]+)?";
        return str.matches(regExp);
    }

    /**
     * support Integer format:<br>
     * "33" "003300" "+33" " -0000 "
     *
     * @param str String
     * @return boolean
     */
    public static boolean isInteger(String str) {
        if (str == null || str.trim().equals("")) {
            return false;
        }
        str = str.trim();

        return str.matches("-?[0-9]+");
    }


}