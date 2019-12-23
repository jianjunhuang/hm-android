package com.jianjunhuang.setting;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import com.jianjunhuang.common_base.base.BaseActivity;
import com.jianjunhuang.common_base.router.RouterManager;
import com.jianjunhuang.common_base.rxjava.NetworkObserver;
import com.jianjunhuang.common_base.utils.FragmentUtil;
import com.jianjunhuang.howmuch.protocol.bill.BillResponse;
import com.jianjunhuang.setting.databinding.SettingRegularExpensesActivityBinding;
import com.jianjunhuang.setting.model.RegularModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;

public class RegularExpensesActivity extends BaseActivity {

  private SettingRegularExpensesActivityBinding mBinding;
  private RegularModel mModel;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mBinding = DataBindingUtil.setContentView(this, R.layout.setting_regular_expenses_activity);
    setSupportActionBar(mBinding.toolBar);
    mBinding.toolBar.setNavigationOnClickListener(v -> finish());
    mModel = new RegularModel();

  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.setting_expenses, menu);
    return true;
  }

  @Override
  protected void onResume() {
    super.onResume();
    mModel.query().subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new NetworkObserver<List<BillResponse>>() {
          @Override
          public void onError(String msg, int code) {

          }

          @Override
          public void onNext(List<BillResponse> billResponses) {
            FragmentUtil
                .replace(getSupportFragmentManager(),
                    RouterManager.Fragment.overviewList(new ArrayList<>(billResponses)),
                    R.id.fl_expenses);
          }
        });
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    if (id == R.id.menu_add_expenses) {
      startActivity(AddRegularExpensesActivity.class);
    }
    return super.onOptionsItemSelected(item);
  }
}
