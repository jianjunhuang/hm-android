package com.jianjunhuang.wallet;

import android.os.Bundle;
import androidx.annotation.Nullable;
import com.jianjunhuang.common_base.base.BaseActivity;
import com.jianjunhuang.common_base.utils.FragmentUtil;
import com.jianjunhuang.wallet.view.WalletFragment;

public class WalletActivity extends BaseActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    FragmentUtil.replace(getSupportFragmentManager(), new WalletFragment(), android.R.id.content);
  }
}
