package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        memberService.join(form);
        return "home";
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
        if(memberService.login(form)){
            session.setAttribute("loginEmail", form.getEmail());
            return "home";
        }else{
            return "members/loginMemberForm";
        }
    }
}