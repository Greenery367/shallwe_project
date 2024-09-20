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
	
	// 궁합도로 mbti 찾기
	public Mbti selectMbtiByCompatibility(Integer compatibility) {
		Integer matchedMbtiId = mbtiRepository.selectMbtiByCompatibility(compatibility);
		Mbti matchedMbti = mbtiRepository.selectMbtiById(matchedMbtiId);
		return matchedMbti;
	}
	
	// MBTI 정보 - 저장하기
	public void setMbtiAndUserInfo(Integer userId, Integer mbtiId) {
		mbtiRepository.addMbtiInfo(userId,mbtiId);
		return;
	}
}
