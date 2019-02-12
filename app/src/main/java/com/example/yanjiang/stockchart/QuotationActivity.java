package com.example.yanjiang.stockchart;

import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

import com.example.yanjiang.stockchart.bean.QuotationStockBean;
import com.example.yanjiang.stockchart.mychart.CoupleChartGestureListener;
import com.example.yanjiang.stockchart.volley.QuotationBackResult;
import com.example.yanjiang.stockchart.volley.VolleyBean;
import com.example.yanjiang.stockchart.volley.VolleyInterface;
import com.example.yanjiang.stockchart.volley.VolleyQuotationGetManage;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by kinger on 2017/8/1.
 */

public class QuotationActivity extends BaseActivity {
    private XAxis xAxisK;
    private YAxis axisLeftK, axisRightK;
    private CombinedChart combinedchart;
    private List<QuotationStockBean> kLineDatas;
    private int klineSize = 0;
    private float curHighVisibleX = 0f;
    private float defaultSize = 620f;
    private float lowestVisibleX;
    float scalex = 0f;
    private float curScaleX = 1f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_quotation);

        initView();
        initListener();
        initData();
    }

    private void initView() {
        combinedchart = (CombinedChart) findViewById(R.id.combinedchart);
        initChart();
    }

    private void initData() {
//        getOffLineData();
        initKlineData();
    }

    private void initChart() {
        combinedchart.setDrawOrder(new CombinedChart.DrawOrder[]{
                CombinedChart.DrawOrder.BAR, CombinedChart.DrawOrder.BUBBLE, CombinedChart.DrawOrder.CANDLE, CombinedChart.DrawOrder.LINE, CombinedChart.DrawOrder.SCATTER
        });

        combinedchart.setDrawBorders(true);
        combinedchart.setBorderWidth(1);
        combinedchart.setBorderColor(getResources().getColor(R.color.minute_grayLine));
        combinedchart.setDragEnabled(true);
        combinedchart.setScaleYEnabled(false);
        combinedchart.getLegend().setEnabled(false);
        combinedchart.setAutoScaleMinMaxEnabled(true);
        combinedchart.setBackgroundColor(Color.WHITE);
//        combinedchart.setVisibleXRangeMinimum(60f);
        combinedchart.getDescription().setEnabled(false);
//        combinedchart.setMinOffset(2f);
        combinedchart.setHighlightPerDragEnabled(true);

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
//        combinedchart.setMaxVisibleValueCount(60);

        xAxisK = combinedchart.getXAxis();
        xAxisK.setDrawLabels(true);
        xAxisK.setDrawGridLines(false);
        xAxisK.setDrawAxisLine(false);
        xAxisK.setTextColor(getResources().getColor(R.color.minute_zhoutv));
        xAxisK.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxisK.setGridColor(getResources().getColor(R.color.minute_grayLine));

        axisLeftK = combinedchart.getAxisLeft();
        axisLeftK.setDrawGridLines(true);
        axisLeftK.setDrawAxisLine(false);
        axisLeftK.setDrawLabels(true);
//        axisLeftK.setTextSize(14);
        axisLeftK.setLabelCount(5, true);
        axisLeftK.setTextColor(getResources().getColor(R.color.black));
        axisLeftK.setGridColor(getResources().getColor(R.color.line));
//        axisLeftK.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
        axisRightK = combinedchart.getAxisRight();
        axisRightK.setEnabled(false);
//        axisRightK.setDrawLabels(false);
//        axisRightK.setDrawGridLines(false);
//        axisRightK.setDrawAxisLine(false);
//        axisRightK.setGridColor(getResources().getColor(R.color.minute_grayLine));
        combinedchart.setDragDecelerationEnabled(true);
//        combinedchart.setDragDecelerationFrictionCoef(1f);

        combinedchart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                combinedchart.setDragEnabled(false);
            }

            @Override
            public void onNothingSelected() {
                combinedchart.setDragEnabled(true);
            }
        });
    }

