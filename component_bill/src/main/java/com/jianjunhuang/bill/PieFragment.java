package com.jianjunhuang.bill;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.Nullable;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.jianjunhuang.bill.view.BillChartFragment;
import com.jianjunhuang.common_base.rxjava.NetworkObserver;
import com.jianjunhuang.howmuch.protocol.bill.BillResponse;
import com.jianjunhuang.howmuch.protocol.bill.TypeExpend;
import java.util.ArrayList;
import java.util.List;

public class PieFragment extends BillChartFragment {

  private String[] parties = {"wechat", "alipay", "招商", "cash"};

  private PieChart pieChart;

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    mBinding.chartViewStub.getViewStub().setLayoutResource(R.layout.bill_chart_pie);
    mBinding.chartViewStub.getViewStub().inflate();
    pieChart = mBinding.getRoot().findViewById(R.id.pie_chart);
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
    pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
      @Override
      public void onValueSelected(Entry e, Highlight h) {
        // e.getX()方法得到x数据
        PieEntry pieEntry = (PieEntry) e;
        TypeExpend typeExpend = (TypeExpend) pieEntry.getData();
        getPresenter().queryBillsByType(typeExpend.getTypeId());
      }

      @Override
      public void onNothingSelected() {
      }
    });
  }

  @Override
  public void onResume() {
    super.onResume();
    getPresenter().queryTypeBills().subscribe(new NetworkObserver<List<TypeExpend>>() {
      @Override
      public void onError(String msg, int code) {

      }

      @Override
      public void onNext(List<TypeExpend> typeExpends) {
        setData(pieChart, typeExpends);
      }
    });
  }

  private void setData(PieChart chart, List<TypeExpend> typeExpends) {
    ArrayList<PieEntry> entries = new ArrayList<>();

    // NOTE: The order of the entries when being added to the entries array determines their position around the center of
    // the chart.
    for (int i = 0; i < typeExpends.size(); i++) {
      entries.add(new PieEntry(typeExpends.get(i).getExpend().abs().floatValue(),
          typeExpends.get(i).getType(), typeExpends.get(i)));
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
