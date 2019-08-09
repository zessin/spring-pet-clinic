package com.zessin.springpetclinic.service.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.zessin.springpetclinic.model.Owner;

public class OwnerMapServiceTest {

	private OwnerMapService ownerMapService;
	private final Long ownerId = 1L;
	private final String lastName = "Tribbiani";

	@BeforeEach
	public void setup() {
		ownerMapService = new OwnerMapService(new PetMapService(), new PetTypeMapService());
		ownerMapService.save(Owner.builder().id(ownerId).lastName(lastName).build());
	}

	@Test
	public void testFindAll() {
		Set<Owner> owners = ownerMapService.findAll();
		assertEquals(1, owners.size());
	}

	@Test
	public void testFindById() {
		Owner owner = ownerMapService.findById(ownerId);
		assertEquals(ownerId, owner.getId());
	}

	@Test
	public void testSaveOwnerWithExistingId() {
		Long id = 2L;
		Owner owner2 = Owner.builder().id(id).build();
		Owner savedOwner = ownerMapService.save(owner2);

		assertEquals(id, savedOwner.getId());
	}

	@Test
	public void testSaveOwnerWithNoId() {
		Owner savedOwner = ownerMapService.save(Owner.builder().build());

		assertNotNull(savedOwner);
		assertNotNull(savedOwner.getId());
	}

	@Test
	public void testDeleteOwner() {
		ownerMapService.delete(ownerMapService.findById(ownerId));

		assertEquals(0, ownerMapService.findAll().size());
	}

	@Test
	public void testDeleteById() {
		ownerMapService.deleteById(ownerId);

		assertEquals(0, ownerMapService.findAll().size());
	}

	@Test
	public void testFindByLastName() {
		Owner owner = ownerMapService.findByLastName(lastName);

		assertNotNull(owner);
		assertEquals(ownerId, owner.getId());
	}

	@Test
	public void testFindByLastNameNotFound() {
		Owner owner = ownerMapService.findByLastName("Green");

		assertNull(owner);
	}

}
