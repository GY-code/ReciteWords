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

import com.bjtu.recitewords.discover.adapter.ArticleRecyclerViewAdapter;
import com.bjtu.recitewords.discover.adapter.listener.OnItemChildClickListener;
import com.bjtu.recitewords.discover.adapter.listener.OnItemClickListener;
import com.bjtu.recitewords.discover.bean.ArticleBean;
import com.bjtu.recitewords.discover.fragment.BaseFragment;
import com.bjtu.recitewords.discover.util.DataUtil;

/**
 * RecyclerView demo
 */
public class FragmentReadHome extends BaseFragment implements OnItemChildClickListener, OnItemClickListener {

    //article 实体list
    protected List<ArticleBean> mArticles = new ArrayList<>();
    //内容管理 adapter
    protected ArticleRecyclerViewAdapter mAdapter;
    protected RecyclerView mRecyclerView;
    protected LinearLayoutManager mLinearLayoutManager;

    //设置 fragment布局layout
    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_recycler_view;
    }

    @Override
    protected void initView() {
        super.initView();
        initArticleView();

        mRecyclerView = findViewById(R.id.rv);
        mLinearLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        mAdapter = new ArticleRecyclerViewAdapter(mArticles);
        mAdapter.setOnItemChildClickListener(this);
        mRecyclerView.setAdapter(mAdapter);


        mRecyclerView.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(@NonNull View view) {
            }

            @Override
            public void onChildViewDetachedFromWindow(@NonNull View view) {

            }
        });

    }

    protected void initArticleView() {

    }

    //数据初始化
    @Override
    protected void initData() {
        super.initData();
        List<ArticleBean> articleList = DataUtil.getArticleList();
        mArticles.addAll(articleList);
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

    @Override
    public void onItemClick(int position) { jumpToReadActivity(position); }

    /**
     * 在这里处理点击后的跳转
     * @param position 列表位置
     */
    protected void jumpToReadActivity (int position) {
        ArticleBean articleBean = mArticles.get(position);
        //
    }

}
