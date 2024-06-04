package com.projekt.security.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projekt.security.entities.FlatPrices;
import com.projekt.security.repository.FlatPricesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.List;

@RequiredArgsConstructor
@Service
public class LoadFlatPrices implements CommandLineRunner {

    private final FlatPricesRepository repository;

    @Override
    public void run(String... args) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<FlatPrices>> typeReference = new TypeReference<List<FlatPrices>>() {};
//         File file = new File("src/main/java/com/projekt/security/data/sources/ceny.json").getAbsoluteFile();
        File file = new File("data/ceny.json").getAbsoluteFile();

        try {
            List<FlatPrices> flatPrices = objectMapper.readValue(file, typeReference);
            repository.saveAll(flatPrices);
        }
        catch (IOException e){
            System.out.printf("Unable to save flat prices: " + e.getMessage());
        }
    }
}