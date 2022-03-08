package org.springframework.samples.petclinic.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.model.Vets;
import org.springframework.samples.petclinic.service.ClinicService;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class VetControllerTest {
    @Mock
    ClinicService clinicService;

    @Mock
    Map<String,Object>model;

    @InjectMocks
    VetController controller;

    List<Vet> vetsList=new ArrayList<>();
    @BeforeEach
    void setUp(){
        Vet vet=new Vet();
        vetsList.add(new Vet());
    }
    @Test
    void showVetList() {
      //given--
        given(clinicService.findVets()).willReturn(vetsList);
      //when--
        String returendResult=controller.showVetList(model);
      //then--
        assertThat(returendResult).isEqualTo("vets/vetList");
        then(clinicService).should().findVets();
        then(model).should().put(anyString(),any());
    }

    @Test
    void showResourcesVetList() {
        //given--
        given(clinicService.findVets()).willReturn(vetsList);
        //when--
        Vets vets=controller.showResourcesVetList();
        //then--
        assertThat(vets.getVetList().size()).isEqualTo(1);
        then(clinicService).should().findVets();

    }
}