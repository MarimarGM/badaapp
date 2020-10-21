package com.badapp.badapp.repository;

import com.badapp.badapp.model.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
//facilita hacer consultas a la base de datos
public interface UsuariosRepo extends JpaRepository<com.badapp.badapp.model.Usuarios, Integer> {

    Usuarios findByNombre(String nombre);


}
