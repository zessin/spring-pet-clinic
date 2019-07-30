package com.zessin.springpetclinic.bootstrap;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.zessin.springpetclinic.model.Owner;
import com.zessin.springpetclinic.model.Pet;
import com.zessin.springpetclinic.model.PetType;
import com.zessin.springpetclinic.model.Specialty;
import com.zessin.springpetclinic.model.Vet;
import com.zessin.springpetclinic.model.Visit;
import com.zessin.springpetclinic.service.OwnerService;
import com.zessin.springpetclinic.service.PetTypeService;
import com.zessin.springpetclinic.service.SpecialtyService;
import com.zessin.springpetclinic.service.VetService;
import com.zessin.springpetclinic.service.VisitService;

@Component
public class DataInitializer implements CommandLineRunner {

	private final OwnerService ownerService;
	private final VetService vetService;
	private final PetTypeService petTypeService;
	private final SpecialtyService specialtyService;
	private final VisitService visitService;

	public DataInitializer(OwnerService ownerService, VetService vetService, PetTypeService petTypeService,
						   SpecialtyService specialtyService, VisitService visitService) {
		this.ownerService = ownerService;
		this.vetService = vetService;
		this.petTypeService = petTypeService;
		this.specialtyService = specialtyService;
		this.visitService = visitService;
	}

	@Override
	public void run(String... args) throws Exception {
		int count = petTypeService.findAll().size();

		if (count == 0) {
			initializeData();
		}
	}

	private void initializeData() {
		PetType petType1 = new PetType();
		petType1.setName("Dog");
		petType1 = petTypeService.save(petType1);

		PetType petType2 = new PetType();
		petType2.setName("Cat");
		petType2 = petTypeService.save(petType2);

		Specialty specialty1 = new Specialty();
		specialty1.setDescription("Radiology");
		specialty1 = specialtyService.save(specialty1);

		Specialty specialty2 = new Specialty();
		specialty2.setDescription("Surgery");
		specialty2 = specialtyService.save(specialty2);

		Specialty specialty3 = new Specialty();
		specialty3.setDescription("Dentistry");
		specialty3 = specialtyService.save(specialty3);

		Owner owner1 = new Owner();
		owner1.setFirstName("Chandler");
		owner1.setLastName("Bing");
		owner1.setAddress("123 Walnut St.");
		owner1.setCity("Philadelphia");
		owner1.setTelephone("12344321");

		Pet pet1 = new Pet();
		pet1.setName("Marley");
		pet1.setPetType(petType1);
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
		pet2.setPetType(petType2);
		pet2.setOwner(owner2);
		pet2.setBirthDate(LocalDate.now());
		owner2.getPets().add(pet2);

		ownerService.save(owner2);

		Vet vet1 = new Vet();
		vet1.setFirstName("Ross");
		vet1.setLastName("Geller");
		vet1.getSpecialties().add(specialty1);
		vet1.getSpecialties().add(specialty2);
		vetService.save(vet1);

		Vet vet2 = new Vet();
		vet2.setFirstName("Phoebe");
		vet2.setLastName("Buffay");
		vet2.getSpecialties().add(specialty3);
		vetService.save(vet2);

		Visit visit1 = new Visit();
		visit1.setDate(LocalDate.now());
		visit1.setDescription("Marley's visit");
		visit1.setPet(pet1);
		visitService.save(visit1);

		System.out.println("Finished initializing data.");
	}

}
