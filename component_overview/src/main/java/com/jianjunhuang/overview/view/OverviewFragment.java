package com.jianjunhuang.overview.view;

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
import com.jianjunhuang.common_base.base.BaseMVPFragment;
import com.jianjunhuang.common_base.router.RouterManager;
import com.jianjunhuang.common_base.router.RouterPath;
import com.jianjunhuang.common_base.utils.FragmentUtil;
import com.jianjunhuang.howmuch.protocol.bill.BillResponse;
import com.jianjunhuang.howmuch.protocol.bill.RemainResponse;
import com.jianjunhuang.overview.BudgetRemainAdapter;
import com.jianjunhuang.overview.BudgetRemainHolder;
import com.jianjunhuang.overview.OverviewContact.IOverviewView;
import com.jianjunhuang.overview.OverviewListFragment;
import com.jianjunhuang.overview.R;
import com.jianjunhuang.overview.databinding.OverviewFragmentBinding;
import com.jianjunhuang.overview.presenter.OverviewPresenter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Route(path = RouterPath.OVERVIEW_FRAGMENT)
public class OverviewFragment extends BaseMVPFragment<OverviewPresenter> implements IOverviewView {

  private OverviewFragmentBinding mBinding;
  private BudgetRemainAdapter mBudgetAdapter;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    mBinding = DataBindingUtil.inflate(inflater, R.layout.overview_fragment, container, false);

    mBinding.ivPriceVisibility.setOnClickListener(v -> {
      mBinding.ivPriceVisibility.setSelected(!mBinding.ivPriceVisibility.isSelected());
//      setPriceVisibility(!mBinding.ivPriceVisibility.isSelected());
    });

    return mBinding.getRoot();
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    mBinding.rvBudgetRemain.setLayoutManager(new LinearLayoutManager(getContext()));
    mBudgetAdapter = new BudgetRemainAdapter();
    mBinding.rvBudgetRemain.setAdapter(mBudgetAdapter);


  }

  private void setPriceVisibility(BigDecimal budget, BigDecimal expend, BigDecimal income,
      boolean visible) {
    String hide = "****";
    mBinding.tvBudget.setText(
        visible ? String.format("￥%.3f / %.3f", expend.floatValue(), budget.floatValue())
            : hide + "/" + hide);
    mBinding.tvExpend.setText(visible ? String.format("￥%.3f", expend.floatValue()) : hide);
    mBinding.tvIncome.setText(visible ? String.format("￥%.3f", income.floatValue()) : hide);
  }

  @Override
  public void onResume() {
    super.onResume();
    getPresenter().queryOverviewDate();
    getPresenter().queryRemains();
  }

  @Override
  public void onOverview(BigDecimal budget, BigDecimal expend, BigDecimal income) {
    setPriceVisibility(budget, expend, income, true);
  }

  @Override
  public void onBills(List<BillResponse> bills) {
    FragmentUtil
        .replace(getChildFragmentManager(),
            RouterManager.Fragment.overviewList(new ArrayList<>(bills)),
            R.id.fl_content);
  }

  @Override
  public void onRemain(List<RemainResponse> remainResponses) {
    mBudgetAdapter.replaceData(remainResponses);
  }

  @Override
  protected OverviewPresenter createPresenter() {
    return new OverviewPresenter();
  }
}
