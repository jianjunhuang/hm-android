package com.jianjunhuang.main;

import android.os.Bundle;
import android.util.SparseArray;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jianjunhuang.common_base.base.BaseActivity;
import com.jianjunhuang.common_base.base.BaseFragment;
import com.jianjunhuang.main.databinding.MainActivityBinding;

public class MainActivity extends BaseActivity {

  private MainActivityBinding mBinding;

  private int mLastFragmentId = -1;
  private SparseArray<BaseFragment> mFragmentMap;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mBinding = DataBindingUtil.setContentView(this, R.layout.main_activity);
    initBottomNavigation(mBinding.bottomNavigation);
    mFragmentMap = new SparseArray<>();
    mBinding.bottomNavigation.setSelectedItemId(R.id.menu_add);
  }

  private void initBottomNavigation(BottomNavigationView bottomNavigation) {
    bottomNavigation.setOnNavigationItemSelectedListener(menuItem -> {
      if (menuItem.getItemId() != mLastFragmentId) {
        navigateToPager(menuItem.getItemId());
      }
      return true;
    });
  }

  private void navigateToPager(int id) {
    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
    BaseFragment fragment = mFragmentMap.get(id);
    if (fragment == null) {
      fragment = MainFragmentFactory.createFragmentById(id);
      if (fragment != null) {
        mFragmentMap.put(id, fragment);
        transaction.add(R.id.fl_main, fragment);
      }
    } else {
      transaction.show(fragment);
    }
    BaseFragment lastFragment = mFragmentMap.get(mLastFragmentId);
    if (lastFragment != null) {
      transaction.hide(lastFragment);
    }
    mLastFragmentId = id;
    transaction.commit();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();

  }

  @Override
  protected void onResume() {
    super.onResume();
  }
}
