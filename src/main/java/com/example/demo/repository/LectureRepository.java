package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.repository.model.Lecture;

@Mapper
public interface LectureRepository {
	
	// 카테고리별 강의 조회
	public List<Lecture> findCategoryByLecture(Integer categoryId);
	
	
	
}
