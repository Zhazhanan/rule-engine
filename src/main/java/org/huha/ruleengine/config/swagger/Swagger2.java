package org.huha.ruleengine.config.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.sql.Timestamp;

@Configuration
@EnableSwagger2
public class Swagger2 {

    @Value("${swagger.enable}")
    private boolean enableSwagger;

    @Bean
    public Docket inerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(enableSwagger)
                .directModelSubstitute(Timestamp.class, Long.class)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/api/**/v1/**"))
                .build()
                .groupName("规则引擎接口文档V1.0")
                .pathMapping("/")
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("规则引擎系统 RESTful APIs")
                .description("规则引擎接口文档")
                .version("1.0")
                .build();
    }

}
