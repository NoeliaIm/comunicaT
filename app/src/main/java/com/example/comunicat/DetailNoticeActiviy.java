package com.example.comunicat;

import android.os.Bundle;
import android.widget.TextView;

import com.example.comunicat.utils.Constants;

public class DetailNoticeActiviy extends MainActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailnoticia_main);
        String note= getIntent().getStringExtra(Constants.EXTRA_NOTE_TITLE);

        TextView tvNote= findViewById(R.id.noteDetail);
        tvNote.setText(note);
    }
}
