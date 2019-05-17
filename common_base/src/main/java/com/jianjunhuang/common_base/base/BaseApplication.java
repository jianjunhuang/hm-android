package com.jianjunhuang.common_base.base;

import android.app.Application;
import com.alibaba.android.arouter.launcher.ARouter;
import com.jianjunhuang.common_base.BuildConfig;
import com.jianjunhuang.common_base.net.NetUtils;
import com.jianjunhuang.common_base.utils.KeyboardUtil;
import com.jianjunhuang.common_base.utils.SPUtils;
import com.jianjunhuang.common_base.utils.SizeUtils;
import com.jianjunhuang.common_base.utils.ToastUtils;

public class BaseApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    SPUtils.init(this);
    ToastUtils.init(this);
    SizeUtils.init(this);
    KeyboardUtil.init(this);
    initARouter();
    NetUtils.init("http://192.168.31.130:8080/");
  }

  /**
   * 初始化路由
   */
  private void initARouter() {
    if (BuildConfig.DEBUG) {
      ARouter.openLog();  // 打印日志
      ARouter.openDebug(); // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
    }
    ARouter.init(this);// 尽可能早，推荐在Application中初始化
  }
}
