package com.zessin.springpetclinic.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zessin.springpetclinic.model.Owner;
import com.zessin.springpetclinic.service.OwnerService;

@Controller
@RequestMapping("/owners")
public class OwnerController {

	private final OwnerService ownerService;

	public OwnerController(OwnerService ownerService) {
		this.ownerService = ownerService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping("/find")
	public String initFindForm(Model model) {
		model.addAttribute("owner", Owner.builder().build());

		return "owners/findOwners";
	}

	@GetMapping
	public String processFindForm(Owner owner, BindingResult result, Model model) {
		if (owner.getLastName() == null) {
			owner.setLastName("");
		}

		List<Owner> results = ownerService.findAllByLastNameIgnoringCaseContaining(owner.getLastName());

		if (results.isEmpty()) {
			result.rejectValue("lastName", "notFound", "not found");

			return "owners/findOwners";
		} else if (results.size() == 1) {
			owner = results.iterator().next();

			return "redirect:/owners/" + owner.getId();
		} else {
			model.addAttribute("selections", results);

			return "owners/ownersList";
		}
	}

	@GetMapping("/{id}")
	public ModelAndView initDetailsForm(@PathVariable Long id) {
		ModelAndView modelAndView = new ModelAndView("owners/ownerDetails");

		modelAndView.addObject(ownerService.findById(id));

		return modelAndView;
	}

	@GetMapping("/new")
	public String initCreationForm(Model model) {
		model.addAttribute("owner", Owner.builder().build());

		return "owners/createOrUpdateOwnerForm";
	}

	@PostMapping("/new")
	public String processCreationForm(@Valid Owner owner, BindingResult result) {
		if (result.hasErrors()) {
			return "owners/createOrUpdateOwnerForm";
		} else {
			Owner savedOwner = ownerService.save(owner);

			return "redirect:/owners/" + savedOwner.getId();
		}
	}

	@GetMapping("/{id}/edit")
	public String initUpdateForm(@PathVariable Long id, Model model) {
		model.addAttribute("owner", ownerService.findById(id));

		return "owners/createOrUpdateOwnerForm";
	}

	@PostMapping("/{id}/edit")
	public String processUpdateForm(@Valid Owner owner, BindingResult result, @PathVariable Long id) {
		if (result.hasErrors()) {
			return "owners/createOrUpdateOwnerForm";
		} else {
			owner.setId(id);
			Owner savedOwner = ownerService.save(owner);

			return "redirect:/owners/" + savedOwner.getId();
		}
	}

}
