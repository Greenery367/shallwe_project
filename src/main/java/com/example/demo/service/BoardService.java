package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.BoardCreateDTO;
import com.example.demo.repository.BoardRepository;
import com.example.demo.repository.model.Board;

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
	public void createBoard(BoardCreateDTO boardCreateDTO){
		try {
			boardRepository.insert(boardCreateDTO.getTitle(), boardCreateDTO.getContent(), boardCreateDTO.getAuthor());
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
	
	/**
	 * 게시글 전체 죄회
	 * @return
	 */
	public List<Board> findAll() {
		List<Board> boardListEntity = null;
		
		try {
			boardListEntity = boardRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
        return boardListEntity;
    }
	
	/**
	 * 게시글 상세 보기
	 * @param id
	 * @return
	 */
	public Board readBoardDetail(Integer id) {
//		boardRepository.increaseViewNum(id);
		try {
			return boardRepository.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("디테일 조회 이상", e);
		}
	}
	
	/**
	 * 카테고리 별 게시판 조회
	 * @param categoryId
	 * @return
	 */
	public List<Board> getBoardByCategroy(Integer categoryId) {
		
		return boardRepository.findCategory(categoryId);
	}
	
	
	public void increaseViewNum(Integer id) {
        boardRepository.increaseViewNum(id);
    }
	
	
	
	
}
