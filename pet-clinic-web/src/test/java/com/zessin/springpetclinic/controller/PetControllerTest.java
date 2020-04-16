package com.zessin.springpetclinic.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.zessin.springpetclinic.model.Owner;
import com.zessin.springpetclinic.model.Pet;
import com.zessin.springpetclinic.model.PetType;
import com.zessin.springpetclinic.service.OwnerService;
import com.zessin.springpetclinic.service.PetService;
import com.zessin.springpetclinic.service.PetTypeService;

@ExtendWith(MockitoExtension.class)
public class PetControllerTest {

	@Mock
	private PetService petService;

	@Mock
	private OwnerService ownerService;

	@Mock
	private PetTypeService petTypeService;

	@InjectMocks
	private PetController petController;

	private Owner owner;
	private Set<PetType> petTypes;

	private MockMvc mockMvc;

	@BeforeEach
	public void setup() throws Exception {
		owner = Owner.builder().id(1L).build();

		petTypes = new HashSet<>();
		petTypes.add(PetType.builder().id(1L).name("Dog").build());
		petTypes.add(PetType.builder().id(2L).name("Cat").build());

		mockMvc = MockMvcBuilders.standaloneSetup(petController).build();
	}

	@Test
	public void initCreationForm() throws Exception {
		when(ownerService.findById(eq(1L))).thenReturn(owner);
		when(petTypeService.findAll()).thenReturn(petTypes);

		mockMvc.perform(get("/owners/1/pets/new"))
			   .andExpect(status().isOk())
			   .andExpect(view().name("pets/createOrUpdatePetForm"))
			   .andExpect(model().attributeExists("owner"))
			   .andExpect(model().attributeExists("pet"));

		verify(ownerService).findById(eq(1L));
		verify(petTypeService).findAll();
		verifyZeroInteractions(petService);
	}

	@Test
	public void processCreationForm() throws Exception {
		when(ownerService.findById(eq(1L))).thenReturn(owner);
		when(petTypeService.findAll()).thenReturn(petTypes);

		mockMvc.perform(post("/owners/1/pets/new"))
			   .andExpect(status().is3xxRedirection())
			   .andExpect(view().name("redirect:/owners/1"))
			   .andExpect(model().attributeExists("owner"));

		verify(ownerService).findById(eq(1L));
		verify(petTypeService).findAll();
		verify(petService).save(any(Pet.class));
	}

	@Test
	public void initUpdateForm() throws Exception {
		when(ownerService.findById(eq(1L))).thenReturn(owner);
		when(petTypeService.findAll()).thenReturn(petTypes);
		when(petService.findById(eq(2L))).thenReturn(Pet.builder().id(2L).build());

		mockMvc.perform(get("/owners/1/pets/2/edit"))
			   .andExpect(status().isOk())
			   .andExpect(view().name("pets/createOrUpdatePetForm"))
			   .andExpect(model().attributeExists("owner"))
			   .andExpect(model().attributeExists("pet"));

		verify(ownerService).findById(eq(1L));
		verify(petTypeService).findAll();
		verify(petService).findById(eq(2L));
	}

	@Test
	public void processUpdateForm() throws Exception {
		when(ownerService.findById(eq(1L))).thenReturn(owner);
		when(petTypeService.findAll()).thenReturn(petTypes);

		mockMvc.perform(post("/owners/1/pets/2/edit"))
			   .andExpect(status().is3xxRedirection())
			   .andExpect(view().name("redirect:/owners/1"))
			   .andExpect(model().attributeExists("owner"));

		verify(ownerService).findById(eq(1L));
		verify(petTypeService).findAll();
		verify(petService).save(any(Pet.class));
	}

}
