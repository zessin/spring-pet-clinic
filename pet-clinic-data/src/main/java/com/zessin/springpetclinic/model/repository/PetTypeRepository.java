package com.zessin.springpetclinic.model.repository;

import org.springframework.data.repository.CrudRepository;

import com.zessin.springpetclinic.model.PetType;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {

}