//    private void getOffLineData() {
//           /*方便测试，加入假数据*/
//        DataParse mData = new DataParse();
//        JSONObject object = null;
//        try {
//            object = new JSONObject(ConstantTest.KLINEURL);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        mData.parseKLine(object);
//
//        mData.getKLineDatas();
//
//        setData(mData);
//    }

    private boolean isRequesting = false;

    private void initKlineData() {
        isRequesting = true;
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("type", "1300013");
        param.put("StockID", "17377216");
        param.put("Num", "150");
        param.put("Pos", "-1");
        param.put("TypeEx", "1");
        param.put("lasttime", kLineDatas == null || kLineDatas.size() == 0 ? "0" : kLineDatas.get(0).getTime() + "");
        param.put("Zip", "1");
        param.put("Type", "6");
        param.put("formula_index", "");
        VolleyQuotationGetManage.getInstance().methodGetStreamQuotation(QuotationActivity.this, null, param, new StockGetKlineVolleyReal(), true);
    }


    private class StockGetKlineVolleyReal implements VolleyInterface {

        @Override
        public void gainData(VolleyBean bean) {
            if (bean.isSuccess()) {
                QuotationBackResult result = com.alibaba.fastjson.JSONObject.parseObject(bean.getContent(), QuotationBackResult.class);
                List<QuotationStockBean> kLineBeans = (ArrayList<QuotationStockBean>) result.getKlineresult();
                if (kLineBeans != null || kLineBeans.size() > 0) {
                    if (kLineDatas == null || kLineDatas.size() == 0) {
                        kLineDatas = kLineBeans;
                        setData(kLineBeans.size());
                    } else {
                        addBeanToDataStart(kLineBeans);
                        setData(kLineBeans.size());
                    }
                }


//                GroupChartData turnedData = StockBeantoData.parseBeanToData(QuotationActivity.this, kLineBean);
//                lastClose = result.getLastClose();
//                curPrice = result.getNew();
//                changPoint = result.getNew() - result.getLastClose();
//                changeRate = (result.getNew() - result.getLastClose()) * 100 / result.getLastClose();
//                openPrice = result.getOpen();
//                highPrice = result.getHigh();
//                lowPrice = result.getLow();
//                amount = result.getVolume();
//                stockBase = result.getTotalv();
//                upNum = result.getUp();
//                equalNum = result.getEqual();
//                downNum = result.getDown();
//                exchangeRate = amount * 10000 / ((float) stockBase);
//                sale = result.getAmount();

            } else {
//                ToastUtil.singleTextToast(QuotationActivity.this, bean.getMessage(), Toast.LENGTH_SHORT);
            }
            isLoading = false;
            isRequesting = false;

        }

        @Override
        public void onNetworkError() {
//            ToastUtil.singleTextToast(QuotationActivity.this, "请连接网络", Toast.LENGTH_SHORT);
        }
    }


    private void setData(float offset) {
        if (klineSize == 0) {
            klineSize = kLineDatas.size();
        }

        CandleData candleData = new CandleData();
        candleData.addDataSet(createDataSet());
        CombinedData combinedData = new CombinedData();
        combinedData.setData(candleData);
        combinedchart.setData(combinedData);

//        combinedchart.setVisibleXRangeMaximum(200f);
//        combinedchart.setVisibleXRangeMinimum(60f);
        xAxisK.setAxisMaximum(combinedData.getXMax() + 0.5f);
        xAxisK.setAxisMinimum(-0.5f);

        combinedchart.setVisibleXRange(200f, 60f);

        // 重新计算缩放比例
        final ViewPortHandler viewPortHandler = combinedchart.getViewPortHandler();
        if (curScaleX == 1f) {
            curScaleX = (kLineDatas.size()) / 60;
        } else {
            float highIndex = combinedchart.getHighestVisibleX();
            float lowIndex = combinedchart.getLowestVisibleX();
            curScaleX = (kLineDatas.size()) / (highIndex - lowIndex);
        }
        float[] dstVals = new float[9];
        Matrix dstMatrix = combinedchart.getViewPortHandler().getMatrixTouch();
        dstMatrix.getValues(dstVals);
        dstVals[Matrix.MSCALE_X] = curScaleX;
        dstMatrix.setValues(dstVals);
        viewPortHandler.refresh(dstMatrix, combinedchart, false);


//        combinedchart.getViewPortHandler().getMatrixTouch().postScale(curScaleX,1f);

        if (klineSize == 0) {
            combinedchart.moveViewToX(combinedData.getEntryCount());
        } else {
            combinedchart.moveViewToX(0 + offset - 0.5f);
        }
        candleData.notifyDataChanged();
        combinedData.notifyDataChanged();
        combinedchart.notifyDataSetChanged();


//        dstMatrix.postScale(scalex,1f);

//        combinedchart.moveViewToX( 600);


//        combinedchart.setVisibleXRange(combinedData.getXMax() - 60 , combinedData.getXMax());
//        combinedchart.moveViewToX(mData.getKLineDatas().size() - 1);

//        Matrix matrixCombin = viewPortHandlerCombin.getMatrixTouch();

    }
    // 将K线控的滑动事件传递给交易量控件
