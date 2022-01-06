package com.pro.starter.logger.annotation;

import com.pro.starter.logger.selector.LoggerConfigureSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author lijunping on 2022/1/5
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(LoggerConfigureSelector.class)
public @interface EnableLogger {

}
