package com.example.inventory_capstone.config;

import com.example.inventory_capstone.repository.CsvInventoryRepository;
import com.example.inventory_capstone.repository.InventoryRepository;
import com.example.inventory_capstone.repository.SampleInventoryRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InventoryConfiguration {

    @Value("${inventory.repository.type:sample}")
    private String repositoryType;

    @Bean
    public InventoryRepository inventoryRepository() {
        switch (repositoryType.toLowerCase()) {
            case "csv":
                return new CsvInventoryRepository("data/inventory.csv");
            case "sample":
                return new SampleInventoryRepository();
            default:
                throw new IllegalArgumentException(
                        "Invalid repository type: " +repositoryType +
                                " Supported types are: 'csv', 'sample'"
                );
        }
    }
}
