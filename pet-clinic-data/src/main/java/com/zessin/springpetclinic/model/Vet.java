package com.zessin.springpetclinic.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "vet")
public class Vet extends Person {

	@Builder
	public Vet(Long id, String firstName, String lastName, Set<Specialty> specialties) {
		super(id, firstName, lastName);
		this.specialties = specialties;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "vet_specialty",
			   joinColumns = @JoinColumn(name = "vet_id"),
			   inverseJoinColumns = @JoinColumn(name = "specialty_id"))
	private Set<Specialty> specialties;

}
