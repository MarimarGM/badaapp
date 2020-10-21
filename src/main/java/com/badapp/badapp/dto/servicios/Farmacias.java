package com.badapp.badapp.dto.servicios;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

public class Farmacias {

    //contiene el array de tipo farmacia
    @JacksonXmlElementWrapper(useWrapping = false)
    private Farmacia[] farmacia;

    public Farmacias() {
    }

    public Farmacia[] getFarmacia() {
        return farmacia;
    }

    public void setFarmacia(Farmacia[] farmacia) {
        this.farmacia = farmacia;
    }
}
