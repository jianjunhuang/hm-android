package com.jianjunhuang.common_base.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * @author jianjunhuang.me@foxmail.com
 * @since 2017/5/24.
 */

public class SizeUtils {

  public static float displayDensity = 0.0F;

  private static Context mContext;

  public static void init(Context mContext) {
    SizeUtils.mContext = mContext;
  }

  /**
   * 获取分辨率
   *
   * @return float density
   */
  public static float getDensity() {
    if (displayDensity == 0.0F) {
      displayDensity = getDisplayMetrics().density;
    }
    return displayDensity;
  }

  public static DisplayMetrics getDisplayMetrics() {
    check();
    DisplayMetrics displayMetrics = new DisplayMetrics();
    ((WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay()
        .getMetrics(displayMetrics);
    return displayMetrics;
  }

  /**
   * 获取屏幕高度(pixels)
   *
   * @return float pixels 屏幕高度
   */
  public static float getScreenHeight() {
    return getDisplayMetrics().heightPixels;
  }

  /**
   * 获取屏幕宽度(pixels)
   *
   * @return float pixels 屏幕宽度
   */
  public static float getScreenWitdth() {
    return getDisplayMetrics().widthPixels;
  }

  /**
   * dp to pixel
   *
   * @param dp dp
   * @return pixel
   * @see <a href="https://developer.android.com/guide/practices/screens_support.html?hl=zh-cn">android
   * doc</a>
   */
  public static float dpToPx(float dp) {
    return dp * (getDisplayMetrics().densityDpi / 160F);
  }

  /**
   * dp to pixel
   *
   * @param dp dp
   * @return pixel
   * @see <a href="https://developer.android.com/guide/practices/screens_support.html?hl=zh-cn">android
   * doc</a>
   */
  public static int dpToPx(int dp) {
    return (dp * (getDisplayMetrics().densityDpi / 160));
  }

  /**
   * px to dp
   *
   * @param px px
   * @return dp
   * @see <a href="https://developer.android.com/guide/practices/screens_support.html?hl=zh-cn">android
   * doc</a>
   */
  public static float pxToDp(float px) {
    return px / (getDisplayMetrics().densityDpi / 160F);
  }

  /**
   * 通过反射获取 id 来获取状态栏高度（固定值）
   *
   * @return 状态栏高度
   */
  public static int getStatusBarHeight() {
    check();
    Class<?> c = null;
    Object obj = null;
    java.lang.reflect.Field field = null;
    int x = 0;
    try {
      c = Class.forName("com.android.internal.R$dimen");
      obj = c.newInstance();
      field = c.getField("status_bar_height");
      x = Integer.parseInt(field.get(obj).toString());
      return mContext.getResources().getDimensionPixelSize(x);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (NoSuchFieldException e) {
      e.printStackTrace();
    }
    return 0;
  }


  private static void check() {
    if (mContext == null) {
      throw new RuntimeException("please call SizeUtils.init(context) first!");
    }
  }
}
