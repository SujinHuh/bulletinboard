package com.eun.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**********************************************************************************************
 * @FileName  : MvcConfig.java
 * @Date      : 2019-07-26
 * @Author    : kuk
 * @설명      : MVC를 위한 설정 파일
 **********************************************************************************************/
@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "com.eun")
public class MvcConfig implements WebMvcConfigurer {

    // 정적 파일 경로
    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
            "classpath:/resources/**", "classpath:/static/", "classpath:/templates/"  // TODO 개발 완료 후 아래로 변경
//            "classpath:/resources/", "classpath:/static/"
    };

    @Override	// 정적 리소스 핸들러
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
    }

//    @Override	// 정적 컨텐츠 처리 설정
//    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
////        configurer.enable();
//    }

    @Bean    // 뷰리졸버 설정
    public ViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/templates");
        viewResolver.setSuffix(".html");
        viewResolver.setCache(false);
        return viewResolver;
    }

}