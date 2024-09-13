package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.FAQDTO;
import com.example.demo.dto.FrequeDTO;

@Mapper
public interface CsRepository {
	public int countFreq();
	public int countFAQ();
	public FAQDTO readFAQById(int id);
	public FrequeDTO readFreqById(int id);
	public List<FrequeDTO> findAllFreq(@Param ("limit") int limit, @Param("offset") int offset);
	public List<FAQDTO> findAllFAQ(@Param ("limit") int limit, @Param("offset") int offset);
	public int insert(FAQDTO dto);
	public int updateFAQ(@Param("id") int id, @Param("title") String title, @Param("content") String content);
}
