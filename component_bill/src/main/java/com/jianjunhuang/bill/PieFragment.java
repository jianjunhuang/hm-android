package com.jianjunhuang.bill;

import android.os.Bundle;
import android.view.View;
import androidx.annotation.Nullable;

public class PieFragment extends BillChartFragment {

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    mBinding.chartViewStub.getViewStub().setLayoutResource(R.layout.bill_chart_pie);
    mBinding.chartViewStub.getViewStub().inflate();
  }
}
