package com.jianjunhuang.howmuch.protocol.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import java.math.BigDecimal;

public class Wallet implements Parcelable {

  private String name;
  private BigDecimal total;
  private BigDecimal liability;
  private long createDate;
  private long updateDate;
  private String walletId;

  public Wallet() {
  }

  protected Wallet(Parcel in) {
    name = in.readString();
    createDate = in.readLong();
    updateDate = in.readLong();
    walletId = in.readString();
  }

  public static final Creator<Wallet> CREATOR = new Creator<Wallet>() {
    @Override
    public Wallet createFromParcel(Parcel in) {
      return new Wallet(in);
    }

    @Override
    public Wallet[] newArray(int size) {
      return new Wallet[size];
    }
  };

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public BigDecimal getTotal() {
    return total;
  }

  public void setTotal(BigDecimal total) {
    this.total = total;
  }

  public BigDecimal getLiability() {
    return liability;
  }

  public void setLiability(BigDecimal liability) {
    this.liability = liability;
  }

  public long getCreateDate() {
    return createDate;
  }

  public void setCreateDate(long createDate) {
    this.createDate = createDate;
  }

  public long getUpdateDate() {
    return updateDate;
  }

  public void setUpdateDate(long updateDate) {
    this.updateDate = updateDate;
  }

  public String getWalletId() {
    return walletId;
  }

  public void setWalletId(String walletId) {
    this.walletId = walletId;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(name);
    dest.writeLong(createDate);
    dest.writeLong(updateDate);
    dest.writeString(walletId);
  }
}
