package com.jianjunhuang.auth;

import android.text.TextUtils;
import com.jianjunhuang.auth.AuthContact.IAuthModel;
import com.jianjunhuang.auth.AuthContact.IAuthView;
import com.jianjunhuang.common_base.mvp.BasePresenter;
import com.jianjunhuang.common_base.rxjava.NetworkObserver;
import com.jianjunhuang.howmuch.protocol.user.LoginRequest;
import com.jianjunhuang.howmuch.protocol.user.User;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AuthPresenter extends BasePresenter<IAuthView, IAuthModel> {

  @Override
  protected IAuthModel createModel() {
    return new AuthModel();
  }

  public void login(String email, String code) {
    LoginRequest request = new LoginRequest();
    request.setCode(code);
    request.setEmail(email);
    getModel().login(request)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<User>() {
          @Override
          public void onSubscribe(Disposable d) {

          }

          @Override
          public void onNext(User user) {

          }

          @Override
          public void onError(Throwable e) {
            getView().onLoginFailed(e.getMessage());
          }

          @Override
          public void onComplete() {
            getView().onLoginSuccess();
          }
        });
  }

  public void send(String email) {
    if (TextUtils.isEmpty(email)) {
      getView().onSendFailed("email is empty");
      return;
    }
    getModel().getCode(email)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new NetworkObserver<String>() {
          @Override
          public void onError(String msg, int code) {
            getView().onSendFailed(msg);
          }

          @Override
          public void onNext(String s) {
            getView().onSendSuccess();
          }
        });
  }
}
