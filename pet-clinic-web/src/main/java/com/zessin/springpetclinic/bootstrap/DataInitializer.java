package com.zessin.springpetclinic.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.zessin.springpetclinic.model.Owner;
import com.zessin.springpetclinic.model.Vet;
import com.zessin.springpetclinic.service.OwnerService;
import com.zessin.springpetclinic.service.VetService;

@Component
public class DataInitializer implements CommandLineRunner {

	private final OwnerService ownerService;
	private final VetService vetService;

	public DataInitializer(OwnerService ownerService, VetService vetService) {
		this.ownerService = ownerService;
		this.vetService = vetService;
	}

	@Override
	public void run(String... args) throws Exception {

		Owner owner1 = new Owner();
		owner1.setFirstName("Joao");
		owner1.setLastName("Silva");
		ownerService.save(owner1);

		Owner owner2 = new Owner();
		owner2.setFirstName("Maria");
		owner2.setLastName("Dias");
		ownerService.save(owner2);

		Vet vet1 = new Vet();
		vet1.setFirstName("Jose");
		vet1.setLastName("Silva");
		vetService.save(vet1);

		Vet vet2 = new Vet();
		vet2.setFirstName("Joana");
		vet2.setLastName("Dias");
		vetService.save(vet2);

		System.out.println("Finished initializing data.");

	}

}
