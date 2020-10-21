package com.badapp.badapp.controller;

import com.badapp.badapp.model.Barrios;
import com.badapp.badapp.model.Eventos;
import com.badapp.badapp.model.Localidades;
import com.badapp.badapp.model.Usuarios;
import com.badapp.badapp.repository.BarriosRepo;
import com.badapp.badapp.repository.EventosRepo;
import com.badapp.badapp.repository.LocalidadesRepo;
import com.badapp.badapp.repository.UsuariosRepo;
import com.badapp.badapp.service.UserSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class AdminController {

    private final UserSession userSession;
    private final BarriosRepo barriosRepo;
    private final LocalidadesRepo localidadesRepo;

    public AdminController(UserSession userSession,
                           BarriosRepo barriosRepo,
                           LocalidadesRepo localidadesRepo) {
        this.userSession = userSession;
        this.barriosRepo = barriosRepo;
        this.localidadesRepo = localidadesRepo;
    }

    //nos lleva a la pagina de acciones de admin
    @GetMapping(value = "/admin.html")
    public ModelAndView admin(Model model) {

        Usuarios usuario = this.userSession.recuperarSesion();
        //si no está logeado nos lleva a la raiz
        if (usuario == null) {
            return new ModelAndView("redirect:/");
        }else{
            //nos lleva a admin con la sesion rellena
            model.addAttribute("usuario", usuario);
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("admin");
            return modelAndView;
        }

    }
}
