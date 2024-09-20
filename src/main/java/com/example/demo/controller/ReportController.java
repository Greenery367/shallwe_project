package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.ReportDTO;
import com.example.demo.repository.model.User;
import com.example.demo.service.ChatService;
import com.example.demo.service.ReportService;
import com.example.demo.service.UserService;

import lombok.RequiredArgsConstructor;



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
	public String postMethodName(ReportDTO dto,Model model) {
		dto.setType("match");
		int result = reportService.sendReport(dto);
		if(result != 1) {
			model.addAttribute("fail",true);
			return "report/reportPage";
		} else {
			model.addAttribute("success",true);
		    return "report/reportPage";			
		}
	}
	
	
}
