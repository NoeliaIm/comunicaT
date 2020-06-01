package com.example.comunicat.notes;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import com.example.comunicat.MainActivity;
import com.example.comunicat.R;
import com.example.comunicat.RegisterActivity;
import com.example.comunicat.notes.models.Nota;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class NoticiasActivity extends MainActivity {

    private RecyclerView rdNotes;
    Connection conexionMySQL = null;
    ArrayList<Nota> noteList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.noticias_main);
        setRecyclerViewData();

    }

    public void setRecyclerViewData() {
        rdNotes = findViewById(R.id.rdNotes);
        NotesAdapter adapter= new NotesAdapter(noteList, NoticiasActivity.this);
        rdNotes.setAdapter(adapter);
        rdNotes.setHasFixedSize(true);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(NoticiasActivity.this);
        rdNotes.setLayoutManager(manager);
        sqlThread.start();
    }

    Thread sqlThread = new Thread() {
        public void run() {

            try {
                Class.forName("com.mysql.jdbc.Driver");
                // "jdbc:mysql://IP:PUERTO/DB", "USER", "PASSWORD");
                // Si estás utilizando el emulador de android y tenes el mysql en tu misma PC no utilizar 127.0.0.1 o localhost como IP, utilizar 10.0.2.2
                conexionMySQL = DriverManager.getConnection(
                        "jdbc:mysql://10.0.2.2:3306/androidapp?verifyServerCertificate=false&useSSL=true", "root", "4xM4aJk4E9!");
                //En el stsql se puede agregar cualquier consulta SQL deseada.
                String sql= "select * from androidapp.notices order by notices.date desc";
                Statement st = conexionMySQL.createStatement();
                ResultSet resul= st.executeQuery(sql);
                while (resul.next()){
                    String mssg= resul.getString(2);
                    Date date= resul.getDate(3);
                    String title= resul.getString(4);
                    Nota nota= new Nota(title, mssg, date);
                    Log.d("nota", nota.toString());
                    noteList.add(nota);
                    Log.d("Result", noteList.toString());
                }
                conexionMySQL.close();
            } catch (SQLException se) {
                System.out.println("oops! No se puede conectar. Error: " + se.toString());
            } catch (ClassNotFoundException e) {
                System.out.println("oops! No se encuentra la clase. Error: " + e.getMessage());
            }
        }
    };
}
