package com.jianjunhuang.bill;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import com.jianjunhuang.bill.databinding.BillChartFragmentBinding;
import com.jianjunhuang.common_base.base.BaseFragment;

public class BillChartFragment extends BaseFragment {

  private BillChartFragmentBinding mBinding;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    mBinding = DataBindingUtil.inflate(inflater, R.layout.bill_chart_fragment, container, false);
    mBinding.setLifecycleOwner(this);

    return mBinding.getRoot();
  }
}
