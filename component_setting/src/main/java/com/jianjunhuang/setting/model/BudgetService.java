package com.jianjunhuang.setting.model;

import com.jianjunhuang.howmuch.protocol.user.AddBudgetRequest;
import com.jianjunhuang.howmuch.protocol.user.Budget;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface BudgetService {

  @POST("/user/requestBudget")
  Observable<Budget> requestBudget(@Body String empty);

  @POST("/user/updateBudget")
  Observable<Budget> updateBudget(@Body AddBudgetRequest request);
}
