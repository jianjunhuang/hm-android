package com.jianjunhuang.add.model;

import com.jianjunhuang.add.AddContact;
import com.jianjunhuang.common_base.net.NetUtils;
import com.jianjunhuang.howmuch.protocol.bill.AddBillRequest;
import com.jianjunhuang.howmuch.protocol.bill.BillResponse;
import com.jianjunhuang.howmuch.protocol.type.Type;
import com.jianjunhuang.howmuch.protocol.wallet.Wallet;
import io.reactivex.Observable;
import java.util.List;

public class AddModel implements AddContact.IAddModel {

  private AddService mAddService;

  public AddModel() {
    mAddService = NetUtils.createService(AddService.class);
  }

  @Override
  public Observable<List<Wallet>> queryWallets() {
    return mAddService.queryWallets("");
  }

  @Override
  public Observable<List<Type>> queryTypes() {
    return mAddService.queryType("");
  }

  @Override
  public Observable<BillResponse> addBill(AddBillRequest request) {
    return mAddService.addBill(request);
  }
}
