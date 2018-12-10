package com.widgets.wwq.customwidgets;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.widgets.wwq.customwidgets.BannerView.BannerView;
import com.widgets.wwq.customwidgets.BannerView.BannerViewFragment;
import com.widgets.wwq.customwidgets.TextViewList.TextViewList;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private BannerView bvBanner;
    private TextViewList tvList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        initBanner();
        initTvList();
    }

    private void initTvList() {
        tvList=findViewById(R.id.tvl_list);
        List<String> strings=new ArrayList<>();
        strings.add("测试123456789123456789123456789测试123456789123456789123456789;");
        strings.add("测试1;");
        strings.add("测试12;");
        strings.add("测试123;");
        strings.add("测试1234;");
        strings.add("测试12345;");
        strings.add("测试123456;");
        strings.add("测试1234567;");
        strings.add("测试12345678;");
        strings.add("测试123456789;");
        strings.add("测试123456789123456789123456789测试123456789123456789123456789;");
        strings.add("测试123456789123456789123456789;");
        tvList.loadTextList(strings,this);


    }


    @Override
    protected void onResume() {
        super.onResume();
//        bvBanner.onRestart();
    }

    @Override
    protected void onPause() {
        super.onPause();
//        bvBanner.onPause();
    }

    @Override
    protected void onDestroy() {
//        bvBanner.onDestroy();
        super.onDestroy();

    }


//    private void initBanner() {
//        List<String> lists=new ArrayList<>();
//        lists.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1544490566&di=d67c72cd38d2d342440b9b03bd3ba611&imgtype=jpg&er=1&src=http%3A%2F%2Fgss0.baidu.com%2F-vo3dSag_xI4khGko9WTAnF6hhy%2Fzhidao%2Fpic%2Fitem%2F8435e5dde71190ef3bee9ce4cc1b9d16fdfa60f7.jpg");
//        lists.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1543895984834&di=324c2d8efb14c2b4ca40d9c3278d230b&imgtype=0&src=http%3A%2F%2Fpic.qiantucdn.com%2F58pic%2F22%2F06%2F55%2F57b2d98e109c6_1024.jpg");
//        lists.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1543895984833&di=3a0abc5aa78bd244cdce55e73fb23cde&imgtype=0&src=http%3A%2F%2Fimg4.duitang.com%2Fuploads%2Fitem%2F201401%2F10%2F20140110211351_HJhH8.thumb.700_0.jpeg");
//        List<BannerViewFragment> bannerLists=new ArrayList<>();
//        bannerLists.add(BannerViewFragment.newInstance(lists.get(0),"实景图",1,3));
//        bannerLists.add(BannerViewFragment.newInstance(lists.get(1),"测试图",2,3));
//        bannerLists.add(BannerViewFragment.newInstance(lists.get(2),"户型图",3,3));
//        bvBanner=findViewById(R.id.bv_banner);
//        bvBanner.initView(getSupportFragmentManager(),bannerLists);
//
//    }
}
