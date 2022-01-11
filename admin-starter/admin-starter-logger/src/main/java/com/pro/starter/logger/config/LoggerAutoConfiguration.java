package com.pro.starter.logger.config;

import com.pro.starter.logger.factory.ParseFunctionFactory;
import com.pro.starter.logger.function.support.DefaultFunction;
import com.pro.starter.logger.function.IFunction;
import com.pro.starter.logger.function.IParseFunction;
import com.pro.starter.logger.function.support.DefaultParseFunction;
import com.pro.starter.logger.handler.LoggerHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;

import java.util.List;

/**
 * @author lijunping on 2022/1/5
 */
@Configuration
public class LoggerAutoConfiguration {

    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public LoggerHandler logRecordInterceptor(IFunction iFunction) {
        LoggerHandler interceptor = new LoggerHandler();
        interceptor.setIFunction(iFunction);
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
}
