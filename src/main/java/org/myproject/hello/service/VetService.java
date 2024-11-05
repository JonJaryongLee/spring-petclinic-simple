package org.myproject.hello.service;

import lombok.RequiredArgsConstructor;
import org.myproject.hello.entity.Vet;
import org.myproject.hello.repository.VetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class VetService {

    private final VetRepository vetRepository;

    public List<Vet> findVets() {
        return vetRepository.findAll();
    }

    public Vet findVet(Long vetId) {
        return verifyVetExists(vetId);
    }

    private Vet verifyVetExists(Long vetId) {
        return vetRepository.findById(vetId).orElseThrow(
                () -> new NoSuchElementException("Vet does not exist")
        );
    }
}
