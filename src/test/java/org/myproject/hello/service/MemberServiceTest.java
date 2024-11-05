package org.myproject.hello.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.myproject.hello.entity.Member;
import org.myproject.hello.repository.MemberRepository;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    private static final String MEMBER_USERNAME = "admin123";
    private static final String MEMBER_PASSWORD = "12345678";
    private static final Boolean MEMBER_ENABLE = true;
    private static final String ERROR_MESSAGE = "Member does not exist";

    @InjectMocks
    private MemberService memberService;

    @Mock
    private MemberRepository memberRepository;

    private AutoCloseable mocks;

    @BeforeEach
    void setUp() {
        mocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        mocks.close();
    }

    @Test
    void findMembersTest() {
        // given
        Member member = new Member();
        member.setUsername(MEMBER_USERNAME);
        member.setPassword(MEMBER_PASSWORD);
        member.setEnabled(MEMBER_ENABLE);

        when(memberRepository.findAll()).thenReturn(Arrays.asList(member, member));

        // when
        List<Member> foundMembers = memberService.findMembers();

        // then
        assertThat(foundMembers.size()).isEqualTo(2);
        verify(memberRepository, times(1)).findAll();
    }

    @Test
    void findPartsEmptyTest() {
        // given
        when(memberRepository.findAll()).thenReturn(Collections.emptyList());

        // when
        List<Member> foundMembers = memberService.findMembers();

        // then
        assertThat(foundMembers.size()).isEqualTo(0);
        verify(memberRepository, times(1)).findAll();
    }

    @Test
    void findMemberTest() {
        // given
        Member member = new Member();
        member.setUsername(MEMBER_USERNAME);
        member.setPassword(MEMBER_PASSWORD);
        member.setEnabled(MEMBER_ENABLE);

        when(memberRepository.findById(MEMBER_USERNAME)).thenReturn(Optional.empty());

        // when
        Member foundMember = memberService.findMember(MEMBER_USERNAME);

        // then
        assertThat(foundMember.getUsername()).isEqualTo(MEMBER_USERNAME);
        assertThat(foundMember.getPassword()).isEqualTo(MEMBER_PASSWORD);
    }

    @Test
    void findMemberVerifyTest() {
        // given
        when(memberRepository.findById(MEMBER_USERNAME)).thenReturn(Optional.empty());

        // when
        NoSuchElementException e = assertThrows(NoSuchElementException.class, () -> {
            memberService.findMember(MEMBER_USERNAME);
        });

        // then
        assertThat(e.getMessage()).isEqualTo(ERROR_MESSAGE);
        verify(memberRepository, times(1)).findById(MEMBER_USERNAME);
    }
}