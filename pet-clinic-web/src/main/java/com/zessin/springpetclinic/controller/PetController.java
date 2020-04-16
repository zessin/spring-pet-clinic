package com.zessin.springpetclinic.controller;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zessin.springpetclinic.model.Owner;
import com.zessin.springpetclinic.model.Pet;
import com.zessin.springpetclinic.model.PetType;
import com.zessin.springpetclinic.service.OwnerService;
import com.zessin.springpetclinic.service.PetService;
import com.zessin.springpetclinic.service.PetTypeService;

@Controller
@RequestMapping("/owners/{ownerId}/pets")
public class PetController {

	private final PetService petService;
	private final PetTypeService petTypeService;
	private final OwnerService ownerService;

	public PetController(PetService petService, PetTypeService petTypeService, OwnerService ownerService) {
		this.petService = petService;
		this.petTypeService = petTypeService;
		this.ownerService = ownerService;
	}

	@ModelAttribute("petTypes")
	public Collection<PetType> populatePetTypes() {
		return petTypeService.findAll();
	}

	@ModelAttribute("owner")
	public Owner populateOwner(@PathVariable("ownerId") Long ownerId) {
		return ownerService.findById(ownerId);
	}

	@InitBinder("owner")
	public void initOwnerBinder(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping("/new")
	public String initCreationForm(Owner owner, Model model) {
		Pet pet = new Pet();
		owner.getPets().add(pet);
		model.addAttribute("pet", pet);

		return "pets/createOrUpdatePetForm";
	}

	@PostMapping("/new")
	public String processCreationForm(Owner owner, @Valid Pet pet, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("pet", pet);

			return "pets/createOrUpdatePetForm";
		} else {
			owner.getPets().add(pet);
			petService.save(pet);

			return "redirect:/owners/" + owner.getId();
		}
	}

	@GetMapping("/{petId}/edit")
	public String initUpdateForm(@PathVariable Long petId, Model model) {
		model.addAttribute("pet", petService.findById(petId));

		return "pets/createOrUpdatePetForm";
	}

	@PostMapping("/{petId}/edit")
	public String processUpdateForm(Owner owner, @Valid Pet pet, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("pet", pet);

			return "pets/createOrUpdatePetForm";
		} else {
			owner.getPets().add(pet);
			petService.save(pet);

			return "redirect:/owners/" + owner.getId();
		}
	}

}
