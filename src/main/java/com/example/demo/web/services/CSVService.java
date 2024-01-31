package com.example.demo.web.services;

import com.opencsv.CSVReader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

@Service
public class CSVService {

    public List<List<String>> readCsvData(MultipartFile file) throws Exception {
        List<List<String>> csvData = new ArrayList<>();

        try (Reader reader = new InputStreamReader(file.getInputStream());
             CSVReader csvReader = new CSVReader(reader)) {

            String[] line;
            while ((line = csvReader.readNext()) != null) {
                List<String> row = new ArrayList<>();
                for (String cell : line) {
                    row.add(cell);
                }
                csvData.add(row);
            }
        }

        return csvData;
    }
}
