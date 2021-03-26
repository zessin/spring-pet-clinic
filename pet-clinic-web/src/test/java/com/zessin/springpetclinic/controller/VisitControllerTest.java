package com.zessin.springpetclinic.controller;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.net.URI;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.UriTemplate;

import com.zessin.springpetclinic.model.Owner;
import com.zessin.springpetclinic.model.Pet;
import com.zessin.springpetclinic.model.PetType;
import com.zessin.springpetclinic.service.PetService;
import com.zessin.springpetclinic.service.VisitService;

@ExtendWith(MockitoExtension.class)
class VisitControllerTest {

    private static final String PETS_CREATE_OR_UPDATE_VISIT_FORM = "pets/createOrUpdateVisitForm";
    private static final String REDIRECT_OWNER = "redirect:/owners/{ownerId}";
    private static final String VISIT_DESCRIPTION = "visit description";

    @Mock
    PetService petService;

    @Mock
    VisitService visitService;

    @InjectMocks
    VisitController visitController;

    private MockMvc mockMvc;

    private final UriTemplate visitsUriTemplate = new UriTemplate("/owners/{ownerId}/pets/{petId}/visits/new");
    private final Map<String, String> uriVariables = new HashMap<>();
    private URI visitsUri;

    @BeforeEach
    void setUp() {
        Long petId = 1L;
        Long ownerId = 1L;

        when(petService.findById(eq(petId)))
            .thenReturn(Pet.builder()
                           .id(petId)
                           .birthDate(LocalDate.of(2019,2,1))
                           .name("Cutie")
                           .visits(new HashSet<>())
                           .owner(Owner.builder()
                                       .id(ownerId)
                                       .lastName("Doe")
                                       .firstName("Joe")
                                       .build())
                           .petType(PetType.builder()
                                           .name("Dog")
                                           .build())
                           .build());

        uriVariables.clear();
        uriVariables.put("ownerId", ownerId.toString());
        uriVariables.put("petId", petId.toString());
        visitsUri = visitsUriTemplate.expand(uriVariables);

        mockMvc = MockMvcBuilders.standaloneSetup(visitController).build();
    }

    @Test
    void initNewVisitForm() throws Exception {
        mockMvc.perform(get(visitsUri))
                .andExpect(status().isOk())
                .andExpect(view().name(PETS_CREATE_OR_UPDATE_VISIT_FORM));
    }


    @Test
    void processNewVisitForm() throws Exception {
        mockMvc.perform(post(visitsUri)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("date", "2020-02-01")
                        .param("description", VISIT_DESCRIPTION))
               .andExpect(status().is3xxRedirection())
               .andExpect(view().name(REDIRECT_OWNER))
               .andExpect(model().attributeExists("visit"));
    }
}
