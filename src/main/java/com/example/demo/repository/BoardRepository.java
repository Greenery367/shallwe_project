package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.BoardCreateDTO;
import com.example.demo.repository.model.Board;

@Mapper
public interface BoardRepository {
	
	//게시글 입력
	public int insert(@Param("title")String title,@Param("content") String content,@Param("author") String author);
	
	// 게시글 수정
	public int updateBoard(@Param("id")Integer id,@Param("title")String title, @Param("content")String content, @Param("author")Integer author);
	
	// 게시글 삭제
	public int deleteById(@Param("id")Integer id, @Param("author")Integer author);
	
	// 게시글 전체 조회
	public List<Board> findAll();

	// 게시글 ID별 조회
	public Board findById(Integer id);
	
	// 게시글 검색 - 제목기준
	public List<Board> findByTitle(String title);
	
	// 게시글 검색 - 내용기준
	public List<Board> findByContent(String content);
	
	// 게시글 검색 - 닉네임 기준
	public List<Board> findByAuthor(Integer author);
	
	
	
}
