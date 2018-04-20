package com.example.ducnguyenvan.uidemo.layouts;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ducnguyenvan.uidemo.R;

public class Layout3Pics extends FrameLayout {

    private ImageView img1;
    private ImageView img2;
    private ImageView img3;
    private TextView title;
    private TextView source;
    private TextView comments;

    public Layout3Pics(@NonNull Context context) {
        super(context);
    }

    public Layout3Pics(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Layout3Pics(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public Layout3Pics(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        img1 = (ImageView) findViewById(R.id.img_view1);
        img2 = (ImageView) findViewById(R.id.img_view2);
        img3 = (ImageView) findViewById(R.id.img_view3);
        title = (TextView) findViewById(R.id.txtTitle);
        source = (TextView) findViewById(R.id.txtSource);
        comments = (TextView)findViewById(R.id.txtComments);

        int widthConstraints = getPaddingLeft() + getPaddingRight();
        int heightConstraints = getPaddingTop() + getPaddingBottom();
        int width = 0;
        int height = 0;

        //measure title
        measureChildWithMargins(title,widthMeasureSpec,widthConstraints,heightMeasureSpec,heightConstraints);

        //update constraints
        width += title.getMeasuredWidth();
        height += title.getMeasuredHeight();
        heightConstraints += title.getMeasuredHeight();

        int horizontalWidthMeasureSpec = MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(widthMeasureSpec),MeasureSpec.getMode(widthMeasureSpec));
        int horizontalHeightMeasureSpec = MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(heightMeasureSpec) - title.getMeasuredHeight(),MeasureSpec.getMode(heightMeasureSpec));

        //measure img1
        measureChildWithMargins(img1,horizontalWidthMeasureSpec,0,horizontalHeightMeasureSpec,0);

        //measure img2
        measureChildWithMargins(img2,horizontalWidthMeasureSpec,img1.getMeasuredWidth(),horizontalHeightMeasureSpec,0);

        //measure img3
        measureChildWithMargins(img3,horizontalWidthMeasureSpec,img1.getMeasuredWidth() + img2.getMeasuredWidth(),horizontalHeightMeasureSpec,0);

        height += img1.getMeasuredHeight();

        int horzontalHeightMeasureSpec2 = MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(heightMeasureSpec) - title.getMeasuredHeight() - img1.getMeasuredHeight(), MeasureSpec.getMode(heightMeasureSpec));

        //measure source
        measureChildWithMargins(source,horizontalWidthMeasureSpec, 0, horzontalHeightMeasureSpec2, 0);

        //measure comment
        measureChildWithMargins(comments,horizontalWidthMeasureSpec, source.getMeasuredWidth(),horzontalHeightMeasureSpec2,0);

        height += source.getMeasuredHeight();
        setMeasuredDimension(resolveSize(width,widthMeasureSpec),resolveSize(height,heightMeasureSpec));
    }

    @Override
    protected void measureChildWithMargins(View child, int parentWidthMeasureSpec, int widthUsed, int parentHeightMeasureSpec, int heightUsed) {
        MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
        int childWidthMeasureSpec = getChildMeasureSpec(parentWidthMeasureSpec, widthUsed + lp.leftMargin + lp.rightMargin,lp.width);
        int childHeightMeasureSpec = getChildMeasureSpec(parentHeightMeasureSpec, heightUsed + lp.topMargin + lp.bottomMargin, lp.height);

        child.measure(childWidthMeasureSpec,childHeightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {

        final int thisLeft = this.getPaddingLeft();
        final int thisTop = this.getPaddingTop();
        final int thisRight = this.getMeasuredWidth() - this.getPaddingRight();
        final int thisBottom = this.getMeasuredHeight() - this.getPaddingBottom();

        int curLeft, curWidth, curTop, curHeight;

        curLeft = thisLeft;
        curTop = thisTop;
        curHeight = title.getMeasuredHeight();
        curWidth = title.getMeasuredWidth();

        title.layout(curLeft, curTop,curLeft + curWidth,curTop + curHeight);

        curTop += title.getMeasuredHeight();
        //Log.i("top1", "" + curTop);
        curHeight = img1.getMeasuredHeight();
        curWidth = img1.getMeasuredWidth();

        img1.layout(curLeft, curTop,curLeft + curWidth,curTop + curHeight);

        curLeft += img1.getMeasuredWidth() + 10;

        img2.layout(curLeft, curTop,curLeft + curWidth,curTop + curHeight);

        curLeft += img1.getMeasuredWidth() + 10;

        img3.layout(curLeft, curTop,curLeft + curWidth,curTop + curHeight);

        curLeft = thisLeft;
        curTop = thisTop + title.getMeasuredHeight() +  img1.getMeasuredHeight();
        //Log.i("top2", "" + curTop);
        curHeight = source.getMeasuredHeight();
        curWidth = source.getMeasuredWidth();

        source.layout(curLeft, curTop,curLeft + curWidth,curTop + curHeight);

        int curRight = thisRight;
        curHeight = comments.getMeasuredHeight();
        curWidth = comments.getMeasuredWidth();
        curLeft = curRight - curWidth;

        comments.layout(curLeft, curTop,curLeft + curWidth,curTop + curHeight);
    }
}
