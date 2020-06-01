package com.example.comunicat.notes;

import android.os.Bundle;
import android.widget.TextView;

import com.example.comunicat.MainActivity;
import com.example.comunicat.R;
import com.example.comunicat.utils.Constants;

public class DetailNoticeActiviy extends MainActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailnoticia_main);

        String noteTitle= getIntent().getStringExtra(Constants.EXTRA_NOTE_TITLE);
        String noteBody= getIntent().getStringExtra(Constants.EXTRA_NOTE_BODY);
        String noteDate= getIntent().getStringExtra(Constants.EXTRA_NOTE_DATE);

        TextView tvNoteTitle= findViewById(R.id.noteDetailTitle);
        TextView tvNoteDate = findViewById(R.id.noteDetailDate);
        TextView tvNoteBody= findViewById(R.id.noteDetailBody);

        tvNoteTitle.setText(noteTitle);
        tvNoteDate.setText(noteDate);
        tvNoteBody.setText(noteBody);
    }
}
