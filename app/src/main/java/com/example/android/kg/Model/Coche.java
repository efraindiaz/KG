package com.example.android.kg.Model;

/**
 * Created by Root on 27/03/2017.
 */

public class Coche {

    String img;
    String dueno;
    String marca;
    int puertas;
    int ruedas;

    public Coche(){

    }

    public Coche(String img, String dueno, String marca, int puertas, int ruedas) {
        this.img = img;
        this.dueno = dueno;
        this.marca = marca;
        this.puertas = puertas;
        this.ruedas = ruedas;
    }

    public String getImg(){ return img;}

    public void setImg(String img){this.img = img;}

    public String getDueno() {
        return dueno;
    }

    public void setDueno(String dueno) {
        this.dueno = dueno;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getPuertas() {
        return puertas;
    }

    public void setPuertas(int puertas) {
        this.puertas = puertas;
    }

    public int getRuedas() {
        return ruedas;
    }

    public void setRuedas(int ruedas) {
        this.ruedas = ruedas;
    }
}
