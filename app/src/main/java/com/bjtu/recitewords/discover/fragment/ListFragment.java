package com.bjtu.recitewords.discover.fragment;

import androidx.viewpager.widget.ViewPager;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import com.bjtu.recitewords.R;
import com.bjtu.recitewords.discover.adapter.ListPagerAdapter;
import com.bjtu.recitewords.discover.util.Tag;

public class ListFragment extends BaseFragment {

    public ListPagerAdapter listPagerAdapter;

    private TabLayout tabLayout;

    private CommonTabLayout cTabLayout;

    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private String[] mTitles = {"悦读","悦看"};

    public ViewPager viewPager;

    public ListPagerAdapter getListPagerAdapter() {
        return listPagerAdapter;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_list;
    }

    @Override
    protected void initView() {
        super.initView();

        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i]));
        }
        viewPager = findViewById(R.id.view_pager);

        List<String> titles = new ArrayList<>();
        titles.add("有声读物");
        titles.add("看视频");

        listPagerAdapter = new ListPagerAdapter(getChildFragmentManager(), titles);

        viewPager.setAdapter(listPagerAdapter);

//        tabLayout = findViewById(R.id.tab_layout);
//        tabLayout.setupWithViewPager(viewPager);

        cTabLayout = findViewById(R.id.tl);
        cTabLayout.setTabData(mTabEntities);
        cTabLayout.getTitleView(0).setTextSize(25f);
        cTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPager.setCurrentItem(position);
                cTabLayout.getTitleView(position).setTextSize(25f);
                cTabLayout.getTitleView((position+1)%2).setTextSize(19f);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                cTabLayout.setCurrentTab(position);
                cTabLayout.getTitleView(position).setTextSize(24f);
                cTabLayout.getTitleView((position+1)%2).setTextSize(19f);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
        getVideoViewManager().releaseByTag(Tag.LIST);
        getVideoViewManager().releaseByTag(Tag.SEAMLESS);
    }
}
