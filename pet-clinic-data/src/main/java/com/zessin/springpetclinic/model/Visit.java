package com.zessin.springpetclinic.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "visit")
public class Visit extends BaseEntity {

	@Builder
	public Visit(Long id, LocalDate date, String description, Pet pet) {
		super(id);
		this.date = date;
		this.description = description;
		this.pet = pet;
	}

	@Column(name = "date")
	private LocalDate date;

	@Column(name = "description")
	private String description;

	@ManyToOne
	@JoinColumn(name = "pet_id")
	private Pet pet;

}
