package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.CreateLectureDTO;
import com.example.demo.repository.model.Category;
import com.example.demo.repository.model.Lecture;
import com.example.demo.repository.model.Spend;

@Mapper
public interface LectureRepository {
	
	// 카테고리별 강의 조회
	public List<Lecture> findCategoryByLecture(Integer categoryId);
	
	// 아이디 기준 상세보기
	public Lecture LectureFindById(Integer id);
	
	// userId로 내강의리스트 조회하기
	public List<Lecture> findLectureListByUserId(Integer userId);
	
	// 카테고리 리스트 조회
	public List<Category> getCategoryList();
	
	// 강의 개설
	public void insertLecture(CreateLectureDTO dto);
	
	// 강좌 정보 수정
	public int updateLecture(CreateLectureDTO dto);
	
	// 강좌 삭제 요청
	public void deleteLecture(Integer lectureId);

	//강좌 id로 결제내역 조회
	public List<Spend> selectSpendHistoryByLectureId(Integer lectureId);
	
	public String getCategoryNameById(@Param("categoryId")Integer categoryId); 
}