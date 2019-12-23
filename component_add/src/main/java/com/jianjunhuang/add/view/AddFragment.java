package com.jianjunhuang.add.view;

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
import androidx.viewpager.widget.ViewPager;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.jianjunhuang.add.R;
import com.jianjunhuang.add.databinding.AddFragmentBinding;
import com.jianjunhuang.common_base.base.BaseFragment;
import com.jianjunhuang.common_base.router.RouterPath;

@Route(path = RouterPath.ADD_FRAGMENT)
public class AddFragment extends BaseFragment {

  private AddFragmentBinding mBinding;

  private InputFragment expend;
  private InputFragment income;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    mBinding = DataBindingUtil.inflate(inflater, R.layout.add_fragment, container, false);
    boolean isRegular = false;
    if (getArguments() != null) {
      isRegular = getArguments().getBoolean("data");
    }
    expend = InputFragment.newInstance(InputFragment.STYLE_EXPAND, isRegular);
    income = InputFragment.newInstance(InputFragment.STYLE_INCOME,isRegular);
    initViewPager(mBinding.viewPager);
    return mBinding.getRoot();
  }

  private void initViewPager(ViewPager viewPager) {
    viewPager
        .setAdapter(new InputFragmentViewPagerAdapter(getChildFragmentManager(), expend, income));
    mBinding.tabLayout.setupWithViewPager(viewPager);
  }

  class InputFragmentViewPagerAdapter extends FragmentPagerAdapter {

    BaseFragment[] fragments;
    String[] titles = {"Expend", "Income"};

    public InputFragmentViewPagerAdapter(FragmentManager fm, BaseFragment... baseFragments) {
      super(fm);
      fragments = baseFragments;
    }

    @Override
    public Fragment getItem(int position) {
      return fragments[position];
    }

    @Override
    public int getCount() {
      return fragments.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
      return titles[position];
    }
  }
}
