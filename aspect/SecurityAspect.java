package hello.hellospring.aspect;

import antlr.StringUtils;
import hello.hellospring.security.SecurityService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class SecurityAspect {
    @Autowired
    SecurityService securityService;

    @Before("@annotation(tokenRequired)")
    public void authenticateWithToken(TokenRequired tokenRequired){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        String token = request.getHeader("token");
        if(securityService.getClaims(token) == null || !securityService.getAuth(token)){
            throw new IllegalArgumentException("token error@@ claims or subject are null");
        }


    }
}
