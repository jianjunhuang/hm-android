package com.jianjunhuang.overview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.jianjunhuang.common_base.base.BaseFragment;
import com.jianjunhuang.common_base.utils.FragmentUtil;
import com.jianjunhuang.component_overview.R;
import com.jianjunhuang.overview.databinding.OverviewFragmentBinding;

@Route(path = "/overview/fragment")
public class OverviewFragment extends BaseFragment {

  private OverviewFragmentBinding mBinding;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    mBinding = DataBindingUtil.inflate(inflater, R.layout.overview_fragment, container, false);

    mBinding.ivPriceVisibility.setOnClickListener(v -> {
      mBinding.ivPriceVisibility.setSelected(!mBinding.ivPriceVisibility.isSelected());
      setPriceVisibility(!mBinding.ivPriceVisibility.isSelected());
    });

    FragmentUtil
        .replace(getChildFragmentManager(), OverviewListFragment.newInstance(), R.id.fl_content);
    return mBinding.getRoot();
  }

  private void setPriceVisibility(boolean visible) {
    String hide = "****";
    mBinding.tvBudget.setText(visible ? "￥1,600 / 3,000" : hide + "/" + hide);
    mBinding.tvExpend.setText(visible ? "￥5,000" : hide);
    mBinding.tvIncome.setText(visible ? "￥5,000" : hide);
  }

}
