package com.jianjunhuang.common_base.net.converter;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.jianjunhuang.common_base.net.RemoteException;
import com.jianjunhuang.common_base.utils.StringUtils;
import com.jianjunhuang.howmuch.protocol.BaseResponse;
import java.io.IOException;
import okhttp3.ResponseBody;
import retrofit2.Converter;

final class RemoteGsonResponseConverter<T> implements Converter<ResponseBody, T> {

  private final Gson gson;
  private final TypeAdapter<T> adapter;

  RemoteGsonResponseConverter(Gson gson, TypeAdapter<T> adapter) {
    this.gson = gson;
    this.adapter = adapter;
  }

  @Override
  public T convert(ResponseBody value) throws IOException {
    String response = value.string();
    try {
      BaseResponse<T> basicResponse = gson.fromJson(response, new TypeToken<BaseResponse<T>>() {
      }.getType());
      if (basicResponse.getCode() != 200) {
        throw new RemoteException(basicResponse.getCode(), basicResponse.getMsg());
      }
      if (basicResponse.getData() == null) {
        return (T) StringUtils.EMPTY;
      } else {
        return adapter.fromJson(gson.toJson(basicResponse.getData()));
      }
    } finally {
      value.close();
    }
  }

}
