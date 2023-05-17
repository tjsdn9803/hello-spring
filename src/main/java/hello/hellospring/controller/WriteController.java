package hello.hellospring.controller;

import hello.hellospring.security.SecurityService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WriteController {


    @GetMapping("/write")
    public String writeForm(){
        return "writeForm";
    }

    @PostMapping("/write")
    public String write(MemberForm form){

    }
}
