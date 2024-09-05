package com.example.demo.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.CompatibilityListDTO;
import com.example.demo.dto.MbtiDTO;
import com.example.demo.dto.TestUser;
import com.example.demo.repository.MatchRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MatchService {

	// ... 생략
	// 초기 파라메터 가져오는 방법
	@Value("${file.upload-dir}")
	private String uploadDir;

	@Autowired
	private final MatchRepository matchRepository;

	@Transactional
	public List<CompatibilityListDTO> getCompatibilityList(int id) {
		List<CompatibilityListDTO> compatibilityList = null;
		compatibilityList = matchRepository.getCompatibilityListByMbti(id);
		return compatibilityList;
	}

	public int getMbtiIdByUserId(int id) {
		return matchRepository.getMbtiIdByUserId(id);
	}

	public MbtiDTO getMbtiNameById(int id) {
		MbtiDTO mbti = matchRepository.getMbtiNameByMbtiId(id);
		return mbti;
	}
	
	public TestUser createUser(TestUser dto) {
		TestUser user = dto;
		if (user.getFileName() != null && !user.getFileName().isEmpty()) {
			// 파일 업로드 로직 구현
			String[] fileNames = uploadFile(user.getFileName());
			
			user.setOriginFileName(fileNames[0]);
			user.setUploadFileName(fileNames[1]);
		}
		return user;
	}
	
	public String[] uploadFile(MultipartFile mFile) {

		// 코드 수정
		// File - getAbsolutePath() : 파일 시스템의 절대 경로를 나타냅니다.
		// (리눅스 또는 MacOS)에 맞춰서 절대 경로가 생성을 시킬 수 있다.
		// String saveDirectory = new File(uploadDir).getAbsolutePath();
		String saveDirectory = uploadDir;

		// 파일 이름 생성(중복 이름 예방)
		String uploadFileName = UUID.randomUUID() + "_" + mFile.getOriginalFilename();
		// 파일 전체경로 + 새로생성한 파일명
		String uploadPath = saveDirectory + File.separator + uploadFileName;
		File destination = new File(uploadPath);

		// 반드시 수행
		try {
			mFile.transferTo(destination);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		return new String[] { mFile.getOriginalFilename(), uploadFileName };
	}
}
