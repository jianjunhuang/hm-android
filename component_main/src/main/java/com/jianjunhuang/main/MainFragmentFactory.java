package com.jianjunhuang.main;

import com.alibaba.android.arouter.launcher.ARouter;
import com.jianjunhuang.common_base.base.BaseFragment;

public class MainFragmentFactory {

  public static BaseFragment createFragmentById(int id) {
    if (id == R.id.menu_home) {
      return (BaseFragment) ARouter.getInstance().build("/overview/fragment").navigation();
    }
    if (id == R.id.menu_wallet) {
      return (BaseFragment) ARouter.getInstance().build("/wallet/fragment").navigation();
    }
    if (id == R.id.menu_add) {
      return (BaseFragment) ARouter.getInstance().build("/add/fragment").navigation();
    }
    if (id == R.id.menu_bill) {
      return (BaseFragment) ARouter.getInstance().build("/bill/fragment").navigation();
    }
    if (id == R.id.menu_settings) {
      return (BaseFragment) ARouter.getInstance().build("/setting/fragment").navigation();
    }
    return null;
  }
}
