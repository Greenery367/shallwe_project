package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.FAQDTO;
import com.example.demo.dto.FrequeDTO;
import com.example.demo.handler.exception.DataDeleveryException;
import com.example.demo.handler.exception.RedirectException;
import com.example.demo.repository.CsRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CsService {
	
	@Autowired
	private final CsRepository csRepository;
	@Autowired
	private final JdbcTemplate jdbcTemplate;
	
	
	public int countFreq() {
		return csRepository.countFreq();
	}
	
	public int countFAQ() {
		return csRepository.countFAQ();
	}
	public FAQDTO readFAQById(Integer id) {
		return csRepository.readFAQById(id);
	}
	public FrequeDTO readFreqById(Integer id) {
		return csRepository.readFreqById(id);
	}
	
	@Transactional
	public void deleteFAQ(Integer id) {
		csRepository.deleteFAQ(id);
	}
	
	@Transactional
	public int updateFAQ(Integer id, String title, String content) {
		return csRepository.updateFAQ(id, title, content);
	}
	
	
	@Transactional
	public List<FrequeDTO> readAllFreq(int page, int size){
		List<FrequeDTO> list = new ArrayList<>();
		int limit = size;
		int offset = (page -1) * size;
		list = csRepository.findAllFreq(limit, offset);
		return list;
	}
	
	
	@Transactional
	public List<FAQDTO> readAllFAQ(int page, int size){
		List<FAQDTO> list = new ArrayList<>();
		int limit = size;
		int offset = (page -1) * size;
		list = csRepository.findAllFAQ(limit, offset);
		return list;
	}
	
	@Transactional
	public void createFAQ(FAQDTO dto) {
		int result = 0;
		try {
			jdbcTemplate.execute("SET SESSION auto_increment_increment = 2");
            result = csRepository.insert(dto);
            jdbcTemplate.execute("SET SESSION auto_increment_increment = 1");
		} catch (DataDeleveryException e) {
			throw new DataDeleveryException("문의글 작성에 실패하였습니다.", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RedirectException("알 수 없는 오류", HttpStatus.SERVICE_UNAVAILABLE);
		}
		if(result != 1) {
			throw new DataDeleveryException("문의글 작성 실패", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
}
