package com.example.demo.service;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSocket // 웹소켓 활성화
@RequiredArgsConstructor // 만들어둔 WebsocketHandler 의존성 부여
public class WebServer implements WebSocketConfigurer{
	
	private final WebSocketHandler webSocketHandler;
	
	// 채팅서버 url설정 and 허용 도메인 지정
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(webSocketHandler, "/chat")
		.addInterceptors(new HttpSessionHandshakeInterceptor()).setAllowedOrigins("*");
		
		registry.addHandler(webSocketHandler, "/match")
		.addInterceptors(new HttpSessionHandshakeInterceptor()).setAllowedOrigins("*");
	}
}
