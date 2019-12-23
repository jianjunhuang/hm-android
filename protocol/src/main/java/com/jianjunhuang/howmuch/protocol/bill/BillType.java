package com.jianjunhuang.howmuch.protocol.bill;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 账单类型
 */
public class BillType implements Parcelable {

  private String typeId;
  private String type;

  protected BillType(Parcel in) {
    typeId = in.readString();
    type = in.readString();
  }

  public static final Creator<BillType> CREATOR = new Creator<BillType>() {
    @Override
    public BillType createFromParcel(Parcel in) {
      return new BillType(in);
    }

    @Override
    public BillType[] newArray(int size) {
      return new BillType[size];
    }
  };

  public String getTypeId() {
    return typeId;
  }

  public void setTypeId(String typeId) {
    this.typeId = typeId;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(typeId);
    dest.writeString(type);
  }
}
