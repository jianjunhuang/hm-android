package com.jianjunhuang.auth;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.jianjunhuang.auth.AuthContact.IAuthView;
import com.jianjunhuang.auth.databinding.AuthActivityBinding;
import com.jianjunhuang.common_base.base.BaseActivity;
import com.jianjunhuang.common_base.base.BaseMVPActivity;
import com.jianjunhuang.common_base.router.RouterPath;
import com.jianjunhuang.common_base.utils.ToastUtils;

@Route(path = RouterPath.AUTH_ACTIVITY)
public class AuthActivity extends BaseMVPActivity<AuthPresenter> implements OnClickListener,
    IAuthView {

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
  protected AuthPresenter createPresenter() {
    return new AuthPresenter();
  }

  @Override
  public void onClick(View v) {
    int id = v.getId();
    if (id == R.id.btn_login) {
      getPresenter()
          .login(mBinding.edtEmail.getText().toString(), mBinding.edtCode.getText().toString());
    } else if (id == R.id.iv_send) {
      getPresenter().send(mBinding.edtEmail.getText().toString());
    }
  }

  @Override
  public void onLoginSuccess() {
    finish();
  }

  @Override
  public void onLoginFailed(String msg) {
    ToastUtils.show(msg);
  }

  @Override
  public void onSendFailed(String msg) {
    ToastUtils.show(msg);
  }

  @Override
  public void onSendSuccess() {
    ToastUtils.show("send!");
  }
}
