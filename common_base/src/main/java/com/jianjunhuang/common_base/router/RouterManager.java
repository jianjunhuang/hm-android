package com.jianjunhuang.common_base.router;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.launcher.ARouter;
import com.jianjunhuang.common_base.base.BaseFragment;

public class RouterManager {

  public static class Navigate {

    public static void to(String url) {
      ARouter.getInstance().build(url).navigation();
    }

    public static Postcard with(String url) {
      return ARouter.getInstance().build(url);
    }

    public static void toAuth() {
      to(RouterPath.AUTH_ACTIVITY);
    }

    public static void toWalletAdd() {
      to(RouterPath.WALLET_ACTIVITY);
    }

    public static void toTypeAdd() {
      to(RouterPath.TYPE_ACTIVITY);
    }

  }

  public static class Fragment {

    public static BaseFragment get(String url) {
      return (BaseFragment) ARouter.getInstance().build(url).navigation();
    }

    public static BaseFragment overview() {
      return get(RouterPath.OVERVIEW_FRAGMENT);
    }

    public static BaseFragment wallet() {
      return get(RouterPath.WALLET_FRAGMENT);
    }

    public static BaseFragment add() {
      return get(RouterPath.ADD_FRAGMENT);
    }

    public static BaseFragment bill() {
      return get(RouterPath.BILL_FRAGMENT);
    }

    public static BaseFragment setting() {
      return get(RouterPath.SETTING_FRAGMENT);
    }

    public static BaseFragment overviewList() {
      return get(RouterPath.OVERVIEW_LIST_FRAGMENT);
    }
  }

}
