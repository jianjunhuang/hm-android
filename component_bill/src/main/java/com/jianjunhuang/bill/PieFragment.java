package com.jianjunhuang.bill;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.Nullable;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import java.util.ArrayList;

public class PieFragment extends BillChartFragment {

  private String[] parties = {"wechat", "alipay", "招商", "cash"};

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    mBinding.chartViewStub.getViewStub().setLayoutResource(R.layout.bill_chart_pie);
    mBinding.chartViewStub.getViewStub().inflate();
    PieChart pieChart = mBinding.getRoot().findViewById(R.id.pie_chart);
    pieChart.setUsePercentValues(true);
    pieChart.getDescription().setEnabled(false);
    pieChart.setExtraOffsets(5, 10, 5, 5);

    pieChart.setDragDecelerationFrictionCoef(0.95f);

    pieChart.setDrawHoleEnabled(true);
    pieChart.setHoleColor(Color.WHITE);

    pieChart.setTransparentCircleColor(Color.WHITE);
    pieChart.setTransparentCircleAlpha(110);

    pieChart.setHoleRadius(58f);
    pieChart.setTransparentCircleRadius(61f);

    pieChart.setDrawCenterText(true);

    pieChart.setRotationAngle(0);
    // enable rotation of the chart by touch
    pieChart.setRotationEnabled(true);
    pieChart.setHighlightPerTapEnabled(true);

    // chart.setUnit(" €");
    // chart.setDrawUnitsInChart(true);

    // add a selection listener

    Legend l = pieChart.getLegend();
    l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
    l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
    l.setOrientation(Legend.LegendOrientation.VERTICAL);
    l.setDrawInside(false);
    l.setXEntrySpace(7f);
    l.setYEntrySpace(0f);
    l.setYOffset(0f);

    // entry label styling
    pieChart.setEntryLabelColor(Color.WHITE);
    pieChart.setEntryLabelTextSize(12f);

    setData(pieChart, 10f);
  }

  private void setData(PieChart chart, float range) {
    ArrayList<PieEntry> entries = new ArrayList<>();

    // NOTE: The order of the entries when being added to the entries array determines their position around the center of
    // the chart.
    for (int i = 0; i < 4; i++) {
      entries.add(new PieEntry((float) ((Math.random() * range) + range / 5),
          parties[i]));
    }

    PieDataSet dataSet = new PieDataSet(entries, "");

    dataSet.setDrawIcons(false);

    dataSet.setSliceSpace(3f);
    dataSet.setIconsOffset(new MPPointF(0, 40));
    dataSet.setSelectionShift(5f);

    // add a lot of colors

    ArrayList<Integer> colors = new ArrayList<>();

    for (int c : ColorTemplate.VORDIPLOM_COLORS) {
      colors.add(c);
    }

    for (int c : ColorTemplate.JOYFUL_COLORS) {
      colors.add(c);
    }

    for (int c : ColorTemplate.COLORFUL_COLORS) {
      colors.add(c);
    }

    for (int c : ColorTemplate.LIBERTY_COLORS) {
      colors.add(c);
    }

    for (int c : ColorTemplate.PASTEL_COLORS) {
      colors.add(c);
    }

    colors.add(ColorTemplate.getHoloBlue());

    dataSet.setColors(colors);
    //dataSet.setSelectionShift(0f);

    PieData data = new PieData(dataSet);
    data.setValueTextSize(11f);
    data.setValueTextColor(Color.WHITE);
    chart.setData(data);

    // undo all highlights
    chart.highlightValues(null);

    chart.invalidate();
  }
}
