package com.zessin.springpetclinic.repository;

import org.springframework.data.repository.CrudRepository;

import com.zessin.springpetclinic.model.Vet;

public interface VetRepository extends CrudRepository<Vet, Long> {

}
