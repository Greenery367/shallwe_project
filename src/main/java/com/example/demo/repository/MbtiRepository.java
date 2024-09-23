package com.example.demo.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.repository.model.Mbti;

@Mapper
public interface MbtiRepository {
	
	// MBTI 테스트 결과값 뽑기 (name)
	public Mbti selectMbtiByName(String name);
	
	// MBTI 테스트 결과값 뽑기 (id)
	public Mbti selectMbtiById(Integer id);
	
	// 궁합에 따른 MBTI 찾기
	public Integer selectMbtiByCompatibility(@Param("mbtiId") Integer mbtiId,
											@Param("compatibility") Integer compatibility);
	
	// 유저-mbti 정보 저장
	public void addMbtiInfo(@Param("userId")Integer userId, @Param("mbtiId")Integer mbtiId);

	// 최근에 테스트한 정보 가져오기
	public Mbti getRecentInfo(Integer userId);

}
