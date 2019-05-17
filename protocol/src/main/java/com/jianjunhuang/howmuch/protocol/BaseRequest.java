package com.jianjunhuang.howmuch.protocol;

public class BaseRequest<T> {

  private String token = "ff8081816a932586016a932712a50000";

  private String appVersion;

  private String brand;

  private String device;

  private int api;

  private T data;

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public String getAppVersion() {
    return appVersion;
  }

  public void setAppVersion(String appVersion) {
    this.appVersion = appVersion;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public int getApi() {
    return api;
  }

  public void setApi(int api) {
    this.api = api;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  public String getDevice() {
    return device;
  }

  public void setDevice(String device) {
    this.device = device;
  }
}
