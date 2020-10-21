package com.badapp.badapp.controller;

import com.badapp.badapp.dto.servicios.Areas;
import com.badapp.badapp.dto.servicios.Farmacias;
import com.badapp.badapp.dto.servicios.Urgencias;
import com.badapp.badapp.dto.servicios.Veterinarios;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
public class ServiciosController {

    //servicio que carga los XML
    private final ResourceLoader resourceLoader;

    public ServiciosController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    //nos lleva a la página HTML
    @GetMapping(value = "/farmacias.html")
    public ModelAndView farmacias(Model model) {
        //obtenemos los datos del XML y se guardan en la variable 'farmacias' que se mostraran en el HTML.
        model.addAttribute("farmacias", getFarmacias());
        return new ModelAndView("farmacias");
    }
    //método que recupera los datos del XML
    public Farmacias getFarmacias() {
        XmlMapper xmlMapper = new XmlMapper();
        try {
            return xmlMapper.readValue(new ClassPathResource("/static/servicios/farmacias.xml").getFile(),
                    Farmacias.class);
        } catch (IOException e) {
            return null;
        }
    }

    //nos lleva a la página HTML
    @GetMapping(value = "/areas.html")
    public ModelAndView areas(Model model) {
        //obtenemos los datos del XML y se guardan en la variable 'areas' que se mostraran en el HTML.
        model.addAttribute("areas", getAreas());
        return new ModelAndView("areas");
    }
    //método que recupera los datos del XML
    public Areas getAreas() {
        XmlMapper xmlMapper = new XmlMapper();
        try {
            return xmlMapper.readValue(new ClassPathResource("/static/servicios/areas-comerciales.xml").getFile(),
                    Areas.class);
        } catch (IOException e) {
            return null;
        }
    }

    //nos lleva a la página HTML
    @GetMapping(value = "/urgencias.html")
    public ModelAndView urgencias(Model model) {
        //obtenemos los datos del XML y se guardan en la variable 'urgencias' que se mostraran en el HTML.
        model.addAttribute("urgencias", getUrgencias());
        return new ModelAndView("urgencias");
    }
    //método que recupera los datos del XML
    public Urgencias getUrgencias() {
        XmlMapper xmlMapper = new XmlMapper();
        try {
            return xmlMapper.readValue(new ClassPathResource("/static/servicios/urgencias-medicas.xml").getFile(),
                    Urgencias.class);
        } catch (IOException e) {
            return null;
        }
    }

    //nos lleva a la página HTML
    @GetMapping(value = "/veterinarios.html")
    public ModelAndView veterinarios(Model model) {
        //obtenemos los datos del XML y se guardan en la variable 'veterinarios' que se mostraran en el HTML
        model.addAttribute("veterinarios", getVeterinarios());
        return new ModelAndView("veterinarios");
    }
    //método que recupera los datos del XML
    public Veterinarios getVeterinarios() {
        XmlMapper xmlMapper = new XmlMapper();
        try {
            return xmlMapper.readValue(new ClassPathResource("/static/servicios/veterinarios.xml").getFile(),
                    Veterinarios.class);
        } catch (IOException e) {
            return null;
        }
    }
}
