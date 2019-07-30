package com.zessin.springpetclinic.repository;

import org.springframework.data.repository.CrudRepository;

import com.zessin.springpetclinic.model.Visit;

public interface VisitRepository extends CrudRepository<Visit, Long> {

}
