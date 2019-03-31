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
import com.jianjunhuang.common_base.router.RouterManager;
import com.jianjunhuang.common_base.utils.FragmentUtil;

public class BillChartFragment extends BaseFragment {

  protected BillChartFragmentBinding mBinding;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    mBinding = DataBindingUtil.inflate(inflater, R.layout.bill_chart_fragment, container, false);
    mBinding.setLifecycleOwner(this);
    FragmentUtil
        .replace(getChildFragmentManager(), RouterManager.Fragment.overviewList(), R.id.fl_list);
    return mBinding.getRoot();
  }



}
