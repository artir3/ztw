package com.arma.ztw.restrezerv;

import io.swagger.annotations.Api;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class RestrezervApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestrezervApplication.class, args);
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.arma.ztw.restrezerv.controllers"))
//                .apis(RequestHandlerSelectors.any())
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any())
//                .paths(regex("/users.*"))
                .build()
                .pathMapping("/")
//                .apiInfo(metaData())
//                .useDefaultResponseMessages(false)
                ;
    }

    private ApiInfo metaData(){
        return new ApiInfo(
                "Spring Boot REST API",
                "Spring Boot REST API for restrezerv",
                "1.0",
                "Terms of service",
                new Contact("Artur Markowski", "test", "markowski.artur@outlook.com").toString(),
                "sure","http://sure.sure");
    }
}
