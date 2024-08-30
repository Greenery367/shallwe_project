package com.example.demo.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.repository.model.Question;

@Mapper
public interface QuestionRepository {

	// id로 질문 찾기
	Question selectQuestionById(Integer id);

}
