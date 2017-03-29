package com.example.android.kg.Model;

/**
 * Created by Root on 28/03/2017.
 */

public class Usuario {

    String movil;
    String noInterior;
    String noExterior;
    String calle;
    String colonia;
    String ciudad;
    String estado;
    String codPostal;
    String referencias;

    public Usuario(){

    }

    public Usuario(String movil, String noInterior, String noExterior, String colonia, String calle, String ciudad, String estado, String codPostal, String referencias) {
        this.movil = movil;
        this.noInterior = noInterior;
        this.noExterior = noExterior;
        this.colonia = colonia;
        this.calle = calle;
        this.ciudad = ciudad;
        this.estado = estado;
        this.codPostal = codPostal;
        this.referencias = referencias;
    }

    public String getMovil() {
        return movil;
    }

    public void setMovil(String movil) {
        this.movil = movil;
    }

    public String getNoInterior() {
        return noInterior;
    }

    public void setNoInterior(String noInterior) {
        this.noInterior = noInterior;
    }

    public String getNoExterior() {
        return noExterior;
    }

    public void setNoExterior(String noExterior) {
        this.noExterior = noExterior;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCodPostal() {
        return codPostal;
    }

    public void setCodPostal(String codPostal) {
        this.codPostal = codPostal;
    }

    public String getReferencias() {
        return referencias;
    }

    public void setReferencias(String referencias) {
        this.referencias = referencias;
    }
}
