package com.bjtu.recitewords.discover.fragment.list;

import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import com.bjtu.recitewords.R;

//??

import com.bjtu.recitewords.discover.adapter.VideoRecyclerViewAdapter;
import com.bjtu.recitewords.discover.adapter.listener.OnItemChildClickListener;
import com.bjtu.recitewords.discover.bean.ArticleBean;
import com.bjtu.recitewords.discover.fragment.BaseFragment;
import com.bjtu.recitewords.discover.util.DataUtil;

/**
 * RecyclerView demo
 */
public class FragmentReadHome extends BaseFragment implements OnItemChildClickListener {

    protected List<ArticleBean> mArticles = new ArrayList<>();
    protected VideoRecyclerViewAdapter mAdapter;
    protected RecyclerView mRecyclerView;
    protected LinearLayoutManager mLinearLayoutManager;

//    protected VideoView mVideoView;
//    protected StandardVideoController mController;
//    protected ErrorView mErrorView;
//    protected CompleteView mCompleteView;
//    protected TitleView mTitleView;

    /**
     * 当前播放的位置
     */
//    protected int mCurPos = -1;
    /**
     * 上次播放的位置，用于页面切回来之后恢复播放
     */
//    protected int mLastPos = mCurPos;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_recycler_view;
    }

    @Override
    protected void initView() {
        super.initView();

        initArticleView();
        //保存进度
//        mVideoView.setProgressManager(new ProgressManagerImpl());

        mRecyclerView = findViewById(R.id.rv);
        mLinearLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mAdapter = new VideoRecyclerViewAdapter(mArticles);
        mAdapter.setOnItemChildClickListener(this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(@NonNull View view) {

            }

            @Override
            public void onChildViewDetachedFromWindow(@NonNull View view) {
                FrameLayout playerContainer = view.findViewById(R.id.player_container);
                View v = playerContainer.getChildAt(0);
                if (v != null && v == mVideoView && !mVideoView.isFullScreen()) {
                    releaseVideoView();
                }
            }
        });

//        View view = findViewById(R.id.add);
//        view.setVisibility(View.VISIBLE);
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mAdapter.addData(DataUtil.getVideoList());
//            }
//        });
    }

    protected void initArticleView() {

    }

    @Override
    protected void initData() {
        super.initData();
        List<ArticleBean> videoList = DataUtil.getVideoList();
        mArticles.addAll(videoList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected boolean isLazyLoad() {
        return true;
    }

    /**
     * 在这里处理被点击
     */
    @Override
    public void onItemChildClick(int position) {
        jumpToReadActivity(position);
    }

    /**
     * 在这里处理点击后的跳转
     * @param position 列表位置
     */
    protected void jumpToReadActivity (int position) {
        ArticleBean articleBean = mArticles.get(position);
        //
    }

}
