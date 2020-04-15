package com.zessin.springpetclinic.service;

import java.util.List;

import com.zessin.springpetclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {

	Owner findByLastName(String lastName);

	List<Owner> findAllByLastNameIgnoringCaseContaining(String lastName);

}
