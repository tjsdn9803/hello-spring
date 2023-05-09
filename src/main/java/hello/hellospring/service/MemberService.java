package hello.hellospring.service;

import hello.hellospring.controller.MemberForm;
import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
//    회원가입
    public Long join(Member member){
        //email, nickname 중복 x
        ValidateDuplicateMemberByEmail(member);
        ValidateDuplicateMemberByNickname(member);
        System.out.println("service----member.getIndex() = " + member.getIndex());
        memberRepository.save(member);
        return member.getId();
    }

    private void ValidateDuplicateMemberByNickname(Member member) {//email 중복
        memberRepository.findByNickname(member.getNickname())
            .ifPresent(m -> {
                throw new IllegalStateException("이미 존재하는 닉네임입니다.");
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
        Optional<Member> optionalMember = memberRepository.findByEmail(memberForm.getEmail());
        if(optionalMember.isPresent()){
            Member member = optionalMember.get();
            if(member.getPassword().equals(memberForm.getPassword())){
                MemberForm form = MemberForm.toMemberForm(member);
                System.out.println("login success!!");
                return form;
            }else{
                System.out.println("password is not correct!!");
                return null;
            }
        }else{
            System.out.println("email not exist");
            return null;
        }
    }
}
