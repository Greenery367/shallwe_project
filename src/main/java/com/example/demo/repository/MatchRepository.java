package com.example.demo.repository;

import java.util.List;

import com.example.demo.dto.CompatibilityListDTO;

public interface MatchRepository {

	public List<CompatibilityListDTO> getCompatibilityListByMbti(int id);
	// todo mbti id 받아오기 만들기
	
}
