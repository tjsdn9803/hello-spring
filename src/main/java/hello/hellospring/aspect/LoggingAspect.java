package hello.hellospring.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    @Before("execution(* hello.hellospring.service.*.find*(..))")
    public void loggerBefore(){
        System.out.println("get으로 시작되는 메서드 시작");
    }

    @After("execution(* hello.hellospring.service.*.find*(..))")
    public void loggerAfter(){
        System.out.println("get으로 시작되는 메서드 끝");
    }
    @Around("execution(* hello.hellospring.controller.MemberController.*(..))")
    public Object loggerAround(ProceedingJoinPoint pjp) throws Throwable{
        long beforeTimeMillis = System.currentTimeMillis();
        System.out.println("[UserController] 실행시작 : "
                +pjp.getSignature().getDeclaringTypeName() + ","
                +pjp.getSignature().getName());
        Object result = pjp.proceed();

        long afterTimeMillis = System.currentTimeMillis() - beforeTimeMillis;
        System.out.println("[UserController] 실행완료 : " + afterTimeMillis + "밀리초 소요"
                +pjp.getSignature().getDeclaringTypeName() + ","
                +pjp.getSignature().getName());
        return  result;
    }


}
