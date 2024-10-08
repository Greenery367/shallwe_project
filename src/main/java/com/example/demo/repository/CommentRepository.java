package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.repository.model.Comment;

@Mapper
public interface CommentRepository {
	
	// 댓글 입력
	public int InsertComment(@Param("postId") Integer postId,
							@Param("content") String content,
							@Param("authorId") Integer authorId);
	
	// 댓글 수정
    public int UpdateComment(@Param("id") Integer id,
                             @Param("content") String content,
                             @Param("authorId") Integer authorId, 
                             @Param("postId") Integer postId); 
        
    // 댓글 삭제
    public int DeleteComment(@Param("id") Integer id,
                             @Param("authorId") Integer authorId, 
                             @Param("postId") Integer postId); 
	
	
	// 게시글 별 댓글 조회
	public List<Comment> FindByPostId(@Param("postId") int postId);
	
	
}
