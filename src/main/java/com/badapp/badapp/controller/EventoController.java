package com.badapp.badapp.controller;

import com.badapp.badapp.model.Eventos;
import com.badapp.badapp.model.Usuarios;
import com.badapp.badapp.repository.BarriosRepo;
import com.badapp.badapp.repository.EventosRepo;
import com.badapp.badapp.repository.LocalidadesRepo;
import com.badapp.badapp.service.UserSession;
import com.badapp.badapp.service.UtilidadesEventos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EventoController {

    //dependencias que llaman a los objetos y asi permite usarlos
    private final UserSession userSession;
    private final BarriosRepo barriosRepo;
    private final LocalidadesRepo localidadesRepo;
    private final UtilidadesEventos utilidadesEventos;
    private final EventosRepo eventosRepo;

    //constructor al que se le inyectan las dependencias
    public EventoController(UserSession userSession,
                            BarriosRepo barriosRepo,
                            LocalidadesRepo localidadesRepo,
                            UtilidadesEventos utilidadesEventos,
                            EventosRepo eventosRepo) {
        this.userSession = userSession;
        this.barriosRepo = barriosRepo;
        this.localidadesRepo = localidadesRepo;
        this.utilidadesEventos = utilidadesEventos;
        this.eventosRepo = eventosRepo;
    }

    //esta anotaación se usa cuando es un evento que se produce al enviar un formulario
    //nº de página y nº de elementos por página.
    @RequestMapping("/admin/crear-evento.html")
    public ModelAndView crearEvento(Model model,
                                    @RequestParam(required = false, defaultValue = "0") Integer pagina,
                                    @RequestParam(required = false, defaultValue = "5") Integer total) {

        Usuarios usuario = this.userSession.recuperarSesion();
        //si no hay usuario logeado nos lleva al index
        if (usuario == null) {
            return new ModelAndView("redirect:/");
         //si hay usuario logeado podemos realizar las acciones que nos dan los modelos
        } else {
            model.addAttribute("usuario", usuario);
            model.addAttribute("barrios", this.barriosRepo.findAll());
            model.addAttribute("localidades", this.localidadesRepo.findAll());
            model.addAttribute("eventos", this.eventosRepo.findAll(PageRequest.of(pagina, total)));
            model.addAttribute("pagina", pagina);
            model.addAttribute("total", total);
            return new ModelAndView("crear-evento");
        }
    }
    //esta anotación se usa cuando es un evento que se produce al enviar un formulario
    //acción del formulario
    @RequestMapping("/admin/crearEventoAction")
    @ResponseBody
    public ModelAndView crearEventoAction(@ModelAttribute("eventos") Eventos eventos, Model model) {

        //hace un insert en la bbdd
        this.eventosRepo.save(eventos);
        return crearEvento(model, 0, 5);
    }

    //página de transición al borrar un evento por id
    @GetMapping(value = "/admin/borrarEvento/{id}")
    public ModelAndView borrarEvento(@PathVariable(value = "id") Integer id, Model model) {
        this.eventosRepo.deleteById(id);
        return crearEvento(model,0,5);
    }
    //página de tansición para editar un evento por id
    @GetMapping(value = "/admin/editarEvento/{id}")
    public ModelAndView editarEvento(@PathVariable(value = "id") Integer id, Model model) {

        model.addAttribute("evento", this.eventosRepo.findById(id).get());
        model.addAttribute("barrios", this.barriosRepo.findAll());
        model.addAttribute("localidades", this.localidadesRepo.findAll());
        return new ModelAndView("editar-evento");
    }

    //botón del formulario para la acción de editar
    @RequestMapping("/admin/editarEventoAction/{id}")
    @ResponseBody
    public ModelAndView editarEventoAction(@PathVariable(value = "id") Integer id,
                                           @ModelAttribute("eventos") Eventos eventos,
                                           Model model) {
        //traemos el evento a modificar por id
        Eventos eventoAModificar = this.eventosRepo.findById(id).get();
        //actualizamos con set lo modificado
        eventoAModificar.setBarriosId(eventos.getBarriosId());
        eventoAModificar.setDescripcion(eventos.getDescripcion());
        eventoAModificar.setFechaDesde(eventos.getFechaDesde());
        eventoAModificar.setFechaHasta(eventos.getFechaHasta());
        eventoAModificar.setLocalidadesId(eventos.getLocalidadesId());
        eventoAModificar.setTitulo(eventos.getTitulo());
        //se hace un update en la bbdd de lo modificado
        this.eventosRepo.save(eventoAModificar);
        //vuelves a la página de crear eventos
        return crearEvento(model, 0, 5);
    }

    //entramos en la página de eventos
    @GetMapping("/eventos.html")
    public ModelAndView eventos (Model model,
                                  @RequestParam(required = false, defaultValue = "0") Integer pagina,
                                  @RequestParam(required = false, defaultValue = "8") Integer total) {
        //rellena la variable usuario con el objeto java de usuarios
        model.addAttribute("usuario", this.userSession.recuperarSesion());

        //carga la página HTML para poder verla
        ModelAndView modelAndView = new ModelAndView();
        //concretamos la página a ver (eventos)
        modelAndView.setViewName("eventos");
        //lista de eventos paginados
        Page<Eventos> eventos = this.eventosRepo.findAll(PageRequest.of(pagina, total));
        //gracias al servicio se muestra la descripción con un limite de palabras
        model.addAttribute("eventos", this.utilidadesEventos.getEventosDto(eventos));
        model.addAttribute("pagina", pagina);
        model.addAttribute("total", total);
        return modelAndView;
    }
    //pagina para ver un evento concreto por id
    @GetMapping("/ver-evento.html/{id}")
    public ModelAndView verevento (Model model,
                           @PathVariable(value = "id") Integer id) {
        //coge el evento de la bbdd por id y lo muestra entero
        Eventos eventoAMostrar = this.eventosRepo.findById(id).get();
        model.addAttribute("usuario", this.userSession.recuperarSesion());
        model.addAttribute("evento", this.utilidadesEventos.getEventoDto(eventoAMostrar, true));
        return new ModelAndView("ver-evento");
    }

}
