package com.jianjunhuang.overview;

import androidx.core.content.ContextCompat;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jianjunhuang.howmuch.protocol.bill.BillResponse;

public class OverviewAdapter extends BaseSectionQuickAdapter<ViewHolder, BaseViewHolder> {

  public OverviewAdapter() {
    super(R.layout.overview_item, R.layout.overview_item_floating, null);
  }

  @Override
  protected void convertHead(BaseViewHolder helper, ViewHolder item) {
    if (item.isHeader) {

    } else {
      BillResponse billResponse = item.t;
      helper.setText(R.id.tv_price, String.valueOf(billResponse.getMoney().doubleValue()));
      helper.setTextColor(R.id.tv_price, ContextCompat.getColor(helper.itemView.getContext(),
          billResponse.getMoney().doubleValue() > 0 ? R.color.incomeGreen : R.color.expendRed));

    }
  }

  @Override
  protected void convert(BaseViewHolder helper, ViewHolder item) {

  }
}
