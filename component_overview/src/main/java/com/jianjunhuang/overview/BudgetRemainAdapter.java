package com.jianjunhuang.overview;

import androidx.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jianjunhuang.howmuch.protocol.bill.RemainResponse;
import java.util.List;

public class BudgetRemainAdapter extends BaseQuickAdapter<RemainResponse, BaseViewHolder> {

  public BudgetRemainAdapter() {
    super(R.layout.overview_item_budget_remain);
  }

  @Override
  protected void convert(BaseViewHolder helper, RemainResponse item) {
    helper.setText(R.id.tv_type, item.getTypeName());
    helper.setText(R.id.tv_times, String.format("%d times", item.getTimes()));
  }
}
