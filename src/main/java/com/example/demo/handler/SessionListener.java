package com.example.demo.handler;

import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.repository.model.User;
import com.example.demo.service.UserService;

@Component
public class SessionListener implements HttpSessionListener {

    @Autowired
    private UserService userService;

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        // 세션 만료 또는 종료 시 실행됨
        User user = (User) se.getSession().getAttribute("principal");
        if (user != null) {
            userService.updateUserStatus(user.getUserId(), 0);  // 사용자의 상태를 0으로 업데이트
            System.out.println("세션만료/종료 로 인한 비접속 상태로 변경");
        }
    }
}
