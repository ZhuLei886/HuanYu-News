package com.huanyunews.news.aspect;


import com.google.gson.Gson;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.logging.Logger;

@Aspect
//申明是个spring管理的bean
@Component
public class LogAspect {

    private Logger log = Logger.getLogger("LogAspect");
    private Gson gson = new Gson();
    //申明一个切点 里面是 execution表达式
    @Pointcut("execution(* com.huanyunews.news.controller.*.*(..))")
    private void controllerAspect(){}
    //请求method前打印内容
    @Before(value = "controllerAspect()")
    public void methodBefore(JoinPoint joinPoint){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        //打印请求内容
        log.warning("===============请求内容===============");
        log.warning("请求地址:"+request.getRequestURL().toString());
        log.warning("请求方式:"+request.getMethod());
        log.warning("请求类方法:"+joinPoint.getSignature());
        log.warning("请求类方法参数:"+ Arrays.toString(joinPoint.getArgs()));
        log.warning("===============请求内容===============");
    }
    //在方法执行完结后打印返回内容
    @AfterReturning(returning = "o",pointcut = "controllerAspect()")
    public void methodAfterReturing(Object o ){
        log.info("--------------返回内容----------------");
        log.info("Response内容:"+gson.toJson(o));
        log.info("--------------返回内容----------------");
    }
}
