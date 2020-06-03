package com.example.comunicat.notes;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.comunicat.R;
import com.example.comunicat.notes.models.Nota;
import com.example.comunicat.config.Constants;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NotesAdapter extends RecyclerView.Adapter<NoteViewHolder> {
    ArrayList<Nota> noteList;
    Context ctx;

    public NotesAdapter(ArrayList<Nota> noteList, Context ctx){
        this.noteList= noteList;
        this.ctx= ctx;
    }
    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder,final int i) {
        holder.tvItemNoteTitle.setText(noteList.get(i).getTitle());
        holder.tvItemNoteBody.setText(noteList.get(i).getBody());
        holder.tvItemNoteDate.setText((noteList.get(i).getDate()).toString());

        holder.llNoteContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(ctx,DetailNoticeActiviy.class);
                intent.putExtra(Constants.EXTRA_NOTE_TITLE, noteList.get(i).getTitle());
                intent.putExtra(Constants.EXTRA_NOTE_DATE, noteList.get(i).getDate().toString());
                intent.putExtra(Constants.EXTRA_NOTE_BODY, noteList.get(i).getBody());
                ctx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }
}
