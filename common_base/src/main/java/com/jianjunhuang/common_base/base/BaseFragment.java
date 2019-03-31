package com.jianjunhuang.common_base.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class BaseFragment extends Fragment {

  private boolean isLoad = false;
  private boolean isInit = false;
  private String mTitle;

  /**
   * 是否可以加载数据 可以加载数据的条件： 1.视图已经初始化 2.视图对用户可见
   */
  private void canLoadData() {
    if (!isInit) {
      return;
    }

    if (getUserVisibleHint()) {
      onLazyLoad(isLoad);
      isLoad = true;
    }
  }

  protected void onLazyLoad(boolean isLoad) {

  }

  /**
   * 视图是否已经对用户可见，系统的方法
   */
  @Override
  public void setUserVisibleHint(boolean isVisibleToUser) {
    super.setUserVisibleHint(isVisibleToUser);
    if (isVisibleToUser) {
      canLoadData();
    }
  }


  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    isInit = true;
  }

  public String getTitle() {
    return mTitle;
  }

  public BaseFragment setTitle(String mTitle) {
    this.mTitle = mTitle;
    return this;
  }

  public void startActivity(Class<? extends Activity> tClass) {
    Intent intent = new Intent(getActivity(), tClass);
    startActivity(intent);
  }
}
