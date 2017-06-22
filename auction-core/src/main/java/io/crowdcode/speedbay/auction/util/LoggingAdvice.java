package io.crowdcode.speedbay.auction.util;

import io.crowdcode.speedbay.common.AnsiColor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * Created by idueppe on 22.06.17.
 */
@Slf4j
@Aspect
public class LoggingAdvice {

    @Around("execution(* io.crowdcode.speedbay.auction.service.*.*(..) )")
    public Object applog(ProceedingJoinPoint pjp) throws Throwable {
        log.info(AnsiColor.green("Method {}"), pjp.getSignature().getName());
        Object returnValue = pjp.proceed();
        return returnValue;
    }

}
