package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RequestMapping("/cash")
@Controller
public class ChargeContainer {
	
	
	// http://localhost:8080/cash/charge
	@GetMapping("/charge")
	public String chargeCash() {
		return "/cash/chargeCash";
	}
	

}
