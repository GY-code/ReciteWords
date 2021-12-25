package com.bjtu.recitewords.activity.load;

import androidx.annotation.NonNull;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bjtu.recitewords.R;
import com.bjtu.recitewords.activity.BaseActivity;
import com.bjtu.recitewords.activity.MainActivity;
import com.bjtu.recitewords.activity.review.GameActivity;
import com.bjtu.recitewords.database.Interpretation;
import com.bjtu.recitewords.database.Word;
import com.bjtu.recitewords.entity.GameWord;
import com.bjtu.recitewords.util.ActivityCollector;
import com.bjtu.recitewords.util.CustomVideoView;
import com.bjtu.recitewords.util.MediaHelper;
import com.bjtu.recitewords.util.NumberController;

import org.litepal.LitePal;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LoadGameActivity extends BaseActivity {

    private ProgressBar progressBar;

    private ImageView imgPlay;

    private CustomVideoView videoView;

    int num = 50;

    private final int FINISH = 1;

    private static final String TAG = "LoadGameActivity";

    private boolean isDone = false;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case FINISH:
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imgPlay.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.GONE);
                            isDone = true;
                        }
                    }, 2000);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_game);

        Log.d(TAG, "onCreate: ");

        noNight();


        init();

        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video));
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                videoView.start();
            }
        });
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                videoView.start();
            }
        });


        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Word> words = LitePal.select("wordId", "word").find(Word.class);
                Collections.shuffle(words);
                GameActivity.allWord = words;
                int[] randomIndex = NumberController.getRandomNumberList(0, GameActivity.allWord.size() - 1, num);
                Log.d(TAG, "content" + Arrays.toString(randomIndex));
                for (int i = 0; i < randomIndex.length; ++i) {
                    Interpretation interpretation = LitePal.where("wordId = ?", GameActivity.allWord.get(randomIndex[i]).getWordId() + "").find(Interpretation.class).get(0);
                    GameActivity.gameWord.add(new GameWord(
                            GameActivity.allWord.get(randomIndex[i]).getWordId(),
                            GameActivity.allWord.get(randomIndex[i]).getWord(),
                            interpretation.getWordType() + ". " + interpretation.getCHSMeaning()));
                }
                // 打乱顺序
                Collections.shuffle(GameActivity.allWord);
                Message message = new Message();
                message.what = FINISH;
                handler.sendMessage(message);
            }
        }).start();

        imgPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaHelper.releasePlayer();
                ActivityCollector.startOtherActivity(LoadGameActivity.this, GameActivity.class);
                finish();
            }
        });

    }

    private void init() {
        progressBar = findViewById(R.id.progress_ldg);
        progressBar.setVisibility(View.VISIBLE);
        videoView = findViewById(R.id.video);
        imgPlay = findViewById(R.id.img_play);
        imgPlay.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        MainActivity.lastFragment = 1;
        MainActivity.needRefresh = false;
        super.onBackPressed();
        MediaHelper.releasePlayer();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (videoView != null)
            videoView.suspend();
    }

    @Override
    protected void onPause() {
        super.onPause();
        videoView.suspend();
    }

    @Override
    protected void onStart() {
        super.onStart();
        videoView.resume();
        if (isDone) {
            imgPlay.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
        } else {
            imgPlay.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
        }
    }

}
