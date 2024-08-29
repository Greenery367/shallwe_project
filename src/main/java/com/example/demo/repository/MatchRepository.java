package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.dto.CompatibilityListDTO;

@Mapper
public interface MatchRepository {

	public List<CompatibilityListDTO> getCompatibilityListByMbti(int id);
	
	public int getMbtiIdByUserId(int id);
	
}
