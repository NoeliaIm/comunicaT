package com.example.comunicat.notes;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.comunicat.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NoteViewHolder extends RecyclerView.ViewHolder {

    LinearLayout llNoteContainer;
    TextView tvItemNoteTitle, tvItemNoteBody;

    public NoteViewHolder(@NonNull View v) {
        super(v);

        llNoteContainer = v.findViewById(R.id.llNoteContainer);
        tvItemNoteTitle= v.findViewById(R.id.tvItemNoteTitle);
        tvItemNoteBody= v.findViewById(R.id.tvItemNoteBody);
    }
}
