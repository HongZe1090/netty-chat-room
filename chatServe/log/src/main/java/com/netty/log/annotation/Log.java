package com.netty.log.annotation;

import com.netty.log.constants.BusinessType;
import com.netty.log.constants.OperatorType;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 自定义操作日志记录注解
 * 
 * @author ruoyi
 *
 */
@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log
{
    /**
     * 模块
     */
    String title() default "";

    /**
     * 功能
     */
    String businessType() default BusinessType.OTHER;

    /**
     * 操作人类别
     */
    OperatorType operatorType() default OperatorType.ADMIN;
}
