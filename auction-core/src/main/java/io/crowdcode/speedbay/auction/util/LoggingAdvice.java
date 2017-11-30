package io.crowdcode.speedbay.auction.util;

import io.crowdcode.speedbay.common.AnsiColor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingAdvice {

    @Around("execution(* io.crowdcode.speedbay.auction.service.*.*(..))")
    public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable {
        log.info(AnsiColor.purple("Starting method {}"), pjp.toString());
        Object returnValue = pjp.proceed();
        // caching oder irgendwas anderes mit dem Rückgabewert machen
        return returnValue;
    }

}
