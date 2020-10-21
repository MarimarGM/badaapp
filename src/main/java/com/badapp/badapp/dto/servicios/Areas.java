package com.badapp.badapp.dto.servicios;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

public class Areas {

    //contiene el array de tipo centro
    @JacksonXmlElementWrapper(useWrapping = false)
    private Centro[] centro;

    public Areas() {

    }

    public Centro[] getCentro() {
        return centro;
    }

    public void setCentro(Centro[] centro) {
        this.centro = centro;
    }
}
