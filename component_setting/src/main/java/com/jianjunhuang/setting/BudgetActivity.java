package com.jianjunhuang.setting;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import com.jianjunhuang.common_base.base.BaseActivity;
import com.jianjunhuang.setting.databinding.SettingBudgetActivityBinding;

public class BudgetActivity extends BaseActivity implements OnClickListener {

  private SettingBudgetActivityBinding mBinding;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mBinding = DataBindingUtil.setContentView(this, R.layout.setting_budget_activity);
    mBinding.setListener(this);
    setSupportActionBar(mBinding.toolBar);
    mBinding.toolBar.setNavigationOnClickListener(v -> finish());
  }

  @Override
  public void onClick(View v) {
    int id = v.getId();
    if (id == R.id.btn_update) {
      //todo update
    }
  }
}
