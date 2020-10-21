package com.badapp.badapp.model;


import javax.persistence.*;

@Entity//es una entidad de bbdd
@Table(name = "usuarios")
public class Usuarios {

    @Id//significa que es un PK
    @GeneratedValue(strategy = GenerationType.IDENTITY)//indica que es autoincrementable
    private int id;

    private String nombre;

    private String password;

    private String rol;

    public Usuarios() {
    }

    public Usuarios(int id, String nombre, String password, String rol) {
        this.id = id;
        this.nombre = nombre;
        this.password = password;
        this.rol = rol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
