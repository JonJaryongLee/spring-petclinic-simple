package org.myproject.hello.service;

import lombok.RequiredArgsConstructor;
import org.myproject.hello.entity.Speciality;
import org.myproject.hello.repository.SpecialityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SpecialityService {

    private final SpecialityRepository specialityRepository;

    public List<Speciality> findSpecialities() {
        return specialityRepository.findAll();
    }

    public Speciality findSpeciality(Long specialityId) {
        return verifySpecialityExists(specialityId);
    }

    private Speciality verifySpecialityExists(Long specialityId) {
        return specialityRepository.findById(specialityId).orElseThrow(
                () -> new NoSuchElementException("Speciality does not exist")
        );
    }
}
