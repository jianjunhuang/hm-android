package com.jianjunhuang.common_base.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;
import com.jianjunhuang.common_base.R;


public class DotView extends View {
  private static final float DEFAULT_DTO_RADIUS = 3.5f;
  private static final int DEFAULT_BORDER_COLOR = Color.RED;
  private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

  public DotView(Context context) {
    this(context, null);
  }

  public DotView(Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public DotView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);

    TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DotView, defStyleAttr, 0);
    mPaint.setColor(a.getColor(R.styleable.DotView_dotColor, DEFAULT_BORDER_COLOR));
    a.recycle();
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    float x = this.getWidth() / (float) 2;
    float y = this.getHeight() / (float) 2;
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    int defSize = dp2px(getContext(), DEFAULT_DTO_RADIUS * 2);
    int specSize = MeasureSpec.getSize(widthMeasureSpec);
    int specMode = MeasureSpec.getMode(widthMeasureSpec);

    int result = 0;
    switch (specMode) {
      case MeasureSpec.UNSPECIFIED:
      case MeasureSpec.AT_MOST:
        result = defSize;
        break;
      case MeasureSpec.EXACTLY:
        result = specSize;
        break;
    }
    setMeasuredDimension(result, result);
  }

  private int dp2px(Context context, float dipValue) {
    final float scale = context.getResources().getDisplayMetrics().density;
    return (int) (dipValue * scale + 0.5f);
  }

  public void setDotFillColor(@ColorInt int color) {
    mPaint.setColor(color);
    invalidate();
  }

}
