package com.jianjunhuang.setting.model;

import com.jianjunhuang.howmuch.protocol.type.Type;
import com.jianjunhuang.howmuch.protocol.type.TypeRequest;
import io.reactivex.Observable;
import java.util.List;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface TypeService {

  @POST("/type/list")
  Observable<List<Type>> queryType(@Body String empty);

  @POST("/type/add")
  Observable<Type> addType(@Body TypeRequest type);

  @POST("/type/del")
  Observable<String> delType(@Body String type);

}
