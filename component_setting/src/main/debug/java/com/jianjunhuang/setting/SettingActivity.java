package com.jianjunhuang.setting;

import android.os.Bundle;
import androidx.annotation.Nullable;
import com.jianjunhuang.common_base.base.BaseActivity;
import com.jianjunhuang.common_base.utils.FragmentUtil;

public class SettingActivity extends BaseActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    FragmentUtil.replace(getSupportFragmentManager(), new SettingFragment(), android.R.id.content);
  }
}
