package com.netty.log.aspect;

import cn.hutool.core.date.DateTime;
import com.netty.common.config.MQUtils;
import com.netty.common.constants.Topic;
import com.netty.common.entity.LogInfo;
import com.netty.log.annotation.Log;
import com.netty.log.constants.BusinessStatus;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * 操作日志记录处理
 *
 * @author ruoyi
 */
@Aspect
@Component
public class LogAspect {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private MQUtils mqUtils;

    //    aop切面的实现??  在注解上加上切点，即切入带有此注解的方法
    @Pointcut("@annotation(com.netty.log.annotation.Log)")
    public void logAspect() {
    }

    /**
     *
     * 处理完请求后执行
     *
     * @param joinPoint 切点
     */
    @AfterReturning("logAspect()")
    public void doAfterReturning(JoinPoint joinPoint) throws Throwable {

        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        Log annotation = method.getAnnotation(Log.class);

        LogInfo loginInfo = new LogInfo();
        loginInfo.setOperIp(request.getRemoteAddr());
        loginInfo.setOperUrl(request.getRequestURI());
        loginInfo.setOperTime(new DateTime());
        loginInfo.setOperStatus(BusinessStatus.SUCCESS.ordinal());
        loginInfo.setBusinessType(annotation.businessType());
        loginInfo.setTitle(annotation.title());
        loginInfo.setOperatorType(annotation.operatorType().ordinal());
        loginInfo.setRequestMethod(methodSignature.getMethod().getName());

        System.out.println("这里的消息有没有发送呢");
        mqUtils.MessageSend(Topic.LogTopic,loginInfo);
    }

    /**
     * 拦截异常操作
     * 还没写
     * @param joinPoint 切点
     * @param e         异常
     */
}
