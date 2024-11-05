package org.myproject.hello.controller;

import lombok.RequiredArgsConstructor;
import org.myproject.hello.controller.response.VetDto;
import org.myproject.hello.entity.Vet;
import org.myproject.hello.service.VetService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/vets")
@RequiredArgsConstructor
public class VetController {

    private final VetService vetService;

    @GetMapping
    public List<VetDto> list() {
        return vetService.findVets().stream()
                .map(v -> new VetDto(
                        v.getId(),
                        v.getFirstName(),
                        v.getLastName()
                ))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public VetDto detail(@PathVariable Long id) {
        Vet v = vetService.findVet(id);
        return new VetDto(
                v.getId(),
                v.getFirstName(),
                v.getLastName()
        );
    }
}
