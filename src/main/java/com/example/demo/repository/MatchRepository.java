package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.dto.CompatibilityListDTO;
import com.example.demo.dto.MbtiDTO;

@Mapper
public interface MatchRepository {

	public List<CompatibilityListDTO> getCompatibilityListByMbti(int id);
	
	public Integer getMbtiIdByUserId(int id);
	
	public MbtiDTO getMbtiNameByMbtiId(int id);
}
