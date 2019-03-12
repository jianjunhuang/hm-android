package com.jianjunhuang.add;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import com.jianjunhuang.add.databinding.AddInputFragmentBinding;
import com.jianjunhuang.common_base.base.BaseFragment;

public class InputFragment extends BaseFragment {

  private AddInputFragmentBinding mBinding;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    mBinding = DataBindingUtil.inflate(inflater, R.layout.add_input_fragment, container, false);
    return mBinding.getRoot();
  }
}
