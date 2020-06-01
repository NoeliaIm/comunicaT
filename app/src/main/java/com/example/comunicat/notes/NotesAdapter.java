package com.example.comunicat.notes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.comunicat.R;
import com.example.comunicat.notes.models.Nota;

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
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int i) {
        holder.tvItemNoteTitle.setText(noteList.get(i).getTitle());
        holder.tvItemNoteBody.setText(noteList.get(i).getBody());
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }
}
