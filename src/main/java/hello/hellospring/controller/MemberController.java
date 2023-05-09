package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());
        member.setNickname(form.getNickname());
        member.setPassword(form.getPassword());
        member.setEmail(form.getEmail());
        member.setUserAuth("active");
        member.setAppendDate("2023_05_09");
        member.setUpdateDate("2023_05_09");//미구형
        System.out.println("controller--member.getIndex() = " + member.getIndex());
        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members =  memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

    @GetMapping("/members/login")
    public String loginForm(){
        return "members/loginMemberForm";
    }

    @PostMapping("/members/login")
    public String login(MemberForm form, HttpSession session) {
        MemberForm loginResult = memberService.login(form);
        if(loginResult != null){
            session.setAttribute("loginEmail", loginResult.getEmail());
            return "redirect:/";
        }else{
            return "members/loginMemberForm";
        }
    }
}