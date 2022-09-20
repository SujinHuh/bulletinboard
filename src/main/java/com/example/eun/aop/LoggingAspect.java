package com.example.eun.aop;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

//import java.util.Set;

/**
 * LoggingAspect
 * 각 서비스 계층마다 진입과 종료의 로그를 실행 시켜주는 AOP
 *
 */


@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class LoggingAspect {

//    private final Set<String> excludeControllerNames = Set.of("healthCheck"); // 특정 클래스 명과 메소드명은 로그를 남기지 않고 SKIP 합니다.
//    private final Set<String> excludeServiceNames = Set.of();
//    private final Set<String> excludeRepositoryNames = Set.of();
//    private final Set<String> excludeValidatorNames = Set.of();
//    private final Set<String> excludeHandlerNames = Set.of("MessageHandler");

    /**
     * 모든 컨트롤러 시작과 종료 부분에 로그를 남깁니다.
     */
    @Around("execution(* com.example.eun.process.*.action.*Action.*(..))")
    public Object controllerLogging(ProceedingJoinPoint joinPoint) throws Throwable {
        String className = joinPoint.getTarget().getClass().getCanonicalName();
        String methodName = joinPoint.getSignature().getName();

//        if (excludeControllerNames.contains(methodName) || excludeControllerNames.contains(className)) {
//            return joinPoint.proceed();
//        }

        log.info("--- controller 컨트롤러 요청이 시작 되었습니다. START : " + className + "." + methodName);
        Object obj = joinPoint.proceed();
        log.info("--- controller 컨트롤러 요청이 종료 되었습니다. END : " + className + "." + methodName);
        return obj;
    }

    /**
     * 모든 서비스 시작과 종료 부분에 로그를 남깁니다.
     */
    @Around("execution(* com.example.eun.process.*.service.*Service.*(..))")
    public Object serviceLogging(ProceedingJoinPoint joinPoint) throws Throwable {
        String className = joinPoint.getTarget().getClass().getCanonicalName();
        String methodName = joinPoint.getSignature().getName();

//        if (excludeServiceNames.contains(methodName) || excludeServiceNames.contains(className)) {
//            return joinPoint.proceed();
//        }

        log.info("--- service 서비스 요청이 시작 되었습니다. START : " + className + "." + methodName);
        Object obj = joinPoint.proceed();
        log.info("--- service 서비스 요청이 종료 되었습니다. END : " + className + "." + methodName);
        return obj;
    }

    /**
     * 모든 레파지토리 시작과 종료 부분에 로그를 남깁니다.
     */
    @Around("execution(* com.example.eun.process.*.dao.*Dao.*(..))")
    public Object repositoryLogging(ProceedingJoinPoint joinPoint) throws Throwable {
        String className = joinPoint.getTarget().getClass().getCanonicalName();
        String methodName = joinPoint.getSignature().getName();

//        if (excludeRepositoryNames.contains(methodName) || excludeRepositoryNames.contains(className)) {
//            return joinPoint.proceed();
//        }

        log.info("--- repository 레파지토리 요청이 시작 되었습니다. START : " + className + "." + methodName);
        Object obj = joinPoint.proceed();
        log.info("--- repository 레파지토리 요청이 종료 되었습니다. END : " + className + "." + methodName);
        return obj;
    }

    /**
     * 모든 Handler 시작과 종료 부분에 로그를 남깁니다.
     */
    @Around("execution(* com.example.eun.handler.*Handler.*(..))")
    public Object handlerLogging(ProceedingJoinPoint joinPoint) throws Throwable {
        String className = joinPoint.getTarget().getClass().getCanonicalName();
        String methodName = joinPoint.getSignature().getName();

//        if (excludeHandlerNames.contains(methodName) || excludeHandlerNames.contains(className)) {
//            return joinPoint.proceed();
//        }

        log.info("--- handler 핸들러가 시작 되었습니다. START : " + className + "." + methodName);
        Object obj = joinPoint.proceed();
        log.info("--- handler 핸들러가 종료 되었습니다. END : " + className + "." + methodName);
        return obj;
    }
}
