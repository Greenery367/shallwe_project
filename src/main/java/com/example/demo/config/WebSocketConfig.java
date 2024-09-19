package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.example.demo.handler.AlarmHandler;
import com.example.demo.handler.Chat1vs1Handler;
import com.example.demo.handler.HttpSessionHandshakeInterceptor;
import com.example.demo.handler.MatchHandler;
import com.example.demo.handler.WebSocketHandler;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSocket // 웹소켓 활성화
@RequiredArgsConstructor // 만들어둔 WebsocketHandler 의존성 부여
public class WebSocketConfig implements WebSocketConfigurer{
	
	private final WebSocketHandler webSocketHandler;
	private final MatchHandler matchHandler;
	private final AlarmHandler alarmHandler;
	private final Chat1vs1Handler chat1vs1Handler;
	
	// 채팅서버 url설정 and 허용 도메인 지정
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(chat1vs1Handler, "/friend")
		.addInterceptors(new HttpSessionHandshakeInterceptor()).setAllowedOrigins("*");
		
		registry.addHandler(webSocketHandler, "/chat")
		.addInterceptors(new HttpSessionHandshakeInterceptor()).setAllowedOrigins("*");
		
		registry.addHandler(matchHandler, "/match")
		.addInterceptors(new HttpSessionHandshakeInterceptor()).setAllowedOrigins("*");
		
		registry.addHandler(alarmHandler, "/alarm")
		.addInterceptors(new HttpSessionHandshakeInterceptor()).setAllowedOrigins("*");
		
	}
}
