package com.badapp.badapp.controller;

import com.badapp.badapp.dto.weather.WeatherResponseDto;
import com.badapp.badapp.model.Eventos;
import com.badapp.badapp.repository.EventosRepo;
import com.badapp.badapp.service.UserSession;
import com.badapp.badapp.service.UtilidadesEventos;
import com.badapp.badapp.service.WeatherService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller//anotacion para indicar que es un controlador: direcciones de la url
public class WebController {


    private final UserSession userSession;
    private final WeatherService weatherService;
    private final EventosRepo eventosRepo;
    private final UtilidadesEventos utilidadesEventos;

    public WebController(UserSession userSession,
                         WeatherService weatherService,
                         EventosRepo eventosRepo,
                         UtilidadesEventos utilidadesEventos) {
        this.userSession = userSession;
        this.weatherService = weatherService;
        this.eventosRepo = eventosRepo;
        this.utilidadesEventos = utilidadesEventos;
    }

    @GetMapping("/")//entra en la raiz
    public ModelAndView index(Model model) {
        //rellena la variable usuario con el objeto java de usuarios
        WeatherResponseDto weather = this.weatherService.getWeatherData("Badajoz", "es");
        Page<Eventos> eventos = this.eventosRepo.findAll(PageRequest.of(0, 4, Sort.by("id").descending()));
        //redirigen a la pagina de index
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        model.addAttribute("tiempo", weather);
        model.addAttribute("usuario", this.userSession.recuperarSesion());
        model.addAttribute("eventos", this.utilidadesEventos.getEventosDto(eventos));
        return modelAndView;
    }

    public String formatTem() {
        return "asdf";
    }

}


