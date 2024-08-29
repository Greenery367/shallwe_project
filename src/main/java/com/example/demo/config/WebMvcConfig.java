package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

	// 코드추가
	// C:\Users\GGG\Documents\Lightshot\a.png <-- 서버 컴퓨터상에 실제 이미지 경로지만
	// 프로젝트 상에서(클라이언트가 HTML 소스로 보이는 경로는) /images/uploads/**
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/images/uploads/**")
		.addResourceLocations("file:\\C:\\work_spring\\upload/");
		registry.addResourceHandler("/images/imoticon/**")
		.addResourceLocations("file:\\C:\\work_spring\\imoticon/");
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
