package org.myproject.hello.controller;

import lombok.RequiredArgsConstructor;
import org.myproject.hello.controller.response.OwnerDto;
import org.myproject.hello.entity.Owner;
import org.myproject.hello.service.OwnerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/owners")
@RequiredArgsConstructor
public class OwnerController {

    private final OwnerService ownerService;

    @GetMapping
    public List<OwnerDto> list() {
        return ownerService.findOwners().stream()
                .map(o -> new OwnerDto(
                        o.getId(),
                        o.getFirstName(),
                        o.getLastName(),
                        o.getAddress(),
                        o.getCity(),
                        o.getTelephone()
                ))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public OwnerDto detail(@PathVariable Long id) {
        Owner owner = ownerService.findOwner(id);
        return new OwnerDto(
                owner.getId(),
                owner.getFirstName(),
                owner.getLastName(),
                owner.getAddress(),
                owner.getCity(),
                owner.getTelephone()
        );
    }
}
