package com.jianjunhuang.add.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import com.jianjunhuang.add.AddContact.IAddView;
import com.jianjunhuang.add.R;
import com.jianjunhuang.add.adapter.TypeAdapter;
import com.jianjunhuang.add.adapter.WalletAdapter;
import com.jianjunhuang.add.databinding.AddInputFragmentBinding;
import com.jianjunhuang.add.presenter.AddPresenter;
import com.jianjunhuang.common_base.base.BaseMVPFragment;
import com.jianjunhuang.common_base.utils.KeyboardUtil;
import com.jianjunhuang.common_base.utils.ToastUtils;
import com.jianjunhuang.howmuch.protocol.type.Type;
import com.jianjunhuang.howmuch.protocol.wallet.Wallet;
import com.kunzisoft.switchdatetime.SwitchDateTimeDialogFragment;
import com.kunzisoft.switchdatetime.SwitchDateTimeDialogFragment.OnButtonClickListener;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InputFragment extends BaseMVPFragment<AddPresenter> implements OnClickListener,
    IAddView {

  private AddInputFragmentBinding mBinding;

  private String mStyle;

  public static final String STYLE_EXPAND = "style_expand";

  public static final String STYLE_INCOME = "style_income";

  private static final String KEY_STYLE = "key_style";

  private TypeAdapter mTypeAdapter;
  private WalletAdapter mWalletAdapter;

  private SimpleDateFormat mDateFormat = new SimpleDateFormat("YYYY/MM/dd HH:mm");

  private SwitchDateTimeDialogFragment mDateDialog;

  private Date mNowDate = new Date();

  private BigDecimal mZero = new BigDecimal(0);

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

    setDate(mNowDate);
    mDateDialog = SwitchDateTimeDialogFragment.newInstance(
        "Time",
        "OK",
        "Cancel"
    );

    mDateDialog.startAtCalendarView();
    mDateDialog.setOnButtonClickListener(new OnButtonClickListener() {
      @Override
      public void onPositiveButtonClick(Date date) {
        setDate(date);
      }

      @Override
      public void onNegativeButtonClick(Date date) {

      }
    });

    mBinding.edtMoney.requestFocus();
    mWalletAdapter = new WalletAdapter(getContext());
    mWalletAdapter.setOnChipCheckListener((chip, data, pos) -> {
      getPresenter().updateWalletId(data.getWalletId());
    });
    mTypeAdapter = new TypeAdapter(getContext());
    mTypeAdapter.setOnChipCheckListener((chip, data, pos) -> {
      getPresenter().updateTypes(data.getTypeId());
    });
    mWalletAdapter.bindChipGroup(mBinding.chipGroupWallet);
    mTypeAdapter.bindChipGroup(mBinding.chipGroupType);

  }

  private void setDate(Date date) {
    mBinding.tvDate.setText(mDateFormat.format(date));
  }

  @Override
  public void onClick(View v) {
    int id = v.getId();
    if (id == R.id.tv_date) {
      mDateDialog.show(getChildFragmentManager(), "dataTime");
      return;
    }
    if (id == R.id.fab_add) {
      BigDecimal money = new BigDecimal(mBinding.edtMoney.getText().toString());
      getPresenter()
          .addBill(mStyle.equals(STYLE_EXPAND) ? mZero.subtract(money) : money,
              mBinding.edtTitle.getText().toString(), mNowDate.getTime());
    }
  }

  @Override
  public void onResume() {
    super.onResume();
    getPresenter().queryTypes();
    getPresenter().queryWallets();
  }

  @Override
  public void onWalletList(List<Wallet> wallets) {
    if (wallets == null) {
      wallets = new ArrayList<>();
    }
    // for add
    wallets.add(new Wallet());
    mWalletAdapter.refresh(wallets);
  }

  @Override
  public void onTypeList(List<Type> types) {
    if (types == null) {
      types = new ArrayList<>();
    }
    // for add
    types.add(new Type());
    mTypeAdapter.refresh(types);
  }

  @Override
  public void onAddSuccess() {
    ToastUtils.show("success");
    mBinding.edtMoney.setText("");
    mBinding.edtTitle.setText("");
    setDate(mNowDate = new Date());
    KeyboardUtil.hideSoftInput(getActivity());
    mBinding.chipGroupType.clearCheck();
    mBinding.chipGroupWallet.clearCheck();
  }

  @Override
  public void onAddFailed(String reason) {
    ToastUtils.show(reason);
  }

  @Override
  protected AddPresenter createPresenter() {
    return new AddPresenter();
  }
}
