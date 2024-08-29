package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.DashBoardService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/account/dashboard")
@RequiredArgsConstructor
public class DashBoardController {

	private final HttpSession session;
	private final DashBoardService dashBoardService;
	
	
	
	
}
