package com.badapp.badapp.controller;

import com.badapp.badapp.model.Localidades;
import com.badapp.badapp.model.Usuarios;
import com.badapp.badapp.repository.LocalidadesRepo;
import com.badapp.badapp.service.UserSession;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LocalidadesController {
    private final UserSession userSession;
    private final LocalidadesRepo localidadesRepo;

    public LocalidadesController (UserSession userSession,
                                  LocalidadesRepo localidadesRepo){
        this.userSession = userSession;
        this.localidadesRepo = localidadesRepo;
    }
    //nos lleva a la página de crear localidad
    //nº de página y nº de elementos por página.
    @GetMapping(value = "/admin/crear-localidad.html")
    public ModelAndView crearLocalidades(Model model,
                                         @RequestParam(required = false, defaultValue = "0") Integer pagina,
                                         @RequestParam(required = false, defaultValue = "5") Integer total) {
        Usuarios usuario = this.userSession.recuperarSesion();

        if (usuario == null) {
            return new ModelAndView("redirect:/");
        } else {
            //nos lleva a crear localidad con la sesion rellena del admin
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("crear-localidad");
            // Pasamos las variables del controlador a el html mediante model (objeto)
            model.addAttribute("usuario", usuario);
            model.addAttribute("localidades", this.localidadesRepo.findAll(PageRequest.of(pagina, total)));
            model.addAttribute("pagina", pagina);
            model.addAttribute("total", total);
            return modelAndView;
        }
    }

    //página de transición al borrar una localidad por id
    @GetMapping(value = "/admin/borrarLocalidad/{id}")
    public ModelAndView borrarLocalidades(@PathVariable(value = "id") Integer id, Model model) {
        this.localidadesRepo.deleteById(id);
        return crearLocalidades(model,0,5);
    }

    //página de tansición para editar una localidad por id
    @GetMapping(value = "/admin/editarLocalidad/{id}")
    public ModelAndView editarLocalidad(@PathVariable(value = "id") Integer id, Model model) {

        model.addAttribute("localidad", this.localidadesRepo.findById(id).get());
        return new ModelAndView("editar-localidad");
    }

    //botón del formulario para la acción de editar
    @RequestMapping("/admin/editarLocalidadAction/{id}")
    @ResponseBody
    public ModelAndView editarLocalidadAction(@PathVariable(value = "id") Integer id,
                                              @ModelAttribute("localidades") Localidades localidades, Model model) {
        //traemos la localidad a modificar por id
        Localidades localidadAModificar = this.localidadesRepo.findById(id).get();
        //actualizamos con set lo modificado
        localidadAModificar.setNombre(localidades.getNombre());
        //se hace un update en la bbdd de lo modificado
        this.localidadesRepo.save(localidadAModificar);
        //vuelves a la página de crear localidades
        return crearLocalidades(model,0,5);
    }

    //esta anotación se usa cuando es una localidad que se produce al enviar un formulario
    //acción del formulario
    @RequestMapping("/admin/crearLocalidadAction")
    @ResponseBody
    public ModelAndView crearLocalidadesAction(@ModelAttribute("localidades") Localidades localidades, Model model) {

        //hace un insert en la bbdd
        this.localidadesRepo.save(localidades);
        return crearLocalidades(model,0,5);
    }
}
