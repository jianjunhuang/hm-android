package com.jianjunhuang.setting.view;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.jianjunhuang.common_base.router.RouterPath;
import com.jianjunhuang.common_base.utils.KeyboardUtil;
import com.jianjunhuang.common_base.utils.ToastUtils;
import com.jianjunhuang.setting.R;
import com.jianjunhuang.setting.TypeChipsAdapter;
import com.jianjunhuang.setting.contact.TypeContact.ITypeView;
import com.jianjunhuang.common_base.base.BaseMVPActivity;
import com.jianjunhuang.howmuch.protocol.type.Type;
import com.jianjunhuang.setting.databinding.SettingTypeActivityBinding;
import com.jianjunhuang.setting.presenter.TypePresenter;
import java.util.List;

@Route(path = RouterPath.TYPE_ACTIVITY)
public class TypeActivity extends BaseMVPActivity<TypePresenter> implements ITypeView {

  private SettingTypeActivityBinding mBinding;
  private TypeChipsAdapter mChipsAdapter;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mBinding = DataBindingUtil.setContentView(this, R.layout.setting_type_activity);
    mChipsAdapter = new TypeChipsAdapter(this);
    mChipsAdapter.bindChipGroup(mBinding.typeLayout);
    mBinding.ivAdd
        .setOnClickListener(v -> getPresenter().addType(mBinding.edtType.getText().toString()));
    mChipsAdapter
        .setOnDelClickListener((chip, data, pos) -> getPresenter().delType(pos, data.getTypeId()));
  }

  @Override
  protected TypePresenter createPresenter() {
    return new TypePresenter();
  }

  @Override
  protected void onResume() {
    super.onResume();
    getPresenter().queryTypes();
  }

  @Override
  public void onAddTypeSuccess(Type type) {
    if (type != null) {
      ToastUtils.show("type added!");
      mBinding.edtType.setText("");
      mChipsAdapter.addData(type);
    }
  }

  @Override
  public void onAddTypeFailed(String reason) {
    ToastUtils.show(reason);
  }

  @Override
  public void onTypeList(List<Type> types) {
    if (types != null) {
      mChipsAdapter.refresh(types);
    }
  }

  @Override
  public void onDelTypeSuccess(int pos) {
    mChipsAdapter.remove(pos);
  }

  @Override
  public void onDelTypeFailed(String reason) {
    ToastUtils.show(reason);
  }
}
