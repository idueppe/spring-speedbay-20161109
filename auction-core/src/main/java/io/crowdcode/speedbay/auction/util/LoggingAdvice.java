package io.crowdcode.speedbay.auction.util;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import static io.crowdcode.speedbay.common.AnsiColor.yellow;

/**
 * @author Ingo Düppe (Crowdcode)
 */
@Slf4j
@Aspect
public class LoggingAdvice {

    @Around("execution(* io.crowdcode.speedbay.auction.service.*.*(..))")
    public Object doProfiling(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        Object returnValue = pjp.proceed();
        log.info(yellow("Call duration was {} ms."), (System.currentTimeMillis()-start));
        return returnValue;
    }


}
