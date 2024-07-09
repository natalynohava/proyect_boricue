package com.example.project_boricue;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class productoAdaptador extends RecyclerView.Adapter<productoAdaptador.productoHolder> {
    private Context context;
    private List<producto> productoList;

    public productoAdaptador(Context context, List<producto> producto){
        this.context = context;
        this.productoList = producto;
    }

    @NonNull
    @Override
    public productoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_producto , parent , false);

        return new productoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull productoHolder holder, int position) {
        producto producto = productoList.get(position);
        holder.titulo.setText(producto.getTitulo());


    }

    @Override
    public int getItemCount() {
        return productoList.size();
    }

    public class productoHolder extends RecyclerView.ViewHolder{

        TextView titulo;

        public productoHolder(@NonNull View itemView) {
            super(itemView);

            titulo = itemView.findViewById(R.id.Ttitulo);
        }
    }
}
