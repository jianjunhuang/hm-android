package com.jianjunhuang.add;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import com.jianjunhuang.add.databinding.AddInputFragmentBinding;
import com.jianjunhuang.common_base.base.BaseFragment;
import com.jianjunhuang.common_base.router.RouterManager;

public class InputFragment extends BaseFragment implements OnClickListener {

  private AddInputFragmentBinding mBinding;

  private String mStyle;

  public static final String STYLE_EXPAND = "style_expand";

  public static final String STYLE_INCOME = "style_income";

  private static final String KEY_STYLE = "key_style";

  public static InputFragment newInstance(String style) {
    Bundle args = new Bundle();
    args.putString(KEY_STYLE, style);
    InputFragment fragment = new InputFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    mBinding = DataBindingUtil.inflate(inflater, R.layout.add_input_fragment, container, false);
    mBinding.setListener(this);
    return mBinding.getRoot();
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    Bundle bundle = getArguments();
    if (bundle != null) {
      mStyle = bundle.getString(KEY_STYLE);
    }

    if (STYLE_EXPAND.equals(mStyle)) {
      mBinding.tvMoney.setTextColor(ContextCompat.getColor(getContext(), R.color.expendRed));
      mBinding.tvTitleMoney.setTextColor(ContextCompat.getColor(getContext(), R.color.expendRed));
    } else {
      mBinding.tvMoney.setTextColor(ContextCompat.getColor(getContext(), R.color.incomeGreen));
      mBinding.tvTitleMoney.setTextColor(ContextCompat.getColor(getContext(), R.color.incomeGreen));
    }
  }

  @Override
  public void onClick(View v) {
    int id = v.getId();
    if (id == R.id.chip_add_type) {
      RouterManager.Navigate.toTypeAdd();
    } else if (id == R.id.chip_add_wallet) {
      RouterManager.Navigate.toWalletAdd();
    }
  }
}
