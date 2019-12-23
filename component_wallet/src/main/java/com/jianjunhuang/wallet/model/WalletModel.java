package com.jianjunhuang.wallet.model;

import com.jianjunhuang.common_base.net.NetUtils;
import com.jianjunhuang.howmuch.protocol.bill.BillByWalletRequest;
import com.jianjunhuang.howmuch.protocol.bill.BillResponse;
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
  public Observable<List<BillResponse>> queryBillsById(String walletId) {
    BillByWalletRequest request = new BillByWalletRequest();
    request.setWalletId(walletId);
    return mWalletService.queryBillsByWallet(request);
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
