package org.springframework.samples.petclinic.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.repository.OwnerRepository;
import org.springframework.samples.petclinic.repository.PetRepository;
import org.springframework.samples.petclinic.repository.VetRepository;
import org.springframework.samples.petclinic.repository.VisitRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class ClinicServiceImplTest {

    @Mock
    PetRepository petRepository;

    @Mock
    VetRepository vetRepository;

    @Mock
    OwnerRepository ownerRepository;

    @Mock
    VisitRepository visitRepository;

    @InjectMocks
    ClinicServiceImpl clinicService;

    @Test
    void findPetById() {
        //given
        Pet pet = new Pet();
        given(petRepository.findById(anyInt())).willReturn(pet);

        //when
        Pet petReturned = petRepository.findById(1);

        //then
        assertThat(pet).isEqualTo(petReturned);
        then(petRepository).should().findById(anyInt());
        then(petRepository).shouldHaveNoMoreInteractions();
    }

    @Test
    void findPetTypes() {
        List<PetType> petTypeList = new ArrayList<>();
        given(petRepository.findPetTypes()).willReturn(petTypeList);

        Collection<PetType> petsReturned = clinicService.findPetTypes();

        then(petRepository).should().findPetTypes();
        assertThat(petsReturned).hasSize(0);
        assertThat(petsReturned).isNotNull();
    }
}