package com.jianjunhuang.main;

import com.jianjunhuang.common_base.base.BaseFragment;
import com.jianjunhuang.common_base.router.RouterManager;

public class MainFragmentFactory {

  public static BaseFragment createFragmentById(int id) {
    if (id == R.id.menu_home) {
      return RouterManager.Fragment.overview();
    }
    if (id == R.id.menu_wallet) {
      return RouterManager.Fragment.wallet();
    }
    if (id == R.id.menu_add) {
      return RouterManager.Fragment.add();
    }
    if (id == R.id.menu_bill) {
      return RouterManager.Fragment.bill();
    }

    if (id == R.id.menu_settings) {
      return RouterManager.Fragment.setting();
    }
    return null;
  }
}
