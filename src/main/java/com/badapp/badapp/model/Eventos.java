package com.badapp.badapp.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity//es una entidad de bbdd
@Table(name = "eventos")
public class Eventos {

    @Id//significa que es un PK
    @GeneratedValue(strategy = GenerationType.IDENTITY)//indica que es autoincrementable
    private Integer id;

    private String titulo;

    private String descripcion;

    @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm")
    private LocalDateTime fechaDesde;

    @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm")
    private LocalDateTime fechaHasta;

    private Integer barriosId;

    private Integer localidadesId;

    public Eventos() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(LocalDateTime fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public LocalDateTime getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(LocalDateTime fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public Integer getBarriosId() {
        return barriosId;
    }

    public void setBarriosId(Integer barriosId) {
        this.barriosId = barriosId;
    }

    public Integer getLocalidadesId() {
        return localidadesId;
    }

    public void setLocalidadesId(Integer localidadesId) {
        this.localidadesId = localidadesId;
    }
}
