package com.jianjunhuang.howmuch.protocol.bill;

import android.os.Parcel;
import android.os.Parcelable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 账单
 */
public class BillResponse implements Parcelable {

  private String billId;
  private String email;
  private long createDate;
  private long updateDate;
  private BigDecimal money;
  private String memo;
  private String position;
  private List<BillType> billTypeList;
  private String walletName;
  private String walletId;
  private String title;
  private int tag = 0;

  public int getTag() {
    return tag;
  }

  public void setTag(int tag) {
    this.tag = tag;
  }

  public String getBillId() {
    return billId;
  }

  public void setBillId(String billId) {
    this.billId = billId;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
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

  public BigDecimal getMoney() {
    return money;
  }

  public void setMoney(BigDecimal money) {
    this.money = money;
  }

  public String getMemo() {
    return memo;
  }

  public void setMemo(String memo) {
    this.memo = memo;
  }

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  public List<BillType> getBillTypeList() {
    return billTypeList;
  }

  public void setBillTypeList(List<BillType> billTypeList) {
    this.billTypeList = billTypeList;
  }

  public String getWalletName() {
    return walletName;
  }

  public void setWalletName(String walletName) {
    this.walletName = walletName;
  }

  public String getWalletId() {
    return walletId;
  }

  public void setWalletId(String walletId) {
    this.walletId = walletId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  protected BillResponse(Parcel in) {
    billId = in.readString();
    email = in.readString();
    createDate = in.readLong();
    updateDate = in.readLong();
    memo = in.readString();
    position = in.readString();
    billTypeList = in.createTypedArrayList(BillType.CREATOR);
    walletName = in.readString();
    walletId = in.readString();
    title = in.readString();
    money = new BigDecimal(in.readString());
    tag = in.readInt();
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(billId);
    dest.writeString(email);
    dest.writeLong(createDate);
    dest.writeLong(updateDate);
    dest.writeString(memo);
    dest.writeString(position);
    dest.writeTypedList(billTypeList);
    dest.writeString(walletName);
    dest.writeString(walletId);
    dest.writeString(title);
    dest.writeString(money.toString());
    dest.writeInt(tag);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public static final Creator<BillResponse> CREATOR = new Creator<BillResponse>() {
    @Override
    public BillResponse createFromParcel(Parcel in) {
      return new BillResponse(in);
    }

    @Override
    public BillResponse[] newArray(int size) {
      return new BillResponse[size];
    }
  };
}
