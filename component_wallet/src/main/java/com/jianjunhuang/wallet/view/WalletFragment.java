package com.jianjunhuang.wallet.view;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ItemDecoration;
import androidx.recyclerview.widget.RecyclerView.State;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.jianjunhuang.common_base.base.BaseMVPFragment;
import com.jianjunhuang.common_base.router.RouterPath;
import com.jianjunhuang.common_base.utils.CurrencyUtils;
import com.jianjunhuang.common_base.utils.SizeUtils;
import com.jianjunhuang.common_base.utils.ToastUtils;
import com.jianjunhuang.wallet.R;
import com.jianjunhuang.wallet.WalletAdapter;
import com.jianjunhuang.wallet.WalletContact.IOverviewView;
import com.jianjunhuang.wallet.WalletDetailActivity;
import com.jianjunhuang.wallet.WalletViewHolder;
import com.jianjunhuang.wallet.databinding.WalletFragmentBinding;
import com.jianjunhuang.wallet.presenter.WalletOverviewPresenter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Route(path = RouterPath.WALLET_FRAGMENT)
public class WalletFragment extends BaseMVPFragment<WalletOverviewPresenter> implements IOverviewView {

  private WalletFragmentBinding mBinding;
  private WalletAdapter mAdapter;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    mBinding = DataBindingUtil.inflate(inflater, R.layout.wallet_fragment, container, false);
    initRecyclerView(mBinding.rvWallet);
    return mBinding.getRoot();
  }

  private void initRecyclerView(RecyclerView rvWallet) {
    rvWallet.setLayoutManager(new GridLayoutManager(getContext(), 2));
    mAdapter = new WalletAdapter();
    mAdapter.setOnItemClickListener((adapter, view, position) -> {
      if (WalletViewHolder.TYPE_ADD == adapter.getItemViewType(position)) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), WalletAddActivity.class);
        startActivity(intent);
      } else {
        WalletViewHolder viewHolder = mAdapter.getItem(position);
        if (viewHolder != null) {
          WalletDetailActivity.startActivity(getActivity(), viewHolder.getWallet());
        }
      }
    });
    mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
      Intent intent = new Intent();
      intent.setClass(getActivity(), WalletAddActivity.class);
      startActivity(intent);
    });
    mAdapter.setOnItemLongClickListener((adapter, view, position) -> {
      if (position != adapter.getData().size() - 1) {
        PopupMenu popupMenu = new PopupMenu(getContext(), view, Gravity.TOP);
        popupMenu.getMenuInflater().inflate(R.menu.wallet_del_menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(item -> {
          WalletViewHolder viewHolder = mAdapter.getItem(position);
          if (viewHolder != null) {
            getPresenter().delWallet(position, viewHolder.getWallet().getWalletId());
          }
          return true;
        });
        popupMenu.show();
      }
      return true;
    });
    rvWallet.setAdapter(mAdapter);
    rvWallet.addItemDecoration(new ItemDecoration() {
      @Override
      public void getItemOffsets(@NonNull Rect outRect, @NonNull View view,
          @NonNull RecyclerView parent, @NonNull State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int pos = parent.getChildAdapterPosition(view);
        outRect.top = SizeUtils.dpToPx(16);
        boolean tag = pos % 2 == 0;
        outRect.left = SizeUtils.dpToPx(tag ? 16 : 8);
        outRect.right = SizeUtils.dpToPx(tag ? 8 : 16);
      }
    });
  }

  @Override
  protected void onLazyLoad(boolean isLoad) {
    super.onLazyLoad(isLoad);
    //todo fix this
  }

  @Override
  public void onResume() {
    super.onResume();
    getPresenter().queryWallets();
  }

  @Override
  public void onWalletOverview(BigDecimal total, BigDecimal liability) {
    if (total == null || liability == null) {
      return;
    }
    mBinding.includeOverview.tvTotalAssets.setText(CurrencyUtils.format(total.longValue()));
    mBinding.includeOverview.tvLiability.setText(CurrencyUtils.format(liability.longValue()));
    mBinding.includeOverview.tvNetAssets
        .setText(CurrencyUtils.format(total.subtract(liability).longValue()));
  }

  @Override
  public void onWallets(List<WalletViewHolder> walletList) {
    if (walletList == null) {
      walletList = new ArrayList<>();
    }
    walletList.add(new WalletViewHolder(WalletViewHolder.TYPE_ADD));
    mAdapter.replaceData(walletList);
    mAdapter.notifyDataSetChanged();
  }

  @Override
  public void onWalletDelSuccess(int pos) {
    mAdapter.remove(pos);
  }

  @Override
  public void onWalletDelFailed(String reason) {
    ToastUtils.show(reason);
  }

  @Override
  protected WalletOverviewPresenter createPresenter() {
    return new WalletOverviewPresenter();
  }
}
