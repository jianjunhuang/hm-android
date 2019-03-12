package com.jianjunhuang.wallet;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ItemDecoration;
import androidx.recyclerview.widget.RecyclerView.State;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener;
import com.jianjunhuang.common_base.base.BaseFragment;
import com.jianjunhuang.common_base.utils.SizeUtils;
import com.jianjunhuang.wallet.databinding.WalletFragmentBinding;
import java.util.ArrayList;
import java.util.List;

@Route(path = "/wallet/fragment")
public class WalletFragment extends BaseFragment {

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
      Intent intent = new Intent();
      if (WalletAdapter.TYPE_ADD == adapter.getItemViewType(position)) {
        intent.setClass(getActivity(), WalletAddActivity.class);
      } else {
        intent.setClass(getActivity(), WalletDetailActivity.class);
      }
      startActivity(intent);
    });
    mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
      Intent intent = new Intent();
      intent.setClass(getActivity(), WalletAddActivity.class);
      startActivity(intent);
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
    List<ViewHolder> viewHolders = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      ViewHolder viewHolder = new ViewHolder(WalletAdapter.TYPE_DEFAULT);
      viewHolders.add(viewHolder);
    }
    viewHolders.add(new ViewHolder(WalletAdapter.TYPE_ADD));
    mAdapter.addData(viewHolders);
  }
}
