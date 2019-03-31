package com.jianjunhuang.auth;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.jianjunhuang.auth.databinding.AuthActivityBinding;
import com.jianjunhuang.common_base.base.BaseActivity;
import com.jianjunhuang.common_base.router.RouterPath;

@Route(path = RouterPath.AUTH_ACTIVITY)
public class AuthActivity extends BaseActivity implements OnClickListener {

  private AuthActivityBinding mBinding;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mBinding = DataBindingUtil.setContentView(this, R.layout.auth_activity);
    mBinding.setListener(this);
    setSupportActionBar(mBinding.toolBar);
    mBinding.toolBar.setNavigationOnClickListener(v -> finish());
  }

  @Override
  public void onClick(View v) {
    int id = v.getId();
    if (id == R.id.btn_login) {
      //todo login
    }
  }
}
