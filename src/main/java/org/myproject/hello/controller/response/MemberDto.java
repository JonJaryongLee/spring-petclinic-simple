package org.myproject.hello.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberDto {

    private String username;
    private Boolean enabled;
}
