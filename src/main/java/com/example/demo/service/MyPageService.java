package com.example.demo.service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.repository.MyPageRepository;
import com.example.demo.repository.model.User;

@Service
public class MyPageService {
	private final MyPageRepository myPageRepository;
	
	@Autowired
	public MyPageService(MyPageRepository myPageRepository) {
		this.myPageRepository = myPageRepository; 
	}
	
	
	 public String[] uploadFile(MultipartFile mFile, String uploadDir) {

	        // 저장할 디렉토리 절대 경로 생성
	        String saveDirectory = new File(uploadDir).getAbsolutePath();

	        // UUID를 사용해 파일명 생성 (중복 방지)
	        String uploadFileName = UUID.randomUUID().toString() + "_" + mFile.getOriginalFilename();

	        // 저장 경로와 파일명 결합
	        String uploadPath = saveDirectory + File.separator + uploadFileName;
	        File destination = new File(uploadPath);

	        // 파일을 지정된 경로에 저장
	        try {
	            mFile.transferTo(destination);
	        } catch (IllegalStateException | IOException e) {
	            e.printStackTrace();
	            return null; // 오류 발생 시 null 반환
	        }

	        // 원본 파일명과 저장된 파일명 반환
	        return new String[] { mFile.getOriginalFilename(), uploadFileName };
	    }

	    // 사용자 프로필 사진 업데이트
	    public boolean updateProfilePicture(Integer userId, String uploadFileName) {
	        return myPageRepository.updateUserProfilePicture(userId, uploadFileName);
	    }
	    
	    // 사용자 프로필 이름 업데이트
	    public boolean updateUsername(Integer userId, String username) {
	    	return myPageRepository.updateUsername(userId, username);
	    }
	    
	    public boolean updateNickname(Integer userId, String nickname) {
	    	return myPageRepository.updateNickname(userId, nickname);
	    }
	    
	    public boolean updatePhoneNumber(Integer userId, String phoneNumber) {
	    	return myPageRepository.updatePhoneNumber(userId, phoneNumber);
	    }
	    
	    public boolean updateEmail(Integer userId, String email) {
	    	return myPageRepository.updateEmail(userId, email);
	    }

	}