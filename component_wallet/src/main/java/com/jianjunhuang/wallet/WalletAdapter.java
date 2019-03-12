package com.jianjunhuang.wallet;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

public class WalletAdapter extends BaseMultiItemQuickAdapter<ViewHolder, BaseViewHolder> {

  public static int TYPE_DEFAULT = 0;
  public static int TYPE_ADD = 1;

  public WalletAdapter() {
    super(null);
    addItemType(TYPE_DEFAULT, R.layout.wallet_item);
    addItemType(TYPE_ADD, R.layout.wallet_item_add);
  }

  @Override
  protected void convert(BaseViewHolder helper, ViewHolder item) {

  }
}
