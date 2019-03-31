package com.jianjunhuang.bill;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.jianjunhuang.bill.databinding.BillFragmentBinding;
import com.jianjunhuang.common_base.base.BaseFragment;
import com.jianjunhuang.common_base.router.RouterPath;
import java.util.Arrays;
import java.util.List;

@Route(path = RouterPath.BILL_FRAGMENT)
public class BillFragment extends BaseFragment {

  private BillFragmentBinding mBinding;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    mBinding = DataBindingUtil.inflate(inflater, R.layout.bill_fragment, container, false);

    return mBinding.getRoot();
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    List<BaseFragment> baseFragments = Arrays
        .asList(new BillChartFragment().setTitle("Cal"), new BillChartFragment().setTitle("Pie"),
            new BillChartFragment().setTitle("Histogram"));
    mBinding.viewPager
        .setAdapter(new ChartFragmentAdapter(getChildFragmentManager(), baseFragments));

  }

  public static class ChartFragmentAdapter extends FragmentPagerAdapter {

    private List<BaseFragment> mBaseFragments;

    public ChartFragmentAdapter(FragmentManager fm, List<BaseFragment> baseFragments) {
      super(fm);
      this.mBaseFragments = baseFragments;
    }

    @Override
    public Fragment getItem(int position) {
      return mBaseFragments.get(position);
    }

    @Override
    public int getCount() {
      return mBaseFragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
      return mBaseFragments.get(position).getTitle();
    }
  }
}
