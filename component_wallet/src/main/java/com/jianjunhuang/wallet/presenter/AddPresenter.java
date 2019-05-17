package com.jianjunhuang.wallet.presenter;

import com.alibaba.android.arouter.utils.TextUtils;
import com.jianjunhuang.common_base.mvp.BasePresenter;
import com.jianjunhuang.common_base.rxjava.NetworkObserver;
import com.jianjunhuang.howmuch.protocol.wallet.Wallet;
import com.jianjunhuang.wallet.WalletContact.IAddView;
import com.jianjunhuang.wallet.WalletContact.IWalletModel;
import com.jianjunhuang.wallet.model.WalletModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.math.BigDecimal;
import java.util.List;

public class AddPresenter extends BasePresenter<IAddView, IWalletModel> {

  @Override
  protected IWalletModel createModel() {
    return new WalletModel();
  }

  public void addWallet(String walletName, String netAssets, String liability) {
    if (TextUtils.isEmpty(walletName)) {
      getView().onAddFailed("please input wallet name!");
      return;
    }
    if (TextUtils.isEmpty(netAssets)) {
      getView().onAddFailed("please input net assets!");
      return;
    }
    if (TextUtils.isEmpty(liability)) {
      getView().onAddFailed("please input liability!");
      return;
    }

    Wallet wallet = new Wallet();
    wallet.setName(walletName);
    wallet.setTotal(new BigDecimal(netAssets));
    wallet.setLiability(new BigDecimal(liability));
    getModel().addWallet(wallet).observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new NetworkObserver<Wallet>() {
          @Override
          public void onError(String msg, int code) {
            getView().onAddFailed(msg);
          }

          @Override
          public void onNext(Wallet wallet) {
            getView().onAddSuccess();
          }
        });
  }

  public void queryWallets() {
    getModel().queryWallets()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new NetworkObserver<List<Wallet>>() {
          @Override
          public void onError(String msg, int code) {

          }

          @Override
          public void onNext(List<Wallet> wallets) {
            
          }
        });
  }
}
