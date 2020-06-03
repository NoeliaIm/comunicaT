package com.example.comunicat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.comunicat.config.PreferencesManager;
import com.example.comunicat.notes.NoticiasActivity;


public class MainActivity extends AppCompatActivity {

    private Button btnLogin, btnRegister;
    PreferencesManager preferencesManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnRegister = findViewById(R.id.buttonRegistro);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentRegister = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intentRegister);
            }
        });
        btnLogin = findViewById(R.id.buttonLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLogin = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intentLogin);
            }
        });
    }

    /*@Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences(PreferencesManager.PREFERENCIASAPP, MODE_PRIVATE);
        int logged = sharedPreferences.getInt(PreferencesManager.LOGGED, 0);
        Intent intent;
        if (logged != 0) {
            intent = new Intent(MainActivity.this, NoticiasActivity.class);
            startActivity(intent);
        }

    }*/
}



