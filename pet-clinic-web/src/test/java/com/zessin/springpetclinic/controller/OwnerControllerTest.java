package com.zessin.springpetclinic.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;
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
import com.zessin.springpetclinic.service.OwnerService;

@ExtendWith(MockitoExtension.class)
public class OwnerControllerTest {

	@Mock
	private OwnerService ownerService;

	@InjectMocks
	private OwnerController ownerController;

	private Set<Owner> owners;

	private MockMvc mockMvc;

	@BeforeEach
	public void setup() throws Exception {
		owners = new HashSet<>();
		owners.add(Owner.builder().id(1L).build());
		owners.add(Owner.builder().id(2L).build());

		mockMvc = MockMvcBuilders.standaloneSetup(ownerController).build();
	}

	@Test
	public void initFindForm() throws Exception {
		mockMvc.perform(get("/owners/find"))
		   	   .andExpect(status().isOk())
		   	   .andExpect(view().name("owners/findOwners"))
		   	   .andExpect(model().attributeExists("owner"));

		verifyNoInteractions(ownerService);
	}

	@Test
	public void processFindFormReturningMany() throws Exception {
		when(ownerService.findAllByLastNameIgnoringCaseContaining(anyString())).thenReturn(Arrays.asList(Owner.builder().id(1L).build(), Owner.builder().id(2L).build()));

		mockMvc.perform(get("/owners"))
		   	   .andExpect(status().isOk())
		   	   .andExpect(view().name("owners/ownersList"))
		   	   .andExpect(model().attribute("selections", hasSize(2)));

		verify(ownerService).findAllByLastNameIgnoringCaseContaining(anyString());
	}

	@Test
	public void processFindFormReturningOne() throws Exception {
		when(ownerService.findAllByLastNameIgnoringCaseContaining(anyString())).thenReturn(Arrays.asList(Owner.builder().id(1L).build()));

		mockMvc.perform(get("/owners"))
		   	   .andExpect(status().is3xxRedirection())
		   	   .andExpect(view().name("redirect:/owners/1"));

		verify(ownerService).findAllByLastNameIgnoringCaseContaining(anyString());
	}

	@Test
	public void displayOwner() throws Exception {
		when(ownerService.findById(eq(1L))).thenReturn(Owner.builder().id(1L).build());

		mockMvc.perform(get("/owners/1"))
		   	   .andExpect(status().isOk())
		   	   .andExpect(view().name("owners/ownerDetails"))
		   	   .andExpect(model().attribute("owner", hasProperty("id", equalTo(1L))));

		verify(ownerService).findById(eq(1L));
	}

	@Test
	public void initCreationForm() throws Exception {
		mockMvc.perform(get("/owners/new"))
			   .andExpect(status().isOk())
			   .andExpect(view().name("owners/createOrUpdateOwnerForm"))
			   .andExpect(model().attributeExists("owner"));

		verifyNoInteractions(ownerService);
	}

	@Test
	public void processCreationForm() throws Exception {
		when(ownerService.save(any(Owner.class))).thenReturn(Owner.builder().id(1L).build());

		mockMvc.perform(post("/owners/new"))
			   .andExpect(status().is3xxRedirection())
			   .andExpect(view().name("redirect:/owners/1"))
			   .andExpect(model().attributeExists("owner"));

		verify(ownerService).save(any(Owner.class));
	}

	@Test
	public void initUpdateForm() throws Exception {
		when(ownerService.findById(eq(1L))).thenReturn(Owner.builder().id(1L).build());

		mockMvc.perform(get("/owners/1/edit"))
			   .andExpect(status().isOk())
			   .andExpect(view().name("owners/createOrUpdateOwnerForm"))
			   .andExpect(model().attributeExists("owner"));

		verify(ownerService).findById(eq(1L));
	}

	@Test
	public void processUpdateForm() throws Exception {
		when(ownerService.save(any(Owner.class))).thenReturn(Owner.builder().id(1L).build());

		mockMvc.perform(post("/owners/1/edit"))
			   .andExpect(status().is3xxRedirection())
			   .andExpect(view().name("redirect:/owners/1"))
			   .andExpect(model().attributeExists("owner"));

		verify(ownerService).save(any(Owner.class));
	}

}
