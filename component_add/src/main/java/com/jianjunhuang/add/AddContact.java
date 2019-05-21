package com.jianjunhuang.add;

import com.jianjunhuang.common_base.mvp.IModel;
import com.jianjunhuang.common_base.mvp.IView;
import com.jianjunhuang.howmuch.protocol.bill.AddBillRequest;
import com.jianjunhuang.howmuch.protocol.bill.BillResponse;
import com.jianjunhuang.howmuch.protocol.type.Type;
import com.jianjunhuang.howmuch.protocol.wallet.Wallet;
import io.reactivex.Observable;
import java.util.List;

public interface AddContact {

  interface IAddView extends IView {

    void onWalletList(List<Wallet> wallets);

    void onTypeList(List<Type> types);

    void onAddSuccess();

    void onAddFailed(String reason);
  }

  interface IAddModel extends IModel {

    Observable<List<Wallet>> queryWallets();

    Observable<List<Type>> queryTypes();

    Observable<BillResponse> addBill(AddBillRequest request);
  }
}
