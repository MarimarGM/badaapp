package com.badapp.badapp.controller;

import com.badapp.badapp.dto.especiales.Especiales;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
public class EspecialesController {

    private final ResourceLoader resourceLoader;

    public EspecialesController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
    //url para acceder a la página
    @GetMapping(value = "/especiales.html")
    public ModelAndView especiales(Model model) {
        //modelo que nos permite usar variables de Java en el HTML
        model.addAttribute("especiales", getEspeciales());
        return new ModelAndView("especiales");
    }
    //pasamos los XML como objetos Java para poder trabajar con ellos
    public Especiales getEspeciales() {
        XmlMapper xmlMapper = new XmlMapper();
        try {
            return xmlMapper.readValue(new ClassPathResource("/static/especiales/especiales.xml").getFile(),
                    Especiales.class);
        } catch (IOException e) {
            return null;
        }
    }
}
