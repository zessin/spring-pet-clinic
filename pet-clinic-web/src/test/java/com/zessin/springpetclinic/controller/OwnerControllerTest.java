package com.zessin.springpetclinic.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
	public void testListOwners() throws Exception {
		when(ownerService.findAll()).thenReturn(owners);

		mockMvc.perform(get("/owners"))
			   .andExpect(status().isOk())
			   .andExpect(view().name("owners/index"))
			   .andExpect(model().attribute("owners", hasSize(2)));

		verify(ownerService, times(1)).findAll();
	}

	@Test
	public void testListOwnersByIndexUrl() throws Exception {
		when(ownerService.findAll()).thenReturn(owners);

		mockMvc.perform(get("/owners/index"))
			   .andExpect(status().isOk())
			   .andExpect(view().name("owners/index"))
			   .andExpect(model().attribute("owners", hasSize(2)));

		verify(ownerService, times(1)).findAll();
	}

	@Test
	public void testFindOwners() throws Exception {
		mockMvc.perform(get("/owners/find"))
		   	   .andExpect(status().isOk())
		   	   .andExpect(view().name("notImplemented"));

		verifyZeroInteractions(ownerService);
	}

}
