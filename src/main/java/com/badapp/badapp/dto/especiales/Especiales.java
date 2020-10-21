package com.badapp.badapp.dto.especiales;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

public class Especiales {
    //contiene el array de tipo especial
    @JacksonXmlElementWrapper(useWrapping = false)
    private Especial[] especial;

    public Especiales() {

    }

    public Especial[] getEspecial() {
        return especial;
    }

    public void setEspecial(Especial[] especial) {
        this.especial = especial;
    }
}
