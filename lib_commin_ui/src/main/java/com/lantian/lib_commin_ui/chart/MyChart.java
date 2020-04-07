package com.lantian.lib_commin_ui.chart;

import android.content.Context;
import android.graphics.Color;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.lantian.lib_base.utils.Utils;
import com.lantian.lib_commin_ui.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sherlock·Holmes on 2020-04-02
 */
public class MyChart {

    private Context context;
    private PieChart pieChart;
    private PieDataSet pieDataSet;

    public MyChart(Context context,PieDataSet pieDataSet,PieChart pieChart){
        this.pieChart = pieChart;
        this.pieDataSet = pieDataSet;
        this.context = context;


    }

    public void InitChart(){
        List<PieEntry> entries = new ArrayList<>();
        pieDataSet = new PieDataSet(entries, "");

        ArrayList<Integer> colors = new ArrayList<>();

        int defaultColor = Utils.getContext()
                .getResources()
                .getColor(R.color.afterSelector);

        // 饼图色
        colors.add(Color.rgb(114, 188, 223));
        colors.add(Color.rgb(255, 123, 124));
        colors.add(Color.rgb(57, 135, 200));
        colors.add(Color.rgb(59, 125, 255));
        colors.add(Color.rgb(194, 125, 145));
        colors.add(Color.WHITE);
        colors.add(Color.RED);
        colors.add(Color.YELLOW);
        colors.add(Color.rgb(255, 125, 225));
        colors.add(Color.rgb(134, 125, 135));


        pieDataSet.setColors(colors);
        pieDataSet.setSelectionShift(14f);
        //设置间距
        pieDataSet.setSliceSpace(0);
        //设置显示数据的大小
        pieDataSet.setValueTextSize(16);
        pieDataSet.setValueLineVariableLength(true);
        pieDataSet.setValueTextColors(colors);
        //线的颜色
        pieDataSet.setValueLineColor(Color.rgb(205, 205, 205));
        //内圈线
        pieDataSet.setValueLinePart1Length(0.5f);
        //外圈线
        pieDataSet.setValueLinePart2Length(0.3f);

        pieDataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        pieDataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);

        Legend legend = pieChart.getLegend();
        //设置比例图样式 圆
        legend.setForm(Legend.LegendForm.LINE);
        legend.setTextColor(defaultColor);
        //设置比例图位置
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setTextSize(16f);

        //图中标签
        pieChart.setEntryLabelColor(defaultColor);
        pieChart.setEntryLabelTextSize(14f);
        //关闭描述
        pieChart.getDescription().setEnabled(false);
        //关闭中空洞
        pieChart.setDrawHoleEnabled(false);
        //设置动画
        pieChart.animateXY(1000, 1000);
        pieChart.setData(new PieData(pieDataSet));
        pieChart.invalidate();
    }
}
