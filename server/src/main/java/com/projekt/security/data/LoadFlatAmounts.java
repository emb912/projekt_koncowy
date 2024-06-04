package com.projekt.security.data;

import com.projekt.security.entities.FlatAmounts;
import com.projekt.security.entities.FlatPrices;
import com.projekt.security.repository.FlatAmountsRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;
import java.util.List;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@RequiredArgsConstructor
@Service
public class LoadFlatAmounts implements CommandLineRunner {
    private final FlatAmountsRepository repository;

    @Override
    public void run(String... args) throws Exception {
        XmlMapper xmlMapper = new XmlMapper();
//         File file = new File("src/main/java/com/projekt/security/data/sources/liczba_lokali.xml").getAbsoluteFile();
        File file = new File("data/liczba_lokali.xml").getAbsoluteFile();

        try {
            List<FlatAmounts> flatAmounts = xmlMapper.readValue(file,
                    xmlMapper.getTypeFactory().constructCollectionType(List.class, FlatAmounts.class));
            repository.saveAll(flatAmounts);
        }
        catch (IOException e){
            System.out.printf("Unable to save flat amounts: " + e.getMessage());
        }
    }
}
