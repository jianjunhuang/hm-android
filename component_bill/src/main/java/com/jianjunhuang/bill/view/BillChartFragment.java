package com.jianjunhuang.bill.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import com.jianjunhuang.bill.BillListContact.IBillListView;
import com.jianjunhuang.bill.R;
import com.jianjunhuang.bill.databinding.BillChartFragmentBinding;
import com.jianjunhuang.bill.presenter.BillListPresenter;
import com.jianjunhuang.common_base.base.BaseFragment;
import com.jianjunhuang.common_base.base.BaseMVPFragment;
import com.jianjunhuang.common_base.router.RouterManager;
import com.jianjunhuang.common_base.utils.FragmentUtil;
import com.jianjunhuang.howmuch.protocol.bill.BillResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BillChartFragment extends BaseMVPFragment<BillListPresenter> implements IBillListView {

  protected BillChartFragmentBinding mBinding;

  public static BillChartFragment newInstance() {

    Bundle args = new Bundle();

    BillChartFragment fragment = new BillChartFragment();
    fragment.setArguments(args);
    return fragment;
  }


  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    mBinding = DataBindingUtil.inflate(inflater, R.layout.bill_chart_fragment, container, false);
    mBinding.setLifecycleOwner(this);

    return mBinding.getRoot();
  }

  @Override
  public void onResume() {
    super.onResume();
    getPresenter().queryBills();
  }

  @Override
  public void onBills(List<BillResponse> billResponses) {
    BigDecimal expend = new BigDecimal(0);
    BigDecimal income = new BigDecimal(0);
    FragmentUtil
        .replace(getChildFragmentManager(),
            RouterManager.Fragment.overviewList(new ArrayList<>(billResponses)), R.id.fl_list);
    for (BillResponse response : billResponses) {
      int money = response.getMoney().intValue();
      if (money > 0) {
        income = income.add(response.getMoney());
      } else {
        expend = expend.add(response.getMoney());
      }
    }
    mBinding.tvExpend.setText(String.format(getString(R.string.money), expend.toString()));
    mBinding.tvIncome.setText(String.format(getString(R.string.money), income.toString()));
  }

  @Override
  protected BillListPresenter createPresenter() {
    return new BillListPresenter();
  }
}
