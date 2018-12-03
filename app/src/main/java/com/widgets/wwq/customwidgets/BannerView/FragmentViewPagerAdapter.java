package com.widgets.wwq.customwidgets.BannerView;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class FragmentViewPagerAdapter extends FragmentPagerAdapter {

    private List<FragmentBannerView> bannerViewList;

    public FragmentViewPagerAdapter(FragmentManager fm, List<FragmentBannerView> bannerViewList) {
        super(fm);
        this.bannerViewList = bannerViewList;
    }

    @Override
    public Fragment getItem(int i) {
        return bannerViewList.get(i);
    }

    @Override
    public int getCount() {
        return bannerViewList.size();
    }
}
