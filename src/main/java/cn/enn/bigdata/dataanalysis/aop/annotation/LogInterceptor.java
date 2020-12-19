package cn.enn.bigdata.dataanalysis.aop.annotation;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LogInterceptor {
    @Pointcut("@annotation(cn.enn.bigdata.dataanalysis.aop.annotation.PrintMethod)")
    public void printMethodNamePointCut() {
    }

    ;

    @Before("printMethodNamePointCut()")
    public void printDo(JoinPoint joinPoint) {
        log.info("into controller: [{}]", joinPoint.getSignature().getDeclaringType());
    }
}
