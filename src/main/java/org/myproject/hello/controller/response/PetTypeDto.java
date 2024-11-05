package org.myproject.hello.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PetTypeDto {

    private Long id;
    private String name;
}
