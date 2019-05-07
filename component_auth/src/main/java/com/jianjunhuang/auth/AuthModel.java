package com.jianjunhuang.auth;

import com.google.gson.Gson;
import com.jianjunhuang.common_base.constant.SPKey;
import com.jianjunhuang.common_base.net.NetUtils;
import com.jianjunhuang.common_base.utils.SPUtils;
import com.jianjunhuang.howmuch.protocol.user.LoginRequest;
import com.jianjunhuang.howmuch.protocol.user.User;
import io.reactivex.Observable;

public class AuthModel implements AuthContact.IAuthModel {

  private AuthService mService;

  public AuthModel() {
    mService = NetUtils.createService(AuthService.class);
  }

  @Override
  public Observable<User> login(LoginRequest request) {
    return mService.login(request);
  }

  @Override
  public void saveUser(User user) {
    Gson gson = new Gson();
    SPUtils.instance(SPKey.FILE_USER).store(SPKey.KEY_USER, gson.toJson(user));
  }
}
