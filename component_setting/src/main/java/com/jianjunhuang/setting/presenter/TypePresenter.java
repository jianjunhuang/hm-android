package com.jianjunhuang.setting.presenter;

import android.text.TextUtils;
import com.jianjunhuang.common_base.rxjava.NetworkObserver;
import com.jianjunhuang.howmuch.protocol.type.Type;
import com.jianjunhuang.howmuch.protocol.type.TypeRequest;
import com.jianjunhuang.setting.contact.TypeContact.ITypeModel;
import com.jianjunhuang.setting.contact.TypeContact.ITypeView;
import com.jianjunhuang.common_base.mvp.BasePresenter;
import com.jianjunhuang.setting.model.TypeModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;

public class TypePresenter extends BasePresenter<ITypeView, ITypeModel> {

  @Override
  protected ITypeModel createModel() {
    return new TypeModel();
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

  public void addType(String type) {
    if (TextUtils.isEmpty(type)) {
      getView().onAddTypeFailed("type is empty!");
      return;
    }
    TypeRequest request = new TypeRequest();
    request.setType(type);
    getModel().addType(request)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new NetworkObserver<Type>() {
          @Override
          public void onError(String msg, int code) {
            getView().onAddTypeFailed(msg);
          }

          @Override
          public void onNext(Type type) {
            getView().onAddTypeSuccess(type);
          }
        });
  }

  public void delType(int pos, String typeId) {

    getModel().delType(typeId)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new NetworkObserver<String>() {
          @Override
          public void onError(String msg, int code) {
            getView().onDelTypeFailed(msg);
          }

          @Override
          public void onNext(String type) {
            getView().onDelTypeSuccess(pos);
          }
        });
  }
}
