package com.bjtu.recitewords.discover.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.SparseArrayCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

import com.bjtu.recitewords.discover.fragment.list.FragmentReadHome;
import com.bjtu.recitewords.discover.fragment.list.RecyclerViewAutoPlayFragment;
import com.bjtu.recitewords.discover.fragment.list.RecyclerViewFragment;

/**
 * List主页适配器
 */
public class ListPagerAdapter extends FragmentStatePagerAdapter {

    private List<String> mTitles;
    public SparseArrayCompat<Fragment> mFragments = new SparseArrayCompat<>();

    public ListPagerAdapter(FragmentManager fm, List<String> titles) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mTitles = titles;
    }

    @Override
    @NonNull
    public Fragment getItem(int position) {
        Fragment fragment = mFragments.get(position);
        if (fragment == null) {
            switch (position) {
                default:
                case 0:
                    fragment = new RecyclerViewFragment();
                    break;
                case 1:
                    fragment = new RecyclerViewAutoPlayFragment();
                    break;
            }
            mFragments.put(position, fragment);
        }
        return fragment;
    }

    public static String makeFragmentName(int viewId, long id){
        return "android:switcher:" + viewId + ":" + id;
    }

    @Override
    public int getCount() {
        return mTitles.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }
}
