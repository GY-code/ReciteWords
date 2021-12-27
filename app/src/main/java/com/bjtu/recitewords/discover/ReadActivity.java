package com.bjtu.recitewords.discover;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bjtu.recitewords.R;
import com.bjtu.recitewords.activity.BaseActivity;

import java.io.IOException;
import java.io.InputStream;

import com.bjtu.recitewords.discover.lrc.LrcView;

public class ReadActivity extends BaseActivity {

    private LrcView lrcView;
    private SeekBar seekBar;

    private ImageView btnMenu;
    private ImageView btnLast;
    private ImageView btnPlayPause;
    private ImageView btnNext;
    private ImageView btnShrink;

    private MediaPlayer mediaPlayer = new MediaPlayer();
    private Handler handler = new Handler();

    private TextView tv_start;
    private TextView tv_end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        lrcView = findViewById(R.id.lrc_view);
        seekBar = findViewById(R.id.progress_bar);

        btnPlayPause = findViewById(R.id.bt_play_pause);

        btnMenu = findViewById(R.id.bt_menu);
        btnLast = findViewById(R.id.bt_last);
        btnNext = findViewById(R.id.bt_next);
        btnShrink = findViewById(R.id.bt_shrink);

        tv_start = findViewById(R.id.tv_start);
        tv_end = findViewById(R.id.tv_end);

        try {
            mediaPlayer.reset();
            AssetFileDescriptor fileDescriptor = getAssets().openFd("article.mp3");
            mediaPlayer.setDataSource(fileDescriptor.getFileDescriptor(), fileDescriptor.getStartOffset(), fileDescriptor.getLength());
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(mp -> {
                seekBar.setMax(mediaPlayer.getDuration());
                seekBar.setProgress(0);
            });
            mediaPlayer.setOnCompletionListener(mp -> {
                lrcView.updateTime(0);

                seekBar.setProgress(0);
            });

            int duration = mediaPlayer.getDuration() / 1000;
            int position = mediaPlayer.getCurrentPosition();
            tv_start.setText(calculateTime(position / 1000));
            tv_end.setText(calculateTime(duration));

        } catch (IOException e) {
            e.printStackTrace();
        }

        // 加载歌词文本
        String mainLrcText = getLrcText("article_en.lrc");
        String secondLrcText = getLrcText("article_cn.lrc");
        lrcView.loadLrc(mainLrcText, secondLrcText);

        // 加载歌词文件
        // File mainLrcFile = new File("/sdcard/Download/article_cn.lrc");
        // File secondLrcFile = new File("/sdcard/Download/article_en.lrc");
        // lrcView.loadLrc(mainLrcFile, secondLrcFile);

        // 加载在线歌词
        // String url = "http://pz6twp8s0.bkt.clouddn.com/%E6%AD%8C%E8%AF%8D.txt";
        // lrcView.loadLrcByUrl(url, "gb2312");

//        lrcView.setNormalColor(R.color.colorLightBlueN);
//        lrcView.setCurrentColor(R.color.line_color);
        lrcView.setTimelineTextColor(R.color.colorGrey);

        lrcView.setDraggable(true, (view, time) -> {
            mediaPlayer.seekTo((int) time);
            if (!mediaPlayer.isPlaying()) {
                mediaPlayer.start();
                handler.post(runnable);
            }
            return true;
        });

        lrcView.setOnTapListener((view, x, y) -> {
//            Toast.makeText(this, "点击歌词", Toast.LENGTH_SHORT).show();
        });

        btnPlayPause.setOnClickListener(v -> {
            if (!mediaPlayer.isPlaying()) {
                btnPlayPause.setImageResource(R.drawable.ic_mpause);
                mediaPlayer.start();
                handler.post(runnable);
            } else {
                btnPlayPause.setImageResource(R.drawable.ic_mplay);
                mediaPlayer.pause();
                handler.removeCallbacks(runnable);
            }
        });

        btnMenu.setOnClickListener(v -> {

        });

        btnLast.setOnClickListener(v -> {

        });

        btnNext.setOnClickListener(v -> {

        });

        btnShrink.setOnClickListener(v -> {

        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
                lrcView.updateTime(seekBar.getProgress());
            }
        });
    }

    private String getLrcText(String fileName) {
        String lrcText = null;
        try {
            InputStream is = getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            lrcText = new String(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lrcText;
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (mediaPlayer.isPlaying()) {
                long time = mediaPlayer.getCurrentPosition();
                lrcView.updateTime(time);
                seekBar.setProgress((int) time);
            }

            handler.postDelayed(this, 300);
        }
    };

    //计算播放时间
    private String calculateTime(int time) {
        int minute;
        int second;
        if (time >= 60) {
            minute = time / 60;
            second = time % 60;
            //分钟在0~9
            if (minute < 10) {
                //判断秒
                if (second < 10) {
                    return "0" + minute + ":" + "0" + second;
                } else {
                    return "0" + minute + ":" + second;
                }
            } else {
                //分钟大于10再判断秒
                if (second < 10) {
                    return minute + ":" + "0" + second;
                } else {
                    return minute + ":" + second;
                }
            }
        } else {
            second = time;
            if (second >= 0 && second < 10) {
                return "00:" + "0" + second;
            } else {
                return "00:" + second;
            }
        }
    }

    @Override
    protected void onDestroy() {
        handler.removeCallbacks(runnable);
        mediaPlayer.reset();
        mediaPlayer.release();
        mediaPlayer = null;
        super.onDestroy();
    }
}



