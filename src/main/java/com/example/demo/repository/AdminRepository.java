package com.example.demo.repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.AdminSelectCommentDTO;
import com.example.demo.dto.CashChargeGraphVO;
import com.example.demo.dto.CreateAdvertiseDTO;
import com.example.demo.dto.CreateCategoryDTO;
import com.example.demo.repository.model.Admin;
import com.example.demo.repository.model.Advertise;
import com.example.demo.repository.model.Board;
import com.example.demo.repository.model.Category;
import com.example.demo.repository.model.Comment;
import com.example.demo.repository.model.Lecture;

@Mapper
public interface AdminRepository{
	
	// id로 어드민 테이블 조회
	public Admin findbyId(String adminId);
	
	// 전체 유저수
	public int countNumberOfUser();
	// 전체유저 충전캐쉬
	public int countChargeAmount();	
	// 전체유저 사용캐쉬
	public int countSpendAmount();
	// 캐쉬 사용률 
	public double countSpendAmountRate();
	
	// 10일간 캐쉬 충전량
	public List<CashChargeGraphVO> selectChargeAmountBetweenTenDays(CashChargeGraphVO chargeVO) throws Exception;
	// 하루 캐쉬 충전 총액 계산
	public Integer countChargeAmountOneDay(Timestamp createdAt);
	// 일별 충전 총합 계산
	public List<CashChargeGraphVO> countChargeAmountAllDay();
	
	// 전체 광고 조회
	public List<Advertise> selectAllAdvertise();
	// id로 광고 조회
	public Advertise selectAdvertiseById(@Param("id") Integer id);
	// 현재 게시중인 광고 전체 조회 (status = 1)
	public List<Advertise> selectAdvertiseNow();
	// 현재 게시중인 광고 중 위치1 (place_id = 1, status = 1) 조회
	public List<Advertise> selectAdvertisePlaceOne();
	// 현재 게시중인 광고 중 위치2 (place_id = 2, status = 1) 조회
	public List<Advertise> selectAdvertisePlaceTwo();
	// 현재 게시중인 광고 중 위치1 (place_id = 3, status = 1) 조회
	public List<Advertise> selectAdvertisePlaceThree();
	
	// 광고추가 
	public int insertAdvertise(CreateAdvertiseDTO dto);
	// 광고 수정
	public int updateAdvertise(Advertise advertise);
	// 광고 삭제
	public int deleteAdvertiseById(Integer id);
	
	// 만료된 광고 중 상태가 1인 광고 조회
	public List<Integer> selectExpiredAdvertise(@Param("now") LocalDateTime now);
	// 광고 상태 업데이트
	public void updateAdvertiseStatus(@Param("status") int status, @Param("id") Integer id);
	
	// 전체 게시판 카테고리 조회
	public List<Category> selectAllCategory();
	// 게시판 카테고리 추가
	public int insertCategory(CreateCategoryDTO dto);
	// 게시판 카테고리 수정
	public int updateCategory(Category category);
	// 게시판 카테고리 삭제
	public int deleteCategoryById(Integer id);
	
	// 전체 게시글 조회
	public List<Board> selectAllBoard();
	// 게시글 수정
	public int updateBoard(Board board);
	// 게시글 삭제
	public int deleteBoardById(Integer id);
	
	// 게시글 id로 게시글 하나 조회
	public Board selectBoardById(@Param("id") Integer id);
	// 게시글 하나의 댓글 전체 조회
	public List<AdminSelectCommentDTO> selectCommentByPostId(@Param("postId") Integer postId);

	// 댓글 삭제 기능
	public int deleteCommentById(Integer id);
	
	// 광고별 게시일 계산
	public int selectPostingPeriodById(Integer id);
	// 광고별 위치 금액 조회
	public int selectAdvertisePriceById(Integer id);
	
	// 전체 강의 조회
	public List<Lecture> selectAllLecture();
	// 강의 삭제
	public int deleteLectureById(Integer id);
	
}
