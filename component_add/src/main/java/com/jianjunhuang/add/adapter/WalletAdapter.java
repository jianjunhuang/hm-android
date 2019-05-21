package com.jianjunhuang.add.adapter;

import android.content.Context;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.jianjunhuang.add.R;
import com.jianjunhuang.common_base.router.RouterManager;
import com.jianjunhuang.common_base.widget.ChipsAdapter;
import com.jianjunhuang.howmuch.protocol.wallet.Wallet;

public class WalletAdapter extends ChipsAdapter<Wallet> {

  public WalletAdapter(@NonNull Context context) {
    super(context);
  }

  @Override
  protected void convert(ChipGroup group, Chip chip, Wallet data, int pos) {
    if (pos == getData().size() - 1) {
      chip.setText("add wallet");
      chip.setChipIcon(ContextCompat.getDrawable(getContext(), R.drawable.ic_add_black_24dp));
      chip.setOnClickListener(v -> RouterManager.Navigate.toWalletAdd());
    } else {
      chip.setCheckable(true);
      chip.setCheckedIconEnabled(true);
      chip.setText(data.getName());
    }
  }

}
