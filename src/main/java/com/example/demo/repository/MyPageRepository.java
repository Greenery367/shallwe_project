package com.example.demo.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.repository.model.User;

@Mapper
public interface MyPageRepository {
	
	public User selectMyPageUser(Integer userId);
	

    // 사용자 프로필 사진 업데이트
    public boolean updateUserProfilePicture(@Param("userId") Integer userId, 
    										@Param("uploadFileName") String uploadFileName);
    
    public boolean updateUsername(@Param("userId") Integer userId, 
								@Param("username") String username);
	
}
