package ru.tinkoff.orlovich.commons.aspects;

import io.qameta.allure.Step;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Orlovich Artem
 * <p>
 * Аспект предназначенный для выведения в лог имен запущенных методов помеченнх аннотацией @Step
 * удобно при просмотре логов запуска тестов(не удобно при многопоточном запуске тестов)
 */
@Aspect
public class StepLoggingAspect {

    Logger log = LoggerFactory.getLogger(this.getClass());


    @Pointcut("@annotation(io.qameta.allure.Step)")
    public void withStepAnnotation() {
        //pointcut body, should be empty
    }

    @Pointcut("execution(* *(..))")
    public void anyMethod() {
        //pointcut body, should be empty
    }

    String methodName = null;

    @Before("anyMethod() && withStepAnnotation()")
    public void stepStart(final JoinPoint jp) {
        methodName = jp.getSignature().getName();
        final MethodSignature methodSignature = (MethodSignature) jp.getSignature();
        String stepDescription = methodSignature.getMethod().getAnnotation(Step.class).value();
        log.info("\t--- step: " + methodName);
        log.info("\t\t-- " + stepDescription);
    }

    @AfterThrowing(pointcut = "anyMethod() && withStepAnnotation()", throwing = "e")
    public void stepFailed(final Throwable e) {
        log.error("\t--- step: " + methodName + " FAIL, because exception:" + e.getMessage());
    }

    @AfterReturning(pointcut = "anyMethod() && withStepAnnotation()")
    public void stepStop() {
        log.info("\t--- step: " + methodName + " PASSED");
    }

}
