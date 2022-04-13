package com.fish.community.demo.config;


import com.fish.community.demo.interceptor.LoginInterceptor;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/login/**",
                        "/register/**",
                        "/webjars/**",      //swagger-ui的相关资源
                        "/swagger-ui.html/**",
                        "/**/swagger-resources/**",
                        "/error",
                        "/favicon.ico",
                        "/file/image/**"
                );
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/file/**").addResourceLocations("file:/root/file/");
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/file");
    }
}
