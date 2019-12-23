package com.jianjunhuang.wallet;

import com.jianjunhuang.common_base.mvp.IModel;
import com.jianjunhuang.common_base.mvp.IView;
import com.jianjunhuang.howmuch.protocol.bill.BillResponse;
import com.jianjunhuang.howmuch.protocol.wallet.Wallet;
import io.reactivex.Observable;
import java.math.BigDecimal;
import java.util.List;

public interface WalletContact {

  interface IOverviewView extends IView {

    void onWalletOverview(BigDecimal total, BigDecimal liability);

    void onWallets(List<WalletViewHolder> walletList);

    void onWalletDelSuccess(int pos);

    void onWalletDelFailed(String reason);

  }

  interface IAddView extends IView {

    void onAddSuccess();

    void onAddFailed(String reason);

  }

  interface IWalletModel extends IModel {

    Observable<List<Wallet>> queryWallets();

    Observable<List<BillResponse>> queryBillsById(String walletId);

    Observable<Wallet> addWallet(Wallet wallet);

    Observable<String> delWallet(String walletId);

  }


}
