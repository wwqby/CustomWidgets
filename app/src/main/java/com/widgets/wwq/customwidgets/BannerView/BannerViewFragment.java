package com.widgets.wwq.customwidgets.BannerView;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.widgets.wwq.customwidgets.R;

public class BannerViewFragment extends Fragment {

    private static final String PARAM1="PARAM1";
    private static final String PARAM2="PARAM2";
    private static final String PARAM3="PARAM3";
    private static final String PARAM4="PARAM4";

    private static final String TAG = "BannerViewFragment";

    private String url;
    private String tag;
    private int page;
    private int pages;
    private ImageView ivImage;
    private TextView tvTag;
    private TextView tvPages;

    public BannerViewFragment() {
    }

    public static BannerViewFragment newInstance(String url, String tag, int page, int pages){
        BannerViewFragment fragment=new BannerViewFragment();
        Bundle bundle=new Bundle();
        bundle.putString(PARAM1,url);
        bundle.putString(PARAM2,tag);
        bundle.putInt(PARAM3,page);
        bundle.putInt(PARAM4,pages);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments()!=null){
            url=getArguments().getString(PARAM1);
            tag=getArguments().getString(PARAM2);
            page=getArguments().getInt(PARAM3);
            pages=getArguments().getInt(PARAM4);
            Log.i(TAG, "onCreate: url="+url);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.banner_fragment_layout,container,false);
        ivImage=view.findViewById(R.id.iv_image);
        tvTag=view.findViewById(R.id.tv_tag);
        tvPages=view.findViewById(R.id.tv_pages);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Glide.with(getContext()).load(url).into(ivImage);
        tvTag.setText(tag);
        String pagePercent=String.valueOf(page)+"/"+String.valueOf(pages);
        tvPages.setText(pagePercent);
    }
}
