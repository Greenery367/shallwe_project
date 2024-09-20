package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.CreateLectureDTO;
import com.example.demo.repository.LectureRepository;
import com.example.demo.repository.model.Category;
import com.example.demo.repository.model.Lecture;
import com.example.demo.repository.model.Spend;

@Service
public class LectureService {

	private final LectureRepository lectureRepository;
	
	@Autowired
	public LectureService(LectureRepository lectureRepository) {
		this.lectureRepository = lectureRepository;
	}
	
	/**
	 * 카테고리 별 강의 리르슽 조회
	 * @param categoryId
	 * @return
	 */
	public List<Lecture> getLectureByCategory(Integer categoryId) {
		List<Lecture> categoryLectureList = new ArrayList<>();
		categoryLectureList = lectureRepository.findCategoryByLecture(categoryId);
		return categoryLectureList;
	} 
	
	/**
	 * 강의 상세보기
	 * @param id
	 * @return
	 */
	public Lecture readLectureDetail(Integer id) {
		try {
			return lectureRepository.LectureFindById(id); 
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("강의 상세 이상, e");
		}
	}
	
	
	
	/*
	 * userId 로 lectureList 조회 (내 강의 조회)
	 */
	public List<Lecture> getLectureListByUserId(Integer userId){
		List<Lecture> lectureList = new ArrayList<>();
		lectureList = lectureRepository.findLectureListByUserId(userId);
		return lectureList;
	}
	
	// 카테고리 목록 조회
	public List<Category> getCategory(){
		List<Category> categoryList = new ArrayList<>();
		categoryList = lectureRepository.getCategoryList();
		return categoryList;
	}
	
	// 강의 개설 
	@Transactional
	public void createLecture(CreateLectureDTO dto) {
		lectureRepository.insertLecture(dto);
	}
	
	// 강의 정보 수정
	@Transactional
	public int updateLecture(CreateLectureDTO dto) {
		return lectureRepository.updateLecture(dto);
	}
	
	// 강의 삭제 요청
	@Transactional
	public void deleteLectureById(Integer lectureId) {
		lectureRepository.deleteLecture(lectureId);
	}
	
	// 강의 id로 결제 히스토리 불러오기
	@Transactional
	public List<Spend> getSpendHistoryByLectureId(Integer lectureId){
		return lectureRepository.selectSpendHistoryByLectureId(lectureId);
	}
	
}
