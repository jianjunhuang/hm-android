package com.jianjunhuang.wallet;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class ViewHolder implements MultiItemEntity {

  private int mItemType;

  public ViewHolder(int mItemType) {
    this.mItemType = mItemType;
  }

  @Override
  public int getItemType() {
    return mItemType;
  }
}
