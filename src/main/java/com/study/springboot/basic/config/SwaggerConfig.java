package com.study.springboot.basic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * @author xiaolh
 * @date 2020/3/9
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket docketConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.study.springboot"))
                .build()
                .apiInfo(apiInfoConfig());
    }

    @Bean
    public ApiInfo apiInfoConfig(){
        return new ApiInfo("接口文档","使用 rest 风格","V 1.0","http://127.0.0.1:8080/index",new Contact("xiaolh","https://github.com/xiaolh","acexlh@live.com"),"licence","http://127.0.01", Collections.EMPTY_LIST);
    }

}
