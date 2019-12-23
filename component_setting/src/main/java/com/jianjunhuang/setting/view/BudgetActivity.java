package com.jianjunhuang.setting.view;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import com.jianjunhuang.common_base.base.BaseActivity;
import com.jianjunhuang.common_base.base.BaseMVPActivity;
import com.jianjunhuang.common_base.utils.KeyboardUtil;
import com.jianjunhuang.common_base.utils.ToastUtils;
import com.jianjunhuang.setting.R;
import com.jianjunhuang.setting.contact.BudgetContact.IBudgetView;
import com.jianjunhuang.setting.databinding.SettingBudgetActivityBinding;
import com.jianjunhuang.setting.presenter.BudgetPresenter;
import java.math.BigDecimal;

public class BudgetActivity extends BaseMVPActivity<BudgetPresenter> implements OnClickListener,
    IBudgetView {

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
  protected BudgetPresenter createPresenter() {
    return new BudgetPresenter();
  }

  @Override
  public void onClick(View v) {
    int id = v.getId();
    if (id == R.id.btn_update) {
      String budget = mBinding.edtBudget.getText().toString();
      if (TextUtils.isEmpty(budget)) {
        ToastUtils.show("Budget is empty!");
        return;
      }
      getPresenter().updateBudget(new BigDecimal(budget));
    }
  }

  @Override
  protected void onResume() {
    super.onResume();
    getPresenter().requestBudget();
  }

  @Override
  public void onBudget(BigDecimal budget) {
    mBinding.edtBudget.setText(budget.toPlainString());
    KeyboardUtil.hideSoftInput(this);
  }

  @Override
  public void onBudgetFailed(String reason) {
    ToastUtils.show(reason);
  }
}
