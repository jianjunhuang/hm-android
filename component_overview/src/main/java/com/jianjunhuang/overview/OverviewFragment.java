package com.jianjunhuang.overview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.jianjunhuang.common_base.base.BaseFragment;
import com.jianjunhuang.common_base.router.RouterPath;
import com.jianjunhuang.common_base.utils.FragmentUtil;
import com.jianjunhuang.overview.R;
import com.jianjunhuang.overview.databinding.OverviewFragmentBinding;
import java.util.ArrayList;
import java.util.List;

@Route(path = RouterPath.OVERVIEW_FRAGMENT)
public class OverviewFragment extends BaseFragment {

  private OverviewFragmentBinding mBinding;
  private BudgetRemainAdapter mBudgetAdapter;

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

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    mBinding.rvBudgetRemain.setLayoutManager(new LinearLayoutManager(getContext()));
    mBudgetAdapter = new BudgetRemainAdapter();
    mBinding.rvBudgetRemain.setAdapter(mBudgetAdapter);
    List<BudgetRemainHolder> mList = new ArrayList<>();
    mList.add(new BudgetRemainHolder("Breakfast", 10));
    mList.add(new BudgetRemainHolder("Lunch", 10));
    mList.add(new BudgetRemainHolder("Bus", 10));
    mBudgetAdapter.addData(mList);
  }

  private void setPriceVisibility(boolean visible) {
    String hide = "****";
    mBinding.tvBudget.setText(visible ? "￥1,600 / 3,000" : hide + "/" + hide);
    mBinding.tvExpend.setText(visible ? "￥5,000" : hide);
    mBinding.tvIncome.setText(visible ? "￥5,000" : hide);
  }

}
