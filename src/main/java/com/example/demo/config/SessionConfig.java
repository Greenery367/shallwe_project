package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.handler.SessionListener;

import jakarta.servlet.http.HttpSessionListener;

@Configuration
public class SessionConfig {

    @Bean
    public HttpSessionListener httpSessionListener() {
        return new SessionListener(); // 위에서 작성한 리스너 클래스
    }
}