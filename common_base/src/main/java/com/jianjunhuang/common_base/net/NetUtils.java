package com.jianjunhuang.common_base.net;

import com.jianjunhuang.common_base.net.converter.GsonRequestConvertFactory;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

public class NetUtils {

  private static OkHttpClient mOkHttpClient;

  private static Retrofit mRetrofit;

  public static void init(String baseUrl) {
    if (mOkHttpClient == null) {
      mOkHttpClient = new OkHttpClient.Builder().connectTimeout(3, TimeUnit.SECONDS).build();
    }
    if (mRetrofit == null) {
      mRetrofit = new Retrofit.Builder().baseUrl(baseUrl).client(mOkHttpClient)
          .addConverterFactory(new GsonRequestConvertFactory()).build();
    }
  }

  public static <T> T createService(Class<T> service) {
    return mRetrofit.create(service);
  }
}
