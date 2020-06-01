package com.example.comunicat.notes;


import android.os.Bundle;

import com.example.comunicat.MainActivity;
import com.example.comunicat.R;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class NoticiasActivity extends MainActivity {

    private RecyclerView rdNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.noticias_main);
        setRecyclerViewData();

    }

    public void setRecyclerViewData() {
        rdNotes = findViewById(R.id.rdNotes);
        NotesAdapter adapter= new NotesAdapter();
        rdNotes.setAdapter(adapter);
        rdNotes.setHasFixedSize(true);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(NoticiasActivity.this);
        rdNotes.setLayoutManager(manager);
    }
}
