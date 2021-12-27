package com.bjtu.recitewords.discover.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import com.bjtu.recitewords.R;
import com.bjtu.recitewords.discover.adapter.listener.OnItemChildClickListener;
import com.bjtu.recitewords.discover.adapter.listener.OnItemClickListener;
import com.bjtu.recitewords.discover.bean.ArticleBean;

public class ArticleRecyclerViewAdapter extends RecyclerView.Adapter<ArticleRecyclerViewAdapter.ArticleHolder> {

    private List<ArticleBean> articles;

    private OnItemChildClickListener mOnItemChildClickListener;

    private OnItemClickListener mOnItemClickListener;

    public ArticleRecyclerViewAdapter(List<ArticleBean> articles) {
        this.articles = articles;
    }

    //设置单个item布局文件
    @Override
    @NonNull
    public ArticleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_article, parent, false);
        return new ArticleHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleHolder holder, int position) {

//        ArticleBean articleBean = articles.get(position);
//
//        Glide.with(holder.mThumb.getContext())
//                .load(articleBean.getThumb())
//                .placeholder(android.R.color.darker_gray)
//                .into(holder.mThumb);
//        holder.mTitle.setText(articleBean.getTitle());
//
//        holder.mPosition = position;
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public void addData(List<ArticleBean> articleList) {
        int size = articles.size();
        articles.addAll(articleList);
        //使用此方法添加数据，使用notifyDataSetChanged会导致正在播放的视频中断
        notifyItemRangeChanged(size, articles.size());
    }

    public class ArticleHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

//        public int mPosition;
//        public FrameLayout mPlayerContainer;
//        public TextView mTitle;
//        public ImageView mThumb;
//        public PrepareView mPrepareView;

        ArticleHolder(View itemView) {
            super(itemView);
//            mPlayerContainer = itemView.findViewById(R.id.player_container);
//            mTitle = itemView.findViewById(R.id.tv_title);
//            mPrepareView = itemView.findViewById(R.id.prepare_view);
//            mThumb = mPrepareView.findViewById(R.id.thumb);
//            if (mOnItemChildClickListener != null) {
//                mPlayerContainer.setOnClickListener(this);
//            }
            if (mOnItemClickListener != null) {
                itemView.setOnClickListener(this);
            }
            //通过tag将ViewHolder和itemView绑定
            itemView.setTag(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.player_container) {
                if (mOnItemChildClickListener != null) {
//                    mOnItemChildClickListener.onItemChildClick(mPosition);
                }
            } else {
                if (mOnItemClickListener != null) {
//                    mOnItemClickListener.onItemClick(mPosition);
                }
            }

        }
    }


    public void setOnItemChildClickListener(OnItemChildClickListener onItemChildClickListener) {
        mOnItemChildClickListener = onItemChildClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }
}