package com.jianjunhuang.add.model;

import com.jianjunhuang.howmuch.protocol.bill.AddBillRequest;
import com.jianjunhuang.howmuch.protocol.bill.BillResponse;
import com.jianjunhuang.howmuch.protocol.type.Type;
import com.jianjunhuang.howmuch.protocol.wallet.Wallet;
import io.reactivex.Observable;
import java.util.List;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AddService {

  @POST("wallet/list")
  Observable<List<Wallet>> queryWallets(@Body String body);

  @POST("/type/list")
  Observable<List<Type>> queryType(@Body String empty);

  @POST("/bill/add")
  Observable<BillResponse> addBill(@Body AddBillRequest request);
}
