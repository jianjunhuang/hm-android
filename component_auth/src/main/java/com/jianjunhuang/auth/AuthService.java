package com.jianjunhuang.auth;

import com.jianjunhuang.howmuch.protocol.user.LoginRequest;
import com.jianjunhuang.howmuch.protocol.user.User;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthService {

  @POST("/login")
  Observable<User> login(@Body LoginRequest request);

  @POST("/send")
  Observable<String> send(@Body String email);

}
