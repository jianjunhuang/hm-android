package com.jianjunhuang.common_base.utils;

import androidx.annotation.StringRes;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

  public static final String EMPTY = "";

  public static boolean isEmpty(String str) {
    return str == null || str.isEmpty();
  }

  /**
   * 判断是否全为空白字符，包含空格，制表符，换页符
   */
  public static boolean isAllBlockChar(String str) {
    String patternStr = "\\s*";
    Pattern pattern = Pattern.compile(patternStr);
    Matcher matcher = pattern.matcher(str);
    return matcher.matches();
  }

}
