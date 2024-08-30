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
	public List<Board> getBoardByCategory(Integer categoryId , int limit, int offset) {
		List<Board> catagoryBoardList = new ArrayList<>(); 
		int calculatedOffset = offset;
		catagoryBoardList =	boardRepository.findByCategoryBoardForPage(categoryId, limit, calculatedOffset);
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
	
	
	/**
	 * 카테고리별 게시판 총 갯수 
	 * @param categoryId
	 * @return
	 */
	public int findByCategoryTotalBoard(Integer categoryId) {
		int totalNum = boardRepository.findByCategoryTotalBoard(categoryId);
		return totalNum;
	}
	
	// 검색 공백처리
	private String formatSearchTerm(String searchTerm) {
		if (searchTerm == null) {
			return "";
		}
		return "%" + searchTerm.trim().replace(" ", "%") + "%";
	}
	
	public List<Board> findByContent(Integer categoryId , int limit, int offset, String content) {
		List<Board> SearchContentList = new ArrayList<>(); 
		int calculatedOffset = offset;
		String formattedContent = formatSearchTerm(content);
		SearchContentList =	boardRepository.findByContent(categoryId, limit, calculatedOffset, formattedContent);
		System.out.println("Service caboard : " + SearchContentList);
		return SearchContentList;
	}
	

	
	public List<Board> findByTitle(Integer categoryId , int limit, int offset, String title) {
		List<Board> SearchTitleList = new ArrayList<>(); 
		int calculatedOffset = offset;
		String formattedTitle = formatSearchTerm(title);
		SearchTitleList =	boardRepository.findByTitle(categoryId, limit, calculatedOffset, formattedTitle);
		System.out.println("Service caboard : " + SearchTitleList);
		return SearchTitleList;
	}
	
	public List<Board> findByNickName(Integer categoryId , int limit, int offset, String nickName) {
		List<Board> SearchTitleList = new ArrayList<>(); 
		int calculatedOffset = offset;
		String formattedNickName = formatSearchTerm(nickName);
		SearchTitleList =	boardRepository.findByNickName(categoryId, limit, calculatedOffset, formattedNickName);
		System.out.println("Service caboard : " + SearchTitleList);
		return SearchTitleList;
	}
	
	
	
	
	
}
