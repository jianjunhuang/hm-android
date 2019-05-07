package com.jianjunhuang.common_base.rxjava;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class NetworkObserver<T> implements Observer<T> {

  @Override
  public void onSubscribe(Disposable d) {
  }

  @Override
  public void onError(Throwable e) {
  }

  public abstract void onError(String msg, int code);

  @Override
  public void onComplete() {
  }
}
