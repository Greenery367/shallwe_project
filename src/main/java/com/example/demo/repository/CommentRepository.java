package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.repository.model.Comment;

@Mapper
public interface CommentRepository {
	
	// 댓글 입력
//	public int commentInsert();
	// 댓글 수정
//	public int commentUpdate();
	// 댓글 삭제
	 int commentDelete(@Param("id") int id);
	// 게시글 별 댓글 조회
	public List<Comment> FindByPostId(@Param("postId") int postId);
	
	
}
