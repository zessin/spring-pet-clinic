package com.zessin.springpetclinic.service;

import java.util.Set;

import com.zessin.springpetclinic.model.Pet;

public interface PetService {

	Pet findById(Long id);
	
	Pet save(Pet pet);
	
	Set<Pet> findAll();
	
}
