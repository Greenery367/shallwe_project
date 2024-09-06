package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.repository.model.Review;

@Mapper
public interface ReviewRepository {
	
	// 강사별 리뷰 조회
	public List<Review> findReviewsByClassId(@Param("classId") Integer classId);
	
	// 리뷰 작성
	public int insertReview(@Param("classId") Integer classId,
				@Param("authorId") Integer authorId,
				@Param("content") String content,
				@Param("grade") Integer grade);
	
	
}
