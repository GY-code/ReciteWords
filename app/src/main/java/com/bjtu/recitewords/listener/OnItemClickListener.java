package com.bjtu.recitewords.listener;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.bjtu.recitewords.entity.ItemWordMeanChoice;

public interface OnItemClickListener {

    void onItemClick(RecyclerView parent, View view, int position, ItemWordMeanChoice itemWordMeanChoice);

}
