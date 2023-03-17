package com.example.silva_parada;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Producto> listaPrincipalProductos;
    private RecyclerView rcListaProductos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cargarDatos();

        rcListaProductos = findViewById(R.id.rv_lista_productos);

        AdaptadorPersonalizado miAdapter = new AdaptadorPersonalizado(listaPrincipalProductos);
        miAdapter.setOnItemClickListener(new AdaptadorPersonalizado.OnItemClickListener() {
            @Override
            public void OnItemClick(Producto miProducto, int position) {
                Toast.makeText(MainActivity.this, "Click en el producto " + miProducto.getNombre(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void OnItemBtnEliminarClick(Producto miProducto, int position) {
                listaPrincipalProductos.remove(position);
                miAdapter.setListaProductos(listaPrincipalProductos);
                Toast.makeText(MainActivity.this, "Eliminado el producto " + miProducto.getNombre(), Toast.LENGTH_SHORT).show();
            }
        });
        rcListaProductos.setAdapter(miAdapter);
        rcListaProductos.setLayoutManager(new LinearLayoutManager(this));
    }

    public void cargarDatos() {
        Producto producto1 = new Producto();
        producto1.setNombre("Computador HP");
        producto1.setPrecio(8000000.0);
        producto1.setUrlImage("https://www.alkosto.com/medias/193808990359-001-750Wx750H?context=bWFzdGVyfGltYWdlc3wyNDgwODJ8aW1hZ2UvcG5nfGltYWdlcy9oNjUvaGZmLzkxMzMxOTU2OTAwMTQucG5nfDg2YzhiOTM3OTM0NTY5ODIwZDg1NGM4NmVhNGEyMGNhYmFkYmY1MzI3MzE1ODIxZDA4ZDY4YTQ2MDE0YTAwYmY");

        Producto producto2 = new Producto("Teclado DELL", 250000.0, "https://www.atlasvia.com/wp-content/uploads/2021/02/Teclado-Dell-KM636-Tenerife.png");
        Producto producto3 = new Producto("Mouse Inalambrico", 500000.0, "https://frontier.com.co/content/product/0004386_mouse-inalambrico-genius-nx-7000-negro.png");

        listaPrincipalProductos = new ArrayList<>();
        listaPrincipalProductos.add(producto1);
        listaPrincipalProductos.add(producto2);
        listaPrincipalProductos.add(producto3);
    }
}