package com.zessin.springpetclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "specialty")
public class Specialty extends BaseEntity {

	@Builder
	public Specialty(Long id, String description) {
		super(id);
		this.description = description;
	}

	@Column(name = "description")
	private String description;

}
