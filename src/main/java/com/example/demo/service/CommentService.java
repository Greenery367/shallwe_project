package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	// 댓글 입력
	@Transactional
	public void createComment(Comment comment) {

		try {
			Integer userId = comment.getAuthorId(); // 현재 하드코딩된 부분을 수정해야 함
			comment.setAuthorId(userId);

			commentRepository.InsertComment(comment.getPostId(), comment.getContent(), comment.getAuthorId());

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("댓글 작성 오류", e);
		}

	}

	// 댓글 수정
	@Transactional
	public void updateComment(Integer id, String content, Integer authorId, Integer postId) {
		try {
			int result = commentRepository.UpdateComment(id, content, authorId, postId);
			if (result == 0) {
				throw new RuntimeException("댓글 수정 실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("댓글 수정 오류", e);
		}
	}

	// 댓글 삭제
	@Transactional
	public void deleteComment(Integer id, Integer authorId, Integer postId) {
		try {
			int result = commentRepository.DeleteComment(id, authorId, postId); // 매개변수 추가
			if (result == 0) {
				throw new RuntimeException("댓글 삭제 실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("댓글 삭제 오류", e);
		}
	}

}
