package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.SignUpDTO;
import com.example.demo.handler.exception.DataDeleveryException;
import com.example.demo.handler.exception.RedirectException;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.model.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	@Autowired
	private final UserRepository userRepository;
	@Autowired
	private final PasswordEncoder passwordEncoder;
	
	/**
	 * username 으로 DB접근하여 사용자 존재 여부 조회
	 * @param username
	 * @return
	 */
	
	public int temporaryPassword(String password, String email) {
		String hashpwd = passwordEncoder.encode(password);
		return userRepository.updatePasswordByEmail(hashpwd, email);
	}
	
	public User searchByUserId(Integer userId) {
		return userRepository.findByUserId(userId);
	}
	
	public User searchId(String id) {
		return userRepository.findById(id);
	}
	
	public User searchByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	public void updateUserCash(int userId, Long amount) {
		userRepository.updateCurrentCash(userId,amount);
		return;
	}

	public boolean isIdAvailable(String id) {
        // 데이터베이스에서 아이디 존재 여부 확인
		if(userRepository.findById(id) == null){
			return true; 
		}else {
			return false;
		}
    }
	
	// 로그인 로그아웃 시 친구 요청용 상태값 변경 쿼리 (유저 아이디와 상태값[0일떄 로그아웃 / 1일떄 로그인]을 Integer 타입으로 전달 )
	public void updateUserStatus(Integer userId, Integer onlineStatus) {
		userRepository.updateUserStatus(userId, onlineStatus);
	}
	
	
	public User findUserByNickname(String nickname) {
		User user = null;
		user = userRepository.findByNickname(nickname);
		return user;
	}
	
	public boolean isNicknameAvailable(String nickname) {
        // 데이터베이스에서 아이디 존재 여부 확인
		if(userRepository.findByNickname(nickname) == null){
			return true; 
		}else {
			return false;
		}
    }
	
	public boolean isEmailAvailable(String email) {
		// 데이터베이스에서 아이디 존재 여부 확인
		if(userRepository.findByEmail(email) == null){
			return true; 
		}else {
			return false;
		}
	}
	
	@Transactional
	public void createUser(SignUpDTO dto) {
		int result = 0;
		
		try {
			String hashPwd = passwordEncoder.encode(dto.getPassword());
			dto.setPassword(hashPwd);
			result = userRepository.insert(dto.toUser());
		}catch (Exception e) {
			e.printStackTrace();
			throw new RedirectException("알 수 없는 에러", HttpStatus.SERVICE_UNAVAILABLE);
		}
		if(result != 1) {
			throw new DataDeleveryException("회원가입에 실패하였습니다", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	public long getLectureCash (Integer userId) {
		return userRepository.getLectureCash(userId);
	}
	
	
	
	
	
	
}
