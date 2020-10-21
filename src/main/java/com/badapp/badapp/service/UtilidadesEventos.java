package com.badapp.badapp.service;

import com.badapp.badapp.dto.EventosDto;
import com.badapp.badapp.model.Eventos;
import com.badapp.badapp.repository.BarriosRepo;
import com.badapp.badapp.repository.LocalidadesRepo;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service//anotacion que evita que se tenga que instaciar la clase
public class UtilidadesEventos {

    private static final int NUMERO_LETRAS_MAXIMO = 100;
    private final BarriosRepo barriosRepo;
    private final LocalidadesRepo localidadesRepo;
    //constructor
    public UtilidadesEventos(BarriosRepo barriosRepo, LocalidadesRepo localidadesRepo) {
        this.barriosRepo = barriosRepo;
        this.localidadesRepo = localidadesRepo;
    }
    //lista de eventos
    public List<EventosDto> getEventosDto(Page<Eventos> eventos) {
        //lista de eventosdto
        List<EventosDto> eventosDtoList = new ArrayList<>();

        for (Eventos evento : eventos) {
            eventosDtoList.add(getEventoDto(evento, false));
        }

        return eventosDtoList;
    }
    //método que trae eventos dto, no el id de los componentes
    public EventosDto getEventoDto(Eventos evento, boolean entero) {
        EventosDto eventosDto = new EventosDto();
        eventosDto.setId(evento.getId());
        eventosDto.setTitulo(evento.getTitulo());
        //se mostrará la descripción con una limitación de letras
        if (entero) {
            eventosDto.setDescripcion(evento.getDescripcion());
        } else {
            eventosDto.setDescripcion(obtenerDescripccion(evento.getDescripcion()));
        }
        eventosDto.setFechaDesde(evento.getFechaDesde());
        eventosDto.setFechaHasta(evento.getFechaHasta());
        eventosDto.setBarriosNombre(this.barriosRepo.findById(evento.getBarriosId()).get().getNombre());
        eventosDto.setLocalidadesNombre(this.localidadesRepo.findById(evento.getLocalidadesId()).get().getNombre());
        return eventosDto;
    }

    /**
     * Nos devuelve en la descricpión de hasta 100 letras para evitar textos muy largos y le concatena '...'
     * @param desc
     * @return
     */
    public String obtenerDescripccion(String desc) {
        return desc
                .substring(0, Math.min(desc.length(), NUMERO_LETRAS_MAXIMO))
                .concat(" ...");
    }
}
