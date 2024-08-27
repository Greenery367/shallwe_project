package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.SignUpDTO;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.model.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	@Autowired
	private final UserRepository userRepository;
	
	
	/**
	 * username 으로 DB접근하여 사용자 존재 여부 조회
	 * @param username
	 * @return
	 */
	public User searchUserName(String username) {
		return userRepository.findByUsername(username);
	}
	
	@Transactional
	public void createUser(SignUpDTO dto) {
		int result = 0;
		
	}
	
	
	
	
	
	
}
