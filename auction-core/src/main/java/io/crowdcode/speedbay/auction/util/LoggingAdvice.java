package io.crowdcode.speedbay.auction.util;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import static io.crowdcode.speedbay.common.AnsiColor.green;

@Slf4j
@Aspect
public class LoggingAdvice {

    @Around("execution(* io.crowdcode.speedbay.auction.service.*.*(..) )")
    public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable {
        log.info(green("starting method {}"),pjp.toString());
        Object retVal = pjp.proceed();
        return retVal;
    }


}