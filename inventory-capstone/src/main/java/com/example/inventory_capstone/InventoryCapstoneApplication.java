package com.example.inventory_capstone;

import com.example.inventory_capstone.repository.InventoryFileSeeder;
import com.example.inventory_capstone.repository.InventoryRepository;
import com.example.inventory_capstone.view.Adminworkflow;
import com.example.inventory_capstone.view.CustomerWorkflow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InventoryCapstoneApplication implements CommandLineRunner {

@Value("${inventory.mode:admin}")
private String mode;

private final Adminworkflow admin;
private final CustomerWorkflow customer;

@Autowired
public InventoryCapstoneApplication(Adminworkflow admin, CustomerWorkflow customer) {
	this.admin = admin;
	this.customer = customer;
}

	public static void main(String[] args) {
	SpringApplication.run(InventoryCapstoneApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	if (mode.equalsIgnoreCase("admin")) {
		admin.run();
	} else {
		customer.run();
	}



	}
}
