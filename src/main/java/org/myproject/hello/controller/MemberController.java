package org.myproject.hello.controller;

import lombok.RequiredArgsConstructor;
import org.myproject.hello.controller.response.MemberDto;
import org.myproject.hello.entity.Member;
import org.myproject.hello.service.MemberService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    public List<MemberDto> list() {
        return memberService.findMembers().stream()
                .map(m -> new MemberDto(
                        m.getUsername(),
                        m.getEnabled()
                ))
                .collect(Collectors.toList());
    }

    @GetMapping("/{username}")
    public MemberDto detail(@PathVariable String username) {
        Member member = memberService.findMember(username);
        return new MemberDto(
                member.getUsername(),
                member.getEnabled()
        );
    }
}
