package com.jianjunhuang.wallet.model;

import com.jianjunhuang.howmuch.protocol.wallet.Wallet;
import io.reactivex.Observable;
import java.util.List;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface WalletService {

  @POST("wallet/list")
  Observable<List<Wallet>> queryWallets(@Body String body);

  @POST("wallet/update")
  Observable<Wallet> addWallets(@Body Wallet wallet);

  @POST("wallet/del")
  Observable<String> delWallets(@Body String walletId);
}
