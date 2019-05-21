package com.jianjunhuang.add.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.jianjunhuang.add.R;
import com.jianjunhuang.common_base.router.RouterManager;
import com.jianjunhuang.common_base.widget.ChipsAdapter;
import com.jianjunhuang.howmuch.protocol.type.Type;

public class TypeAdapter extends ChipsAdapter<Type> {

  public TypeAdapter(@NonNull Context context) {
    super(context);
  }

  @Override
  protected void convert(ChipGroup group, Chip chip, Type data, int pos) {
    if (pos == getData().size() - 1) {
      chip.setText("add type");
      chip.setChipIcon(ContextCompat.getDrawable(getContext(), R.drawable.ic_add_black_24dp));
      chip.setOnClickListener(v -> RouterManager.Navigate.toTypeAdd());
    } else {
      chip.setText(data.getType());
      chip.setCheckable(true);
      chip.setCheckedIconEnabled(true);
    }
  }
}
