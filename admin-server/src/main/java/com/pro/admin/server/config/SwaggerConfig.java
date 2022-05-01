package com.pro.admin.server.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger配置
 *
 * @author lijunping
 */
@Configuration
@EnableSwagger2
@EnableKnife4j
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {

  @Bean(value = "serverApi")
  @Order(value = 1)
  public Docket groupRestApi() {
    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(this.groupApiInfo())
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.pro"))
        .paths(PathSelectors.any())
        .build();
  }

  private ApiInfo groupApiInfo() {
    return new ApiInfoBuilder()
        .title("分布式用户认证系统")
        .description("<div style='font-size:14px;color:red;'>系统应用微服务 RESTful APIs</div>")
        .termsOfServiceUrl("https://github.com/lijunping365")
        .contact(new Contact("lijunping365@gmail", "", ""))
        .version("1.0")
        .build();
  }

}

