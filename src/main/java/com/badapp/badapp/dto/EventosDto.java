package com.badapp.badapp.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class EventosDto {
    private Integer id;

    private String titulo;

    private String descripcion;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime fechaDesde;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime fechaHasta;

    private String barriosNombre;

    private String localidadesNombre;

    public EventosDto() {
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

    public String getBarriosNombre() {
        return barriosNombre;
    }

    public void setBarriosNombre(String barriosNombre) {
        this.barriosNombre = barriosNombre;
    }

    public String getLocalidadesNombre() {
        return localidadesNombre;
    }

    public void setLocalidadesNombre(String localidadesNombre) {
        this.localidadesNombre = localidadesNombre;
    }
}
