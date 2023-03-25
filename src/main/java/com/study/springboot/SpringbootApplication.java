package com.study.springboot;

import com.study.springboot.basic.utils.LoginInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Slf4j
@SpringBootApplication
@EnableScheduling
@EnableCaching
@MapperScan(basePackages = {"com.study.springboot.*.mapper"})
public class SpringbootApplication extends WebMvcConfigurerAdapter implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }

    /**
     * 必须登录才能访问主页
     * @param registry 拦截器注册中心
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        InterceptorRegistration registration = registry.addInterceptor(new LoginInterceptor());
        registration.addPathPatterns("/**");
        registration.excludePathPatterns("/**/*login*");
        registration.excludePathPatterns("/**/*regist*");
        registration.excludePathPatterns("/js/**");
        registration.excludePathPatterns("/img/**");
        super.addInterceptors(registry);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("============================================================== STARTED ==============================================================");
    }

}
