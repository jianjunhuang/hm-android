package com.jianjunhuang.wallet;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import com.alibaba.android.arouter.launcher.ARouter;
import com.jianjunhuang.common_base.base.BaseActivity;
import com.jianjunhuang.common_base.base.BaseFragment;
import com.jianjunhuang.common_base.utils.FragmentUtil;
import com.jianjunhuang.wallet.R;
import com.jianjunhuang.wallet.databinding.WalletActivityDetailBinding;

public class WalletDetailActivity extends BaseActivity {

  private WalletActivityDetailBinding mBinding;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mBinding = DataBindingUtil.setContentView(this, R.layout.wallet_activity_detail);
    FragmentUtil.replace(getSupportFragmentManager(),
        (BaseFragment) ARouter.getInstance().build("/overview/list/fragment").navigation(),
        R.id.fl_content);

  }
}
