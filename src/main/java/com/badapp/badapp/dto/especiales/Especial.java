package com.badapp.badapp.dto.especiales;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

public class Especial {

    private String id;
    private String nombre;
    private String fecha;
    private String descripcion;
    //convierte el XML en objeto java legible
    @JacksonXmlElementWrapper(useWrapping = false)
    private Imagenes[] imagenes;//array de imagenes

    public Especial() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    public Imagenes[] getImagenes() {
        return imagenes;
    }

    public void setImagenes(Imagenes[] imagenes) {
        this.imagenes = imagenes;
    }
}
