package com.jianjunhuang.setting;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.jianjunhuang.common_base.base.BaseFragment;
import com.jianjunhuang.common_base.router.RouterManager;
import com.jianjunhuang.common_base.router.RouterPath;
import com.jianjunhuang.setting.databinding.SettingFragmentBinding;

@Route(path = RouterPath.SETTING_FRAGMENT)
public class SettingFragment extends BaseFragment implements OnClickListener {

  private SettingFragmentBinding mBinding;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    mBinding = DataBindingUtil.inflate(inflater, R.layout.setting_fragment, container, false);
    mBinding.setListener(this);
    return mBinding.getRoot();
  }

  @Override
  public void onClick(View v) {
    int id = v.getId();
    if (id == R.id.ll_auth) {
      RouterManager.Navigate.toAuth();
      return;
    }
    if (id == R.id.fl_budget) {
      startActivity(BudgetActivity.class);
      return;
    }
  }
}
