package com.example.demo.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.CreateAdvertiseDTO;
import com.example.demo.dto.CreateCategoryDTO;
import com.example.demo.repository.AdminRepository;
import com.example.demo.repository.model.Advertise;
import com.example.demo.repository.model.Category;

@Service
public class AdminService {
	
    @Autowired
    private final AdminRepository adminRepository;
    
	@Value("${file.upload-dir}")
	private String uploadDir;
	
	@Value("${file.advertise-dir}")
	private String advertiseDir;
	
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    // 유저 수 계산
    public int countUser() {
        return adminRepository.countNumberOfUser();
    }

    // 전체 유저 충전 캐쉬
    public int countChargeCash() {
        return adminRepository.countChargeAmount();
    }

    // 캐쉬 사용률
    public double countSpendCashRate() {
        return adminRepository.countSpendAmountRate();
    }

    // 광고 추가
    @Transactional
    public void insertAdvertise(CreateAdvertiseDTO dto) throws IOException {
    	// 광고 정보를 데이터베이스에 추가
        adminRepository.insertAdvertise(dto);
        
    }

    // 광고 수정
    @Transactional
    public void updateAdvertise(Advertise advertise) {
        adminRepository.updateAdvertise(advertise);
    }
    
    // 광고 삭제
    @Transactional
    public void deleteAdvertise(Advertise advertise) {
        adminRepository.deleteAdvertiseById(advertise.getId());
    }
    

    // 전체 광고 조회
    public List<Advertise> selectAllAdvertise() {
        return adminRepository.selectAllAdvertise();
    }
    
    // 현재 게시중인 광고 조회 status = 1
    public List<Advertise> selectAdvertiseNow(){
    	return adminRepository.selectAdvertiseNow();
    }
    
    // 현재 게시중인 광고 중에 위치 1번 (place_id = 1, status = 1)
    public List<Advertise> selectAdvertisePlaceOne(){
    	return adminRepository.selectAdvertisePlaceOne();
    }
    
    // 현재 게시중인 광고 중에 위치 1번 (place_id = 2, status = 1)
    public List<Advertise> selectAdvertisePlaceTwo(){
    	return adminRepository.selectAdvertisePlaceTwo();
    }
    
    // 현재 게시중인 광고 중에 위치 1번 (place_id = 3, status = 1)
    public List<Advertise> selectAdvertisePlaceThree(){
    	return adminRepository.selectAdvertisePlaceThree();
    }
    
    // 전체 카테고리 조회
    public List<Category> selectAllCategory(){
    	return adminRepository.selectAllCategory();
    }
    
    // 카테고리 추가
    @Transactional
    public void insertCategory(CreateCategoryDTO dto){
    	adminRepository.insertCategory(dto);
    }
    
    // 카테고리 수정
    @Transactional
    public void updateCategory(Category category) {
    	adminRepository.updateCategory(category);
    }

    // 카테고리 삭제
    @Transactional
    public void deleteCategoryById(Category category) {
    	adminRepository.deleteCategoryById(category.getId());
    }
    
    
    
}