package org.myproject.hello.service;

import lombok.RequiredArgsConstructor;
import org.myproject.hello.entity.Owner;
import org.myproject.hello.repository.OwnerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OwnerService {

    private final OwnerRepository ownerRepository;

    public List<Owner> findOwners() {
        return ownerRepository.findAll();
    }

    public Owner findOwner(Long ownerId) {
        return verifyOwnerExists(ownerId);
    }

    private Owner verifyOwnerExists(Long ownerId) {
        return ownerRepository.findById(ownerId).orElseThrow(
                () -> new NoSuchElementException("Owner does not exist")
        );
    }
}
