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
@Table(name = "pet_type")
public class PetType extends BaseEntity {

	@Builder
    public PetType(Long id, String name) {
        super(id);
        this.name = name;
    }

	@Column(name = "name")
	private String name;

}
