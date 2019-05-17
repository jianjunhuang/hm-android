package com.jianjunhuang.common_base.net;

import android.util.Log;
import okhttp3.logging.HttpLoggingInterceptor.Logger;

public class HttpLogger implements Logger {

  private static final String TAG = "HttpLogger";

  @Override
  public void log(String message) {
    Log.i(TAG, message);
  }
}
