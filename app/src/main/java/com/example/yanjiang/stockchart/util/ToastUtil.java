package com.example.yanjiang.stockchart.util;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 提示信息
 */
public class ToastUtil {
    private static Toast toast = null;
    private static Toast biggerToast = null;
    public static int LENGTH_LONG = Toast.LENGTH_LONG;
    private static int LENGTH_SHORT = Toast.LENGTH_SHORT;

    public static void TextToast(Context context, CharSequence text, int duration) {
        if (toast == null) {
            toast = Toast.makeText(context, text, duration);
            toast.setGravity(Gravity.CENTER, 0, 300);
            TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
            v.setTextSize(18);
        } else {
            toast.setText(text);
        }
        toast.show();
    }

    public static void TextBiggerToast(Context context, CharSequence text, int duration) {
        if (biggerToast == null) {
            biggerToast = Toast.makeText(context, text, duration);
            biggerToast.setGravity(Gravity.CENTER, 0, 300);
            TextView v = (TextView) biggerToast.getView().findViewById(android.R.id.message);
            v.setTextSize(20);
        } else {
            biggerToast.setText(text);
        }
        biggerToast.show();
    }

    public static void TextToast(Context context, int text, int duration) {
        if (toast == null) {
            toast = Toast.makeText(context, text, duration);
            toast.setGravity(Gravity.CENTER, 0, 300);
            TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
            v.setTextSize(18);
        } else {
            toast.setText(text);
        }
        toast.show();
    }

    public static void ImageToast(Context context, int ImageResourceId, CharSequence text, int duration) {
        toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 300);
        View toastView = toast.getView();
        ImageView img = new ImageView(context);
        img.setImageResource(ImageResourceId);
        LinearLayout ll = new LinearLayout(context);
        ll.addView(img);
        ll.addView(toastView);
        toast.setView(ll);
        toast.show();
    }

    public static void singleTextToast(Context context, String text, int duration) {
        if (toast == null) {
            toast = Toast.makeText(context, text, duration);
            TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
            v.setTextSize(18);
        } else {
            toast.setText(text);
        }
        toast.show();
    }

    public static void singleHorToast(Context context, String text, int duration) {
        if (toast == null) {
            toast = Toast.makeText(context, text, duration);
            toast.setGravity(Gravity.CENTER, 0, 0);
            TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
            v.setTextSize(18);
        } else {
            toast.setText(text);
        }
        toast.show();
    }
}