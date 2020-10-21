package com.badapp.badapp.service;

import com.badapp.badapp.model.Usuarios;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@Service//anotacion que evita que se tenga que instaciar la clase
@SessionScope//anotacion que crea una sesion Spring y se puede recuperar la informacion desde donde se quiere
public class UserSession {
    //esta variable de tipo Usuarios guarda un usuario
    private Usuarios usuario;
    //crea sesion
    public void crearSesion(Usuarios usuario) {
        this.usuario = new Usuarios(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getPassword(),
                usuario.getRol()
        );
    }
    //borrar sesion
    public void borrarSesion() {
        this.usuario = null;
    }
    //recuperar sesion
    public Usuarios recuperarSesion() {
        return this.usuario;
    }
}
