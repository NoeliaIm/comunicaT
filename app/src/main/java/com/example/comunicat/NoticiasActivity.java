package com.example.comunicat;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.comunicat.utils.Constants;

public class NoticiasActivity extends MainActivity {
    private ListView lvNotes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.noticias_main);
        lvNotes = findViewById(R.id.lvNotes);
        final String[] values = new String[]{
                "Nota 1", "Nota 2", "Nota 3"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(NoticiasActivity.this, android.R.layout.simple_list_item_1, values);
        lvNotes.setAdapter(adapter);

        lvNotes.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent= new Intent(NoticiasActivity.this, DetailNoticeActiviy.class);
                intent.putExtra(Constants.EXTRA_NOTE_TITLE, values[position]);
                startActivity(intent);
            }

        });
    }
}
