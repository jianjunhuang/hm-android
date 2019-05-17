package com.jianjunhuang.wallet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import com.jianjunhuang.common_base.base.BaseActivity;
import com.jianjunhuang.common_base.utils.CurrencyUtils;
import com.jianjunhuang.howmuch.protocol.wallet.Wallet;
import com.jianjunhuang.wallet.databinding.WalletActivityDetailBinding;
import java.math.BigDecimal;

public class WalletDetailActivity extends BaseActivity {

  private WalletActivityDetailBinding mBinding;
  private static final String KEY_DATA = "key_data";
  private Wallet mWallet;

  public static void startActivity(@NonNull Activity activity, @NonNull Wallet wallet) {
    Intent intent = new Intent(activity, WalletDetailActivity.class);
    intent.putExtra(KEY_DATA, wallet);
    activity.startActivity(intent);
  }

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mBinding = DataBindingUtil.setContentView(this, R.layout.wallet_activity_detail);
//    FragmentUtil.replace(getSupportFragmentManager(),
//        RouterManager.Fragment.overviewList(),
//        R.id.fl_content);

    Intent intent = getIntent();
    if (intent != null) {
      Wallet wallet = intent.getParcelableExtra(KEY_DATA);
      if (wallet != null) {
        mWallet = wallet;
        BigDecimal total = wallet.getTotal();
        BigDecimal liability = wallet.getLiability();
        mBinding.tvWalletName.setText(wallet.getName());
        if (total == null || liability == null) {
          return;
        }
        mBinding.tvTotalAssets.setText(CurrencyUtils.format(total.longValue()));
        mBinding.tvLiability.setText(CurrencyUtils.format(liability.longValue()));
        mBinding.tvNetAssets
            .setText(CurrencyUtils.format(total.subtract(liability).longValue()));
      }
    }
  }
}
