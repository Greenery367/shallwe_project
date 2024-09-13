package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.repository.model.News;
import com.example.demo.repository.model.Notice;

@Mapper
public interface NewsRepository {

	// 모든 뉴스 가져오기
	public List<News> selectAllNews();
	
	// 아이디로 뉴스 찾기
	public News selectNewsById(Integer id);
}