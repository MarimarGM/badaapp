package com.badapp.badapp.dto.servicios;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

public class Veterinarios {

    //contiene el array de tipo verterinario
    @JacksonXmlElementWrapper(useWrapping = false)
    private Veterinario[] veterinario;

    public Veterinarios() {
    }

    public Veterinario[] getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(Veterinario[] veterinario) {
        this.veterinario = veterinario;
    }
}
