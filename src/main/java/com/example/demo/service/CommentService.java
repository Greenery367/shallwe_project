package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.model.Comment;

@Service
public class CommentService {
	private final CommentRepository commentRepository;
	
	@Autowired
	public CommentService(CommentRepository commentRepository) {
		this.commentRepository = commentRepository;
	}
	
	// 특정 게시글의 댓글 조회
    public List<Comment> getCommentsByPostId(int postId) {
        return commentRepository.FindByPostId(postId);
    }
	
	
}
