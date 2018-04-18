package com.example.ducnguyenvan.uidemo.decoration;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.ducnguyenvan.uidemo.MyAdapter;
import com.example.ducnguyenvan.uidemo.model.MyItem;

public class MyDivider extends RecyclerView.ItemDecoration {

    private Drawable mDivider;
    private int mPadding;
    private MyItemDecorationCallback mSimpleItemDecorationCallback;

    public MyDivider(Drawable drawable, int padding, MyItemDecorationCallback myItemDecorationCallback) {
        mDivider = drawable;
        mPadding = padding;
        mSimpleItemDecorationCallback = myItemDecorationCallback;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        if (shouldDecor(parent, parent.getChildAdapterPosition(view))) {
            outRect.bottom = mDivider.getIntrinsicHeight();
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (!(parent.getLayoutManager() instanceof LinearLayoutManager)) return;

        LinearLayoutManager layoutManager = ((LinearLayoutManager) parent.getLayoutManager());

        int firstVisiblePosition = layoutManager.findFirstVisibleItemPosition();
        int lastVisiblePosition = layoutManager.findLastVisibleItemPosition();

        if (firstVisiblePosition < 0) {
            return;
        }

        for (int i = firstVisiblePosition; i < lastVisiblePosition; i++) {
            View child = layoutManager.findViewByPosition(i);
            if (child.getVisibility() != View.GONE && shouldDecor(parent, i)) {
                int left = parent.getPaddingLeft() + mPadding;
                int right = parent.getWidth() - parent.getPaddingRight() - mPadding;

                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

                int top = child.getBottom() + params.bottomMargin;
                int bottom = top + mDivider.getIntrinsicHeight();

                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }

        }
    }

    private boolean shouldDecor(RecyclerView parent, int position) {
        MyItem item = getItemAt(parent.getAdapter(), position);
        if (item == null) return false;
        MyItem nextItem = getItemAt(parent.getAdapter(), position + 1);
        return mSimpleItemDecorationCallback.shouldDecor(item, nextItem);
    }

    private MyItem getItemAt(RecyclerView.Adapter adapter, int position) {
        if (adapter instanceof MyAdapter) {
            return ((MyAdapter) adapter).getItemAt(position);
        } else {
            return null;
        }
    }
}