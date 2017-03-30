package com.example.android.kg.Model;

/**
 * Created by Root on 29/03/2017.
 */

public class ProductoCarrito {

    String sku;
    String imgPC;
    String nobrePC;
    int precioPC;

    public ProductoCarrito(){

    }

    public ProductoCarrito(String sku, String imgPC, String nobrePC, int precioPC) {
        this.sku = sku;
        this.imgPC = imgPC;
        this.nobrePC = nobrePC;
        this.precioPC = precioPC;
    }

    public String getImgPC() {
        return imgPC;
    }

    public void setImgPC(String imgPC) {
        this.imgPC = imgPC;
    }

    public String getNobrePC() {
        return nobrePC;
    }

    public void setNobrePC(String nobrePC) {
        this.nobrePC = nobrePC;
    }

    public int getPrecioPC() {
        return precioPC;
    }

    public void setPrecioPC(int precioPC) {
        this.precioPC = precioPC;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }
}
