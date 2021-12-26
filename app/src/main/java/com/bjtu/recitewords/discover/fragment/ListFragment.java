package com.bjtu.recitewords.discover.fragment;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import com.bjtu.recitewords.R;
import com.bjtu.recitewords.discover.adapter.ListPagerAdapter;
import com.bjtu.recitewords.discover.util.Tag;

public class ListFragment extends BaseFragment {

    public ListPagerAdapter listPagerAdapter;

    private TabLayout tabLayout;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_list;
    }

    @Override
    protected void initView() {
        super.initView();

        ViewPager viewPager = findViewById(R.id.view_pager);

        List<String> titles = new ArrayList<>();
        titles.add("有声读物");
        titles.add("看视频");

        listPagerAdapter = new ListPagerAdapter(getChildFragmentManager(), titles);

        viewPager.setAdapter(listPagerAdapter);

        tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        getVideoViewManager().releaseByTag(Tag.LIST);
        getVideoViewManager().releaseByTag(Tag.SEAMLESS);
    }
}
