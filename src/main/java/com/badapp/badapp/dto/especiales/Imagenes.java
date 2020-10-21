package com.badapp.badapp.dto.especiales;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

public class Imagenes {

    //convierte el XML en objeto java legible
    @JacksonXmlElementWrapper(useWrapping = false)
    private String[] imagen;

    public Imagenes() {
    }

    public String[] getImagen() {
        return imagen;
    }

    public void setImagen(String[] imagen) {
        this.imagen = imagen;
    }
}
