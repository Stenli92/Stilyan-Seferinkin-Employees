package com.example.demo.config;

import com.example.demo.service.StringToLocalDateTimeConverter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public ModelMapper modelMapper(){

        ModelMapper modelMapper = new ModelMapper();

        modelMapper.addConverter(new StringToLocalDateTimeConverter());

        return modelMapper;
    }
}
