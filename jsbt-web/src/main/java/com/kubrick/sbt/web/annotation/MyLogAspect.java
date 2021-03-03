package com.kubrick.sbt.web.annotation;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author k
 * 1、@Aspect 表明这是一个切面类
 * 2、PointCut表示这是一个切点，@annotation表示这个切点切到一个注解上，后面带该注解的全类名
 * 切面最主要的就是切点，所有的故事都围绕切点发生
 * logPointCut()代表切点名称
 */
@Slf4j
@Aspect
@Component
public class MyLogAspect {

    @Pointcut("@annotation(com.kubrick.sbt.web.annotation.MyLog)")
    public void logPointCut() {
    }

    ;

    /**
     * 3. 环绕通知
     *
     * @param joinPoint
     */
    @Around("logPointCut()")
    public void logAround(ProceedingJoinPoint joinPoint) {
        // 获取方法名称
        String methodName = joinPoint.getSignature().getName();
        // 获取入参
        Object[] param = joinPoint.getArgs();

        StringBuilder sb = new StringBuilder();
        for (Object o : param) {
            sb.append(o + "; ");
        }
        log.info("进入[{}]方法,参数为:{}", methodName, sb.toString());
        // 继续执行方法
        try {
            joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        log.info("{}-方法执行结束", methodName);
    }

}
