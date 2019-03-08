package com.zessin.springpetclinic.bootstrap;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.zessin.springpetclinic.model.Owner;
import com.zessin.springpetclinic.model.Pet;
import com.zessin.springpetclinic.model.PetType;
import com.zessin.springpetclinic.model.Vet;
import com.zessin.springpetclinic.service.OwnerService;
import com.zessin.springpetclinic.service.PetTypeService;
import com.zessin.springpetclinic.service.VetService;

@Component
public class DataInitializer implements CommandLineRunner {

	private final OwnerService ownerService;
	private final VetService vetService;
	private final PetTypeService petTypeService;

	public DataInitializer(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
		this.ownerService = ownerService;
		this.vetService = vetService;
		this.petTypeService = petTypeService;
	}

	@Override
	public void run(String... args) throws Exception {

		PetType dog = new PetType();
		dog.setName("Dog");
		petTypeService.save(dog);

		PetType cat = new PetType();
		cat.setName("Cat");
		petTypeService.save(cat);

		Owner owner1 = new Owner();
		owner1.setFirstName("Chandler");
		owner1.setLastName("Bing");
		owner1.setAddress("123 Walnut St.");
		owner1.setCity("Philadelphia");
		owner1.setTelephone("12344321");

		Pet pet1 = new Pet();
		pet1.setName("Marley");
		pet1.setPetType(dog);
		pet1.setOwner(owner1);
		pet1.setBirthDate(LocalDate.now());
		owner1.getPets().add(pet1);

		ownerService.save(owner1);

		Owner owner2 = new Owner();
		owner2.setFirstName("Monica");
		owner2.setLastName("Geller");
		owner2.setAddress("456 Walnut St.");
		owner2.setCity("Philadelphia");
		owner2.setTelephone("43211234");

		Pet pet2 = new Pet();
		pet2.setName("Snowbell");
		pet2.setPetType(cat);
		pet2.setOwner(owner2);
		pet2.setBirthDate(LocalDate.now());
		owner2.getPets().add(pet2);

		ownerService.save(owner2);

		Vet vet1 = new Vet();
		vet1.setFirstName("Ross");
		vet1.setLastName("Geller");
		vetService.save(vet1);

		Vet vet2 = new Vet();
		vet2.setFirstName("Phoebe");
		vet2.setLastName("Buffay");
		vetService.save(vet2);

		System.out.println("Finished initializing data.");

	}

}
