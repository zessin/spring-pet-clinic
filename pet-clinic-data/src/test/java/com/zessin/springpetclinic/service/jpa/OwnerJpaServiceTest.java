package com.zessin.springpetclinic.service.jpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.zessin.springpetclinic.model.Owner;
import com.zessin.springpetclinic.repository.OwnerRepository;

@ExtendWith(MockitoExtension.class)
public class OwnerJpaServiceTest {

	@Mock
	private OwnerRepository ownerRepository;

	@InjectMocks
	private OwnerJpaService ownerJpaService;

	private Owner mockedOwner;

	@BeforeEach
	public void setup() {
		mockedOwner = Owner.builder().id(1L).firstName("Joey").lastName("Tribbiani").build();
	}

	@Test
	public void testFindAll() {
		Set<Owner> mockedOwners = new HashSet<>();
		mockedOwners.add(Owner.builder().id(1L).build());
		mockedOwners.add(Owner.builder().id(2L).build());

		when(ownerRepository.findAll()).thenReturn(mockedOwners);

		Set<Owner> owners = ownerJpaService.findAll();

		assertNotNull(owners);
		assertEquals(2, owners.size());
		verify(ownerRepository, times(1)).findAll();
	}

	@Test
	public void testFindById() {
		when(ownerRepository.findById(eq(mockedOwner.getId()))).thenReturn(Optional.of(mockedOwner));

		Owner owner = ownerJpaService.findById(mockedOwner.getId());

		assertNotNull(owner);
		verify(ownerRepository, times(1)).findById(eq(owner.getId()));
	}

	@Test
	public void testFindByIdNotFound() {
		when(ownerRepository.findById(eq(mockedOwner.getId()))).thenReturn(Optional.empty());

		Owner owner = ownerJpaService.findById(mockedOwner.getId());

		assertNull(owner);
		verify(ownerRepository, times(1)).findById(eq(mockedOwner.getId()));
	}


	@Test
	public void testSave() {
		when(ownerRepository.save(eq(mockedOwner))).thenReturn(mockedOwner);

		Owner owner = ownerJpaService.save(mockedOwner);

		assertNotNull(owner);
		verify(ownerRepository, times(1)).save(eq(owner));
	}

	@Test
	public void testDelete() {
		ownerJpaService.delete(mockedOwner);

		verify(ownerRepository, times(1)).delete(eq(mockedOwner));
	}

	@Test
	public void testDeleteById() {
		ownerJpaService.deleteById(mockedOwner.getId());

		verify(ownerRepository, times(1)).deleteById(eq(mockedOwner.getId()));
	}

	@Test
	public void testFindByLastName() {
		when(ownerRepository.findByLastName(eq(mockedOwner.getLastName()))).thenReturn(mockedOwner);

		Owner owner = ownerJpaService.findByLastName(mockedOwner.getLastName());

		assertNotNull(owner);
		verify(ownerRepository, times(1)).findByLastName(eq(owner.getLastName()));
	}

}
