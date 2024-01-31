package com.example.demo.service;

import org.modelmapper.AbstractConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StringToLocalDateTimeConverter extends AbstractConverter<String , LocalDate> {

    @Override
    protected LocalDate convert(String source) {
        if (source == null) {
            return null;
        }
        // Customize the date format based on your actual date string format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(source, formatter);
    }
}
