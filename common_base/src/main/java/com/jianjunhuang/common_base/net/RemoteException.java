package com.jianjunhuang.common_base.net;

import java.io.IOException;

public class RemoteException extends IOException {

  private final int code;

  public RemoteException(int code, String msg) {
    super(msg);
    this.code = code;
  }

  public int getCode() {
    return code;
  }
}
