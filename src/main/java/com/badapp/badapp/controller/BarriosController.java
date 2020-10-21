package com.badapp.badapp.controller;

import com.badapp.badapp.model.Barrios;
import com.badapp.badapp.model.Usuarios;
import com.badapp.badapp.repository.BarriosRepo;
import com.badapp.badapp.service.UserSession;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BarriosController {
    private final UserSession userSession;
    private final BarriosRepo barriosRepo;

    public BarriosController (UserSession userSession,
                              BarriosRepo barriosRepo) {
        this.userSession = userSession;
        this.barriosRepo = barriosRepo;
    }

    //dentro de admin nos lleva a la URL de crear barrio
    //nº de página y nº de elementos por página.
    @GetMapping(value = "/admin/crear-barrio.html")
    public ModelAndView crearBarrio(Model model,
                                    @RequestParam(required = false, defaultValue = "0") Integer pagina,
                                    @RequestParam(required = false, defaultValue = "5") Integer total) {

        Usuarios usuario = this.userSession.recuperarSesion();

        if (usuario == null) {
            return new ModelAndView("redirect:/");
        }else {
            //nos lleva a crear barrio con la sesion rellena del admin
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("crear-barrio");
            // Pasamos las variables del controlador a el html mediante model (objeto)
            model.addAttribute("usuario", usuario);
            model.addAttribute("barrios", this.barriosRepo.findAll(PageRequest.of(pagina, total)));
            model.addAttribute("pagina", pagina);
            model.addAttribute("total", total);
            return modelAndView;
        }
    }
    //esta anotación se usa cuando es un barrio que se produce al enviar un formulario
    //acción del formulario
    @RequestMapping("/admin/crearBarrioAction")
    @ResponseBody
    public ModelAndView crearBarriosAction(@ModelAttribute("barrios") Barrios barrios, Model model) {

        //hace un insert en la bbdd
        this.barriosRepo.save(barrios);
        return crearBarrio(model,0,5);
    }

    //página de transición al borrar un barrio por id
    @GetMapping(value = "/admin/borrarBarrio/{id}")
    public ModelAndView borrarBarrio(@PathVariable(value = "id") Integer id, Model model) {
        this.barriosRepo.deleteById(id);
        return crearBarrio(model,0,5);
    }

    //página de tansición para editar un barrio por id
    @GetMapping(value = "/admin/editarBarrio/{id}")
    public ModelAndView editarBarrio(@PathVariable(value = "id") Integer id, Model model) {

        model.addAttribute("barrio", this.barriosRepo.findById(id).get());
        return new ModelAndView("editar-barrio");
    }
    //botón del formulario para la acción de editar
    @RequestMapping("/admin/editarBarrioAction/{id}")
    @ResponseBody
    public ModelAndView editarBarriosAction(@PathVariable(value = "id") Integer id,
                                            @ModelAttribute("barrios") Barrios barrio, Model model) {
        //traemos el barrio a modificar por id
        Barrios barrioAModificar = this.barriosRepo.findById(id).get();
        //actualizamos con set lo modificado
        barrioAModificar.setNombre(barrio.getNombre());
        //se hace un update en la bbdd de lo modificado
        this.barriosRepo.save(barrioAModificar);
        //vuelves a la página de crear barrios
        return crearBarrio(model,0,5);
    }

}
