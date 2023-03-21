package com.example.silva_parada;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class DetalleActivity extends AppCompatActivity {

    private TextView tvTitle, tvPrecio;
    private ImageView ivProducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        setTitle(getString(R.string.txt_detalle_producto));

        tvTitle = findViewById(R.id.tv_title_detalle);
        tvPrecio = findViewById(R.id.tv_precio_detalle);
        ivProducto = findViewById(R.id.iv_producto_detalle);

        Producto miProducto = (Producto) getIntent().getSerializableExtra("producto");
        tvTitle.setText(miProducto.getNombre());
        tvPrecio.setText(miProducto.getPrecio().toString());
        Picasso.get()
                .load(miProducto.getUrlImage())
                .error(R.drawable.ic_launcher_background)
                .into(ivProducto);
    }
}