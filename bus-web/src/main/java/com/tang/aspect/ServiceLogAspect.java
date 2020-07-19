package com.tang.aspect;

import com.tang.annotation.ServiceLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * 日志切面
 *
 * @author tang
 * @since 2020/7/13
 */
@Aspect
@Component
@Slf4j
public class ServiceLogAspect {
    @Pointcut("@annotation(com.tang.annotation.ServiceLog)")
    private void point() {
    }

    @Before("point()&&@annotation(serviceLog)")
    public void before(JoinPoint joinPoint, ServiceLog serviceLog) {
        Object[] args = joinPoint.getArgs();
        log.info(generateLog(serviceLog, Arrays.toString(args)));
    }

    private String generateLog(ServiceLog serviceLog, String args) {
        List<String> elements = List.of(serviceLog.modules(),serviceLog.description(), args);
        return String.join(",", elements);
    }
}