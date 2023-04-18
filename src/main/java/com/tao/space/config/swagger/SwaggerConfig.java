package com.tao.space.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(new ApiInfoBuilder()
                        .description("道")
                        .title("道")
                        .version("版本 1.0.0")
                        .build())
                .groupName("道")
                .enable(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.tao.space"))
                .paths(PathSelectors.any())
                .build();
    }
}
