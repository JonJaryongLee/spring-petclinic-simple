package org.myproject.hello.service;

import lombok.RequiredArgsConstructor;
import org.myproject.hello.entity.Visit;
import org.myproject.hello.repository.VisitRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class VisitService {

    private final VisitRepository visitRepository;

    public List<Visit> findVisits() {
        return visitRepository.findAll();
    }

    public Visit findVisit(Long visitId) {
        return verifyVisitExists(visitId);
    }

    private Visit verifyVisitExists(Long visitId) {
        return visitRepository.findById(visitId).orElseThrow(
                () -> new NoSuchElementException("Visit does not exist")
        );
    }
}
