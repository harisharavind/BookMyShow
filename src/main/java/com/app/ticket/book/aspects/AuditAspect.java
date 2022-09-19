package com.app.ticket.book.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Slf4j
@Aspect
@Component
public class AuditAspect {

    @Around("execution(* com.app.ticket.book..*(..))")
    public Object auditAround(ProceedingJoinPoint joinPoint) throws Throwable {
        final StopWatch stopWatch = new StopWatch();

        stopWatch.start();
        Object result = joinPoint.proceed();
        stopWatch.stop();

        MDC.put("method", joinPoint.getSignature().toString());
        MDC.put("duration in ms",String.valueOf(stopWatch.getTotalTimeMillis()));
        log.info("Aspects Execution Time");

        return result;
    }
}
