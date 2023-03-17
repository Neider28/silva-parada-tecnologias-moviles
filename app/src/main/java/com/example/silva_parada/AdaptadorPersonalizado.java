package com.example.silva_parada;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdaptadorPersonalizado extends RecyclerView.Adapter<AdaptadorPersonalizado.ViewHolder> {

    private ArrayList<Producto> listaProductos;
    private OnItemClickListener onItemClickListener;

    public AdaptadorPersonalizado(ArrayList<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    @NonNull
    @Override
    public AdaptadorPersonalizado.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View miView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista_producto, parent, false);
        return new ViewHolder(miView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorPersonalizado.ViewHolder holder, int position) {
        Producto miProducto = listaProductos.get(position);
        holder.enlazar(miProducto);
    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }

    public void setListaProductos(ArrayList<Producto> listaProductos) {
        this.listaProductos = listaProductos;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvNombre, tvPrecio;
        private ImageView ivProducto;
        private Button btnEliminar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNombre = itemView.findViewById(R.id.tv_item_nombre);
            tvPrecio = itemView.findViewById(R.id.tv_item_precio);
            ivProducto = itemView.findViewById(R.id.img_item_image);
            btnEliminar = itemView.findViewById(R.id.btn_eliminar);
        }

        public void enlazar(Producto miProducto) {
            tvNombre.setText(miProducto.getNombre());
            tvPrecio.setText(miProducto.getPrecio().toString());
            Picasso.get()
                    .load(miProducto.getUrlImage())
                    .error(R.drawable.ic_launcher_foreground)
                    .into(ivProducto);

            if(onItemClickListener != null) {
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onItemClickListener.OnItemClick(miProducto, getAdapterPosition());
                    }
                });

                btnEliminar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onItemClickListener.OnItemBtnEliminarClick(miProducto, getAdapterPosition());
                    }
                });
            }
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void OnItemClick(Producto miProducto, int position);
        void OnItemBtnEliminarClick(Producto miProducto, int position);
    }
}
