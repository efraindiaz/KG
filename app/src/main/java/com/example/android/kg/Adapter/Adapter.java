package com.example.android.kg.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.android.kg.Activities.DetailProductActivity;
import com.example.android.kg.Model.Coche;
import com.example.android.kg.Model.Producto;
import com.example.android.kg.R;

import java.util.List;

/**
 * Created by Root on 27/03/2017.
 */

/*********Adaptador para productos jeje*********/

public class Adapter extends RecyclerView.Adapter<Adapter.ProductosviewHolder>{

    List<Producto> productos;
    Context context;

    public Adapter(List<Producto> productos) {
        this.productos = productos;
    }

    @Override
    public ProductosviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_recycler, parent, false);
        ProductosviewHolder holder = new ProductosviewHolder(v);
        context = parent.getContext();
        return holder;
    }

    @Override
    public void onBindViewHolder(ProductosviewHolder holder, int position) {

        final Producto producto = productos.get(position);

        Glide.with(context).load(producto.getImg()).into(holder.imgProducto);
        holder.txtNombre.setText(producto.getNombre());
        holder.txtPrecio.setText(producto.getPrecio()+"");

        final int indice = position;

        holder.btnDetalles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context, indice+"", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, DetailProductActivity.class);
                intent.putExtra("img", producto.getImg());
                intent.putExtra("nombre", producto.getNombre());
                intent.putExtra("precio", producto.getPrecio());
                intent.putExtra("desc", producto.getDescripcion());
                intent.putExtra("sku", producto.getSku());
                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    public static class ProductosviewHolder extends RecyclerView.ViewHolder{

        TextView txtNombre, txtPrecio;
        ImageView imgProducto;
        Button btnDetalles;

        public ProductosviewHolder(View itemView) {
            super(itemView);

            imgProducto = (ImageView) itemView.findViewById(R.id.imageViewProduct);
            txtNombre = (TextView) itemView.findViewById(R.id.textview_product_name);
            txtPrecio = (TextView) itemView.findViewById(R.id.textview_product_price);
            btnDetalles = (Button) itemView.findViewById(R.id.buttonSeeProduct);

        }
    }


}

/*public class Adapter extends RecyclerView.Adapter<Adapter.CochesViewHolder>{

    List<Coche> coches;
    Context context;


    public Adapter(List<Coche> coches){
        this.coches = coches;
    }

    @Override
    public CochesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_recycler, parent, false);
        CochesViewHolder holder = new CochesViewHolder(v);
        context = parent.getContext();

        return holder;
    }

    @Override
    public void onBindViewHolder(CochesViewHolder holder, final int position) {

        final Coche coche = coches.get(position);
        Glide.with(context).load(coche.getImg()).into(holder.imgPrev);
        holder.textViewMarca.setText(coche.getMarca());
        holder.textViewDueno.setText(coche.getDueno());

        final int indice = position;
        holder.btnProductDet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "clic en : "+indice, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, DetailProductActivity.class);
                intent.putExtra("img", coche.getImg());
                intent.putExtra("nombre", coche.getDueno());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return coches.size();
    }

    public static class CochesViewHolder extends RecyclerView.ViewHolder{

        TextView textViewMarca, textViewDueno;
        ImageView imgPrev;
        Button btnProductDet;

        public CochesViewHolder(View itemView) {
            super(itemView);
            imgPrev = (ImageView) itemView.findViewById(R.id.imageViewTest);
            textViewMarca = (TextView) itemView.findViewById(R.id.textview_marca);
            textViewDueno = (TextView) itemView.findViewById(R.id.textview_dueno);
            btnProductDet = (Button) itemView.findViewById(R.id.buttonSeeProduct);
        }
    }
}*/
