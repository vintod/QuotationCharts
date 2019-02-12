package com.example.yanjiang.stockchart;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;

import com.example.yanjiang.stockchart.api.ConstantTest;
import com.example.yanjiang.stockchart.bean.DataParse;
import com.example.yanjiang.stockchart.bean.MinutesBean;
import com.example.yanjiang.stockchart.mychart.MyBarChart;
import com.example.yanjiang.stockchart.mychart.MyBottomMarkerView;
import com.example.yanjiang.stockchart.mychart.MyLeftMarkerView;
import com.example.yanjiang.stockchart.mychart.MyLineChart;
import com.example.yanjiang.stockchart.mychart.MyRightMarkerView;
import com.example.yanjiang.stockchart.rxutils.MyUtils;
import com.example.yanjiang.stockchart.rxutils.SchedulersCompat;
import com.example.yanjiang.stockchart.rxutils.VolFormatter;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.DefaultAxisValueFormatter;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import rx.Subscriber;
import rx.Subscription;

public class MinutesActivity extends BaseActivity {
    @Bind(R.id.line_chart)
    MyLineChart lineChart;
    @Bind(R.id.bar_chart)
    MyBarChart barChart;
    private Subscription subscriptionMinute;
    private LineDataSet d1, d2;
    XAxis xAxisLine;
    YAxis axisRightLine;
    YAxis axisLeftLine;
    BarDataSet barDataSet;

    XAxis xAxisBar;
    YAxis axisLeftBar;
    YAxis axisRightBar;
    SparseArray<String> stringSparseArray;
    private DataParse mData;
    Integer sum = 0;
    List<Integer> listA, listB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minutes);
        ButterKnife.bind(this);
        initChart();

        stringSparseArray = setXLabels();

        /*网络数据*/
        //getMinutesData();
        /*离线测试数据*/
        getOffLineData();
        lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                barChart.highlightValues(new Highlight[]{h});
            }

            @Override
            public void onNothingSelected() {
                barChart.highlightValue(null);
            }
        });
        barChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                lineChart.highlightValue(h);
            }

            @Override
            public void onNothingSelected() {
                lineChart.highlightValue(null);
            }
        });

        /*测试均线算法*/
        /*listA = new ArrayList<>();
        listB = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            listA.add(i, i);
        }

        for (int i = 0; i < 100; i++) {

            if (i >= 4) {
                sum = 0;
                listB.add(i, fund(i - 4, i));
            } else {
                listB.add(i, 0);
            }
        }

        for (int i = 0; i < 100; i++) {
            Log.e("OUT", listB.get(i) + "");
        }*/

    }

    private Integer fund(Integer a, Integer b) {

        for (int i = a; i <= b; i++) {
            sum += listA.get(i);
        }
        return sum;
    }

    private void initChart() {
        lineChart.setScaleEnabled(false);
        lineChart.setDrawBorders(true);
        lineChart.setBorderWidth(1);
        lineChart.setBorderColor(getResources().getColor(R.color.minute_grayLine));
        lineChart.setDescription(null);
        Legend lineChartLegend = lineChart.getLegend();
        lineChartLegend.setEnabled(false);

        barChart.setScaleEnabled(false);
        barChart.setDrawBorders(true);
        barChart.setBorderWidth(1);
        barChart.setBorderColor(getResources().getColor(R.color.minute_grayLine));
        barChart.setDescription(null);


        Legend barChartLegend = barChart.getLegend();
        barChartLegend.setEnabled(false);
        //x轴
        xAxisLine = lineChart.getXAxis();
        xAxisLine.setDrawLabels(true);
        xAxisLine.setPosition(XAxis.XAxisPosition.BOTTOM);
        // xAxisLine.setLabelsToSkip(59);
        xAxisLine.setLabelCount(5, true);
        xAxisLine.setAvoidFirstLastClipping(true);
        xAxisLine.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return stringSparseArray.get((int) value, "无");
            }
        });


        //左边y
        axisLeftLine = lineChart.getAxisLeft();
        /*折线图y轴左没有basevalue，调用系统的*/
        axisLeftLine.setLabelCount(5, true);
        axisLeftLine.setDrawLabels(true);
        axisLeftLine.setDrawGridLines(false);
        /*轴不显示 避免和border冲突*/
        axisLeftLine.setDrawAxisLine(false);


        //右边y
        axisRightLine = lineChart.getAxisRight();
        axisRightLine.setLabelCount(5, true);
        axisRightLine.setDrawLabels(true);
        axisRightLine.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                DecimalFormat mFormat = new DecimalFormat("#0.00%");
                return mFormat.format(value);
            }
        });

        axisRightLine.setStartAtZero(false);
        axisRightLine.setDrawGridLines(false);
        axisRightLine.setDrawAxisLine(false);
        //背景线
        xAxisLine.setGridColor(getResources().getColor(R.color.minute_grayLine));
        xAxisLine.enableGridDashedLine(10f, 5f, 0f);
        xAxisLine.setAxisLineColor(getResources().getColor(R.color.minute_grayLine));
        xAxisLine.setTextColor(getResources().getColor(R.color.minute_zhoutv));
        axisLeftLine.setGridColor(getResources().getColor(R.color.minute_grayLine));
        axisLeftLine.setTextColor(getResources().getColor(R.color.minute_zhoutv));
        axisRightLine.setAxisLineColor(getResources().getColor(R.color.minute_grayLine));
        axisRightLine.setTextColor(getResources().getColor(R.color.minute_zhoutv));
