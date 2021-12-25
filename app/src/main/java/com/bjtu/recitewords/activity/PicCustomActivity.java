package com.bjtu.recitewords.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bjtu.recitewords.R;
import com.bjtu.recitewords.database.Word;

import org.litepal.LitePal;

import uk.co.senab.photoview.PhotoView;

public class PicCustomActivity extends BaseActivity {

    private PhotoView imgBg;

    private TextView textName;

    public static final String TYPE_WORD_ID = "typeWordId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_custom);

        init();

        int currentWordId = getIntent().getIntExtra(TYPE_WORD_ID, 0);
        Word word = LitePal.where("wordId = ?", currentWordId + "").select("wordId", "word", "picCustom").find(Word.class).get(0);
        Glide.with(this).load(word.getPicCustom()).into(imgBg);
        textName.setText(word.getWord());

    }

    private void init() {
        imgBg = findViewById(R.id.img_pc);
        textName = findViewById(R.id.text_pc_word);
    }

}
