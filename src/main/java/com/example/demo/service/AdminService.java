package com.example.demo.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.CreateAdvertiseDTO;
import com.example.demo.repository.AdminRepository;
import com.example.demo.repository.model.Advertise;

@Service
public class AdminService {
	
    @Autowired
    private final AdminRepository adminRepository;
    
	@Value("${file.upload-dir}")
	private String uploadDir;

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
    public void insertAdvertise(CreateAdvertiseDTO dto, MultipartFile file) throws IOException {
    	// 광고 정보를 데이터베이스에 추가
        adminRepository.insertAdvertise(dto);
        
        // 파일 업로드 처리
        if(file != null && !file.isEmpty()) {
        	 // 원본 파일 이름
            String originFilename = file.getOriginalFilename();
            
            // 파일 확장자 추출
            String fileExtension = originFilename != null ? originFilename.substring(originFilename.lastIndexOf('.')) : "";
            
            // UUID를 사용하여 고유한 파일 이름 생성
            String uuid = UUID.randomUUID().toString();
            
            // UUID와 원본 파일 이름을 결합하여 파일 이름 생성
            String uploadFileName = uuid + "_" + originFilename;
            
            // 파일을 저장할 경로
            File targetFile = new File(uploadDir + File.separator + uploadFileName);
            
            // 디렉토리 존재 여부 확인 및 생성
            targetFile.getParentFile().mkdirs();
            
            // 파일 저장
            file.transferTo(targetFile);
        }
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
    
    // 현재 게시중인 광고 조회
    public List<Advertise> selectAdvertiseNow(){
    	return adminRepository.selectAdvertiseNow();
    }
    
 // 5초마다 실행되는 광고 상태 업데이트
    @Scheduled(fixedRate = 5000)
    public void updateAdvertiseStatus() {
    	// 현재 게시중인 광고 조회
        List<Advertise> currentAds = adminRepository.selectAdvertiseNow();
        
        if (currentAds.size() > 0) {
            // 다음 광고를 선택
            Advertise nextAd = getNextAd(currentAds);
            
            // 현재 게시중인 광고를 비활성화
            for (Advertise ad : currentAds) {
                adminRepository.updateAdvertiseStatus(0, ad.getId());
            }
            
            // 선택된 광고를 게시중으로 활성화
            adminRepository.updateAdvertiseStatus(1, nextAd.getId());
        }
    }
    
    // 광고 순환 로직
    private Advertise getNextAd(List<Advertise> currentAds) {
        // 예를 들어, 현재 광고 리스트의 첫 번째 광고를 마지막으로 이동시키고, 두 번째 광고를 선택하는 방법
        Advertise nextAd = currentAds.remove(0); // 첫 번째 광고를 제거
        currentAds.add(nextAd); // 리스트의 끝에 추가
        return currentAds.get(0); // 새로운 첫 번째 광고를 선택
    }
    

}