package com.example.comunicat;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.comunicat.utils.Constants;
import com.example.comunicat.utils.PreferencesManager;


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
    }



