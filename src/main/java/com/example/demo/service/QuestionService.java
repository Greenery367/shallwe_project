package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.QuestionRepository;
import com.example.demo.repository.model.Question;

@Service
public class QuestionService {
	
	@Autowired
	private final QuestionRepository questionRepository;
	
	public QuestionService(QuestionRepository questionRepository) {
		this.questionRepository=questionRepository;
	}
	
	// ID로 문제 찾기
	public Question getQuestionById(Integer id) {
		Question question = questionRepository.selectQuestionbyId(id);
		return question;
	}
	
	// 모든 문제 불러들어오기
	public List<Question> getAllQuestion() {
		List<Question> questionList = new ArrayList<>();
		questionList=questionRepository.selectAllQuestions();
		return questionList;
	}

}
