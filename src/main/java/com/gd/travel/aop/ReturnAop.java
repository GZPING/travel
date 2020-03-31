package com.gd.travel.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
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
    /**
     * 控制器层controller中所有方法的日志
     * @param jp
     * @throws Throwable
     */
    @Before("returnPoint()")
    public void controllerLog(JoinPoint jp) {
        // 获取请求参数
        log.info("error ");
    }

    @AfterReturning(returning = "ret",pointcut = "returnPoint()")
    public Object doAfterReturning(Object ret) {



        // 处理完请求，返回内容
        log.info("result info,{} xxx,{}",ret);
        return ret;
    }

}
