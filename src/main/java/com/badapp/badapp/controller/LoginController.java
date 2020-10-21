package com.badapp.badapp.controller;

import com.badapp.badapp.dto.LoginDto;
import com.badapp.badapp.model.Usuarios;
import com.badapp.badapp.repository.UsuariosRepo;
import com.badapp.badapp.service.UserSession;
import com.google.common.hash.Hashing;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.nio.charset.StandardCharsets;

@Controller
public class LoginController {

    private final UsuariosRepo usuariosRepo;
    private final UserSession userSession;
    //constructor
    public LoginController(UsuariosRepo usuariosRepo,
                           UserSession userSession) {
        this.usuariosRepo = usuariosRepo;
        this.userSession = userSession;
    }
    //nos lleva a la pagina login.html
    @GetMapping(value = "/login.html")
    public ModelAndView login(Model model) {
        //trae las vistas
        model.addAttribute("usuario", this.userSession.recuperarSesion());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }
    //cuando salgo de la sesion
    @GetMapping(value = "/logout.html")
    public ModelAndView logout(Model model) {
        //borra la sesion y nos redirige al inicio
        this.userSession.borrarSesion();
        return new ModelAndView("redirect:/");
    }
    //posibles acciones para login
    @RequestMapping("/loginAction")
    @ResponseBody
    public ModelAndView loginAction(@ModelAttribute("login")LoginDto login, Model model) {
        //Busca el usuario introducido por nombre
        Usuarios usuario = this.usuariosRepo.findByNombre(login.getNombre());
        if (usuario != null && login.getPassword() != null) {
            //metodo hash para encriptar la contraseña
            String enc = Hashing.sha256().hashString(login.getPassword(), StandardCharsets.UTF_8).toString();
            //usuario y contraseña son distintos a lo esperado
            if (!usuario.getPassword().equals(enc)) {
                model.addAttribute("error", "Usuario y contraseñas incorrectos");
                //nos redirige al login para volver a hacerlo
                return new ModelAndView("redirect:/login.html");
            }
            //como ha ido bien se crea la sesion
            this.userSession.crearSesion(usuario);
            return new ModelAndView("redirect:/");
        }
        //como ha ido mal nos lleva al login para intentarlo de nuevo

        return new ModelAndView("redirect:/login.html");
    }
}
