package com.example.android.kg.Model;

/**
 * Created by Root on 27/03/2017.
 */

public class Producto {


    String sku;
    String img;
    String nombre;
    String descripcion;
    int cantidadDisp;
    int precio;
    int categoria;

    public Producto(){

    }

    public Producto(String sku, String img, String nombre, String descripcion, int cantidadDisp, int precio, int categoria) {
        this.sku = sku;
        this.img = img;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidadDisp = cantidadDisp;
        this.precio = precio;
        this.categoria = categoria;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidadDisp() {
        return cantidadDisp;
    }

    public void setCantidadDisp(int cantidadDisp) {
        this.cantidadDisp = cantidadDisp;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }
}
