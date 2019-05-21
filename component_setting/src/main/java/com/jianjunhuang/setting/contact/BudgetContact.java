package com.jianjunhuang.setting.contact;

import com.jianjunhuang.common_base.mvp.IModel;
import com.jianjunhuang.common_base.mvp.IView;
import com.jianjunhuang.howmuch.protocol.user.Budget;
import io.reactivex.Observable;
import java.math.BigDecimal;

public interface BudgetContact {

  interface IBudgetView extends IView {

    void onBudget(BigDecimal budget);

    void onBudgetFailed(String reason);
  }

  interface IBudgetModel extends IModel {

    Observable<Budget> updateBudget(BigDecimal money);

    Observable<Budget> requestBudget();
  }

}
