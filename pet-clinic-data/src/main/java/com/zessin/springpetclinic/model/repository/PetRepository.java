package com.zessin.springpetclinic.model.repository;

import org.springframework.data.repository.CrudRepository;

import com.zessin.springpetclinic.model.Pet;

public interface PetRepository extends CrudRepository<Pet, Long> {

}
