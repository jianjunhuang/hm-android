package com.jianjunhuang.setting.model;

import com.jianjunhuang.common_base.net.NetUtils;
import com.jianjunhuang.howmuch.protocol.user.AddBudgetRequest;
import com.jianjunhuang.howmuch.protocol.user.Budget;
import com.jianjunhuang.setting.contact.BudgetContact.IBudgetModel;
import io.reactivex.Observable;
import java.math.BigDecimal;

public class BudgetModel implements IBudgetModel {

  private BudgetService mService;

  public BudgetModel() {
    mService = NetUtils.createService(BudgetService.class);
  }

  @Override
  public Observable<Budget> updateBudget(BigDecimal money) {
    AddBudgetRequest addBudgetRequest = new AddBudgetRequest();
    addBudgetRequest.setBudget(money);
    return mService.updateBudget(addBudgetRequest);
  }

  @Override
  public Observable<Budget> requestBudget() {
    return mService.requestBudget("");
  }
}
