package com.example.project_boricue;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder> {
    private List<Producto> productos;
    private Context context;

    public ProductoAdapter(List<Producto> productos, Context context) {
        this.productos = productos;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto, parent, false);
        return new ProductoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoViewHolder holder, int position) {
        Producto producto = productos.get(position);
        holder.textViewNombre.setText(producto.getNombre());
        holder.textViewDescripcion.setText(producto.getDescripcion());
        holder.textViewPrecio.setText("Precio: $" + producto.getPrecio());

        // Usar Glide para cargar la imagen
        Glide.with(context)
                .load(producto.getUrl_imagen())
                .into(holder.imageViewProducto);
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    static class ProductoViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewProducto;
        TextView textViewNombre, textViewDescripcion, textViewPrecio;

        ProductoViewHolder(View itemView) {
            super(itemView);
            imageViewProducto = itemView.findViewById(R.id.imageViewProducto);
            textViewNombre = itemView.findViewById(R.id.textViewNombre);
            textViewDescripcion = itemView.findViewById(R.id.textViewDescripcion);
            textViewPrecio = itemView.findViewById(R.id.textViewPrecio);
        }
    }
}
