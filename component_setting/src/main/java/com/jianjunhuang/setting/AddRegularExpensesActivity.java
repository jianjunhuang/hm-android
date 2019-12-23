package com.jianjunhuang.setting;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import com.jianjunhuang.common_base.base.BaseActivity;
import com.jianjunhuang.common_base.router.RouterManager;
import com.jianjunhuang.common_base.utils.FragmentUtil;
import com.jianjunhuang.setting.databinding.SettingAddExpensesActivityBinding;
import com.jianjunhuang.setting.databinding.SettingRegularExpensesActivityBinding;

public class AddRegularExpensesActivity extends BaseActivity {

  private SettingAddExpensesActivityBinding mBinding;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mBinding = DataBindingUtil.setContentView(this, R.layout.setting_add_expenses_activity);
    setSupportActionBar(mBinding.toolBar);
    mBinding.toolBar.setNavigationOnClickListener(v -> finish());
    FragmentUtil.replace(getSupportFragmentManager(), RouterManager.Fragment.add(true),
        R.id.fl_add);
  }

}
