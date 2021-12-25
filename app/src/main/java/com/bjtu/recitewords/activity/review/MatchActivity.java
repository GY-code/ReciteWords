package com.bjtu.recitewords.activity.review;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;

import com.bjtu.recitewords.R;
import com.bjtu.recitewords.activity.BaseActivity;
import com.bjtu.recitewords.adapter.MatchAdapter;
import com.bjtu.recitewords.database.Word;
import com.bjtu.recitewords.entity.ItemMatch;

import java.util.ArrayList;
import java.util.List;

public class MatchActivity extends BaseActivity {

    public static List<Word> wordList = new ArrayList<>();

    public static ArrayList<ItemMatch> matchList = new ArrayList<>();

    public static ArrayList<ItemMatch> allMatches = new ArrayList<>();

    private RecyclerView recyclerView;

    private static final String TAG = "MatchActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        init();

        windowExplode();

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        MatchAdapter matchAdapter = new MatchAdapter(matchList);
        recyclerView.setAdapter(matchAdapter);

    }

    private void init() {
        recyclerView = findViewById(R.id.recycler_mt);
    }

}
