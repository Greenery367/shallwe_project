package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.LectureRepository;
import com.example.demo.repository.model.Lecture;

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
	
	
	
}
