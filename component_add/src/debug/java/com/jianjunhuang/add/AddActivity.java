package com.jianjunhuang.add;

import android.os.Bundle;
import androidx.annotation.Nullable;
import com.jianjunhuang.common_base.base.BaseActivity;
import com.jianjunhuang.common_base.utils.FragmentUtil;

public class AddActivity extends BaseActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    FragmentUtil.replace(getSupportFragmentManager(), new AddFragment(), android.R.id.content);
  }
}
