package com.example.silva_parada;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private EditText etCorreo, etPassword;
    private SharedPreferences misPreferencias;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        referenciar();
        misPreferencias = getSharedPreferences("tienda_app", MODE_PRIVATE);

        // VERIFICAR LOGGIN
        if(misPreferencias.getBoolean("logueado", false)) {
            Intent miIntent = new Intent(this, MainActivity.class);
            startActivity(miIntent);
            finish();
        }
    }

    private void referenciar() {
        etCorreo = findViewById(R.id.et_correo);
        etPassword = findViewById(R.id.et_password);
    }

    public void clickIniciarSesion(View view) {
        String PASSWORD = "123";
        String USER = "admin";

        String passUser = etPassword.getText().toString();
        String userUser = etCorreo.getText().toString();

        if(PASSWORD.equals(passUser) && USER.equals(userUser)) {
            SharedPreferences.Editor miEditor = misPreferencias.edit();
            miEditor.putBoolean("logueado", true);
            miEditor.apply();

            Intent miIntent = new Intent(this, MainActivity.class);
            startActivity(miIntent);
            finish();
        } else {
            Toast.makeText(this, "CREDENCIALES INCORRECTAS", Toast.LENGTH_SHORT).show();
        }
    }
}