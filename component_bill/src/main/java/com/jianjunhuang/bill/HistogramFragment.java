package com.jianjunhuang.bill;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.Nullable;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import java.util.ArrayList;
import java.util.Random;

public class HistogramFragment extends BillChartFragment {

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    mBinding.chartViewStub.getViewStub().setLayoutResource(R.layout.bill_chart_histogram);
    mBinding.chartViewStub.getViewStub().inflate();

    LineChart lineChart = mBinding.chartViewStub.getRoot().findViewById(R.id.chart_list);

    lineChart.setViewPortOffsets(0, 0, 0, 0);

    // no description text
    lineChart.getDescription().setEnabled(false);

    // enable touch gestures
    lineChart.setTouchEnabled(false);

    // enable scaling and dragging
    lineChart.setDragEnabled(false);
    lineChart.setScaleEnabled(false);

    // if disabled, scaling can be done on x- and y-axis separately
    lineChart.setPinchZoom(false);

    lineChart.setDrawGridBackground(false);
    lineChart.setMaxHighlightDistance(300);

    XAxis x = lineChart.getXAxis();
    x.setEnabled(true);
    x.setTextColor(Color.BLACK);
    x.setPosition(XAxisPosition.BOTTOM_INSIDE);
    x.setDrawGridLines(false);
    x.setDrawAxisLine(true);

    YAxis y = lineChart.getAxisLeft();
    y.setLabelCount(6, false);
    y.setTextColor(Color.BLACK);
    y.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
    y.setDrawGridLines(false);
    y.setAxisLineColor(Color.WHITE);

    lineChart.getAxisRight().setEnabled(false);
    lineChart.getLegend().setEnabled(false);

    ArrayList<Entry> values = new ArrayList<>();

    Random random = new Random();
    for (int i = 0; i < 7; i++) {
      int val = random.nextInt();
      values.add(new Entry(i, val, String.valueOf(i)));
    }

    LineDataSet lineDataSet = new LineDataSet(values, "month");
    lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
    lineDataSet.setCubicIntensity(0.2f);
    lineDataSet.setDrawFilled(true);
    lineDataSet.setDrawCircles(false);
    lineDataSet.setLineWidth(1.8f);
    lineDataSet.setCircleRadius(4f);
    lineDataSet.setCircleColor(Color.BLACK);
    lineDataSet.setHighLightColor(Color.WHITE);
    lineDataSet.setColor(Color.BLACK);
    lineDataSet.setFillColor(Color.LTGRAY);
    lineDataSet.setFillAlpha(100);
    lineDataSet.setDrawHorizontalHighlightIndicator(false);
    lineDataSet.setFillFormatter(new IFillFormatter() {
      @Override
      public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
        return lineChart.getAxisLeft().getAxisMinimum();
      }
    });
    LineData lineData = new LineData(lineDataSet);
    lineData.setValueTextSize(9f);
    lineData.setDrawValues(false);
    lineChart.setData(lineData);

    lineChart.invalidate();
  }
}
