package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.MbtiRepository;
import com.example.demo.repository.model.Mbti;

@Service
public class MbtiService {
	
	@Autowired
	private MbtiRepository mbtiRepository;
	
	// DI - 의존성 주입
	public MbtiService(MbtiRepository mbtiRepository) {
		this.mbtiRepository = mbtiRepository;
	}
	
	// MBTI 결과값 찾기
	public Mbti selectMbtiByName(String name) {
		Mbti resultMbti = mbtiRepository.selectMbtiByName(name);
		return resultMbti;
	}
	
	public Mbti selectMbtiByCompatibility(Integer compatibility) {
		Integer matchedMbtiId = mbtiRepository.selectMbtiByCompatibility(compatibility);
		System.out.println("로깅1:"+matchedMbtiId);
		Mbti matchedMbti = mbtiRepository.selectMbtiById(matchedMbtiId);
		System.out.println("로깅2:"+matchedMbti);
		return matchedMbti;
	}
}
