package org.myproject.hello.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class PetDto {

    private Long id;
    private String name;
    private LocalDateTime birthDate;
    private PetTypeDto type;
    private OwnerDto owner;
}
