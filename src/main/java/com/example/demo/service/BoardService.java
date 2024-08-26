package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.repository.BoardRepository;

@Service
public class BoardService {
	private final BoardRepository boardRepository;
	
	@Autowired
	public BoardService(BoardRepository boardRepository) {
		this.boardRepository = boardRepository;
	}
	
	/**
	 * 게시글 생성
	 * @param title
	 * @param content
	 * @param author
	 */
	@Transactional
	public void createBoard(String title, String content, String author){
		try {
			boardRepository.insert(title, content, author);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 게시글 수정
	 * @param id
	 * @param title
	 * @param content
	 */
	@Transactional
	public void updateBoard(Integer id, String title, String content, Integer author) {
		try {
			boardRepository.updateBoard(id, title, content, author);			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("게시글 수정 오류 발생");
		}
	}
	
	
	/**
	 * 게시글 삭제
	 * @param id
	 */
	public void deleteBoard(Integer id, Integer author) {
		try {
			boardRepository.deleteById(id, author);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
}
