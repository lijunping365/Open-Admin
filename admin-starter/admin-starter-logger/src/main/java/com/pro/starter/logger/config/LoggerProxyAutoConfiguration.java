package com.pro.starter.logger.config;

import com.pro.starter.logger.annotation.EnableLogger;
import com.pro.starter.logger.factory.ParseFunctionFactory;
import com.pro.starter.logger.function.support.DefaultFunction;
import com.pro.starter.logger.function.IFunction;
import com.pro.starter.logger.function.IParseFunction;
import com.pro.starter.logger.function.support.DefaultParseFunction;
import com.pro.starter.logger.interceptor.LoggerInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportAware;
import org.springframework.context.annotation.Role;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

import java.util.List;

/**
 * 这个类继承 ImportAware 是为了拿到 EnableLogger 上的 packages 属性，这个类使用变量 logRecordAdvisor 和 logRecordInterceptor 装配了 AOP，同时把自定义函数注入到了 logRecordAdvisor 中。
 * @author lijunping on 2022/1/5
 */
@Slf4j
@Configuration
public class LoggerProxyAutoConfiguration implements ImportAware {

    private AnnotationAttributes enableLogger;

    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public LoggerInterceptor logRecordInterceptor(IFunction iFunction) {
        LoggerInterceptor interceptor = new LoggerInterceptor();
        interceptor.setIFunction(iFunction);
        interceptor.setPackages(enableLogger.getString("packages"));
        return interceptor;
    }

    @Bean
    @ConditionalOnMissingBean(IFunction.class)
    public IFunction iFunction(ParseFunctionFactory parseFunctionFactory) {
        return new DefaultFunction(parseFunctionFactory);
    }

    @Bean
    public ParseFunctionFactory parseFunctionFactory(@Autowired List<IParseFunction> parseFunctions) {
        return new ParseFunctionFactory(parseFunctions);
    }

    @Bean
    @ConditionalOnMissingBean(IParseFunction.class)
    public DefaultParseFunction parseFunction() {
        return new DefaultParseFunction();
    }


    @Override
    public void setImportMetadata(AnnotationMetadata importMetadata) {
        this.enableLogger = AnnotationAttributes.fromMap(
                importMetadata.getAnnotationAttributes(EnableLogger.class.getName(), false));
        if (this.enableLogger == null) {
            log.info("@EnableLogger is not present on importing class");
        }
    }
}
