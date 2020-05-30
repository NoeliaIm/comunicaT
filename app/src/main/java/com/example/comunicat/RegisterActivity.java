package com.example.comunicat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    Connection conexionMySQL = null;
    private Button btnRegistro;
    private EditText nombre, apellido, pass, email, phone, admin;
    private String nombreIn, apellidoIn, passIn, emailIn, phoneIn, adminIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_main);
        btnRegistro = findViewById(R.id.buttonRegistro);
        btnRegistro.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sqlThread.start();
            }
        });
    }

    Thread sqlThread = new Thread() {
        public void run() {
            nombre = findViewById(R.id.textName);
            apellido = findViewById(R.id.textSurname);
            pass = findViewById(R.id.textPass);
            email = findViewById(R.id.textEmail);
            phone = findViewById(R.id.textPhone);
            nombreIn = nombre.getText().toString();
            apellidoIn = apellido.getText().toString();
            passIn = pass.getText().toString();
            emailIn = email.getText().toString();
            phoneIn = phone.getText().toString();
            try {
                Class.forName("com.mysql.jdbc.Driver");
                // "jdbc:mysql://IP:PUERTO/DB", "USER", "PASSWORD");
                // Si estás utilizando el emulador de android y tenes el mysql en tu misma PC no utilizar 127.0.0.1 o localhost como IP, utilizar 10.0.2.2
                conexionMySQL = DriverManager.getConnection(
                        "jdbc:mysql://10.0.2.2:3306/androidapp", "root", "4xM4aJk4E9!");
                //En el stsql se puede agregar cualquier consulta SQL deseada.
                PreparedStatement st = conexionMySQL.prepareStatement("insert into androidapp.users (name, surname, phone, email, password ) values (?,?,?,?,?)");
                st.setString(1, nombreIn);
                st.setString(2, apellidoIn);
                st.setString(3, phoneIn);
                st.setString(4, emailIn);
                st.setString(5, passIn);
                st.executeUpdate();
                conexionMySQL.close();
            } catch (SQLException se) {
                System.out.println("oops! No se puede conectar. Error: " + se.toString());
            } catch (ClassNotFoundException e) {
                System.out.println("oops! No se encuentra la clase. Error: " + e.getMessage());
            }
        }
    };

}

