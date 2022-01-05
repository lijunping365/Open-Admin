package com.pro.starter.logger.annotation;

import java.lang.annotation.*;

@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Logger {

    /**
     * 操作日志的文本模板
     */
    String success();

    /**
     * 操作日志失败的文本版本
     */
    String fail() default "";

    /**
     * 操作日志的执行人
     */
    String operator() default "";

    /**
     * 操作日志的种类
     */
    String category() default "";

    /**
     * 记录日志的条件
     */
    String condition() default "";
}
