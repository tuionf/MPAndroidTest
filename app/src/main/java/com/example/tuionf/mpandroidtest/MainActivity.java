package com.example.tuionf.mpandroidtest;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.AxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private LineChart chart1;
//    String[] date = {"1月","2月","3月","4月","5月","6月","7月","8月","9月","10月"};//X轴的标注
    String[] date = {"1月","2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月"};//X轴的标注
    int[] score= {1000,500,2200,2500,2800,2900,3000,3600,1900,3100,1600,2400};//图表的数据点
    private LineData data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chart1 = (LineChart) findViewById(R.id.chart1);

        initData();
        initChart();
    }

    private void initData() {
        ArrayList<Entry> values1 = new ArrayList<>();

        values1.add(new Entry(0,33f));
        values1.add(new Entry(1,23.12f));
        values1.add(new Entry(2,11.111f));
        values1.add(new Entry(3,7));
        values1.add(new Entry(4,9));
        values1.add(new Entry(5,6));
        values1.add(new Entry(6,12));
        values1.add(new Entry(7,8));
        values1.add(new Entry(8,15));
        values1.add(new Entry(9,2));
        values1.add(new Entry(10,22));
        values1.add(new Entry(11,2));
//        values1.add(new Entry(12,11));

        //LineDataSet每一个对象就是一条连接线
        LineDataSet set1;
        //设置数据1  参数1：数据源 参数2：图例名称
        set1 = new LineDataSet(values1,"");
        set1.setColor(Color.BLACK);
        set1.setCircleColor(Color.BLACK);
        set1.setLineWidth(1f);//设置线宽
        set1.setCircleRadius(3f);//设置焦点圆心的大小
        set1.enableDashedHighlightLine(10f, 5f, 0f);//点击后的高亮线的显示样式
        set1.setHighlightLineWidth(2f);//设置点击交点后显示高亮线宽
        set1.setHighlightEnabled(true);//是否禁用点击高亮线
        //0xFF 表示透明度100%
        set1.setHighLightColor(0xFF00B9FC);//设置点击交点后显示交高亮线的颜色
        set1.setValueTextSize(9f);//设置显示值的文字大小
        set1.setDrawFilled(true);//设置禁用范围背景填充
        set1.setMode(LineDataSet.Mode.CUBIC_BEZIER); // 设置平滑曲线
        set1.setDrawCircles(true);  // 不显示坐标点的小圆点

        final DecimalFormat mFormat = new DecimalFormat("#####.##");
        set1.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return mFormat.format(value);
            }
        });

        //保存LineDataSet集合
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1); // add the datasets
        data = new LineData(dataSets);

    }

    private void initChart() {
        // 不显示数据描述
//        chart1.getDescription().setEnabled(false);
        //创建描述信息


        // no description text
        chart1.setDescription("");
        // 没有数据的时候，显示“暂无数据”
        chart1.setNoDataText("暂无数据");
//        chart1.setOnChartGestureListener(this);
//        chart1.setOnChartValueSelectedListener(this);
        chart1.setDrawGridBackground(false);
        // 不显示表格颜色
        chart1.setDrawGridBackground(false);
        // 不可以缩放
        chart1.setScaleEnabled(false);
        // 不显示y轴右边的值
        chart1.getAxisRight().setEnabled(false);
        // 不显示图例
        Legend legend = chart1.getLegend();
        legend.setEnabled(false);
        // 向左偏移15dp，抵消y轴向右偏移的30dp
        chart1.setExtraLeftOffset(-15);
//        chart1.setExtraOffsets(0,0,15f,0);

        //获取此图表的x轴
        XAxis xAxis = chart1.getXAxis();
        xAxis.setEnabled(true);//设置轴启用或禁用 如果禁用以下的设置全部不生效
        xAxis.setDrawAxisLine(true);//是否绘制轴线
        xAxis.setDrawGridLines(true);//设置x轴上每个点对应的线 网格线
        xAxis.setDrawLabels(true);//绘制标签  指x轴上的对应数值
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);//设置x轴的显示位置
        xAxis.setLabelCount(10);
        xAxis.setAvoidFirstLastClipping(true);
        xAxis.setAxisMinValue(11f);
        xAxis.setAxisMinValue(0f);
//        xAxis.setlabel
//        xAxis.setGridLineWidth(xAxis.getGridLineWidth()*2);
//        xAxis.setValueFormatter();
        final DecimalFormat mFormat = new DecimalFormat("#####");
        xAxis.setValueFormatter(new AxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {

                Log.e("hhp", "getFormattedValue_ value:::"+value +"---"+date[(int) value]);
                return date[(int) value];
            }

            @Override
            public int getDecimalDigits() {
                return 0;
            }
        });

        /**
         * Y轴默认显示左右两个轴线
         */
        //获取右边的轴线
        YAxis rightYAxis = chart1.getAxisRight();
        //设置图表右边的y轴禁用
        rightYAxis.setEnabled(false);
        //获取左边的轴线
        YAxis leftAxis = chart1.getAxisLeft();
        //设置网格线为虚线效果
//        leftAxis.enableGridDashedLine(10f, 10f, 0f);
        //是否绘制0所在的网格线
        leftAxis.setEnabled(true);
        leftAxis.setDrawZeroLine(true);

        leftAxis.setAxisMaxValue(75.0f);
        //设置Y周最小值
        leftAxis.setAxisMinValue(0f);
        // 设置y轴数据偏移量
        leftAxis.setXOffset(15);

//        chart1.getXAxis().setEnabled(false);




        // 添加数据到图表中
        chart1.setData(data);
//        chart1.setExtraLeftOffset(50);
        chart1.setVisibleXRangeMaximum(12f);

        chart1.setTouchEnabled(true);

        chart1.setScaleEnabled(true);
        // enable touch gestures
        chart1.setTouchEnabled(true);

        // enable scaling and dragging
        chart1.setDragEnabled(true);
        chart1.setScaleEnabled(true);
        // mChart.setScaleXEnabled(true);
        // mChart.setScaleYEnabled(true);

        // if disabled, scaling can be done on x- and y-axis separately
        chart1.setPinchZoom(true);

        MyMarkerView mv = new MyMarkerView(this, R.layout.textview);
        chart1.setMarkerView(mv); // Set the marker to the chart

        //绘制图表
        chart1.invalidate();
    }

}

