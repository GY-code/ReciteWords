package com.bjtu.recitewords.activity.load;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bjtu.recitewords.R;
import com.bjtu.recitewords.activity.BaseActivity;
import com.bjtu.recitewords.activity.LearnWordActivity;
import com.bjtu.recitewords.config.ConfigData;
import com.bjtu.recitewords.database.UserConfig;
import com.bjtu.recitewords.util.TimeController;
import com.bjtu.recitewords.util.WordController;

import org.litepal.LitePal;

import java.util.List;

public class LoadWordActivity extends BaseActivity {

    private ImageView imgLoading;

    private ProgressBar progressBar;

    int progressRate = 0;

    private Runnable runnable;

    private Handler mHandler;

    private Thread thread;

    private static final String TAG = "WaitActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_word);

        windowExplode();

        init();

        Glide.with(this).load(R.drawable.pic_load).into(imgLoading);

        // 准备数据
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                List<UserConfig> userConfigs = LitePal.where("userId = ?", ConfigData.getSinaNumLogged() + "").find(UserConfig.class);
                WordController.generateDailyLearnWords(userConfigs.get(0).getLastStartTime());
                Log.d(TAG, "run: " + userConfigs.get(0).getLastStartTime());
                WordController.generateDailyReviewWords();
                WordController.wordReviewNum = WordController.needReviewWords.size();
                UserConfig userConfig = new UserConfig();
                userConfig.setLastStartTime(TimeController.getCurrentTimeStamp());
                TimeController.todayDate = TimeController.getCurrentDateStamp();
                LearnWordActivity.lastWordMean = "";
                LearnWordActivity.lastWord = "";
                userConfig.updateAll("userId = ?", ConfigData.getSinaNumLogged() + "");
            }
        });
        thread.start();

        try {
            thread.join();
        } catch (Exception e) {

        }

        mHandler = new Handler();
        runnable = new Runnable() {

            @Override
            public void run() {
                //每隔20微秒循环执行run方法
                mHandler.postDelayed(this, 10);
                progressBar.setProgress(++progressRate);
                if (progressRate == 100) {
                    stopTime();
                    Intent mIntent = new Intent(LoadWordActivity.this, LearnWordActivity.class);
                    startActivity(mIntent, ActivityOptions.makeSceneTransitionAnimation(LoadWordActivity.this).toBundle());
                }
            }

        };
        mHandler.postDelayed(runnable, 120);

    }

    private void init() {
        imgLoading = findViewById(R.id.img_loading);
        progressBar = findViewById(R.id.progress_wait);
    }

    // 停止计时器
    private void stopTime() {
        mHandler.removeCallbacks(runnable);
    }

    @Override
    public void onBackPressed() {
        // nothing
    }

}
