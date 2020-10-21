package com.badapp.badapp.controller;

import com.badapp.badapp.dto.transportes.LineasDto;
import com.badapp.badapp.dto.transportes.Record;
import com.badapp.badapp.dto.transportes.Records;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class TransporteController {

    private final ResourceLoader resourceLoader;

    public TransporteController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    //nos lleva a la página
    @GetMapping(value = "/transportes.html")
    public ModelAndView transportes(Model model) {

        //carga los XML de tranporte
        Resource dir = resourceLoader.getResource("classpath:/static/transporte");
        //lista de lineas leidas por nombre
        List<String> lineas;
        //lista de lineasDto
        List<LineasDto> lineasDto = new ArrayList<>();

        //va leyendo los XML,si existen obtiene los nombres de las lineas
        try {
            if (dir.exists()) {
                //los nombre se obtienen dividiendolos por el '_' y nos quedamos con la parte derecha del nombre
                // y se quita el .xml (ej: 5N)
                lineas = Arrays.stream(dir.getFile().listFiles())
                        .map(file ->
                        file.getName().split("_")[1].replace(".xml", ""))
                        //los convierte en una lista de String
                        .collect(Collectors.toList());
                //recorre las lineas y guardamos la primera y ultima parada para saber su origen y destino y así nombrarlas
                lineasDto = lineas.stream().map(linea -> {
                    Record[] record = this.getRecords(linea).getRecord();
                    LineasDto lineaDto = new LineasDto();
                    long count = Arrays.stream(record).count();
                    Stream<Record> stream = Arrays.stream(record);
                    lineaDto.setNombre(linea);
                    lineaDto.setUltimaParada(stream.skip(count - 1).findFirst().get().getStopName());
                    lineaDto.setPrimeraParada(Arrays.stream(record).findFirst().get().getStopName());
                    return lineaDto;
                }).collect(Collectors.toList());

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //devuelve las lineasDto y lo muestra
        model.addAttribute("lineas", lineasDto);
        return new ModelAndView("transportes");
    }

    //vista de informacion ampliada de una linea en concreto
    @GetMapping(value = "/transporte/linea/{linea}")
    public ModelAndView transporte(Model model, @PathVariable(value = "linea") String linea) {

        model.addAttribute("lineas", getRecords(linea));
        return new ModelAndView("linea");
    }

    //método para leer las lineas
    public Records getRecords(String linea) {
        XmlMapper xmlMapper = new XmlMapper();
        try {
            return xmlMapper.readValue(new ClassPathResource("/static/transporte/linea_" + linea + ".xml").getFile(),
                    Records.class);
        } catch (IOException e) {
            return null;
        }
    }

}
