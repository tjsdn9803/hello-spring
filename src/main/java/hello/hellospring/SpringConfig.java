package hello.hellospring;
import hello.hellospring.repository.*;
import hello.hellospring.security.SecurityService;
import hello.hellospring.service.BoardService;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SpringConfig {
    private final MemberRepository memberRepository;
    private final SecurityService securityService;
    public SpringConfig(MemberRepository memberRepository, SecurityService securityService, BoardService boardService, BoardRepository boardRepository) {
        this.memberRepository = memberRepository;
        this.securityService = securityService;
    }
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository, securityService);
    }

}