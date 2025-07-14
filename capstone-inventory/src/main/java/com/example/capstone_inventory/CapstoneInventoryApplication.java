package com.example.capstone_inventory;

import com.example.capstone_inventory.repository.InventoryFileSeeder;
import com.example.capstone_inventory.repository.SampleInventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLOutput;

@SpringBootApplication
public class CapstoneInventoryApplication implements CommandLineRunner {

	public static void main(String[] args) {

		String source = "data/inventory.csv";
		String destination = "data/seed_inventory.csv";
		InventoryFileSeeder.seedInventoryFile(source, destination);


	}

	@Override
	public void run(String... args) throws Exception {


	}
}
