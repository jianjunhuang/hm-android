package com.jianjunhuang.common_base.widget;

import android.content.Context;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import androidx.annotation.NonNull;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

public abstract class ChipsAdapter<T> {

  private List<T> mDatas;
  private ChipGroup mChipGroup;
  private Context mContext;

  public ChipsAdapter(@NonNull Context context) {
    this.mContext = context;
    checkList();
  }

  public void refresh(@NonNull List<T> data) {
    clear();
    addData(data);
  }

  public void addData(List<T> data) {
    checkList();
    mDatas = data;
    checkList();
    for (int i = 0; i < mDatas.size(); i++) {
      Chip chip = new Chip(mContext);
      bindCheckListener(chip, mDatas.get(i), mDatas.size() - 1);
      convert(mChipGroup, chip, mDatas.get(i), i);
      mChipGroup.addView(chip);
    }
  }

  private void clear() {
    mDatas.clear();
    mChipGroup.removeAllViews();
  }

  public void bindChipGroup(ChipGroup group) {
    mChipGroup = group;
    if (mChipGroup.getChildCount() > 0) {
      throw new IllegalArgumentException("ChipGroup can't have child when bind!");
    }
  }

  public void addData(T data) {
    checkList();
    check();
    mDatas.add(data);
    Chip chip = new Chip(mContext);
    bindCheckListener(chip, data, mDatas.size() - 1);
    convert(mChipGroup, chip, data, mDatas.size() - 1);
    mChipGroup.addView(chip);
  }

  protected abstract void convert(ChipGroup group, Chip chip, T data, int pos);

  public void remove(int pos) {
    check();
    if (pos > mDatas.size() || pos < 0) {
      throw new IndexOutOfBoundsException(
          String.format("pos = %d, list size = %d", pos, mDatas.size()));
    }
    mDatas.remove(pos);
    mChipGroup.removeViewAt(pos);
    update();
  }

  public void remove(Chip chip) {
    check();
    int pos = getPos(chip);
    if (pos > 0) {
      remove(pos);
    }
  }

  private void checkList() {
    if (mDatas == null) {
      mDatas = new ArrayList<>();
    }
  }

  private void check() {
    if (mChipGroup.getChildCount() != mDatas.size()) {
      throw new IllegalArgumentException(String
          .format("ChipGroup and List size not match ChipGroup size = %d , list size = %d",
              mChipGroup.getChildCount(), mDatas.size()));
    }
  }

  public T getItem(int pos) {
    check();
    return mDatas.get(pos);
  }

  public T getItem(Chip chip) {
    check();
    int pos = getPos(chip);
    if (pos > 0) {
      return getItem(pos);
    }
    return null;
  }

  private int getPos(Chip chip) {
    for (int i = 0; i < mChipGroup.getChildCount(); i++) {
      Chip child = (Chip) mChipGroup.getChildAt(i);
      if (child == chip) {
        return i;
      }
    }
    return -1;
  }

  private void update() {
    check();
    for (int i = 0; i < mChipGroup.getChildCount(); i++) {
      convert(mChipGroup, (Chip) mChipGroup.getChildAt(i), mDatas.get(i), mDatas.size() - 1);
    }
  }

  public List<T> getData() {
    checkList();
    return mDatas;
  }

  protected Context getContext() {
    return mContext;
  }

  protected void bindCheckListener(final Chip chip, final T data, final int pos) {
    chip.setOnCheckedChangeListener(new OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (mOnChipCheckListener != null) {
          mOnChipCheckListener.onChipCheck(chip, data, pos);
        }
      }
    });
  }

  private OnChipCheckListener mOnChipCheckListener;

  public void setOnChipCheckListener(OnChipCheckListener<T> onChipCheckListener) {
    mOnChipCheckListener = onChipCheckListener;
  }

  public interface OnChipCheckListener<T> {

    void onChipCheck(Chip chip, T data, int pos);
  }
}
