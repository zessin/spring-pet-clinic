package com.zessin.springpetclinic.service.map;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.zessin.springpetclinic.model.Vet;
import com.zessin.springpetclinic.service.SpecialtyService;
import com.zessin.springpetclinic.service.VetService;

@Service
public class VetMapService extends AbstractMapService<Vet, Long> implements VetService {

	private final SpecialtyService specialtyService;

	public VetMapService(SpecialtyService specialtyService) {
		this.specialtyService = specialtyService;
	}

	@Override
	public Set<Vet> findAll() {
		return super.findAll();
	}

	@Override
	public Vet findById(Long id) {
		return super.findById(id);
	}

	@Override
	public Vet save(Vet object) {
		if (object.getSpecialties() != null) {
			object.getSpecialties().forEach(specialty -> {
				if (specialty.getId() == null) {
					specialty = specialtyService.save(specialty);
				}
			});
		}

		return super.save(object);
	}

	@Override
	public void delete(Vet object) {
		super.delete(object);
	}

	@Override
	public void deleteById(Long id) {
		super.deleteById(id);
	}

}
