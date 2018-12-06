package com.zessin.springpetclinic.service;

import java.util.Set;

import com.zessin.springpetclinic.model.Owner;

public interface OwnerService {

	Owner findById(Long id);
	
	Owner findByLastName(String lastName);
	
	Owner save(Owner owner);
	
	Set<Owner> findAll();
	
}
