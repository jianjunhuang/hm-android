package com.jianjunhuang.overview;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ItemDecoration;
import androidx.recyclerview.widget.RecyclerView.State;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.jianjunhuang.common_base.base.BaseFragment;
import com.jianjunhuang.common_base.router.RouterPath;
import com.jianjunhuang.common_base.utils.SizeUtils;
import com.jianjunhuang.howmuch.protocol.bill.BillResponse;
import com.jianjunhuang.overview.databinding.OverviewListFragmentBinding;
import java.util.ArrayList;
import java.util.List;

@Route(path = RouterPath.OVERVIEW_LIST_FRAGMENT)
public class OverviewListFragment extends BaseFragment {

  private OverviewAdapter mAdapter;
  private OverviewListFragmentBinding mBinding;

  List<BillResponse> billResponses;

  public static OverviewListFragment newInstance() {

    Bundle args = new Bundle();

    OverviewListFragment fragment = new OverviewListFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    ARouter.getInstance().inject(this);
    mBinding = DataBindingUtil.inflate(inflater, R.layout.overview_list_fragment, container, false);
    billResponses = getArguments().getParcelableArrayList("data");
    initRecyclerView(mBinding.rvOverview);
    return mBinding.getRoot();
  }

  private void initRecyclerView(RecyclerView rvOverview) {
    rvOverview.setLayoutManager(new LinearLayoutManager(getContext()));
    mAdapter = new OverviewAdapter();
    rvOverview.setAdapter(mAdapter);
    rvOverview.addItemDecoration(new ItemDecoration() {
      @Override
      public void getItemOffsets(@NonNull Rect outRect, @NonNull View view,
          @NonNull RecyclerView parent, @NonNull State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.top = SizeUtils.dpToPx(16);
      }
    });
    List<ViewHolder> viewHolders = new ArrayList<>();
    for (int i = 0; i < billResponses.size(); i++) {
      ViewHolder viewHolder;
      viewHolder = new ViewHolder(false, "");
      viewHolder.t = billResponses.get(i);
      viewHolders.add(viewHolder);
    }
    mAdapter.replaceData(viewHolders);
    mAdapter.notifyDataSetChanged();
  }
}
