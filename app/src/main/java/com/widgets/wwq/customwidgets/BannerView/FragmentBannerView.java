package com.widgets.wwq.customwidgets.BannerView;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.widgets.wwq.customwidgets.R;

public class FragmentBannerView extends Fragment {

    private static final String PARAM1="PARAM1";
    private static final String PARAM2="PARAM2";
    private static final String PARAM3="PARAM3";
    private static final String PARAM4="PARAM4";

    private String url;
    private String tag;
    private int page;
    private int pages;

    public FragmentBannerView() {
    }

    public FragmentBannerView newInstance(String url,String tag,int page,int pages){
        FragmentBannerView fragment=new FragmentBannerView();
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
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_banner_view_layout,null,false);
        ImageView ivImage=view.findViewById(R.id.iv_image);
        TextView tvTag=view.findViewById(R.id.tv_tag);
        TextView tvPages=view.findViewById(R.id.tv_pages);
        Glide.with(view).load(url).into(ivImage);
        tvTag.setText(tag);
        String pagePercent=String.valueOf(page)+"/"+String.valueOf(pages);
        tvPages.setText(pagePercent);
        return view;
    }
}
