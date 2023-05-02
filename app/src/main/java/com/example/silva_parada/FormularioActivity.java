package com.example.silva_parada;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

public class FormularioActivity extends AppCompatActivity {

    private EditText etNombre, etPrecio, etImagen;
    Producto miProducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        miProducto = (Producto) getIntent().getSerializableExtra("producto");

        etNombre = findViewById(R.id.et_nombre_producto);
        etPrecio = findViewById(R.id.et_precio_producto);
        etImagen = findViewById(R.id.et_imagen_producto);

        if (miProducto != null) {
            etNombre.setText(miProducto.getNombre());
            etPrecio.setText(miProducto.getPrecio().toString());
            etImagen.setText(miProducto.getUrlImage());
        }
    }

    public void guardarClick(View view) {
        String nombre = etNombre.getText().toString();
        String imagen = etImagen.getText().toString();
        Double precio = Double.parseDouble(etPrecio.getText().toString());

        Producto newProducto = new Producto();
        newProducto.setNombre(nombre);
        newProducto.setPrecio(precio);
        newProducto.setUrlImage(imagen);

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();

        if (miProducto != null) {
            firestore.collection("productos").document(miProducto.getId()).update("nombre", newProducto.getNombre(), "precio", newProducto.getPrecio(), "url_imagen", newProducto.getUrlImage());
            Toast.makeText(this, "Producto actualizado", Toast.LENGTH_SHORT).show();
        } else {
            firestore.collection("productos").add(newProducto);
            Toast.makeText(this, "Producto creado", Toast.LENGTH_SHORT).show();
        }

        finish();
    }
}