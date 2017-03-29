package com.example.android.kg.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.android.kg.LoginActivity;
import com.example.android.kg.Model.FirebaseReferences;
import com.example.android.kg.Model.Usuario;
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

public class PerfilActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private  GoogleApiClient googleApiClient;

    private FirebaseAuth firebaseAuth;
    private  FirebaseAuth.AuthStateListener firebaseAuthListener;

    private FirebaseDatabase database;

    private String UID_user;

    ImageView imgProfile;
    TextView nombre, correo;

    EditText movil, noInterior, noExterior, calle, colonia, ciudad, estado, codPostal, referencias;

    Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);


        imgProfile = (ImageView) findViewById(R.id.imageViewUserProfile);
        nombre= (TextView) findViewById(R.id.txtNombre);
        correo = (TextView) findViewById(R.id.txtCorreo);

        /*Cst inputs info usuario*/
        movil = (EditText) findViewById(R.id.etMovil);
        noInterior = (EditText) findViewById(R.id.etInt);
        noExterior = (EditText) findViewById(R.id.etExt);
        calle = (EditText) findViewById(R.id.etCalle);
        colonia = (EditText) findViewById(R.id.etColonia);
        ciudad = (EditText) findViewById(R.id.etCiudad);
        estado = (EditText) findViewById(R.id.etEstado);
        codPostal = (EditText) findViewById(R.id.etCP);
        referencias = (EditText) findViewById(R.id.etRef);

        btnGuardar = (Button) findViewById(R.id.btnGurdarPerfil);


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


        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardaInfoUsuario();
            }
        });


    }

    private void guardaInfoUsuario() {

        database = FirebaseDatabase.getInstance();

        DatabaseReference tiendaRef = database.getReference(FirebaseReferences.TIENDA_REFERENCE);

       // Usuario user = new Usuario( "sdasas", "sdasas", "sdasas", "sdasas", "sdasas", "sdasas", "sdasas", "sdasas", "sdasas");

        Usuario user = new Usuario( movil.getText().toString(),
                                    noInterior.getText().toString(),
                                    noExterior.getText().toString(),
                                    calle.getText().toString(),
                                    colonia.getText().toString(),
                                    ciudad.getText().toString(),
                                    estado.getText().toString(),
                                    codPostal.getText().toString(),
                                    referencias.getText().toString());

        tiendaRef.child(FirebaseReferences.INFO_USUARIO_REFERENCE).child(UID_user).setValue(user);

    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(firebaseAuthListener);
    }


    private void setUserData(FirebaseUser user) {

        UID_user = user.getUid();
        nombre.setText(user.getDisplayName());
        correo.setText(user.getEmail());
        Glide.with(this).load(user.getPhotoUrl()).into(imgProfile);

        //Render datos en Campos de informacion de usuario

        database = FirebaseDatabase.getInstance();

        DatabaseReference tiendaRef = database.getReference(FirebaseReferences.TIENDA_REFERENCE);

        tiendaRef.child(FirebaseReferences.INFO_USUARIO_REFERENCE).child(UID_user).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Usuario userInfo = dataSnapshot.getValue(Usuario.class);
                movil.setText(userInfo.getMovil());
                noInterior.setText(userInfo.getNoInterior());
                noExterior.setText(userInfo.getNoExterior());
                calle.setText(userInfo.getCalle());
                colonia.setText(userInfo.getColonia());
                ciudad.setText(userInfo.getCiudad());
                estado.setText(userInfo.getEstado());
                codPostal.setText(userInfo.getCodPostal());
                referencias.setText(userInfo.getReferencias());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });





    }

    private void goLogInScreen() {

        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
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
