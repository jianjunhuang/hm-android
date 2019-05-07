package com.jianjunhuang.wallet;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ItemDecoration;
import androidx.recyclerview.widget.RecyclerView.State;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.jianjunhuang.common_base.base.BaseActivity;
import com.jianjunhuang.common_base.router.RouterPath;
import com.jianjunhuang.common_base.utils.SizeUtils;
import com.jianjunhuang.wallet.databinding.WalletActivityAddBinding;
import java.util.Arrays;

@Route(path = RouterPath.WALLET_ACTIVITY)
public class WalletAddActivity extends BaseActivity {

  private WalletActivityAddBinding mBinding;
  private WalletColorAdapter mAdapter;
  private static final String TAG = "WalletAddActivity";

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mBinding = DataBindingUtil.setContentView(this, R.layout.wallet_activity_add);
    initToolbar(mBinding.toolBar);
    initColorRecyclerView(mBinding.rvColor);
  }

  private void initToolbar(Toolbar toolBar) {
    setSupportActionBar(toolBar);
    toolBar.setNavigationOnClickListener(v -> finish());
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.wallet_add_menu, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == R.id.menu_save) {
      finish();
    }
    return super.onOptionsItemSelected(item);
  }

  private void initColorRecyclerView(RecyclerView rvColor) {
    rvColor.setLayoutManager(new GridLayoutManager(this, 4));
    mAdapter = new WalletColorAdapter();
    rvColor.setAdapter(mAdapter);
    rvColor.addItemDecoration(new ItemDecoration() {
      @Override
      public void getItemOffsets(@NonNull Rect outRect, @NonNull View view,
          @NonNull RecyclerView parent, @NonNull State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.left = SizeUtils.dpToPx(8);
        outRect.right = SizeUtils.dpToPx(8);
        outRect.top = SizeUtils.dpToPx(16);
      }
    });
    mAdapter.addData(Arrays.asList(getResources().getStringArray(R.array.wallet_colors)));
  }

}
