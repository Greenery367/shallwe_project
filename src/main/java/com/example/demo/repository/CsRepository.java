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
<<<<<<< HEAD
	public FAQDTO readFAQById(int id);
	public FrequeDTO readFreqById(int id);
=======
>>>>>>> ae88f71a5d98cf993b2fdaf0d6538975fc5d4348
	public List<FrequeDTO> findAllFreq(@Param ("limit") int limit, @Param("offset") int offset);
	public List<FAQDTO> findAllFAQ(@Param ("limit") int limit, @Param("offset") int offset);
	public int insert(FAQDTO dto);
}
