package com.bjtu.recitewords.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.bjtu.recitewords.discover.adapter.ListPagerAdapter;
import com.bjtu.recitewords.discover.fragment.list.RecyclerViewAutoPlayFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.bjtu.recitewords.R;
import com.bjtu.recitewords.activity.index.FragmentMe;
import com.bjtu.recitewords.activity.index.FragmentReview;
import com.bjtu.recitewords.activity.index.FragmentWord;
import com.bjtu.recitewords.discover.fragment.ListFragment;


import com.bjtu.recitewords.util.ActivityCollector;

import xyz.doikki.videoplayer.player.VideoViewManager;

public class MainActivity extends BaseActivity {

    private Fragment fragWord, fragReview, fragMe;

    private Fragment fragList;

    public Fragment[] fragments;

    public Fragment yueKanFragment;

    //用于记录上个选择的Fragment
    public static int lastFragment = 0;

    private BottomNavigationView bottomNavigationView;

    private LinearLayout linearLayout;

    private static final String TAG = "MainActivity";

    public static boolean needRefresh = true;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: ");

        init();

        if (needRefresh) {

            TranslateAnimation animation = new TranslateAnimation(
                    Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f,
                    Animation.RELATIVE_TO_PARENT, 1.0f, Animation.RELATIVE_TO_PARENT, 0.0f
            );
            animation.setDuration(2000);
            //bottomNavigationView.startAnimation(animation);
        }

        initFragment();

    }

    // 初始化控件
    private void init() {
        bottomNavigationView = findViewById(R.id.bottom_nav);
        linearLayout = findViewById(R.id.linear_frag_container);
    }

    // 初始化initFragment
    private void initFragment() {
        fragWord = new FragmentWord();
        fragReview = new FragmentReview();
        fragList = new ListFragment();
        fragMe = new FragmentMe();
        fragments = new Fragment[]{fragWord, fragReview, fragList, fragMe};
        switch (lastFragment) {
            case 0:
                getSupportFragmentManager().beginTransaction().replace(R.id.linear_frag_container, fragWord).show(fragWord).commit();
                break;
            case 1:
                getSupportFragmentManager().beginTransaction().replace(R.id.linear_frag_container, fragReview).show(fragReview).commit();
                break;
            case 2:
                getSupportFragmentManager().beginTransaction().replace(R.id.linear_frag_container, fragList).show(fragList).commit();
                break;
            case 3:
                getSupportFragmentManager().beginTransaction().replace(R.id.linear_frag_container, fragMe).show(fragMe).commit();
                break;
        }
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.bnavigation_word:
                        VideoViewManager.instance().releaseByTag("list");
                        if (lastFragment != 0) {
                            switchFragment(lastFragment, 0);
                            lastFragment = 0;
                        }
                        return true;
                    case R.id.bnavigation_review:
                        VideoViewManager.instance().releaseByTag("list");
                        if (lastFragment != 1) {
                            switchFragment(lastFragment, 1);
                            lastFragment = 1;
                        }
                        return true;
                    case R.id.bnavigation_news:
                        if (lastFragment != 2) {
                            switchFragment(lastFragment, 2);
                            lastFragment = 2;
                        }
                        return true;
                    case R.id.bnavigation_me:
                        VideoViewManager.instance().releaseByTag("list");
                        if (lastFragment != 3) {
                            switchFragment(lastFragment, 3);
                            lastFragment = 3;
                        }
                        return true;
                }
                return true;
            }
        });
    }

    private void switchFragment(int lastIndex, int index) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //隐藏上个Fragment
        transaction.hide(fragments[lastIndex]);
        if (fragments[index].isAdded() == false) {
            transaction.add(R.id.linear_frag_container, fragments[index]);
        }
        transaction.show(fragments[index]).commitAllowingStateLoss();
    }

    @Override
    public void onBackPressed() {
        if(yueKanFragment == null){
            yueKanFragment = ((ListFragment) fragList).getListPagerAdapter().mFragments.get(1);
        }

        if(yueKanFragment != null && yueKanFragment instanceof RecyclerViewAutoPlayFragment
        && !yueKanFragment.isHidden() && ((RecyclerViewAutoPlayFragment)yueKanFragment).onBackPressed()){
            System.out.println("blocked!");
            return;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("提示")
                .setMessage("今天不再背单词了吗？")
                .setPositiveButton("退出", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        needRefresh = true;
                        ActivityCollector.finishAll();
                    }
                })
                .setNegativeButton("再看看", null)
                .show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
