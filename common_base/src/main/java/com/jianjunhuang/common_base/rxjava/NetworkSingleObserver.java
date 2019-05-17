package com.jianjunhuang.common_base.rxjava;

import android.util.Log;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public abstract class NetworkSingleObserver<T> implements SingleObserver<T> {

  private static final String TAG = "NetworkSingleObserver";

  @Override
  public void onSubscribe(Disposable d) {
  }

  @Override
  public void onError(Throwable e) {
    Log.e(TAG, e.toString());
    onError(e.getMessage(), -1);
  }

  public abstract void onError(String msg, int code);
}
