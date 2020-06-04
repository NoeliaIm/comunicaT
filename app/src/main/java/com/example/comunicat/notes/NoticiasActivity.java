package com.example.comunicat.notes;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.comunicat.MainActivity;
import com.example.comunicat.R;
import com.example.comunicat.admin.RegisterAdmin;
import com.example.comunicat.admin.WriteNotice;
import com.example.comunicat.notes.models.Nota;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class NoticiasActivity extends MainActivity {

    private RecyclerView rdNotes;
    Connection conexionMySQL = null;
    ArrayList<Nota> noteList = new ArrayList<>();
    Boolean isAdmin = false;
    String email="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.noticias_main);
        setRecyclerViewData();
        isAdmin = getIntent().getBooleanExtra("isAdmin", false);
        email= getIntent().getStringExtra("email");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.adminoptions, menu);
        if (isAdmin) {
            return super.onCreateOptionsMenu(menu);
        }
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.escribirNotices:
                startActivity(new Intent(this, WriteNotice.class));
                break;
            case R.id.verNotices:
                startActivity(new Intent(this, NoticiasActivity.class));
                break;
            case R.id.registerAdmin:
                startActivity(new Intent(this, RegisterAdmin.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setRecyclerViewData() {
        rdNotes = findViewById(R.id.rdNotes);
        NotesAdapter adapter = new NotesAdapter(noteList, NoticiasActivity.this);
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
                String sql = "select * from androidapp.notices order by notices.date desc";
                Statement st = conexionMySQL.createStatement();
                ResultSet resul = st.executeQuery(sql);
                while (resul.next()) {
                    String mssg = resul.getString(2);
                    Date date = resul.getDate(3);
                    String title = resul.getString(4);
                    Nota nota = new Nota(title, mssg, date);
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

    public void eliminar(View v) {
        new AlertDialog.Builder(NoticiasActivity.this)
                .setTitle("Eliminar cuenta")
                .setMessage("Confirma que deseas elimiar la cuenta")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        sqlThread2.start();
                        dialog.cancel();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                })
                .show();
    }

    Thread sqlThread2 = new Thread() {
        public void run() {

            try {
                Class.forName("com.mysql.jdbc.Driver");
                conexionMySQL = DriverManager.getConnection("jdbc:mysql://10.0.2.2:3306/androidapp?useSSL=false", "root", "4xM4aJk4E9!");
                PreparedStatement st = conexionMySQL.prepareStatement("delete from androidapp.users where users.email = ? ");
                st.setString(1, email);
                int resul = st.executeUpdate();
                if (resul==1) {
                    Intent intent = new Intent(NoticiasActivity.this, MainActivity.class);
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
