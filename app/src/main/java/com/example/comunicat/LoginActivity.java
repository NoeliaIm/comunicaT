package com.example.comunicat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    Connection conexionMySQL = null;
    private Button btnLogin;
    private EditText etUserEmail, etUserPass;
    private String userEmail, userPass;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlThead.start();
            }
        });
    }

    Thread sqlThead = new Thread() {
        @Override
        public void run() {
            etUserEmail = findViewById(R.id.userEmail);
            etUserPass = findViewById(R.id.userPass);
            userEmail = etUserEmail.getText().toString();
            userPass = etUserPass.getText().toString();
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conexionMySQL = DriverManager.getConnection("jdbc:mysql://10.0.2.2:3306/androidapp?useSSL=false", "root", "4xM4aJk4E9!");
                PreparedStatement st = conexionMySQL.prepareStatement("select * from androidapp.users where users.email = ? and users.password = ?");
                st.setString(1, userEmail);
                st.setString(2, userPass);
                ResultSet resul = st.executeQuery();
                if (resul.first()) {
                    Intent intent = new Intent(LoginActivity.this, NoticiasActivity.class);
                    startActivity(intent);
                }
            } catch (SQLException se) {
                System.out.println("oops! No se puede conectar. Error: " + se.toString());
            } catch (ClassNotFoundException e) {
                System.out.println("oops! No se encuentra la clase. Error: " + e.getMessage());
            }
        }
    };
}
