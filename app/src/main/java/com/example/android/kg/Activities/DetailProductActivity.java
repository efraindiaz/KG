package com.example.android.kg.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.android.kg.LoginActivity;
import com.example.android.kg.Model.Carrito;
import com.example.android.kg.Model.Coche;
import com.example.android.kg.Model.FirebaseReferences;
import com.example.android.kg.Model.Producto;
import com.example.android.kg.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DetailProductActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private FirebaseAuth firebaseAuth;
    private  FirebaseAuth.AuthStateListener firebaseAuthListener;
    private GoogleApiClient googleApiClient;

    private FirebaseDatabase database;

    private String UID_user;

    TextView nombreProducto, precioProducto, descProducto;
    ImageView imgProducto;
    Button add_to_cart;

    int precio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);

        //Init ses conf Firebase


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        //Firebase
        //database = FirebaseDatabase.getInstance();

        //Firebase auth

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null){
                    setUserData(user);
                }
                else{
                    goLogInScreen();
                }
            }
        };

        nombreProducto = (TextView) findViewById(R.id.textViewProductName);
        precioProducto = (TextView) findViewById(R.id.textViewPrice);
        imgProducto = (ImageView) findViewById(R.id.imageViewProd);
        descProducto = (TextView) findViewById(R.id.textViewDesc);

        add_to_cart = (Button) findViewById(R.id.add_to_cart);

        Glide.with(this).load(getIntent().getStringExtra("img")).into(imgProducto);
        nombreProducto.setText(getIntent().getStringExtra("nombre"));
        precioProducto.setText("$"+getIntent().getStringExtra("precio"));
        descProducto.setText(getIntent().getStringExtra("desc"));



        add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToCart();
            }
        });



    }

    private void addToCart() {

        //Toast.makeText(this, "Añadir", Toast.LENGTH_SHORT).show();
        database = FirebaseDatabase.getInstance();

        /*Ref to new product
        DatabaseReference tiendaRef = database.getReference(FirebaseReferences.TIENDA_REFERENCE);
        Producto producto = new Producto("HZ-PTAPF723300", "http://www.kontrolgeek.com/img/productos/drones/parrot/HZ-PTAPF723300/HZ-PTAPF723300-lg.jpg",
                "Parrot Travis Airborne Cargo Camera Drone", "No descripcion disponible", 5,2660,1);
        tiendaRef.child(FirebaseReferences.PRODUCTOS_REFERENCE).push().setValue(producto);*/

        //Ref to cart
        /*DatabaseReference tutoralRef = database.getReference(FirebaseReferences.TUTORIAL_REFERENCE);
        Carrito cart = new Carrito(UID_user,1,1,2300);
        tutoralRef.child(FirebaseReferences.CARRITO_REFERENCE).push().setValue(cart);*/

    }


    private void setUserData(FirebaseUser user) {

        UID_user = user.getUid();
        Toast.makeText(this, UID_user+"", Toast.LENGTH_SHORT).show();
        //descProducto.setText(UID_user);

    }

    private void goLogInScreen() {

        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(firebaseAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(firebaseAuthListener != null){

            firebaseAuth.removeAuthStateListener(firebaseAuthListener);
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
