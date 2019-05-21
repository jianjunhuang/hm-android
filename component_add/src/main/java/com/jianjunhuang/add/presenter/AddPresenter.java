package com.jianjunhuang.add.presenter;

import android.text.TextUtils;
import android.util.ArraySet;
import com.jianjunhuang.add.AddContact;
import com.jianjunhuang.add.AddContact.IAddModel;
import com.jianjunhuang.add.model.AddModel;
import com.jianjunhuang.common_base.mvp.BasePresenter;
import com.jianjunhuang.common_base.rxjava.NetworkObserver;
import com.jianjunhuang.howmuch.protocol.bill.AddBillRequest;
import com.jianjunhuang.howmuch.protocol.bill.BillResponse;
import com.jianjunhuang.howmuch.protocol.type.Type;
import com.jianjunhuang.howmuch.protocol.wallet.Wallet;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AddPresenter extends BasePresenter<AddContact.IAddView, AddContact.IAddModel> {

  @Override
  protected IAddModel createModel() {
    return new AddModel();
  }

  public void queryWallets() {
    getModel().queryWallets()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new NetworkObserver<List<Wallet>>() {
          @Override
          public void onError(String msg, int code) {
            getView().onWalletList(null);
          }

          @Override
          public void onNext(List<Wallet> wallets) {
            getView().onWalletList(wallets);
          }
        });
  }

  public void queryTypes() {
    getModel().queryTypes()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new NetworkObserver<List<Type>>() {
          @Override
          public void onError(String msg, int code) {
            getView().onTypeList(null);
          }

          @Override
          public void onNext(List<Type> types) {
            getView().onTypeList(types);
          }
        });
  }

  private String mWalletId;
  private Set<String> mTypeIds = new HashSet<>();

  public void updateWalletId(String walletId) {
    this.mWalletId = walletId;
  }

  public void updateTypes(String typeId) {
    if (mTypeIds.contains(typeId)) {
      mTypeIds.remove(typeId);
    } else {
      mTypeIds.add(typeId);
    }
  }

  public void addBill(BigDecimal money, String title, long date) {
    if (TextUtils.isEmpty(title)) {
      getView().onAddFailed("title is empty!");
      return;
    }
    AddBillRequest request = new AddBillRequest();
    request.setTypeIds(new ArrayList<>(mTypeIds));
    request.setWalletId(mWalletId);
    request.setTitle(title);
    request.setDate(date);
    request.setMoney(money);
    getModel().addBill(request)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new NetworkObserver<BillResponse>() {
          @Override
          public void onError(String msg, int code) {
            getView().onAddFailed(msg);
          }

          @Override
          public void onNext(BillResponse s) {
            getView().onAddSuccess();
          }
        });
  }


}
