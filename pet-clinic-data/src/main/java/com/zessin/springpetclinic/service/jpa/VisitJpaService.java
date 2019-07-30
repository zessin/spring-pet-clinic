package com.zessin.springpetclinic.service.jpa;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.zessin.springpetclinic.model.Visit;
import com.zessin.springpetclinic.repository.VisitRepository;
import com.zessin.springpetclinic.service.VisitService;

@Service
@Profile("jpa")
public class VisitJpaService implements VisitService {

	private final VisitRepository visitRepository;

	public VisitJpaService(VisitRepository visitRepository) {
		this.visitRepository = visitRepository;
	}

	@Override
	public Set<Visit> findAll() {
		Set<Visit> visits = new HashSet<>();

		visitRepository.findAll().forEach(visits::add);

		return visits;
	}

	@Override
	public Visit findById(Long id) {
		return visitRepository.findById(id).orElse(null);
	}

	@Override
	public Visit save(Visit object) {
		return visitRepository.save(object);
	}

	@Override
	public void delete(Visit object) {
		visitRepository.delete(object);
	}

	@Override
	public void deleteById(Long id) {
		visitRepository.deleteById(id);
	}

}
