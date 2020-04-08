package com.gd.travel.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : GD
 * @date :2020/3/31 : 13:44
 */
@Aspect
@Component
@Slf4j
public class ReturnAop{


    @Pointcut("execution(* com.gd.travel.controller..*.*(..))")
    public void returnPoint(){}

    @Around(value = "returnPoint()")
    public Object doAfterReturning(ProceedingJoinPoint point) throws Throwable {
       return point.proceed();
    }

    @AfterReturning(pointcut = "returnPoint()",returning = "ret")
    public Object doAfterReturning(Object ret) {
        // 处理完请求，返回内容
        log.info("result info,{} xxx,{}",ret);
        return ret;
    }

}
