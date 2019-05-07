package com.jianjunhuang.overview;

import androidx.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import java.util.List;

public class BudgetRemainAdapter extends BaseQuickAdapter<BudgetRemainHolder, BaseViewHolder> {

  public BudgetRemainAdapter() {
    super(R.layout.overview_item_budget_remain);
  }

  @Override
  protected void convert(BaseViewHolder helper, BudgetRemainHolder item) {
    helper.setText(R.id.tv_type, item.getType());
    helper.setText(R.id.tv_times, String.format("%d times", item.getTimes()));
  }
}
