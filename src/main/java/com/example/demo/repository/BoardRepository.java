package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.repository.model.Board;

@Mapper
public interface BoardRepository {
	
	//게시글 입력
	public int insert(@Param("categoryId") Integer categoryId,
            		  @Param("authorId") Integer authorId,
            		  @Param("content") String content,
            		  @Param("title") String title);
	
	// 게시글 수정
	public int updateBoard(@Param("id")Integer id,
						   @Param("title")String title, 
						   @Param("content")String content, 
						   @Param("authorId")Integer authorId);
	
	// 게시글 삭제
	public int deleteBoard(@Param("id")Integer id, 
						  @Param("authorId")Integer authorId);
	
	// 게시글 전체 조회
	public List<Board> findAll();

	// 게시글 ID별 조회
	public Board findById(Integer id);
	
	// 게시글 검색 - 제목기준
	public List<Board> findByTitle(@Param("categoryId")Integer categoryId, 
									@Param("limit")Integer limit,
									@Param("offset")Integer offset,
									@Param("title") String title);
	
	// 게시글 검색 - 내용기준
	public List<Board> findByContent(@Param("categoryId")Integer categoryId, 
									@Param("limit")Integer limit,
									@Param("offset")Integer offset, 
									@Param("content") String content);
	
	// 게시글 검색 - 닉네임 기준
	public List<Board> findByNickName(@Param("categoryId")Integer categoryId, 
									@Param("limit")Integer limit,
									@Param("offset")Integer offset,
									@Param("nickName") String nickName);
	
	// 카테고리별 게시글 조회
	public List<Board> findCategory(Integer categoryId);
	
	// 조회수 증가
	public void increaseViewNum(Integer id);
	
	// 페이징 처리 
	public List<Board> findByCategoryBoardForPage(@Param("categoryId")Integer categoryId, 
			@Param("limit")Integer limit,
			@Param("offset")Integer offset);
	
	// 게시글 전체 갯수 
	public int findByCategoryTotalBoard(Integer categoryId);
	
	// 제목 검색 전체 갯수
	public int SearchTitleTotalBoard(@Param("categoryId")Integer categoryId, @Param("title") String title);
	
	// 내용 검색 전체 갯수
	public int SearchContentTotalBoard(@Param("categoryId")Integer categoryId, @Param("content") String content);
	
	// 작성자 검색 전체 갯수
	public int SearchAuthorTotalBoard(@Param("categoryId")Integer categoryId, @Param("nickName") String nickName);
	
}
