package com.bjtu.recitewords.discover.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import com.bjtu.recitewords.R;
import com.bjtu.recitewords.discover.adapter.listener.OnItemChildClickListener;
import com.bjtu.recitewords.discover.adapter.listener.OnItemClickListener;
import com.bjtu.recitewords.discover.bean.ArticleBean;
import com.bumptech.glide.Glide;

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

        ArticleBean articleBean = articles.get(position);
        holder.mPosition = position;

        Glide.with(holder.mTitleImg.getContext())
                .load(articleBean.getImgUrl())
                .placeholder(android.R.color.darker_gray)
                .into(holder.mTitleImg);

        holder.mPrimaryTv.setText(articleBean.tPrimary);
        holder.mSecondaryTv.setText(articleBean.tSecondary);
        holder.mTagTv.setText(articleBean.tTag);
        holder.playAmountTv.setText(articleBean.tPlayAmount);

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

        public int mPosition;
        public ImageView mTitleImg;
        public TextView mPrimaryTv;
        public TextView mSecondaryTv;
        public TextView mTagTv;
        public TextView playAmountTv;

        ArticleHolder(View itemView) {
            super(itemView);
            mTitleImg = itemView.findViewById(R.id.title_image);
            mPrimaryTv = itemView.findViewById(R.id.title_primary);
            mSecondaryTv = itemView.findViewById(R.id.title_secondary);
            mTagTv = itemView.findViewById(R.id.tag_text);
            playAmountTv = itemView.findViewById(R.id.play_amount);

//            mPlayerContainer = itemView.findViewById(R.id.player_container);
//            mTitle = itemView.findViewById(R.id.tv_title);
//            mPrepareView = itemView.findViewById(R.id.prepare_view);
//            mThumb = mPrepareView.findViewById(R.id.thumb);
            if (mOnItemChildClickListener != null) {
                mTitleImg.setOnClickListener(this);
            }

            if (mOnItemClickListener != null) {
                itemView.setOnClickListener(this);
            }
            //通过tag将ViewHolder和itemView绑定
            itemView.setTag(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.title_image) {
                if (mOnItemChildClickListener != null) {
                    mOnItemChildClickListener.onItemChildClick(mPosition);
                }
            } else {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(mPosition);
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