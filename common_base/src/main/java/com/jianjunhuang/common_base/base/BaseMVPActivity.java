package com.jianjunhuang.common_base.base;

import android.os.Bundle;
import androidx.annotation.Nullable;
import com.jianjunhuang.common_base.mvp.BasePresenter;
import com.jianjunhuang.common_base.mvp.IView;

public abstract class BaseMVPActivity<P extends BasePresenter> extends BaseActivity implements
    IView {

  private P presenter;


  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //创建present
    presenter = createPresenter();
    if (presenter != null) {
      presenter.attach(this);
    }
  }

  protected P getPresenter() {
    return presenter;
  }

  protected abstract P createPresenter();

  @Override
  protected void onDestroy() {
    super.onDestroy();
    if (presenter != null) {
      presenter.detach();
    }
  }
}
