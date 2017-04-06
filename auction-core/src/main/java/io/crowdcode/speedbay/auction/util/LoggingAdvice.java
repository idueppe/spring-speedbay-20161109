package io.crowdcode.speedbay.auction.util;


import io.crowdcode.speedbay.common.AnsiColor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Slf4j
@Aspect
public class LoggingAdvice {

    @Around("execution(* io.crowdcode.speedbay.auction.service.*.*(..))")
    public Object doLogging(ProceedingJoinPoint pjp) throws Throwable {
        log.debug(AnsiColor.blue("Starting method {}"), pjp.toString());

        Object returnValue = pjp.proceed();
        return returnValue;
    }

}
