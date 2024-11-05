package org.myproject.hello.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VetDto {

    private Long id;
    private String firstName;
    private String lastName;
}
