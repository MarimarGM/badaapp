package com.badapp.badapp.model;

import javax.persistence.*;

@Entity//es una entidad de bbdd
@Table(name = "barrios")
public class Barrios {

    @Id//significa que es un PK
    @GeneratedValue(strategy = GenerationType.IDENTITY)//indica que es autoincrementable
    private Integer id;

    private String nombre;

    public Barrios() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
