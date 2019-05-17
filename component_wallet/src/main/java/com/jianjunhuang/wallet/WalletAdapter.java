package com.jianjunhuang.wallet;

import static com.jianjunhuang.wallet.WalletViewHolder.TYPE_ADD;
import static com.jianjunhuang.wallet.WalletViewHolder.TYPE_DEFAULT;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jianjunhuang.common_base.utils.CurrencyUtils;
import com.jianjunhuang.howmuch.protocol.wallet.Wallet;
import java.math.BigDecimal;

public class WalletAdapter extends BaseMultiItemQuickAdapter<WalletViewHolder, BaseViewHolder> {

  public WalletAdapter() {
    super(null);
    addItemType(TYPE_DEFAULT, R.layout.wallet_item);
    addItemType(TYPE_ADD, R.layout.wallet_item_add);
  }

  @Override
  protected void convert(BaseViewHolder helper, WalletViewHolder item) {
    if (item.getItemType() == TYPE_DEFAULT) {
      Wallet wallet = item.getWallet();
      if (wallet == null) {
        return;
      }
      BigDecimal total = wallet.getTotal();
      BigDecimal liability = wallet.getLiability();
      helper.setText(R.id.tv_wallet_name, wallet.getName());
      helper
          .setText(R.id.tv_net_assets, CurrencyUtils.format(total.subtract(liability).longValue()));
      helper.setText(R.id.tv_total_assets, CurrencyUtils.format(total.longValue()));
      helper.setText(R.id.tv_liability, CurrencyUtils.format(liability.longValue()));
    }
  }
}
