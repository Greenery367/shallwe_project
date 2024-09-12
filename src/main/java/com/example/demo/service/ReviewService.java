package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.repository.ReviewRepository;
import com.example.demo.repository.model.Review;

@Service
public class ReviewService {
	private final ReviewRepository reviewRepository;
	
	@Autowired
	public ReviewService(ReviewRepository reviewRepository) {
		this.reviewRepository = reviewRepository;
	}
	
	// 특정 강의 리뷰 조회
	public List<Review> getReviewByClassId(Integer classId) {
		return reviewRepository.findReviewsByClassId(classId);
	}
	
	// 리뷰 입력
	@Transactional
	public void createReview(Review review) {
		try {
			Integer userId = review.getAuthor_id();
			review.setAuthor_id(userId);
			
			reviewRepository.insertReview(review.getClassId(), review.getAuthor_id(), review.getComment(), review.getGrade());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("리뷰 작성 오류", e);
		}
		
	}
	
	
}
