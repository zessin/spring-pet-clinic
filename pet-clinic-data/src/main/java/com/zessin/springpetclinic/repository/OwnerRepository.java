package com.zessin.springpetclinic.repository;

import org.springframework.data.repository.CrudRepository;

import com.zessin.springpetclinic.model.Owner;

public interface OwnerRepository extends CrudRepository<Owner, Long> {

	Owner findByLastName(String lastName);

}
