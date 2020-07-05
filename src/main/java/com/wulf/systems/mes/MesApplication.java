package com.wulf.systems.mes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@SpringBootApplication
@EnableSwagger2
public class MesApplication {

    public static void main(String[] args) {
        SpringApplication.run(MesApplication.class, args);
    }

    @Bean
    public Docket swaggerConfigurations(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.ant("/api/**"))
                .apis(RequestHandlerSelectors.basePackage("com.wulf.systems.mes"))
                .build()
                .apiInfo(apiDetails());
    }
    private ApiInfo apiDetails(){
        return new ApiInfo(
                "Mes API",
                "Products/Order API",
                "1.0",
                "Under Development",
                new springfox.documentation.service.Contact("Fawad Bin Tariq","https://fawad.netlify.app/","fawad_12@outlook.com"),
                "API Liscence",
                "https://fawad.netlify.app/",
                Collections.emptyList()
        );
    }
}
