package com.jianjunhuang.common_base.net.converter;

import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.jianjunhuang.common_base.BuildConfig;
import com.jianjunhuang.howmuch.protocol.BaseRequest;
import java.io.IOException;
import java.nio.charset.Charset;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;

final class RemoteGsonRequestBodyConverter<T> implements Converter<T, RequestBody> {

  private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
  private static final Charset UTF_8 = Charset.forName("UTF-8");

  private final Gson gson;
  private final TypeAdapter<T> adapter;

  RemoteGsonRequestBodyConverter(Gson gson, TypeAdapter<T> adapter) {
    this.gson = gson;
    this.adapter = adapter;
  }

  @Override
  public RequestBody convert(T value) throws IOException {
    T data = value;
    if (TextUtils.isEmpty(value.toString())) {
      data = null;
    }

    BaseRequest<T> basicRequest = new BaseRequest<>();
    basicRequest.setAppVersion(BuildConfig.VERSION_NAME);
    basicRequest.setBrand(Build.BRAND);
    basicRequest.setApi(VERSION.SDK_INT);
    basicRequest.setDevice(Build.DEVICE);
    basicRequest.setData(value);
    return RequestBody.create(MEDIA_TYPE, gson.toJson(basicRequest));
  }
}
