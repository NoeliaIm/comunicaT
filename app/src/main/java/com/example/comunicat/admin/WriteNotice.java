package com.example.comunicat.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.comunicat.MainActivity;
import com.example.comunicat.R;
import com.example.comunicat.notes.NoticiasActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class WriteNotice extends MainActivity {
    Connection conexionMySQL = null;

    EditText etNoteTitle, etNoteBody;
    Button btnEnviar;
    String title, notice;
    Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.writenotice);
        btnEnviar= findViewById(R.id.enviarNoticia);
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sqlThread.start();
            }
        });
    }

    Thread sqlThread = new Thread() {
        public void run() {
            etNoteTitle= findViewById(R.id.noteTitle);
            etNoteBody= findViewById(R.id.noteBody);

            title = etNoteTitle.getText().toString();
            notice = etNoteBody.getText().toString();
            try {
                Class.forName("com.mysql.jdbc.Driver");
                // "jdbc:mysql://IP:PUERTO/DB", "USER", "PASSWORD");
                // Si estás utilizando el emulador de android y tenes el mysql en tu misma PC no utilizar 127.0.0.1 o localhost como IP, utilizar 10.0.2.2
                conexionMySQL = DriverManager.getConnection(
                        "jdbc:mysql://10.0.2.2:3306/androidapp?verifyServerCertificate=false&useSSL=true", "root", "4xM4aJk4E9!");
                //En el stsql se puede agregar cualquier consulta SQL deseada.
                PreparedStatement st = conexionMySQL.prepareStatement("insert into androidapp.notices (message, title) values (?,?)");
                st.setString(1, notice);
                st.setString(2, title);
                st.executeUpdate();
                conexionMySQL.close();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        clearForm(etNoteTitle, etNoteBody);
                    }
                });
                Intent intent = new Intent(WriteNotice.this, NoticiasActivity.class);
                intent.putExtra("isAdmin", true);
                startActivity(intent);
            } catch (SQLException se) {
                System.out.println("oops! No se puede conectar. Error: " + se.toString());
            } catch (ClassNotFoundException e) {
                System.out.println("oops! No se encuentra la clase. Error: " + e.getMessage());
            }
        }
    };

    public static void clearForm(EditText etNoteTitle, EditText etNoteBody){
        etNoteTitle.setText("");
        etNoteBody.setText("");
    }
}
