package com.jianjunhuang.overview;

import android.os.Bundle;
import androidx.annotation.Nullable;
import com.jianjunhuang.common_base.base.BaseActivity;
import com.jianjunhuang.common_base.utils.FragmentUtil;
import com.jianjunhuang.overview.view.OverviewFragment;

public class OverviewActivity extends BaseActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    FragmentUtil.replace(getSupportFragmentManager(), new OverviewFragment(), android.R.id.content);
  }
}
