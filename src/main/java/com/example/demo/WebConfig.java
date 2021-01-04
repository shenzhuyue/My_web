package com.example.demo;

import com.example.demo.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer
{

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(new LoginInterceptor());
        registration.addPathPatterns("/addPost");
        registration.addPathPatterns("/comments.action");
        registration.addPathPatterns("/addGood/*");
        registration.addPathPatterns("/subscribe/*");
        registration.addPathPatterns("/mainPage/subscribed");
    }
}
