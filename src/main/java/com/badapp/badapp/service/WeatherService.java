package com.badapp.badapp.service;

import com.badapp.badapp.dto.weather.WeatherResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import java.io.IOException;
import java.net.URI;

@Service//anotacion que evita que se tenga que instaciar la clase
public class WeatherService {

    private static final String WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather?q={city},{country}&APPID={key}&units=metric";
    private static final String API_KEY = "28ef52b4a0133553b3e579e28c59201e";
    private final RestTemplate restTemplate;

    //constructor
    public WeatherService(final RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }
    //metodo para recuperar los datos de la ciudad que queremos saber la temperatura
    public WeatherResponseDto getWeatherData(final String city, final String country) {

        final URI url = new UriTemplate(WEATHER_URL).expand(city, country, API_KEY);
        try {
            return restTemplate.getForEntity(url, WeatherResponseDto.class).getBody();
        } catch (Exception e) {
            // Exception
            return getMockResponse();
        }
    }

    //si hay algun problema se mostrará el archivo json
    private WeatherResponseDto getMockResponse() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new ClassPathResource("badajoz.json").getFile(),
                            WeatherResponseDto.class);
        } catch (IOException e) {
            return new WeatherResponseDto();
        }
    }
}