//        axisLeftLine.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
//        axisRightLine.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);

        //bar x y轴
        xAxisBar = barChart.getXAxis();
        xAxisBar.setDrawLabels(false);
        xAxisBar.setDrawGridLines(true);
        xAxisBar.setDrawAxisLine(false);
        xAxisBar.setLabelCount(5, true);
        // xAxisBar.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxisBar.setGridColor(getResources().getColor(R.color.minute_grayLine));
        axisLeftBar = barChart.getAxisLeft();
        axisLeftBar.setAxisMinValue(0);
        axisLeftBar.setDrawGridLines(false);
        axisLeftBar.setDrawAxisLine(false);
        axisLeftBar.setTextColor(getResources().getColor(R.color.minute_zhoutv));
        axisLeftBar.setLabelCount(2, true);
        axisLeftBar.setYOffset(5);


        axisRightBar = barChart.getAxisRight();
        axisRightBar.setDrawLabels(false);
        axisRightBar.setDrawGridLines(false);
        axisRightBar.setDrawAxisLine(false);
//        axisLeftBar.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
//        axisRightBar.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
        //y轴样式
//        this.axisLeftLine.setValueFormatter(new YAxisValueFormatter() {
//            @Override
//            public String getFormattedValue(float value, YAxis yAxis) {
//                DecimalFormat mFormat = new DecimalFormat("#0.00");
//                return mFormat.format(value);
//            }
//        });
        this.axisLeftLine.setValueFormatter(new DefaultAxisValueFormatter(2));

    }


    private void setData(DataParse mData) {
        setMarkerView(mData);
        Log.e("###", mData.getDatas().size() + "ee");
        if (mData.getDatas().size() == 0) {
            lineChart.setNoDataText("暂无数据");
            return;
        }
        //设置y左右两轴最大最小值
        axisLeftLine.setAxisMinimum(mData.getMin());
        axisLeftLine.setAxisMaximum(mData.getMax());
        axisRightLine.setAxisMinimum(mData.getPercentMin());
        axisRightLine.setAxisMaximum(mData.getPercentMax());
        xAxisLine.setAxisMaximum(242);
        xAxisBar.setAxisMaximum(242);


        axisLeftBar.setAxisMaxValue(mData.getVolmax());
        /*单位*/
        String unit = MyUtils.getVolUnit(mData.getVolmax());
        int u = 1;
        if ("万手".equals(unit)) {
            u = 4;
        } else if ("亿手".equals(unit)) {
            u = 8;
        }
        /*次方*/
        axisLeftBar.setValueFormatter(new VolFormatter((int) Math.pow(10, u)));
        axisLeftBar.setDrawLabels(true);
        //axisLeftBar.setAxisMinValue(0);//即使最小是不是0，也无碍

        axisRightBar.setAxisMaxValue(mData.getVolmax());
        //   axisRightBar.setAxisMinValue(mData.getVolmin);//即使最小是不是0，也无碍
        //axisRightBar.setShowOnlyMinMax(true);

        //基准线
        LimitLine ll = new LimitLine(0);
        ll.setLineWidth(1f);
        ll.setLineColor(getResources().getColor(R.color.minute_jizhun));
        ll.enableDashedLine(10f, 10f, 0f);
        ll.setLineWidth(1);
        axisRightLine.addLimitLine(ll);

        ArrayList<Entry> lineCJEntries = new ArrayList<>();
        ArrayList<Entry> lineJJEntries = new ArrayList<>();
        ArrayList<String> dateList = new ArrayList<>();
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        ArrayList<String> xVals = new ArrayList<>();
        Log.e("##", Integer.toString(xVals.size()));
        for (int i = 0, j = 0; i < mData.getDatas().size(); i++, j++) {
           /* //避免数据重复，skip也能正常显示
            if (mData.getDatas().get(i).time.equals("13:30")) {
                continue;
            }*/
            MinutesBean t = mData.getDatas().get(j);

            if (t == null) {
                lineCJEntries.add(new Entry(i, Float.NaN));
                lineJJEntries.add(new Entry(i, Float.NaN));
                barEntries.add(new BarEntry(i, Float.NaN));
                continue;
            }
            if (!TextUtils.isEmpty(stringSparseArray.get(i)) &&
                    stringSparseArray.get(i).contains("/")) {
                i++;
            }
            lineCJEntries.add(new Entry(i, mData.getDatas().get(i).cjprice));
            lineJJEntries.add(new Entry(i, mData.getDatas().get(i).avprice));
            barEntries.add(new BarEntry(i, mData.getDatas().get(i).cjnum));
            // dateList.add(mData.getDatas().get(i).time);
        }
        d1 = new LineDataSet(lineCJEntries, "成交价");
        d2 = new LineDataSet(lineJJEntries, "均价");
        d1.setDrawValues(false);
        d1.setDrawCircles(false);
        d2.setDrawValues(false);
        d2.setDrawCircles(false);
        barDataSet = new BarDataSet(barEntries, "成交量");

        d1.setCircleRadius(0);
        d2.setCircleRadius(0);
        d1.setColor(getResources().getColor(R.color.minute_blue));
        d2.setColor(getResources().getColor(R.color.minute_yellow));
        d1.setHighLightColor(Color.WHITE);
        d2.setHighlightEnabled(false);
        d1.setDrawFilled(true);
        barDataSet.setHighLightColor(Color.WHITE);
        barDataSet.setHighLightAlpha(255);
        barDataSet.setDrawValues(false);
        barDataSet.setHighlightEnabled(true);
        barDataSet.setColor(Color.RED);
        List<Integer> list = new ArrayList<>();
        list.add(Color.RED);
        list.add(Color.GREEN);
        barDataSet.setColors(list);
        //谁为基准
        d1.setAxisDependency(YAxis.AxisDependency.LEFT);
        // d2.setAxisDependency(YAxis.AxisDependency.RIGHT);
        /*注老版本LineData参数可以为空，最新版本会报错，修改进入ChartData加入if判断*/
        LineData cd = new LineData(d1, d2);
        lineChart.setData(cd);
        BarData barData = new BarData(barDataSet);
        barData.setBarWidth(0.6f);
        barChart.setData(barData);

        setOffset();
//        lineChart.invalidate();//刷新图
//        barChart.invalidate();
    }

    private void getMinutesData() {
        String code = "sz002081";
        subscriptionMinute = clientApi.getMinutes(code)
                .compose(SchedulersCompat.<ResponseBody>applyIoSchedulers())
                .subscribe(new Subscriber<ResponseBody>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        showToast("更新失败" + e.toString());
                    }

                    @Override
                    public void onNext(ResponseBody minutes) {

                        mData = new DataParse();
                        JSONObject object = null;
                        try {
                            object = new JSONObject(minutes.string());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        mData.parseMinutes(object);
                        setData(mData);

                    }
                });

        mCompositeSubscription.add(subscriptionMinute);
    }

    private void getOffLineData() {
           /*方便测试，加入假数据*/
        mData = new DataParse();
        JSONObject object = null;
        try {
            object = new JSONObject(ConstantTest.MINUTESURL);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mData.parseMinutes(object);
        setData(mData);
    }

    private SparseArray<String> setXLabels() {
        SparseArray<String> xLabels = new SparseArray<>();
        xLabels.put(0, "09:30");
        xLabels.put(60, "10:30");
        xLabels.put(121, "11:30/13:00");
        xLabels.put(181, "14:00");
        xLabels.put(242, "15:00");
        return xLabels;
    }


    /*设置量表对齐*/
    private void setOffset() {
        float lineLeft = lineChart.getViewPortHandler().offsetLeft();
        float barLeft = barChart.getViewPortHandler().offsetLeft();
        float lineRight = lineChart.getViewPortHandler().offsetRight();
        float barRight = barChart.getViewPortHandler().offsetRight();
        float barBottom = barChart.getViewPortHandler().offsetBottom();
        float offsetLeft, offsetRight;
        float transLeft = 0, transRight = 0;
 /*注：setExtraLeft...函数是针对图表相对位置计算，比如A表offLeftA=20dp,B表offLeftB=30dp,则A.setExtraLeftOffset(10),并不是30，还有注意单位转换*/
        if (barLeft < lineLeft) {
            //offsetLeft = Utils.convertPixelsToDp(lineLeft - barLeft);
            // barChart.setExtraLeftOffset(offsetLeft);
            transLeft = lineLeft;

        } else {
            offsetLeft = Utils.convertPixelsToDp(barLeft - lineLeft);
            lineChart.setExtraLeftOffset(offsetLeft);
            transLeft = barLeft;
        }

  /*注：setExtraRight...函数是针对图表绝对位置计算，比如A表offRightA=20dp,B表offRightB=30dp,则A.setExtraLeftOffset(30),并不是10，还有注意单位转换*/
        if (barRight < lineRight) {
            //offsetRight = Utils.convertPixelsToDp(lineRight);
            //barChart.setExtraRightOffset(offsetRight);
            transRight = lineRight;
        } else {
            offsetRight = Utils.convertPixelsToDp(barRight);
            lineChart.setExtraRightOffset(offsetRight);
            transRight = barRight;
        }
        barChart.setViewPortOffsets(transLeft, 5, transRight, barBottom);
    }

    private void setMarkerView(DataParse mData) {
        MyLeftMarkerView leftMarkerView = new MyLeftMarkerView(MinutesActivity.this, R.layout.mymarkerview);
        MyRightMarkerView rightMarkerView = new MyRightMarkerView(MinutesActivity.this, R.layout.mymarkerview);
        MyBottomMarkerView bottomMarkerView = new MyBottomMarkerView(MinutesActivity.this, R.layout.mymarkerview);
        lineChart.setMarker(leftMarkerView, rightMarkerView, bottomMarkerView, mData);
        barChart.setMarker(leftMarkerView, rightMarkerView, bottomMarkerView, mData);
    }
}
