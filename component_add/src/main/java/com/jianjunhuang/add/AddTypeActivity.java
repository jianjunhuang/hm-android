package com.jianjunhuang.add;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import com.jianjunhuang.add.databinding.AddTypeActivityBinding;
import com.jianjunhuang.common_base.base.BaseActivity;


public class AddTypeActivity extends BaseActivity {

  private AddTypeActivityBinding mBinding;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mBinding = DataBindingUtil.setContentView(this, R.layout.add_type_activity);
  }
}
