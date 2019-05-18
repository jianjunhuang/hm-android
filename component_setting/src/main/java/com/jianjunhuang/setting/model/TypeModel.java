package com.jianjunhuang.setting.model;

import com.jianjunhuang.common_base.net.NetUtils;
import com.jianjunhuang.howmuch.protocol.type.TypeRequest;
import com.jianjunhuang.setting.contact.TypeContact.ITypeModel;
import com.jianjunhuang.howmuch.protocol.type.Type;
import io.reactivex.Observable;
import java.util.List;

public class TypeModel implements ITypeModel {

  private TypeService mTypeService;

  public TypeModel() {
    mTypeService = NetUtils.createService(TypeService.class);
  }

  @Override
  public Observable<List<Type>> queryTypes() {
    return mTypeService.queryType("");
  }

  @Override
  public Observable<Type> addType(TypeRequest request) {
    return mTypeService.addType(request);
  }

  @Override
  public Observable<String> delType(String typeId) {
    return mTypeService.delType(typeId);
  }
}
