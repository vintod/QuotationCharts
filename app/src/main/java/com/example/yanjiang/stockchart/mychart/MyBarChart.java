package com.example.yanjiang.stockchart.mychart;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

import com.example.yanjiang.stockchart.bean.DataParse;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;

/**
 * 作者：ajiang
 * 邮箱：1025065158
 * 博客：http://blog.csdn.net/qqyanjiang
 */
public class MyBarChart extends BarChart {
    private MyLeftMarkerView myMarkerViewLeft;
    private MyRightMarkerView myMarkerViewRight;
    private MyBottomMarkerView mMyBottomMarkerView;
    private DataParse minuteHelper;

    public MyBarChart(Context context) {
        super(context);
    }

    public MyBarChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyBarChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setMarker(MyLeftMarkerView markerLeft, MyRightMarkerView markerRight, MyBottomMarkerView markerBottom, DataParse minuteHelper) {
        this.myMarkerViewLeft = markerLeft;
        this.myMarkerViewRight = markerRight;
        this.mMyBottomMarkerView = markerBottom;
        this.minuteHelper = minuteHelper;
    }

//    @Override
//    protected void init() {
//        super.init();
//        /*此处不能重新示例*/
//        mXAxis = new MyXAxis();
//
//        mAxisLeft = new MyYAxis(YAxis.AxisDependency.LEFT);
//        mAxisRendererLeft = new MyYAxisRenderer(mViewPortHandler, (MyYAxis) mAxisLeft, mLeftAxisTransformer);
//        mXAxisRenderer = new MyXAxisRenderer(mViewPortHandler, (MyXAxis) mXAxis, mLeftAxisTransformer, this);
//        mAxisRight = new MyYAxis(YAxis.AxisDependency.RIGHT);
//        mAxisRendererRight = new MyYAxisRenderer(mViewPortHandler, (MyYAxis) mAxisRight, mRightAxisTransformer);
//
//    }

    public void setHighlightValue(Highlight h) {
        mIndicesToHighlight = new Highlight[]{
                h};
    }

    @Override
    protected void drawMarkers(Canvas canvas) {

        // if there is no marker view or drawing marker is disabled
        if (!isDrawMarkersEnabled() || !valuesToHighlight())
            return;

        for (int i = 0; i < mIndicesToHighlight.length; i++) {

            Highlight highlight = mIndicesToHighlight[i];

            IDataSet set = mData.getDataSetByIndex(highlight.getDataSetIndex());

            Entry e = mData.getEntryForHighlight(mIndicesToHighlight[i]);
            int entryIndex = set.getEntryIndex(e);

            // make sure entry not null
            if (e == null || entryIndex > set.getEntryCount() * mAnimator.getPhaseX())
                continue;

            float[] pos = getMarkerPosition(highlight);

            // check bounds
            if (!mViewPortHandler.isInBounds(pos[0], pos[1]))
                continue;

            // callbacks to update the content
            String time = minuteHelper.getDatas().get(entryIndex).time;
            mMyBottomMarkerView.setData(time);
            mMyBottomMarkerView.refreshContent(e, highlight);

            /*修复bug*/
            // invalidate();
                /*重新计算大小*/
            mMyBottomMarkerView.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
                    MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
            mMyBottomMarkerView.layout(0, 0, mMyBottomMarkerView.getMeasuredWidth(),
                    mMyBottomMarkerView.getMeasuredHeight());

            // draw the marker
            mMyBottomMarkerView.draw(canvas, pos[0] - mMyBottomMarkerView.getWidth() / 2, mViewPortHandler.contentBottom());
        }
    }
}
