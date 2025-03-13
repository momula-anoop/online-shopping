package com.medplus.online_shopping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.WebRequestHandlerInterceptorAdapter;

import com.medplus.online_shopping.config.RequestInterceptor;

@SpringBootApplication
public class OnlineShoppingApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(OnlineShoppingApplication.class, args);
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		WebMvcConfigurer.super.addInterceptors(registry);
		registry.addInterceptor(new WebRequestHandlerInterceptorAdapter(new RequestInterceptor()));
	}

}
