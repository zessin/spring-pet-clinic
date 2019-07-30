package com.zessin.springpetclinic.service.jpa;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.zessin.springpetclinic.model.Specialty;
import com.zessin.springpetclinic.repository.SpecialtyRepository;
import com.zessin.springpetclinic.service.SpecialtyService;

@Service
@Profile("jpa")
public class SpecialtyJpaService implements SpecialtyService {

	private final SpecialtyRepository specialtyRepository;

	public SpecialtyJpaService(SpecialtyRepository specialtyRepository) {
		this.specialtyRepository = specialtyRepository;
	}

	@Override
	public Set<Specialty> findAll() {
		Set<Specialty> specialtys = new HashSet<>();

		specialtyRepository.findAll().forEach(specialtys::add);

		return specialtys;
	}

	@Override
	public Specialty findById(Long id) {
		return specialtyRepository.findById(id).orElse(null);
	}

	@Override
	public Specialty save(Specialty object) {
		return specialtyRepository.save(object);
	}

	@Override
	public void delete(Specialty object) {
		specialtyRepository.delete(object);
	}

	@Override
	public void deleteById(Long id) {
		specialtyRepository.deleteById(id);
	}

}
