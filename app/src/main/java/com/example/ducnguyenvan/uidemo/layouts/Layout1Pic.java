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

public class Layout1Pic extends FrameLayout {

    private ImageView img;
    private TextView title;
    private TextView source;
    private TextView comments;

    public Layout1Pic(@NonNull Context context) {
        super(context);
    }

    public Layout1Pic(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Layout1Pic(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public Layout1Pic(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void measureChildWithMargins(View child, int parentWidthMeasureSpec, int widthUsed, int parentHeightMeasureSpec, int heightUsed) {
        MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
        int childWidthMeasureSpec = getChildMeasureSpec(parentWidthMeasureSpec, widthUsed + lp.leftMargin + lp.rightMargin,lp.width);
        int childHeightMeasureSpec = getChildMeasureSpec(parentHeightMeasureSpec, heightUsed + lp.topMargin + lp.bottomMargin, lp.height);

        child.measure(childWidthMeasureSpec,childHeightMeasureSpec);
        /*Log.i("measure", "h: " + child.getMeasuredHeight() + ", " +  MeasureSpec.toString(childHeightMeasureSpec)
        + "; w: " + child.getMeasuredWidth() + ", " + MeasureSpec.toString(childWidthMeasureSpec));*/
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        //Log.i("onLayout", "...");
        final int thisLeft = this.getPaddingLeft();
        final int thisTop = this.getPaddingTop();
        final int thisRight = this.getMeasuredWidth() - this.getPaddingRight();
        final int thisBottom = this.getMeasuredHeight() - this.getPaddingBottom();
        final int thisWidth = this.getMeasuredWidth();
        final int thisHeight = this.getMeasuredHeight();

        int curLeft, curWidth, curTop, curHeight;
        curLeft = thisLeft;
        curTop = thisTop;
        curWidth = img.getMeasuredWidth();
        curHeight = img.getMeasuredHeight();

        img.layout(curLeft, curTop,curLeft + curWidth, curTop + curHeight);

        curLeft += curWidth;
        curWidth = title.getMeasuredWidth();
        curHeight = title.getMeasuredHeight();

        title.layout(curLeft, curTop,curLeft + curWidth, curTop + curHeight);

        curHeight = source.getMeasuredHeight();
        int curBottom = thisBottom;
        curTop = curBottom - curHeight;
        curWidth = source.getMeasuredWidth();


        source.layout(curLeft, curTop,curLeft + curWidth, curTop + curHeight);

        int curRight = thisRight;
        curHeight = comments.getMeasuredHeight();
        curWidth = comments.getMeasuredWidth();
        curLeft = curRight - curWidth;

        comments.layout(curLeft, curTop,curLeft + curWidth, curTop + curHeight);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //Log.i("onMeasure","...");
        img = (ImageView) findViewById(R.id.img_view);
        title = (TextView) findViewById(R.id.txtTitle);
        source = (TextView) findViewById(R.id.txtSource);
        comments = (TextView)findViewById(R.id.txtComments);

        int widthConstraints = getPaddingLeft() + getPaddingRight();
        int heightConstraints = getPaddingTop() + getPaddingBottom();
        int width = 0;
        int height = 0;

        //measure image
        measureChildWithMargins(img,widthMeasureSpec,widthConstraints,heightMeasureSpec,heightConstraints);
        //Log.i("measure img", "width: " + img.getMeasuredWidth() + ", height: " + img.getMeasuredHeight());

        //update constraints
        widthConstraints += img.getMeasuredWidth();
        width += img.getMeasuredWidth();
        height = Math.max(height, img.getMeasuredHeight());

        //measure title
        measureChildWithMargins(title,widthMeasureSpec,widthConstraints,heightMeasureSpec,heightConstraints);
        //Log.i("measure title", "width: " + title.getMeasuredWidth() + ", height: " + title.getMeasuredHeight());
        heightConstraints += title.getMeasuredHeight();
        width += title.getMeasuredWidth();
        height = Math.max(height, title.getMeasuredHeight());


        //prepare horizontal measurespec
        int horizontalWidthMeasureSpec = MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(widthMeasureSpec) - img.getMeasuredWidth(),MeasureSpec.getMode(widthMeasureSpec));
        int horizontalHeightMeasureSpec = MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(heightMeasureSpec) - heightConstraints,MeasureSpec.getMode(heightMeasureSpec));


        //measure source
        measureChildWithMargins(source,horizontalWidthMeasureSpec,0,horizontalHeightMeasureSpec,0);
        //Log.i("measure source", "width: " + source.getMeasuredWidth() + ", height: " + source.getMeasuredHeight());

        //measure comment
        measureChildWithMargins(comments,horizontalWidthMeasureSpec,source.getMeasuredWidth(),horizontalHeightMeasureSpec,0);
        //Log.i("measure comment", "width: " + comments.getMeasuredWidth() + ", height: " + comments.getMeasuredHeight());

        //update size
        height = Math.max(height,title.getMeasuredHeight() + source.getMeasuredHeight());

        //set dimension for viewgroup
        setMeasuredDimension(resolveSize(width,widthMeasureSpec),resolveSize(height,heightMeasureSpec));

    }


}
