package com.jianjunhuang.common_base.base;

import android.os.Bundle;
import androidx.annotation.Nullable;
import com.jianjunhuang.common_base.mvp.BasePresenter;
import com.jianjunhuang.common_base.mvp.IView;

public abstract class BaseMVPFragment<P extends BasePresenter> extends BaseFragment implements
    IView {

  private P presenter;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //创建present
    presenter = createPresenter();
    if (presenter != null) {
      presenter.attach(this);
    }
  }

  protected abstract P createPresenter();

  protected P getPresenter() {
    return presenter;
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    if (presenter != null) {
      presenter.detach();
    }

  }


}
