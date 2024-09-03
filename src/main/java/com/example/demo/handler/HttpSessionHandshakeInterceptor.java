package com.example.demo.handler;
	
import java.util.Map;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import com.example.demo.dto.TestUser;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class HttpSessionHandshakeInterceptor implements HandshakeInterceptor{

	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Map<String, Object> attributes) throws Exception {
			if (request instanceof ServletServerHttpRequest) {
            HttpServletRequest servletRequest = ((ServletServerHttpRequest) request).getServletRequest();
            HttpSession httpSession = servletRequest.getSession(false); // 세션이 없으면 null 반환
            if (httpSession != null) {
                // HttpSession에서 저장된 Principal 객체를 가져옴
                TestUser principal = (TestUser)httpSession.getAttribute("principal");
                if (principal != null) {
                	String key = (String)httpSession.getAttribute("chat");
                    // WebSocketSession의 attributes에 저장
                    attributes.put("principal", principal);
                    attributes.put("key", key);
                }
            }
        }
		return true;
	}

	@Override
	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Exception exception) {
		
	}

}
