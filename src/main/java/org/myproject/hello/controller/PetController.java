package org.myproject.hello.controller;

import lombok.RequiredArgsConstructor;
import org.myproject.hello.controller.response.OwnerDto;
import org.myproject.hello.controller.response.PetDto;
import org.myproject.hello.controller.response.PetTypeDto;
import org.myproject.hello.entity.Pet;
import org.myproject.hello.service.PetService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/pets")
@RequiredArgsConstructor
public class PetController {

    private final PetService petService;

    @GetMapping
    public List<PetDto> list() {
        return petService.findPets().stream()
                .map(p -> {
                    PetTypeDto petType = new PetTypeDto(
                            p.getType().getId(),
                            p.getType().getName()
                    );
                    OwnerDto owner = new OwnerDto(
                            p.getOwner().getId(),
                            p.getOwner().getFirstName(),
                            p.getOwner().getLastName(),
                            p.getOwner().getAddress(),
                            p.getOwner().getCity(),
                            p.getOwner().getTelephone()
                    );
                    return new PetDto(
                            p.getId(),
                            p.getName(),
                            p.getBirthDate(),
                            petType,
                            owner
                    );
                })
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public PetDto detail(@PathVariable Long id) {
        Pet p = petService.findPet(id);

        PetTypeDto petType = new PetTypeDto(
                p.getType().getId(),
                p.getType().getName()
        );
        OwnerDto owner = new OwnerDto(
                p.getOwner().getId(),
                p.getOwner().getFirstName(),
                p.getOwner().getLastName(),
                p.getOwner().getAddress(),
                p.getOwner().getCity(),
                p.getOwner().getTelephone()
        );
        return new PetDto(
                p.getId(),
                p.getName(),
                p.getBirthDate(),
                petType,
                owner
        );
    }
}
