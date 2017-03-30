package com.example.android.kg.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.android.kg.Adapter.Adapter;
import com.example.android.kg.Model.Coche;
import com.example.android.kg.Model.FirebaseReferences;
import com.example.android.kg.Model.Producto;
import com.example.android.kg.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements SearchView.OnQueryTextListener{

    RecyclerView rv;

    List<Producto> productos;

    Adapter adapter;

    private FirebaseDatabase database;


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_home, container, false);

        rv = (RecyclerView) v.findViewById(R.id.recyclerHome); //buscamos el id del recyclerview

        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        productos = new ArrayList<>();

        adapter = new Adapter(productos);

        rv.setAdapter(adapter);

        //Firebase info

        database = FirebaseDatabase.getInstance();

        DatabaseReference tiendaref = database.getReference(FirebaseReferences.TIENDA_REFERENCE);

        tiendaref.child(FirebaseReferences.PRODUCTOS_REFERENCE).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                productos.removeAll(productos);

                for (DataSnapshot snapshot:
                        dataSnapshot.getChildren()) {
                    Producto producto = snapshot.getValue(Producto.class);
                    productos.add(producto);
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




        return  v;

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(this);
        searchView.setQueryHint("Search");
        //MenuItem searchItem = menu.findItem(R.id.action_search);

        super.onCreateOptionsMenu(menu, inflater);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        newText = newText.toLowerCase();

        List<Producto> newList = new ArrayList<>();
        for(Producto producto: productos){

            String name = producto.getNombre().toLowerCase();
            if(name.contains(newText)){
                newList.add(producto);
            }
        }
        adapter.setFilter(newList);
        return true;
    }

}
