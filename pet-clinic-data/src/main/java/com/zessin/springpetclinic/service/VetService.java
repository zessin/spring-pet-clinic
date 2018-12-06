package com.zessin.springpetclinic.service;

import java.util.Set;

import com.zessin.springpetclinic.model.Vet;

public interface VetService {

	Vet findById(Long id);
	
	Vet save(Vet vet);
	
	Set<Vet> findAll();
	
}
