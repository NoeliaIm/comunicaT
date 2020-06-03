package com.example.comunicat.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.comunicat.MainActivity;
import com.example.comunicat.R;
import com.example.comunicat.RegisterActivity;
import com.example.comunicat.notes.NoticiasActivity;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterAdmin extends MainActivity {
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
    public static void  clearForm(EditText nombre, EditText apellido, EditText email, EditText pass, EditText phone){
        nombre.setText("");
        apellido.setText("");
        email.setText("");
        pass.setText("");
        phone.setText("");
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
                        "jdbc:mysql://10.0.2.2:3306/androidapp?verifyServerCertificate=false&useSSL=true", "root", "4xM4aJk4E9!");
                //En el stsql se puede agregar cualquier consulta SQL deseada.
                PreparedStatement st = conexionMySQL.prepareStatement("insert into androidapp.users (name, surname, phone, email, password, admin ) values (?,?,?,?,?, ?)");
                st.setString(1, nombreIn);
                st.setString(2, apellidoIn);
                st.setString(3, phoneIn);
                st.setString(4, emailIn);
                st.setString(5, passIn);
                st.setBoolean(6,true);
                st.executeUpdate();
                conexionMySQL.close();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        clearForm(nombre, apellido, email, pass, phone);
                    }
                });
                Intent intent = new Intent(RegisterAdmin.this, NoticiasActivity.class);
                intent.putExtra("isAdmin", true);
                startActivity(intent);
            }catch (MySQLIntegrityConstraintViolationException e){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast toast= Toast.makeText(getApplicationContext(), "El email ya está en uso", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                        finish();
                        Intent intent = new Intent(RegisterAdmin.this, RegisterAdmin.class);
                        startActivity(intent);
                    }
                });
            } catch (SQLException se) {
                System.out.println("oops! No se puede conectar. Error: " + se.toString());
            } catch (ClassNotFoundException e) {
                System.out.println("oops! No se encuentra la clase. Error: " + e.getMessage());
            }
        }
    };
}
