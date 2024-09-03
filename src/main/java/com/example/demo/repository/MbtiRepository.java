package com.example.demo.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.repository.model.Mbti;

@Mapper
public interface MbtiRepository {
	
	// MBTI 테스트 결과값 뽑기 (name)
	public Mbti selectMbtiByName(String name);
	
	// MBTI 테스트 결과값 뽑기 (id)
	public Mbti selectMbtiById(Integer id);
	
	// 궁합에 따른 MBTI 찾기
	public Integer selectMbtiByCompatibility(Integer compatibility);

}
