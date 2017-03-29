package com.example.android.kg.Model;

/**
 * Created by Root on 28/03/2017.
 */

public class Carrito {

    String uid;
    int id_producto;
    int cantidad;
    int precio;


    public Carrito(){

    }

    public Carrito(String uid, int id_producto, int cantidad, int precio) {
        this.uid = uid;
        this.id_producto = id_producto;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
}
