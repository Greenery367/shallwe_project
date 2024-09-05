package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.repository.model.Question;

@Mapper
public interface QuestionRepository {
	
	// id로 질문 찾아오기
	Question selectQuestionbyId(Integer id);

	// 모든 질문 찾아오기
	List<Question> selectAllQuestions();
}
	