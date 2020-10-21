package com.badapp.badapp.controller;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest//anotacion que nos dice que la clase ejecuta casos de prueba
@AutoConfigureMockMvc
public class WebControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void usuario_entra_en_la_web() throws Exception {

        // When
        this.mockMvc.perform(get("/")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .string(containsString("¡BadaApp, la Aplicación Para Pacenses!")));
    }

    @Test
    public void usuario_entra_en_url_invalida() throws Exception {

        // When
        this.mockMvc.perform(get("/transportes.html"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}
