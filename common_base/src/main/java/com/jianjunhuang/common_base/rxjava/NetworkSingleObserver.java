package com.jianjunhuang.common_base.rxjava;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public abstract class NetworkSingleObserver<T> implements SingleObserver<T> {

  @Override
  public void onSubscribe(Disposable d) {
  }

  @Override
  public void onError(Throwable e) {
  }

  public abstract void onError(String msg, int code);
}
