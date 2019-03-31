package com.jianjunhuang.bill;

import android.os.Bundle;
import android.view.View;
import androidx.annotation.Nullable;

public class CalendarFragment extends BillChartFragment {

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    mBinding.chartViewStub.getViewStub().setLayoutResource(R.layout.bill_chart_calendar);
    mBinding.chartViewStub.getViewStub().inflate();
  }
}
