package com.gd.travel.aop;

import com.gd.travel.common.baseEntity.ResultVO;
import com.gd.travel.entity.CompaniesDTO;
import com.gd.travel.utils.HttpsClientUtil;
import com.gd.travel.utils.MockObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
        ResultVO resultVO = ResultVO.getSuccess();
        MethodSignature method = (MethodSignature)point.getSignature();
        Method m = method.getMethod();
        Type genericSuperclass = m.getGenericReturnType();
        Type[] childTypes = ((ParameterizedType) genericSuperclass).getActualTypeArguments();
        // 如果存在二级返回
        if(childTypes.length > 0){
            ParameterizedTypeImpl childType1 = (ParameterizedTypeImpl)childTypes[0];
            Class<?> rawClazz = childType1.getRawType();
            Type[] child2Types = childType1.getActualTypeArguments();
            // 如果是集合类型
            if(List.class.isAssignableFrom(rawClazz) || rawClazz.newInstance() instanceof List
                    || Set.class.isAssignableFrom(rawClazz) || rawClazz.newInstance() instanceof Set){
                List childList = new ArrayList();
                if(child2Types != null && child2Types.length > 0){
                    Type child2Type =  child2Types[0];
                    Class<?> childClazz = Class.forName(child2Type.getTypeName());
                    childList = MockObjectUtil.mockList(childClazz);
                }
                resultVO.setData(childList);
            }
        }
        log.info("通过自动生成对象mock 对象 resultVO = {}",resultVO);
        return resultVO;
    }
}
