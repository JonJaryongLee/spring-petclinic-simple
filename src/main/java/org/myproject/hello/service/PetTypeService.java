package org.myproject.hello.service;

import lombok.RequiredArgsConstructor;
import org.myproject.hello.entity.PetType;
import org.myproject.hello.repository.PetTypeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PetTypeService {

    private final PetTypeRepository petTypeRepository;

    public List<PetType> findPetTypes() {
        return petTypeRepository.findAll();
    }

    public PetType findPetType(Long petTypeId) {
        return verifyPetTypeExists(petTypeId);
    }

    private PetType verifyPetTypeExists(Long petTypeId) {
        return petTypeRepository.findById(petTypeId).orElseThrow(
                () -> new NoSuchElementException("PetType does not exist")
        );
    }
}
