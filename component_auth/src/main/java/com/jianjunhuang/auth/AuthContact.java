package com.jianjunhuang.auth;

import com.jianjunhuang.common_base.mvp.IModel;
import com.jianjunhuang.common_base.mvp.IView;
import com.jianjunhuang.howmuch.protocol.user.LoginRequest;
import com.jianjunhuang.howmuch.protocol.user.User;
import io.reactivex.Observable;

public interface AuthContact {

  interface IAuthView extends IView {

    void onLoginSuccess();

    void onLoginFailed(String msg);

    void onSendFailed(String msg);

    void onSendSuccess();
  }

  interface IAuthModel extends IModel {

    Observable<User> login(LoginRequest request);

    void saveUser(User user);

    Observable<String> getCode(String email);

  }

}
