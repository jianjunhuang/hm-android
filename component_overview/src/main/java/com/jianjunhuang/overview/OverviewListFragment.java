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
import com.alibaba.android.arouter.facade.annotation.Route;
import com.jianjunhuang.common_base.base.BaseFragment;
import com.jianjunhuang.common_base.utils.SizeUtils;
import com.jianjunhuang.component_overview.R;
import com.jianjunhuang.overview.databinding.OverviewListFragmentBinding;
import java.util.ArrayList;
import java.util.List;

@Route(path = "/overview/list/fragment")
public class OverviewListFragment extends BaseFragment {

  private OverviewAdapter mAdapter;
  private OverviewListFragmentBinding mBinding;

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
    mBinding = DataBindingUtil.inflate(inflater, R.layout.overview_list_fragment, container, false);
    initRecyclerView(mBinding.rvOverview);
    return mBinding.getRoot();
  }

  private void initRecyclerView(RecyclerView rvOverview) {
    rvOverview.setLayoutManager(new LinearLayoutManager(getContext()));
    mAdapter = new OverviewAdapter();
    rvOverview.setAdapter(mAdapter);
    rvOverview.addItemDecoration(new ItemDecoration() {

      Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

      @Override
      public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull State state) {
        super.onDraw(c, parent, state);
        paint.setColor(Color.parseColor("#9e9e9e"));
        for (int i = 0; i < parent.getChildCount(); i++) {
          View view = parent.getChildAt(i);
          int pos = parent.getChildAdapterPosition(view);
          if (pos % 5 == 0 && pos != 0) {
            c.drawLine(0, view.getTop(), view.getWidth(),
                view.getTop(), paint);
          }
        }
      }

      @Override
      public void getItemOffsets(@NonNull Rect outRect, @NonNull View view,
          @NonNull RecyclerView parent, @NonNull State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.top = SizeUtils.dpToPx(16);
      }
    });
    List<ViewHolder> viewHolders = new ArrayList<>();
    for (int i = 0; i < 50; i++) {
      ViewHolder viewHolder;
      viewHolder = new ViewHolder(i % 5 == 0, "");
      viewHolders.add(viewHolder);
    }
    mAdapter.addData(viewHolders);
  }
}
