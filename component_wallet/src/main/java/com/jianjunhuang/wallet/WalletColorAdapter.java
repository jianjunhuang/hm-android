package com.jianjunhuang.wallet;

import android.graphics.Color;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jianjunhuang.wallet.R;

public class WalletColorAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

  public WalletColorAdapter() {
    super(R.layout.wallet_item_color);
  }

  @Override
  protected void convert(BaseViewHolder helper, String item) {
    helper.getView(R.id.view_color).setBackgroundColor(Color.parseColor(item));
  }
}
