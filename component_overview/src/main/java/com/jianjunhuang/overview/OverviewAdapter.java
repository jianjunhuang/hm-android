package com.jianjunhuang.overview;

import androidx.core.content.ContextCompat;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.jianjunhuang.howmuch.protocol.bill.BillResponse;
import com.jianjunhuang.howmuch.protocol.bill.BillType;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OverviewAdapter extends BaseSectionQuickAdapter<ViewHolder, BaseViewHolder> {

  private SimpleDateFormat mDateFormat = new SimpleDateFormat("YYYY/MM/dd HH:mm");

  public OverviewAdapter() {
    super(R.layout.overview_item, R.layout.overview_item_floating, null);
  }

  @Override
  protected void convertHead(BaseViewHolder helper, ViewHolder item) {
    if (item.isHeader) {

    } else {

    }
  }

  @Override
  protected void convert(BaseViewHolder helper, ViewHolder item) {
    BillResponse billResponse = item.t;
    helper.setText(R.id.tv_price, String.format(mContext.getString(R.string.money),
        String.valueOf(billResponse.getMoney().doubleValue())));
    helper.setTextColor(R.id.tv_price, ContextCompat.getColor(helper.itemView.getContext(),
        billResponse.getMoney().doubleValue() > 0 ? R.color.incomeGreen : R.color.expendRed));
    helper.setText(R.id.tv_name, billResponse.getTitle());
    helper.setText(R.id.tv_wallet, billResponse.getWalletName());
    helper.setText(R.id.tv_time, mDateFormat.format(new Date(billResponse.getUpdateDate())));
    ChipGroup group = helper.getView(R.id.chip_group);
    group.removeAllViews();
    for (BillType billType : billResponse.getBillTypeList()) {
      Chip chip = new Chip(helper.itemView.getContext());
      chip.setText(billType.getType());
      group.addView(chip);
    }
  }
}
