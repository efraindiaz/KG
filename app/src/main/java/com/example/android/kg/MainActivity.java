package com.example.android.kg;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.android.kg.Activities.CarritoActivity;
import com.example.android.kg.Activities.PerfilActivity;
import com.example.android.kg.Fragments.CarsFragment;
import com.example.android.kg.Fragments.DronesFragment;
import com.example.android.kg.Fragments.HelicoptersFragment;
import com.example.android.kg.Fragments.HomeFragment;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, GoogleApiClient.OnConnectionFailedListener {


    private GoogleApiClient googleApiClient;

    private FirebaseAuth firebaseAuth;
    private  FirebaseAuth.AuthStateListener firebaseAuthListener;

    private TextView userName, emailUser;
    private ImageView imgUser;

    private NavigationView navigationView;



    //private FirebaseDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intetToCart = new Intent(MainActivity.this,CarritoActivity.class);
                startActivity(intetToCart);
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //campos de informacion de usuario en menu
        userName = (TextView)navigationView.getHeaderView(0).findViewById(R.id.textViewNameUser);
        emailUser = (TextView)navigationView.getHeaderView(0).findViewById(R.id.textViewEmailUser);
        imgUser = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.imageViewImgUser);

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

        /****************************/

        //Fragment por defecto
        defaultFragment();

    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(firebaseAuthListener);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        boolean fragmentTansaction = false;
        Fragment fragment = null;

        switch (id){
            case R.id.nav_home:{
                fragment = new HomeFragment();
                fragmentTansaction = true;
                break;
            }
            case R.id.nav_drones:{
                fragment = new DronesFragment();
                fragmentTansaction = true;
                break;
            }
            case R.id.nav_cars:{
                fragment = new CarsFragment();
                fragmentTansaction = true;
                break;
            }
            case R.id.nav_helicopters:{
                fragment = new HelicoptersFragment();
                fragmentTansaction = true;
                break;
            }
            case R.id.nav_cart:{
                Intent intent = new Intent(MainActivity.this, CarritoActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.nav_profile:{
                Intent intent = new Intent(MainActivity.this, PerfilActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.nav_call:{
                callUs();
                break;
            }
            case R.id.nav_exit:{
                logOut();
                break;
            }
        }

        if(fragmentTansaction){

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.MainFrameLayout, fragment)
                    .commit();
            item.setChecked(true);
            getSupportActionBar().setTitle(item.getTitle());

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void callUs() {

        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:0377778888"));

        startActivity(callIntent);

    }

    private void defaultFragment(){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.MainFrameLayout, new HomeFragment())
                .commit();
        MenuItem item = navigationView.getMenu().getItem(0);
        item.setChecked(true);

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        
    }

    private void setUserData(FirebaseUser user) {

        userName.setText(user.getDisplayName());
        emailUser.setText(user.getEmail());
        //idTextView.setText(user.getUid());
        Glide.with(this).load(user.getPhotoUrl()).into(imgUser);

    }

    private void goLogInScreen() {

        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    public void logOut(){

        firebaseAuth.signOut();

        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                if (status.isSuccess()) {
                    goLogInScreen();
                } else {
                    Toast.makeText(getApplicationContext(), "Error al cerrar sesion", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(firebaseAuthListener != null){

            firebaseAuth.removeAuthStateListener(firebaseAuthListener);
        }
    }
}
