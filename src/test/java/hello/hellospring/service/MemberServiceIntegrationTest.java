package hello.hellospring.service;

import hello.hellospring.controller.MemberForm;
import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
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
        MemberForm memberForm = new MemberForm();
        memberForm.setName("testName");
        memberForm.setEmail("testEmail@naver.com");
        memberForm.setPassword("testPassword");
        memberForm.setNickname("testNickname");
        //When
        Long saveId = memberService.join(memberForm);
        //Then
        Member findMember = memberRepository.findById(saveId).get();
        assertEquals(memberForm.getName(), findMember.getName());
    }
    @Test
    public void 중복_회원_예외() throws Exception {
        //Given
        MemberForm memberForm1 = new MemberForm();
        memberForm1.setName("testName");
        memberForm1.setEmail("testEmail@naver.com");
        memberForm1.setPassword("testPassword");
        memberForm1.setNickname("testNickname");
        MemberForm memberForm2 = new MemberForm();//member1과 이메일이 같음
        memberForm2.setName("test2Name");
        memberForm2.setEmail("testEmail@naver.com");
        memberForm2.setPassword("test2Password");
        memberForm2.setNickname("test2Nickname");
        MemberForm memberForm3 = new MemberForm();//member1과 닉네임이 같음
        memberForm3.setName("test3Name");
        memberForm3.setEmail("test3Email@naver.com");
        memberForm3.setPassword("test3Password");
        memberForm3.setNickname("testNickname");
        //When
        memberService.join(memberForm1);
        IllegalStateException e1 = assertThrows(IllegalStateException.class,
                () -> memberService.join(memberForm2));//이메일 중복 예외가 발생해야 한다.
        IllegalStateException e2 = assertThrows(IllegalStateException.class,
                () -> memberService.join(memberForm3));//닉네임 중복 예외가 발생해야 한다.
        //Then
        assertThat(e1.getMessage()).isEqualTo("이미 존재하는 회원의 이메일입니다.");
        assertThat(e2.getMessage()).isEqualTo("이미 존재하는 회원의 닉네임입니다.");
    }

    @Test
    public void login() throws Exception{
        //Given
        MemberForm memberForm = new MemberForm();
        memberForm.setName("testName");
        memberForm.setEmail("testEmail@naver.com");
        memberForm.setPassword("testPassword");
        memberForm.setNickname("testNickname");
        Long saveId = memberService.join(memberForm);
        MemberForm loginMemberForm = new MemberForm();
        loginMemberForm.setEmail("testEmail@naver.com");
        loginMemberForm.setPassword("testPassword");
        //When
        Boolean b = memberService.login(loginMemberForm);
        //Then
        assertThat(b).isEqualTo(true);
    }

    @Test
    public void loginNotExistEmail() throws Exception{
        //Given
        MemberForm memberForm = new MemberForm();
        memberForm.setName("testName");
        memberForm.setEmail("testEmail@naver.com");
        memberForm.setPassword("testPassword");
        memberForm.setNickname("testNickname");
        Long saveId = memberService.join(memberForm);
        MemberForm loginMemberForm = new MemberForm();
        loginMemberForm.setEmail("NotExistEmail@naver.com");
        loginMemberForm.setPassword("testPassword");
        //When
        Boolean b = memberService.login(loginMemberForm);
        //Then
        assertThat(b).isEqualTo(false);
    }

    @Test
    public void loginNoMatchPassword() throws Exception{
        //Given
        MemberForm memberForm = new MemberForm();
        memberForm.setName("testName");
        memberForm.setEmail("testEmail@naver.com");
        memberForm.setPassword("testPassword");
        memberForm.setNickname("testNickname");
        Long saveId = memberService.join(memberForm);
        MemberForm loginMemberForm = new MemberForm();
        loginMemberForm.setEmail("testEmail@naver.com");
        loginMemberForm.setPassword("noMatchPassword");
        //When
        Boolean b = memberService.login(loginMemberForm);
        //Then
        assertThat(b).isEqualTo(false);
    }
}
