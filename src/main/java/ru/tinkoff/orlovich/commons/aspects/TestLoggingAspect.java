package ru.tinkoff.orlovich.commons.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

@Aspect
public class TestLoggingAspect {

    Logger log = LoggerFactory.getLogger(this.getClass());


    @Pointcut("@annotation(org.testng.annotations.Test)")
    public void withTestAnnotation() {
        //pointcut body, should be empty
    }

    @Pointcut("execution(* *(..))")
    public void anyMethod() {
        //pointcut body, should be empty
    }

    String methodName = null;

    @Before("anyMethod() && withTestAnnotation()")
    public void testStart(final JoinPoint jp) {

        methodName = jp.getSignature().getName();
        final MethodSignature methodSignature = (MethodSignature) jp.getSignature();
        String testDescription = methodSignature.getMethod().getAnnotation(Test.class).description();
        log.info("---- start test: " + methodName);
        log.debug("\t--- desc: " + testDescription);
    }

    @AfterThrowing(pointcut = "anyMethod() && withTestAnnotation()", throwing = "e")
    public void testFailed(final Throwable e) {
        log.info("---- test: " + methodName + " FAIL, because exception: " + e.getMessage());
    }

    @AfterReturning(pointcut = "anyMethod() && withTestAnnotation()")
    public void testStop() {
        log.info("---- test: " + methodName + " PASSED");
    }

}
