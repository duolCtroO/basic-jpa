package oort.cloud.shop.service;

import oort.cloud.shop.entity.Address;
import oort.cloud.shop.entity.Member;
import oort.cloud.shop.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.beans.beancontext.BeanContextMembershipEvent;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MemberServiceTest {

    private MemberRepository memberRepository = mock(MemberRepository.class);
    private MemberService memberService;

    @BeforeEach
    void init(){
        memberService = new MemberService(memberRepository);
    }

    @Test
    void 회원가입시_회원객체_데이터확인(){
        //given
        Address address = new Address();
        address.setStreet("testStreet");
        address.setCity("testCity");
        address.setZipcode("testZipcode");
        Member member = new Member();
        member.setName("test1");
        member.setAddress(address);

        //when
        doNothing().when(memberRepository).save(member);
        memberService.join(member);

        ArgumentCaptor<Member> memberCaptor = ArgumentCaptor.forClass(Member.class);

        verify(memberRepository).save(
                memberCaptor.capture()
        );
        //then
        Member expectMember = new Member();
        expectMember.setName("test1");
        expectMember.setAddress(address);

        Assertions.assertThat(memberCaptor.getValue())
                .usingRecursiveComparison()
                .isEqualTo(expectMember);
    }

    @Test
    void 회원가입시_이름이_중복되면_IllegalArgumentException발생(){
        //given
        Member member = new Member();
        member.setName("test1");

        //when
        when(memberRepository.existsByName(member.getName())).thenReturn(true);

        //then
        verify(memberRepository, never()).save(member);
        assertThrows(IllegalArgumentException.class, () -> memberService.join(member));
    }
}