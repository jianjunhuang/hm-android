package com.jianjunhuang.common_base.rxjava;

import android.util.Log;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class NetworkObserver<T> implements Observer<T> {

  private static final String TAG = "NetworkObserver";

  @Override
  public void onSubscribe(Disposable d) {
  }

  @Override
  public void onError(Throwable e) {
    Log.e(TAG, "onError: ", e);
    onError(e.toString(), -1);
  }

  public abstract void onError(String msg, int code);

  @Override
  public void onComplete() {

  }
}
