package com.widgets.wwq.customwidgets.TextViewList;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.widgets.wwq.customwidgets.R;

import java.util.ArrayList;
import java.util.List;

/**
 * /*@Description
 * /*created by wwq on 2018/12/10 0010
 * /*@company zhongyiqiankun
 */
public class TextViewList extends ViewGroup {
    private static final String TAG = "TextViewList";

    private int mWidth;
    private int mHeight;
    private int randomIndex;

    //    innerPadding 左右边距  linePadding 上下文边距
    private int innerPadding ;
    private int linePadding;


    //    colorList  字体颜色集合  colorMode  颜色选择模式（0 循环；1随机）backgroundList 为了配合字体颜色，可以更换背景
    private List<Integer> colorList;
    private int colorMode ;
    private List<Integer> backgroundList;

    public TextViewList(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta=context.obtainStyledAttributes(attrs,R.styleable.TextViewList);
        innerPadding=(int) ta.getDimension(R.styleable.TextViewList_inner_padding,10);
        linePadding=(int) ta.getDimension(R.styleable.TextViewList_line_padding,10);
        colorMode=ta.getInt(R.styleable.TextViewList_color_mode,0);
        ta.recycle();

        colorList = new ArrayList<>();
        colorList.add(Color.parseColor("#FABC73"));
        colorList.add(Color.parseColor("#8BC34A"));
        colorList.add(Color.parseColor("#2C8EE8"));
        backgroundList = new ArrayList<>();
        backgroundList.add(R.drawable.shape_rounded_12_rectangle_orange);
        backgroundList.add(R.drawable.shape_rounded_12_rectangle_green);
        backgroundList.add(R.drawable.shape_rounded_12_rectangle_blue);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
        int sumWidth = innerPadding;
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != GONE) {
                measureChild(getChildAt(i), widthMeasureSpec, heightMeasureSpec);
                int width = child.getMeasuredWidth();
                int height = child.getMeasuredHeight();
                if (i == 0) {
                    mHeight = child.getMeasuredHeight() + linePadding * 2;
                }
//                如果文字宽度超过控件宽度，就对文字重新测量，新的宽度为控件宽度减去两边innerPadding;
                if (width==mWidth){
                    widthMeasureSpec= MeasureSpec.makeMeasureSpec(width-innerPadding*2,MeasureSpec.AT_MOST);
                    measureChild(getChildAt(i), widthMeasureSpec, heightMeasureSpec);
                    width = child.getMeasuredWidth();
                    height = child.getMeasuredHeight();
                }
                if (width + sumWidth > mWidth) {
                    mHeight += height + linePadding;
                    sumWidth = innerPadding;
                } else {
                    sumWidth += width + innerPadding;
                }
            }
        }
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        switch (heightMode) {
            case MeasureSpec.EXACTLY:
                mHeight = MeasureSpec.getSize(heightMeasureSpec);
                break;
            case MeasureSpec.AT_MOST:
                break;
            case MeasureSpec.UNSPECIFIED:
                break;
        }
        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
//        左边界
        int sumLeft = innerPadding;
//        顶部边界
        int sumTop = linePadding;
//        上一个子View的高度
        int lastHeight=0;
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != GONE) {
                int width = child.getMeasuredWidth();
                int height = child.getMeasuredHeight();
                if (sumLeft + width + innerPadding > mWidth) {
                    sumLeft = innerPadding;
                    sumTop += lastHeight + linePadding;
                    child.layout(sumLeft, sumTop, sumLeft + width, sumTop + height);
                    sumLeft += width + innerPadding;
                } else {
                    child.layout(sumLeft, sumTop, sumLeft + width, sumTop + height);
                    sumLeft += width + innerPadding;
                }
                lastHeight=height;
            }
        }
    }


//    private ___________

    //根据colorMode 返回背景
    private Drawable getBackgroundDrawable(Context context, int index) {
        Drawable drawable=ContextCompat.getDrawable(context, backgroundList.get(0));
        switch (colorMode) {
            case 0:
                int position = index % backgroundList.size();
                drawable = ContextCompat.getDrawable(context, backgroundList.get(position));
                break;
            case 1:
                drawable = ContextCompat.getDrawable(context, backgroundList.get(randomIndex));
                break;
        }
        return drawable;
    }

    private int getColor(int index) {
        int color=colorList.get(0);
        switch (colorMode){
            case 0:
                int position = index % backgroundList.size();
                color=colorList.get(position);
                break;
            case 1:
                int position2 = ((int)(Math.random()*backgroundList.size()))%backgroundList.size();
                randomIndex =position2;
                color=colorList.get(position2);
                break;
        }
        return color;
    }


//    public ___________

    public void loadTextList(List<String> strings, Context context) {
        MarginLayoutParams lp = new MarginLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lp.setMarginStart(innerPadding);
        lp.setMarginEnd(innerPadding);
        for (int i = 0; i < strings.size(); i++) {
            TextView tv = new TextView(context);
            tv.setText(strings.get(i));
            tv.setTextColor(getColor(i));
            tv.setBackground(getBackgroundDrawable(context, i));
            addView(tv, lp);
        }
    }

    public void setColorList(List<Integer> colorList){
        this.colorList=colorList;
    }

    public void setBackgroundList(List<Integer> drawables){
        this.backgroundList=drawables;
    }

}
