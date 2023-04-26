package com.example.silva_parada;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Producto> listaPrincipalProductos = new ArrayList<>();
    private RecyclerView rcListaProductos;
    private AdaptadorPersonalizado miAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(getString(R.string.txt_lista_productos));

        rcListaProductos = findViewById(R.id.rv_lista_productos);

        miAdapter = new AdaptadorPersonalizado(listaPrincipalProductos);
        miAdapter.setOnItemClickListener(new AdaptadorPersonalizado.OnItemClickListener() {
            @Override
            public void OnItemClick(Producto miProducto, int position) {
                Intent intent = new Intent(MainActivity.this, DetalleActivity.class);
                intent.putExtra("producto", miProducto);
                startActivity(intent);
            }

            @Override
            public void OnItemBtnEliminarClick(Producto miProducto, int position) {
                FirebaseFirestore firestore = FirebaseFirestore.getInstance();
                firestore.collection("productos").document(miProducto.getId()).delete();
                listaPrincipalProductos.remove(position);
                miAdapter.setListaProductos(listaPrincipalProductos);
                Toast.makeText(MainActivity.this, "Eliminado el producto " + miProducto.getNombre(), Toast.LENGTH_SHORT).show();
            }
        });
        rcListaProductos.setAdapter(miAdapter);
        rcListaProductos.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onResume() {
        super.onResume();

        listaPrincipalProductos.clear();
        cargarDatos();
    }

    public void cargarDatos() {
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();

        firestore.collection("productos").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()) {
                    for (DocumentSnapshot document : task.getResult()) {
                        Producto productoGet = document.toObject(Producto.class);
                        productoGet.setId(document.getId());
                        listaPrincipalProductos.add(productoGet);
                    }

                    miAdapter.setListaProductos(listaPrincipalProductos);
                } else {
                    Toast.makeText(MainActivity.this, "Algo falló", Toast.LENGTH_SHORT).show();
                }
            }
        });

        /*Producto producto1 = new Producto();
        producto1.setNombre("Computador HP");
        producto1.setPrecio(8000000.0);
        producto1.setUrlImage("https://www.alkosto.com/medias/193808990359-001-750Wx750H?context=bWFzdGVyfGltYWdlc3wyNDgwODJ8aW1hZ2UvcG5nfGltYWdlcy9oNjUvaGZmLzkxMzMxOTU2OTAwMTQucG5nfDg2YzhiOTM3OTM0NTY5ODIwZDg1NGM4NmVhNGEyMGNhYmFkYmY1MzI3MzE1ODIxZDA4ZDY4YTQ2MDE0YTAwYmY");

        Producto producto2 = new Producto("Teclado DELL", 250000.0, "https://www.atlasvia.com/wp-content/uploads/2021/02/Teclado-Dell-KM636-Tenerife.png");
        Producto producto3 = new Producto("Mouse Inalambrico", 500000.0, "https://frontier.com.co/content/product/0004386_mouse-inalambrico-genius-nx-7000-negro.png");*/
    }

    public void clickAgregarProducto(View view) {
        Intent miIntent = new Intent(this, FormularioActivity.class);
        startActivity(miIntent);
    }

    public void clickCerrarSesion(View view) {
        SharedPreferences misPreferencias = getSharedPreferences("tienda_app", MODE_PRIVATE);
        SharedPreferences.Editor miEditor = misPreferencias.edit();
        miEditor.clear();
        miEditor.apply();

        startActivity(new Intent(this, LoginActivity.class));
    }
}