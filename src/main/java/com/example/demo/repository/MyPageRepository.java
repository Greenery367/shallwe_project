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
    
    // 사용자 이름 업데이트
    public boolean updateUsername(@Param("userId") Integer userId, 
								@Param("username") String username);
	
    // 사용자 닉네임 업데이트
    public boolean updateNickname(@Param("userId") Integer userId,
    							  @Param("nickname") String nickname);
    
    // 사용자 핸드폰 번호 업데이트
    public boolean updatePhoneNumber(@Param("userId") Integer userId,
    								@Param("phoneNumber") String phoneNumber);
    
    public boolean updateEmail(@Param("userId") Integer userId,
							   @Param("email") String email);
    
    
}
