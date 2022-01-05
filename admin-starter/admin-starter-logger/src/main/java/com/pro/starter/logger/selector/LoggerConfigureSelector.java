package com.pro.starter.logger.selector;

import com.pro.starter.logger.config.LoggerProxyAutoConfiguration;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author lijunping on 2022/1/5
 */
public class LoggerConfigureSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{LoggerProxyAutoConfiguration.class.getName()};
    }
}
