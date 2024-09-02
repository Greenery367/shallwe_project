package com.example.demo.config;

<<<<<<< HEAD
=======
import org.springframework.beans.factory.annotation.Autowired;
>>>>>>> lee2
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
<<<<<<< HEAD
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

=======
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demo.handler.AuthInterceptor;

import ch.qos.logback.classic.spi.ConfiguratorRank;
>>>>>>> lee2
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
<<<<<<< HEAD
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
=======
public class WebMvcConfig implements WebMvcConfigurer{

	@Autowired
	private final AuthInterceptor authInterceptor;
	// 우리가 만들어 놓은 AuthInterceptor 를 등록해야 함. 
	
		@Override
		public void addInterceptors(InterceptorRegistry registry) {
			registry.addInterceptor(authInterceptor)
				.addPathPatterns("/admin/advertise");
		}
		
		// 코드추가 
		// C:\Users\GGG\Documents\Lightshot\a.png <-- 서버 컴퓨터상에 실제 이미지 경로지만 
		// 프로젝트 상에서(클라이언트가 HTML 소스로 보이는 경로는) /images/uploads/**
		@Override
		public void addResourceHandlers(ResourceHandlerRegistry registry) {
			registry.addResourceHandler("/images/**")
			.addResourceLocations("file:\\C:\\work_spring\\upload/");
		}
		
		
		@Bean // IoC 대상(싱글톤 처리) 
		PasswordEncoder passwordEncoder() {
			return new BCryptPasswordEncoder();
		}	
>>>>>>> lee2
}
