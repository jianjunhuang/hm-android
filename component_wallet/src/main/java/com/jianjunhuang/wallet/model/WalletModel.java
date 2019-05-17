package com.jianjunhuang.wallet.model;

import com.jianjunhuang.common_base.net.NetUtils;
import com.jianjunhuang.howmuch.protocol.wallet.Wallet;
import com.jianjunhuang.wallet.WalletContact.IWalletModel;
import io.reactivex.Observable;
import java.util.List;

public class WalletModel implements IWalletModel {

  private WalletService mWalletService;

  public WalletModel() {
    mWalletService = NetUtils.createService(WalletService.class);
  }

  @Override
  public Observable<List<Wallet>> queryWallets() {
    return mWalletService.queryWallets("");
  }

  @Override
  public void queryBillsById(String walletId) {

  }

  @Override
  public Observable<Wallet> addWallet(Wallet wallet) {
    return mWalletService.addWallets(wallet);
  }

  @Override
  public Observable<String> delWallet(String walletId) {
    return mWalletService.delWallets(walletId);
  }
}
