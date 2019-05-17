package com.jianjunhuang.wallet;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.jianjunhuang.howmuch.protocol.wallet.Wallet;

public class WalletViewHolder implements MultiItemEntity {

  public static int TYPE_DEFAULT = 0;
  public static int TYPE_ADD = 1;

  private int mItemType;
  private Wallet mWallet;

  public WalletViewHolder(int itemType, Wallet wallet) {
    this.mItemType = itemType;
    this.mWallet = wallet;
  }

  public WalletViewHolder(int mItemType) {
    this.mItemType = mItemType;
  }

  @Override
  public int getItemType() {
    return mItemType;
  }

  public Wallet getWallet() {
    return mWallet;
  }

  public void setWallet(Wallet wallet) {
    this.mWallet = wallet;
  }
}
