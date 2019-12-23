package com.jianjunhuang.bill;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.annotation.Nullable;
import com.jianjunhuang.bill.view.BillChartFragment;
import com.jianjunhuang.common_base.utils.ToastUtils;
import com.jianjunhuang.common_base.widget.cal.BaseCalendarAdapter.ItemData;
import com.jianjunhuang.common_base.widget.cal.CalendarView;
import com.jianjunhuang.common_base.widget.cal.CalendarView.OnDayClickListener;
import java.util.Calendar;
import java.util.List;

public class CalendarFragment extends BillChartFragment {

  private CalendarView mCalendarView;

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    mBinding.chartViewStub.getViewStub().setLayoutResource(R.layout.bill_chart_calendar);
    View container = mBinding.chartViewStub.getViewStub().inflate();
    mCalendarView = container.findViewById(R.id.calendar_view);

  }

  private static final String TAG = "CalendarFragment";

  @Override
  public void onResume() {
    super.onResume();
    mCalendarView.setOnDayClickListener(new OnDayClickListener() {
      @Override
      public void onDyClick(int year, int month, int day, List<ItemData> mList) {
        Calendar calendar = Calendar.getInstance();
        --month;
        calendar.set(year, month, day, 0, 0);
        ToastUtils.show(calendar.getTime().toString());
        Log.i(TAG, "onDyClick: " + calendar.getTime().toString());
        long start = calendar.getTime().getTime();
        calendar.set(year, month, day, 24, 0);
        Log.i(TAG, "onDyClick: " + calendar.getTime().toString());
        long end = calendar.getTime().getTime();
        getPresenter().queryBills(start, end);
      }
    });
  }
}
