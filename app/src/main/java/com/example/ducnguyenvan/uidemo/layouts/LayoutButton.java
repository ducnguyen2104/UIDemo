package com.example.ducnguyenvan.uidemo.layouts;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.ducnguyenvan.uidemo.R;

public class LayoutButton extends FrameLayout {
    public LayoutButton(@NonNull Context context) {
        super(context);
    }

    public LayoutButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LayoutButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public LayoutButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private Button button;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        button = (Button)findViewById(R.id.btn);

        int widthConstraints = getPaddingLeft() + getPaddingRight();
        int heightConstraints = getPaddingTop() + getPaddingBottom();
        int width = 0;
        int height = 0;

        //measure button
        measureChildWithMargins(button,widthMeasureSpec,widthConstraints,heightMeasureSpec,heightConstraints);

        width += button.getMeasuredWidth();
        height += button.getMeasuredHeight();

        setMeasuredDimension(resolveSize(width,widthMeasureSpec),resolveSize(height,heightMeasureSpec));
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        final int thisLeft = this.getPaddingLeft();
        final int thisTop = this.getPaddingTop();
        int curRight = thisLeft + button.getMeasuredWidth();
        int curBottom = thisTop + button.getMeasuredHeight();

        button.layout(thisLeft,thisTop,curRight,curBottom);
    }

    @Override
    protected void measureChildWithMargins(View child, int parentWidthMeasureSpec, int widthUsed, int parentHeightMeasureSpec, int heightUsed) {
        MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
        int childWidthMeasureSpec = getChildMeasureSpec(parentWidthMeasureSpec, widthUsed + lp.leftMargin + lp.rightMargin,lp.width);
        int childHeightMeasureSpec = getChildMeasureSpec(parentHeightMeasureSpec, heightUsed + lp.topMargin + lp.bottomMargin, lp.height);

        child.measure(childWidthMeasureSpec,childHeightMeasureSpec);
    }
}
