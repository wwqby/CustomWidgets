package com.widgets.wwq.customwidgets.BannerView;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;

import java.util.List;
import java.util.Objects;

public class BannerView extends ViewPager {

//    轮播间隔
    private int mDuration=5000;
//    区分自动轮播和手动滑动
    private boolean autoScroll=true;
//    定时轮播线程
    private android.os.Handler mHandler;
//    轮播任务
    private final Runnable task=new Runnable() {
        @Override
        public void run() {
            if (mHandler!=null){
                int setItem=getCurrentItem()+1;
                int count=Objects.requireNonNull(getAdapter()).getCount();
                if (setItem==count){
                    setItem=0;
                }
                if (autoScroll){
                    setCurrentItem(setItem);
                    mHandler.postDelayed(task,mDuration);
                }else {
                    mHandler.postDelayed(task,mDuration);
                }
            }
        }
    };

//    ________________________________

    public BannerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mHandler= new android.os.Handler();
    }

//    设置轮播间隔
    public void setDuration(int duration){
        this.mDuration=duration;
    }

//    在onPause()方法中调用，开启轮播
    public void onPause(){
        mHandler.removeCallbacks(task);
    }

//    在onResume()方法中调用，关闭定时
    public void onRestart(){
        mHandler= new android.os.Handler();
        mHandler.postDelayed(task,mDuration);
    }

//    在onDestroy()中调用，清空handler
    public void onDestroy(){
        mHandler.removeCallbacksAndMessages(null);
    }


//    ——————————————————————————————————————

//    初始化ViewPager
    public void initView(FragmentManager fm,List<BannerViewFragment> bannerViewList) {
        BannerFragmentAdapter adapter=new BannerFragmentAdapter(fm, bannerViewList);
        setAdapter(adapter);
        addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {
                switch (i){
                    case SCROLL_STATE_DRAGGING:
                        autoScroll=false;
                        break;
                    case SCROLL_STATE_SETTLING:
                        autoScroll=true;
                        break;
                }
            }
        });
    }

}
