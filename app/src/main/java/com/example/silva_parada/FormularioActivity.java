package com.example.silva_parada;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.firestore.FirebaseFirestore;

public class FormularioActivity extends AppCompatActivity {

    private EditText etNombre, etPrecio, etImagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        etNombre = findViewById(R.id.et_nombre_producto);
        etPrecio = findViewById(R.id.et_precio_producto);
        etImagen = findViewById(R.id.et_imagen_producto);
    }

    public void guardarClick(View view) {
        String nombre = etNombre.getText().toString();
        String imagen = etImagen.getText().toString();
        Double precio = Double.parseDouble(etPrecio.getText().toString());

        Producto newProducto = new Producto(nombre, precio, imagen);

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    }
}