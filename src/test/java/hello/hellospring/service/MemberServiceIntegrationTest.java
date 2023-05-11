package hello.hellospring.service;

import hello.hellospring.controller.MemberForm;
import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class MemberServiceIntegrationTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Test
    public void 회원가입() throws Exception {
        //Given
        Member member = new Member();
        member.setName("testName");
        member.setEmail("testEmail@naver.com");
        member.setPassword("testPassword");
        member.setNickname("testNickname");
        //When
        Long saveId = memberService.join(member);
        //Then
        Member findMember = memberRepository.findById(saveId).get();
        assertEquals(member.getName(), findMember.getName());
    }
    @Test
    public void 중복_회원_예외() throws Exception {
        //Given
        Member member1 = new Member();
        member1.setName("testName");
        member1.setEmail("testEmail@naver.com");
        member1.setPassword("testPassword");
        member1.setNickname("testNickname");
        Member member2 = new Member();//member1과 이메일이 같음
        member2.setName("test2Name");
        member2.setEmail("testEmail@naver.com");
        member2.setPassword("test2Password");
        member2.setNickname("test2Nickname");
        Member member3 = new Member();//member1과 닉네임이 같음
        member3.setName("test3Name");
        member3.setEmail("test3Email@naver.com");
        member3.setPassword("test3Password");
        member3.setNickname("testNickname");
        //When
        memberService.join(member1);
        IllegalStateException e1 = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));//이메일 중복 예외가 발생해야 한다.
        IllegalStateException e2 = assertThrows(IllegalStateException.class,
                () -> memberService.join(member3));//닉네임 중복 예외가 발생해야 한다.
        //Then
        assertThat(e1.getMessage()).isEqualTo("이미 존재하는 회원의 이메일입니다.");
        assertThat(e2.getMessage()).isEqualTo("이미 존재하는 회원의 닉네임입니다.");
    }

    @Test
    public void login() throws Exception{
        //Given
        Member member = new Member();
        member.setName("testName");
        member.setEmail("testEmail@naver.com");
        member.setPassword("testPassword");
        member.setNickname("testNickname");
        MemberForm memberForm = new MemberForm();
        memberForm.setEmail("testEmail@naver.com");
        memberForm.setPassword("testPassword");
        //When
        MemberForm m = memberService.login(memberForm);
        //Then
        m.
    }
}
