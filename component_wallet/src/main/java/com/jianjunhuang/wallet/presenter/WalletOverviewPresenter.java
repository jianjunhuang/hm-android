package com.jianjunhuang.wallet.presenter;

import android.text.TextUtils;
import com.jianjunhuang.common_base.mvp.BasePresenter;
import com.jianjunhuang.common_base.rxjava.NetworkObserver;
import com.jianjunhuang.common_base.rxjava.NetworkSingleObserver;
import com.jianjunhuang.wallet.WalletContact.IOverviewView;
import com.jianjunhuang.wallet.WalletContact.IWalletModel;
import com.jianjunhuang.wallet.WalletViewHolder;
import com.jianjunhuang.wallet.model.WalletModel;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.math.BigDecimal;
import java.util.List;

public class WalletOverviewPresenter extends BasePresenter<IOverviewView, IWalletModel> {

  private BigDecimal mTotal;
  private BigDecimal mLiability;

  @Override
  protected IWalletModel createModel() {
    return new WalletModel();
  }

  public void queryWallets() {
    mTotal = new BigDecimal(0);
    mLiability = new BigDecimal(0);
    getModel().queryWallets()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .flatMap(Observable::fromIterable)
        .flatMap(
            wallet -> {
              if (wallet != null) {
                mTotal = mTotal.add(wallet.getTotal());
                mLiability = mLiability.add(wallet.getLiability());
              }
              return Observable.just(new WalletViewHolder(WalletViewHolder.TYPE_DEFAULT, wallet));
            })
        .toList()
        .subscribe(new NetworkSingleObserver<List<WalletViewHolder>>() {
          @Override
          public void onError(String msg, int code) {
            getView().onWallets(null);
          }

          @Override
          public void onSuccess(List<WalletViewHolder> walletViewHolders) {
            getView().onWallets(walletViewHolders);
            getView().onWalletOverview(mTotal, mLiability);
          }
        });
  }

  public void delWallet(int pos, String walletId) {
    if (TextUtils.isEmpty(walletId)) {
      getView().onWalletDelFailed("walletId is Empty!");
      return;
    }
    getModel().delWallet(walletId)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new NetworkObserver<String>() {
          @Override
          public void onError(String msg, int code) {
            getView().onWalletDelFailed(msg);
          }

          @Override
          public void onNext(String s) {
            getView().onWalletDelSuccess(pos);
          }
        });
  }


}
