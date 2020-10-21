package com.badapp.badapp.dto.transportes;

public class LineasDto {

    private String nombre;
    private String primeraParada;
    private String ultimaParada;

    public LineasDto() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrimeraParada() {
        return primeraParada;
    }

    public void setPrimeraParada(String primeraParada) {
        this.primeraParada = primeraParada;
    }

    public String getUltimaParada() {
        return ultimaParada;
    }

    public void setUltimaParada(String ultimaParada) {
        this.ultimaParada = ultimaParada;
    }
}
