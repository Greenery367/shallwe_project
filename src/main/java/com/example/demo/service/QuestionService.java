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
	private QuestionRepository questionRepository;
	
	public QuestionService() {
		this.questionRepository=questionRepository;
	}
	
	public Question getQuestionById(Integer id) {
		Question question = questionRepository.selectQuestionbyId(id);
		return question;
	}
	
	public List<Question> getAllQuestion() {
		List<Question> questionList = new ArrayList<>();
		questionList=questionRepository.selectAllQuestions();
		return questionList;
	}

}
