package org.myproject.hello.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class VisitDto {

    private Long id;
    private LocalDateTime date;
    private String description;
    private PetDto pet;
}
