package com.example.android.kg.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.android.kg.Model.Producto;
import com.example.android.kg.Model.ProductoCarrito;
import com.example.android.kg.R;

import java.util.List;

/**
 * Created by Root on 29/03/2017.
 */

public class AdapterCarrito extends RecyclerView.Adapter<AdapterCarrito.ProductosviewHolder> {

    List<ProductoCarrito> productosCP;
    Context context;

    public AdapterCarrito(List<ProductoCarrito> productosCP) {
        this.productosCP = productosCP;
    }

    @Override
    public ProductosviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_cart, parent, false);
        AdapterCarrito.ProductosviewHolder holderCP = new AdapterCarrito.ProductosviewHolder(v);
        context = parent.getContext();
        return holderCP;
    }

    @Override
    public void onBindViewHolder(ProductosviewHolder holder, int position) {

        //Aqui va la magia para pintar el cardview
        final ProductoCarrito producto = productosCP.get(position);

        Glide.with(context).load(producto.getImgPC()).into(holder.imgProducto);
        holder.nombre.setText(producto.getNobrePC());
        holder.precio.setText(producto.getPrecioPC()+"");
        holder.eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Remover producto", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return productosCP.size();
    }

    public static class ProductosviewHolder extends RecyclerView.ViewHolder{

        TextView nombre, precio;
        Button eliminar;
        ImageView imgProducto;

        public ProductosviewHolder(View itemView) {
            super(itemView);

            nombre = (TextView) itemView.findViewById(R.id.nombre_productoCP);
            precio = (TextView) itemView.findViewById(R.id.precioCP);
            imgProducto = (ImageView) itemView.findViewById(R.id.imageViewPC);
            eliminar = (Button) itemView.findViewById(R.id.btnEliminarCP);

        }
    }
}
