package com.zessin.springpetclinic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zessin.springpetclinic.service.VetService;

@Controller
public class VetController {

	private final VetService vetService;

	public VetController(VetService vetService) {
		this.vetService = vetService;
	}

	@RequestMapping({"/vet", "/vet/index", "/vet/index.html"})
	public String listVets(Model model) {
		model.addAttribute("vets", vetService.findAll());

		return "vet/index";
	}

}
