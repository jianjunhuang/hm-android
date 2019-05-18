package com.jianjunhuang.setting;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import androidx.annotation.NonNull;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.jianjunhuang.common_base.widget.ChipsAdapter;
import com.jianjunhuang.howmuch.protocol.type.Type;

public class TypeChipsAdapter extends ChipsAdapter<Type> {

  private OnDelClickListener mListener;

  public TypeChipsAdapter(@NonNull Context context) {
    super(context);
  }

  public void setOnDelClickListener(OnDelClickListener listener) {
    mListener = listener;
  }

  @Override
  protected void convert(ChipGroup group, Chip chip, Type data, int pos) {
    chip.setText(data.getType());
    chip.setCloseIconEnabled(true);
    chip.setOnCloseIconClickListener(v -> {
      if (mListener != null) {
        mListener.onDelClick(chip, data, pos);
      }
    });
  }

  public interface OnDelClickListener {

    void onDelClick(Chip chip, Type data, int pos);
  }
}
