package com.jianjunhuang.setting;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import com.jianjunhuang.common_base.base.BaseActivity;
import com.jianjunhuang.common_base.router.RouterManager;
import com.jianjunhuang.common_base.utils.FragmentUtil;
import com.jianjunhuang.setting.databinding.SettingRegularExpensesActivityBinding;

public class RegularExpensesActivity extends BaseActivity {

  private SettingRegularExpensesActivityBinding mBinding;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mBinding = DataBindingUtil.setContentView(this, R.layout.setting_regular_expenses_activity);
    setSupportActionBar(mBinding.toolBar);
    mBinding.toolBar.setNavigationOnClickListener(v -> finish());
    FragmentUtil.replace(getSupportFragmentManager(), RouterManager.Fragment.overviewList(),
        R.id.fl_expenses);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.setting_expenses, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    if (id == R.id.menu_add_expenses) {
      startActivity(AddRegularExpensesActivity.class);
    }
    return super.onOptionsItemSelected(item);
  }
}
