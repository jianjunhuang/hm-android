package com.jianjunhuang.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import com.jianjunhuang.common_base.base.BaseFragment;
import com.jianjunhuang.main.databinding.MainMockFragmentBinding;

public class MockFragment extends BaseFragment {

  private MainMockFragmentBinding mBinding;

  public static MockFragment newInstance(int number) {

    Bundle args = new Bundle();
    args.putInt("key_int", number);
    MockFragment fragment = new MockFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    mBinding = DataBindingUtil.inflate(inflater, R.layout.main_mock_fragment, container, false);

    Bundle args = getArguments();
    if (args != null) {
      mBinding.tvNumber.setText(String.valueOf(args.getInt("key_int")));
    }

    return mBinding.getRoot();
  }
}