package com.example.android.kg.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.kg.Adapter.Adapter;
import com.example.android.kg.Adapter.AdapterCarrito;
import com.example.android.kg.Model.Producto;
import com.example.android.kg.Model.ProductoCarrito;
import com.example.android.kg.R;

import java.util.ArrayList;
import java.util.List;

public class CarritoActivity extends AppCompatActivity {

    TextView txtTotal;

    RecyclerView rv;

    List<ProductoCarrito> productosCP = new ArrayList<>();;

    AdapterCarrito adapter;

    String sku, nombre, img;
    int precio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);

        txtTotal = (TextView) findViewById(R.id.textView);

        Bundle extras = getIntent().getExtras();

        if(extras != null){

            sku = extras.getString("sku");
            nombre = extras.getString("nombre");
            img = extras.getString("img");
            precio = extras.getInt("precio");
            Toast.makeText(this, "Si hay datos precio = "+precio, Toast.LENGTH_SHORT).show();

            rv = (RecyclerView) findViewById(R.id.recyclerCart); //buscamos el id del recyclerview

            rv.setLayoutManager(new LinearLayoutManager(this));

            adapter = new AdapterCarrito(productosCP);

            rv.setAdapter(adapter);

            ProductoCarrito producto = new ProductoCarrito(sku,img,nombre,precio);
            productosCP.add(producto);

            txtTotal.setText("$"+precio+ "MXN");

        }
        else{
            Toast.makeText(this, "No hay productos en el carrito", Toast.LENGTH_SHORT).show();
        }

        /*
        rv = (RecyclerView) findViewById(R.id.recyclerCart); //buscamos el id del recyclerview

        rv.setLayoutManager(new LinearLayoutManager(this));

        //productosCP = new ArrayList<>();

        adapter = new AdapterCarrito(productosCP);

        rv.setAdapter(adapter);
        ProductoCarrito producto = new ProductoCarrito( getIntent().getStringExtra("sku"),getIntent().getStringExtra("img"),getIntent().getStringExtra("nombre"),
                                                        Integer.parseInt(getIntent().getStringExtra("precio")));

        //ProductoCarrito producto = new ProductoCarrito("HZ-PTAPF723300", "http://www.kontrolgeek.com/img/productos/drones/worldtechtoys/ZX-34895-REF/ZX-34895-lg.jpg",
                //"Sphere 2.4GHz 4.5CH Camera RC Spy Drone", 2660);
        productosCP.add(producto);*/
    }
}
