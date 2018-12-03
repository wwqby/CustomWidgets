package com.widgets.wwq.customwidgets.BannerView;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.List;

public class BannerView extends ViewGroup {

    private ViewPager mViewPager;
    private List<FragmentBannerView> bannerViewList;



    public BannerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mViewPager=new ViewPager(context);
        LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
        addView(mViewPager,lp);

    }

    public void initView(FragmentManager fm,List<FragmentBannerView> bannerViewList) {
        FragmentViewPagerAdapter adapter=new FragmentViewPagerAdapter(fm, bannerViewList);
        mViewPager.setAdapter(adapter);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count =getChildCount();
        if (count>0){
            for (int i=0;i<count;i++){
                View child=getChildAt(i);
                if (child.getVisibility()!=GONE){
                    int width=getMeasuredWidth();
                    int height=getMeasuredHeight();
                    child.layout(getLeft(),getTop(),getLeft()+width,getTop()+height);
                }

            }
        }
    }


}
