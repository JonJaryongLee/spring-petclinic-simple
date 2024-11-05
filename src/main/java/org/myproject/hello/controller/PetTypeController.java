package org.myproject.hello.controller;

import lombok.RequiredArgsConstructor;
import org.myproject.hello.controller.response.PetTypeDto;
import org.myproject.hello.entity.PetType;
import org.myproject.hello.service.PetTypeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/types")
@RequiredArgsConstructor
public class PetTypeController {

    private final PetTypeService petTypeService;

    @GetMapping
    public List<PetTypeDto> list() {
        return petTypeService.findPetTypes().stream()
                .map(p -> new PetTypeDto(
                        p.getId(),
                        p.getName()
                ))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public PetTypeDto detail(@PathVariable Long id) {
        PetType p = petTypeService.findPetType(id);
        return new PetTypeDto(
                p.getId(),
                p.getName()
        );
    }
}