//            .setOnChartGestureListener(new CoupleChartGestureListener(combinedchart, null){
//
//    });


    public CandleDataSet createDataSet() {
        ArrayList<CandleEntry> candleEntries = new ArrayList<>();
        for (int i = 0; i < kLineDatas.size(); i++) {
            candleEntries.add(new CandleEntry(i, kLineDatas.get(i).getHigh(), kLineDatas.get(i).getLow(), kLineDatas.get(i).getOpen(), kLineDatas.get(i).getClose()));
        }
        CandleDataSet candleDataSet = new CandleDataSet(candleEntries, "KLine");
        candleDataSet.setDrawHorizontalHighlightIndicator(true);
        candleDataSet.setHighlightEnabled(true);
        candleDataSet.setDrawVerticalHighlightIndicator(true);
        candleDataSet.setHighlightLineWidth(1f);
        candleDataSet.setHighLightColor(Color.GRAY);
        candleDataSet.setValueTextSize(12f);
        candleDataSet.setDrawValues(false);
        candleDataSet.setColor(Color.RED);
        candleDataSet.setShadowWidth(1f);
        candleDataSet.setDecreasingColor(getResources().getColor(R.color.fallgreen));
        candleDataSet.setDecreasingPaintStyle(Paint.Style.FILL);
        candleDataSet.setIncreasingColor(getResources().getColor(R.color.raisered));
        candleDataSet.setIncreasingPaintStyle(Paint.Style.STROKE);
        candleDataSet.setNeutralColor(Color.GRAY);
        candleDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        candleDataSet.setShadowColorSameAsCandle(true);
        candleDataSet.setBarSpace(0.1f);
        return candleDataSet;
    }

    private boolean isLoading = false;

    private void initListener() {
        combinedchart.setOnChartGestureListener(new CoupleChartGestureListener(combinedchart, new Chart[]{}) {
            @Override
            public void onChartTranslate(MotionEvent me, float dX, float dY) {
                float lowestX = combinedchart.getLowestVisibleX();
                curHighVisibleX = combinedchart.getHighestVisibleX();
                Log.e("---->>>>>", "lowestX = " + lowestX + ",curHighVisibleX = " + curHighVisibleX);
                lowestVisibleX = lowestX;
                if (lowestVisibleX == -0.5f) {
                    if (isLoading) {
                        combinedchart.setDragDecelerationEnabled(false);
                        return;
                    }
                    isLoading = true;

                }
            }

            @Override
            public void onChartScale(MotionEvent me, float scaleX, float scaleY) {

            }

            @Override
            public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
                if (isLoading) {
                    combinedchart.setDragDecelerationEnabled(true);
                    if (isRequesting) {
                        return;
                    }
                    initKlineData();
                }
            }
        });

    }


    public synchronized void addBeanToDataStart(List<QuotationStockBean> quotationList) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (int i = quotationList.size(); i > 0; i--) {
            QuotationStockBean bean = quotationList.get(i - 1);
            bean.setOpen(bean.getOpen());
            bean.setClose(bean.getClose());
            bean.setClose(bean.getClose());
            bean.setHigh(bean.getHigh());
            bean.setLow(bean.getLow());
            bean.setAmount(bean.getAmount());
            bean.setVolume(bean.getVolume());
            bean.setTime(bean.getTime());
            bean.setIndexclose(bean.getIndexclose());
            kLineDatas.add(0, bean);
        }
    }
}
