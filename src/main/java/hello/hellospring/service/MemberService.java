package hello.hellospring.service;

import hello.hellospring.controller.MemberForm;
import hello.hellospring.domain.BoardEntity;
import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

enum userAuth{
    ACTIVE("ACTIVE"), INACTIVE("INACTIVE");
    final private String auth;

    userAuth(String auth) {
        this.auth = auth;
    }
}

@Transactional
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final SecurityService securityService;

    @Autowired
    public MemberService(MemberRepository memberRepository, SecurityService securityService) {
        this.memberRepository = memberRepository;
        this.securityService = securityService;
    }
//    회원가입
    Long index = 0L;
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

        member.setId(createMemberId());
        member.setIndex(++index);
        memberRepository.save(member);
        return member.getId();
    }

    public Long createMemberId(){
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());
        Long id = ThreadLocalRandom.current().nextLong(Long.MAX_VALUE);
        while(true){
            if(findMemberById(id).isEmpty()){
                return id;
            }else{
                id = random.nextLong();
            }
        }
    }

    public Optional<Member> findMemberById(Long MemberId){
        return memberRepository.findById(MemberId);
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

    public MemberForm login(MemberForm memberForm){
        NoMemberByEmail(memberForm);
        NoMemberByPassword(memberForm);
        String token = securityService.createToken(memberForm.getEmail(), 2*1000*60);
        memberForm.setJwt(token);
        return memberForm;
    }

    private void NoMemberByEmail(MemberForm memberForm){
        if(memberRepository.findByEmail(memberForm.getEmail())
                .isEmpty()){
            throw new IllegalStateException("이메일이 존재 하지 않습니다.");
        }
    }

    private void NoMemberByPassword(MemberForm memberForm){
        Optional<Member> optionalMember = memberRepository.findByEmail(memberForm.getEmail());
        if(optionalMember.isPresent()){
            Member member = optionalMember.get();
            if(!member.getPassword().equals(memberForm.getPassword())){
                throw new IllegalStateException("비밀번호가 틀립니다.");
            }
        }
    }


}
