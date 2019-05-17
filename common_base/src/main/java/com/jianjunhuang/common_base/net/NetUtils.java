package com.jianjunhuang.common_base.net;

import com.jianjunhuang.common_base.net.converter.GsonRequestConvertFactory;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class NetUtils {

  private static OkHttpClient mOkHttpClient;

  private static Retrofit.Builder mRetrofitBuilder;

  public static void init(String baseUrl) {
    if (mOkHttpClient == null) {
      HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor(new HttpLogger());
      logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
      mOkHttpClient = new OkHttpClient.Builder().connectTimeout(3, TimeUnit.SECONDS)
          .addNetworkInterceptor(logInterceptor).build();
    }
    if (mRetrofitBuilder == null) {
      mRetrofitBuilder = new Retrofit.Builder().baseUrl(baseUrl).client(mOkHttpClient)
          .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
    }
  }

  public static <T> T createService(Class<T> service) {
    return mRetrofitBuilder.client(mOkHttpClient)
        .addConverterFactory(new GsonRequestConvertFactory()).build().create(service);
  }
}
