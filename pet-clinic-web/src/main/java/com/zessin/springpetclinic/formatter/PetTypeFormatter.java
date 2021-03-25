package com.zessin.springpetclinic.formatter;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import com.zessin.springpetclinic.model.PetType;
import com.zessin.springpetclinic.service.PetTypeService;

@Component
public class PetTypeFormatter implements Formatter<PetType> {

    private final PetTypeService petTypeService;

    public PetTypeFormatter(PetTypeService petTypeService) {
        this.petTypeService = petTypeService;
    }

    @Override
    public String print(PetType petType, Locale locale) {
        return petType.getName();
    }

    @Override
    public PetType parse(String text, Locale locale) throws ParseException {
        Collection<PetType> petTypes = petTypeService.findAll();

        for (PetType petType : petTypes) {
            if (petType.getName().equals(text)) {
                return petType;
            }
        }

        throw new ParseException("Pet type not found: " + text, 0);
    }

}
