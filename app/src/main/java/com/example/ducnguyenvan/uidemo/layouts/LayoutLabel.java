package com.example.ducnguyenvan.uidemo.layouts;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.ducnguyenvan.uidemo.R;

public class LayoutLabel extends FrameLayout {
    public LayoutLabel(@NonNull Context context) {
        super(context);
    }

    public LayoutLabel(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LayoutLabel(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public LayoutLabel(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private TextView title;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        title = (TextView)findViewById(R.id.txtContent);

        int widthConstraints = getPaddingLeft() + getPaddingRight();
        int heightConstraints = getPaddingTop() + getPaddingBottom();
        int width = 0;
        int height = 0;

        //measure title
        measureChildWithMargins(title,widthMeasureSpec,widthConstraints,heightMeasureSpec,heightConstraints);

        width += title.getMeasuredWidth();
        height += title.getMeasuredHeight();
        //Log.i("height", "" + height + ", width: " + width);

        setMeasuredDimension(resolveSize(width,widthMeasureSpec),resolveSize(height,heightMeasureSpec));
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        final int thisLeft = this.getPaddingLeft();
        final int thisTop = this.getPaddingTop();
        int curRight = thisLeft + title.getMeasuredWidth();
        int curBottom = thisTop + title.getMeasuredHeight();
        //Log.i("layout", thisLeft + ", " + thisTop + ", " + curRight + ", " + curBottom);
        title.layout(thisLeft,thisTop,curRight,curBottom);
    }
    @Override
    protected void measureChildWithMargins(View child, int parentWidthMeasureSpec, int widthUsed, int parentHeightMeasureSpec, int heightUsed) {
        MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
        int childWidthMeasureSpec = getChildMeasureSpec(parentWidthMeasureSpec, widthUsed + lp.leftMargin + lp.rightMargin,lp.width);
        int childHeightMeasureSpec = getChildMeasureSpec(parentHeightMeasureSpec, heightUsed + lp.topMargin + lp.bottomMargin, lp.height);

        child.measure(childWidthMeasureSpec,childHeightMeasureSpec);
    }
}
