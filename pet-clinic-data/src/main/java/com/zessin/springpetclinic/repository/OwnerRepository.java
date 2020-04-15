package com.zessin.springpetclinic.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.zessin.springpetclinic.model.Owner;

public interface OwnerRepository extends CrudRepository<Owner, Long> {

	Owner findByLastName(String lastName);

	List<Owner> findAllByLastNameIgnoringCaseContaining(String lastName);

}
