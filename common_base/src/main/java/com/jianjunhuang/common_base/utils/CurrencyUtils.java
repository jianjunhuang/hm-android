package com.jianjunhuang.common_base.utils;

public class CurrencyUtils {

  public static String FORMAT_YUAN = "￥ %s";

  public static String format(String money) {
    return String.format(FORMAT_YUAN, money);
  }

  public static String format(long money) {
    return format(String.valueOf(money));
  }

}
