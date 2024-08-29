package com.example.demo.service;

import java.util.ArrayList;
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
	public void updateBoard(Integer id, String title, String content, Integer authorId) {
		try {
			boardRepository.updateBoard(id, title, content, authorId);			
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
	public List<Board> getBoardByCategroy(Integer categoryId , int limit, int offset) {
		List<Board> catagoryBoardList = new ArrayList<>(); 
		int calculatedOffset = offset;
		catagoryBoardList =	boardRepository.findByCategroyBoardForPage(categoryId, limit, calculatedOffset);
		System.out.println("Service caboard : " + catagoryBoardList);
		return catagoryBoardList;
	}
	
	/**
	 * 조회수 +1
	 * @param id
	 */
	public void increaseViewNum(Integer id) {
        boardRepository.increaseViewNum(id);
    }
	
	
	public List<Board> readBoardListByCategoryForPage(Integer categoryId, int limit, int offset){
		List<Board> list = boardRepository.findByCategroyBoardForPage(categoryId, categoryId, categoryId);
		return list;
	};
	
	public int findByCategoryTotalBoard(Integer categoryId) {
		int totalNum = boardRepository.findByCategoryTotalBoard(categoryId);
		return totalNum;
	}
	
	
	
	
}
