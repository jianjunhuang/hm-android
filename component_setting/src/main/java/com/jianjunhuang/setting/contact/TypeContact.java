package com.jianjunhuang.setting.contact;

import com.jianjunhuang.common_base.mvp.IModel;
import com.jianjunhuang.common_base.mvp.IView;
import com.jianjunhuang.howmuch.protocol.type.Type;
import com.jianjunhuang.howmuch.protocol.type.TypeRequest;
import io.reactivex.Observable;
import java.util.List;

public interface TypeContact {

  interface ITypeView extends IView {

    void onAddTypeSuccess(Type type);

    void onAddTypeFailed(String reason);

    void onTypeList(List<Type> types);

    void onDelTypeSuccess(int pos);

    void onDelTypeFailed(String reason);
  }

  interface ITypeModel extends IModel {

    Observable<List<Type>> queryTypes();

    Observable<Type> addType(TypeRequest request);

    Observable<String> delType(String typeId);
  }


}
