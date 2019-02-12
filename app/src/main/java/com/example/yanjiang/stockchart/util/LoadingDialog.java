package com.example.yanjiang.stockchart.util;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yanjiang.stockchart.R;


/**
 * 加载中
 */
public class LoadingDialog extends Dialog {
    private ImageView loadingIv;
    private TextView loadingTv;
    private Context context;
    //    private AnimationDrawable animationDrawable;
    private String content = null;

    public LoadingDialog(Context context) {
        super(context, R.style.progress_dialog);
        this.context = context;
    }

    public LoadingDialog(Context context, String content) {
        super(context, R.style.progress_dialog);
        this.context = context;
        this.content = content;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_loading_dialog);
        setCanceledOnTouchOutside(false);
        loadingIv = (ImageView) findViewById(R.id.loading_iv);
        loadingTv = (TextView) findViewById(R.id.loading_tv);
        if (null != content) {
            loadingTv.setText(content);
        }
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.progress);
        loadingIv.startAnimation(animation);
//        loadingIv.setImageResource(R.anim.progress_round);
//        animationDrawable = (AnimationDrawable) loadingIv.getDrawable();
//        animationDrawable.start();
    }

    @Override
    public void dismiss() {
//        animationDrawable.stop();
        loadingIv.clearAnimation();
        super.dismiss();
    }

    @Override
    public void cancel() {
//        animationDrawable.stop();
        loadingIv.clearAnimation();
        super.cancel();
    }
}
