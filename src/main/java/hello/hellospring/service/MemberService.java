package hello.hellospring.service;

import hello.hellospring.controller.MemberForm;
import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
enum userAuth{
    ACTIVE("ACTIVE"), INACTIVE("INACTIVE");
    final private String auth;

    userAuth(String auth) {
        this.auth = auth;
    }
}
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
//    회원가입
    public Long join(MemberForm memberForm){
        //email, nickname 중복 x
        Member member = new Member();
        member.setName(memberForm.getName());
        member.setNickname(memberForm.getNickname());
        member.setPassword(memberForm.getPassword());
        member.setEmail(memberForm.getEmail());
        member.setUserAuth(String.valueOf(userAuth.ACTIVE));
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String formatedNow = now.format(formatter);
        member.setAppendDate(formatedNow);
        member.setUpdateDate(formatedNow);//미구현
        ValidateDuplicateMemberByEmail(member);
        ValidateDuplicateMemberByNickname(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void ValidateDuplicateMemberByNickname(Member member) {//email 중복
        memberRepository.findByNickname(member.getNickname())
            .ifPresent(m -> {
                throw new IllegalStateException("이미 존재하는 회원의 닉네임입니다.");
            });
    }

    private void ValidateDuplicateMemberByEmail(Member member) {//nickname 중복
        memberRepository.findByEmail(member.getEmail())
            .ifPresent(m -> {
                throw new IllegalStateException("이미 존재하는 회원의 이메일입니다.");
            });
    }

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

    public Boolean login(MemberForm memberForm){
        Optional<Member> optionalMember = memberRepository.findByEmail(memberForm.getEmail());
        if(optionalMember.isPresent()){
            Member member = optionalMember.get();
            if(member.getPassword().equals(memberForm.getPassword())){
                System.out.println("login success!!");
                return true;
            }else{
                System.out.println("password is not correct!!");
                return false;
            }
        }else{
            System.out.println("email not exist");
            return false;
        }
    }
}
