package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.repository.model.Lecture;

@Mapper
public interface LectureRepository {
	
	// 카테고리별 강의 조회
	public List<Lecture> findCategoryByLecture(Integer categoryId);
	
	// 아이디 기준 상세보기
	public Lecture LectureFindById(Integer id);
	
	// userId로 내강의리스트 조회하기
	public List<Lecture> findLectureListByUserId(Integer userId);
	
}
