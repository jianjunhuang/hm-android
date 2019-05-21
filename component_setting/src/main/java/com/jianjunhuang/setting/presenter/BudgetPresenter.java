package com.jianjunhuang.setting.presenter;

import com.jianjunhuang.common_base.mvp.BasePresenter;
import com.jianjunhuang.common_base.rxjava.NetworkObserver;
import com.jianjunhuang.howmuch.protocol.user.Budget;
import com.jianjunhuang.setting.contact.BudgetContact.IBudgetModel;
import com.jianjunhuang.setting.contact.BudgetContact.IBudgetView;
import com.jianjunhuang.setting.model.BudgetModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.math.BigDecimal;

public class BudgetPresenter extends BasePresenter<IBudgetView, IBudgetModel> {

  @Override
  protected IBudgetModel createModel() {
    return new BudgetModel();
  }

  public void requestBudget() {
    getModel().requestBudget()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new NetworkObserver<Budget>() {
          @Override
          public void onError(String msg, int code) {
            getView().onBudgetFailed(msg);
          }

          @Override
          public void onNext(Budget budget) {
            getView().onBudget(budget.getBudget());
          }
        });
  }

  public void updateBudget(BigDecimal budget) {
    getModel().updateBudget(budget)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new NetworkObserver<Budget>() {
          @Override
          public void onError(String msg, int code) {
            getView().onBudgetFailed(msg);
          }

          @Override
          public void onNext(Budget budget) {
            getView().onBudget(budget.getBudget());
          }
        });
  }
}
