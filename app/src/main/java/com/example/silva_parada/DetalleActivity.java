package com.example.silva_parada;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class DetalleActivity extends AppCompatActivity {

    private TextView tvTitle, tvPrecio;
    private ImageView ivProducto;
    Producto miProducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        setTitle(getString(R.string.txt_detalle_producto));

        tvTitle = findViewById(R.id.tv_title_detalle);
        tvPrecio = findViewById(R.id.tv_precio_detalle);
        ivProducto = findViewById(R.id.iv_producto_detalle);

        miProducto = (Producto) getIntent().getSerializableExtra("producto");
        tvTitle.setText(miProducto.getNombre());
        tvPrecio.setText(miProducto.getPrecio().toString());
        Picasso.get()
                .load(miProducto.getUrlImage())
                .error(R.drawable.ic_launcher_background)
                .into(ivProducto);
    }

    public void editarClick(View view) {
        Intent miIntent = new Intent(this, FormularioActivity.class);
        miIntent.putExtra("producto", miProducto);
        startActivity(miIntent);

        finish();
    }
}