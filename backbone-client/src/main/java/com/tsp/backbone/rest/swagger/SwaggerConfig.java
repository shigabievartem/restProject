package com.tsp.backbone.rest.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket postApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("backbone-rest-api")
                .apiInfo(generateApiInfo())
                .select()
                .paths(PathSelectors.regex("/api.*"))
                .build();
    }

    private ApiInfo generateApiInfo() {
        return new ApiInfoBuilder()
                .title("Backbone rest api")
                .description("Шаблон для создания REST сервисов")
                .version("1.0")
                .build();

    }
}
