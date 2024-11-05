package org.myproject.hello.service;

import lombok.RequiredArgsConstructor;
import org.myproject.hello.entity.Member;
import org.myproject.hello.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findMember(String memberId) {
        return verifyMemberExists(memberId);
    }

    private Member verifyMemberExists(String memberId) {
        return memberRepository.findById(memberId).orElseThrow(
                () -> new NoSuchElementException("Member does not exist"));
    }
}
