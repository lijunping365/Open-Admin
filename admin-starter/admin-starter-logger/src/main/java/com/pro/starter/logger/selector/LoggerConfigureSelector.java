package com.pro.starter.logger.selector;

import com.pro.starter.logger.aspect.LogAnnotationAop;
import com.pro.starter.logger.config.LoggerAutoConfiguration;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author lijunping on 2022/1/5
 */
public class LoggerConfigureSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{
            LogAnnotationAop.class.getName(),
            LoggerAutoConfiguration.class.getName()
        };
    }
}
