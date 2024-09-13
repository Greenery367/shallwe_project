package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.ReportDTO;
import com.example.demo.repository.model.User;
import com.example.demo.service.ChatService;
import com.example.demo.service.ReportService;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequiredArgsConstructor
@RequestMapping("/report")
public class ReportController {

	private final ChatService chatService;
	private final UserService userService;
	private final ReportService reportService;
	
	@GetMapping()
	public String matchReportPage(@RequestParam(name="roomId")int id,
			@RequestParam(name="opponentId")int opponentId,
			@RequestParam(name="type")String type, Model model) {
		User opponent = userService.searchByUserId(opponentId);
		model.addAttribute("type",type);
		model.addAttribute("typeId",id);
		model.addAttribute("opponent",opponent);
		return "report/reportPage";
	}
	
	@PostMapping()
	public ResponseEntity<String> postMethodName(ReportDTO dto) {
		dto.setType("match");
		int result = reportService.sendReport(dto);
		if(result != 1) {
			return 
			ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Report submission failed");
		} else {
			// 성공 응답을 반환합니다.
		    return ResponseEntity.ok("Report submitted successfully");			
		}
	}
	
	
}
