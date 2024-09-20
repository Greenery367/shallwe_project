package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.repository.UserRepository;
import com.example.demo.repository.model.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FriendService {

	private final UserRepository userRepository;

	@Transactional
	// 친구 검색기능
	public List<User> findLikeUser(String nickname, int limit, int offset) {
		List<User> userList = null;
		userList = userRepository.findLikeNickname(nickname, limit, offset);
		return userList;
	}

	public int findLikeUserSize(String nickname) {
		int count = 0;
		count = userRepository.findLikeNicknameSize(nickname);
		return count;
	}

	public User findByUserID(int id) {
		User user = userRepository.findByUserId(id);
		return user;
	}

	public int insertWaitingFriend(int userId, int friendId) {
		int result = userRepository.insertWatingFriend(userId, friendId);
		return result;
	}

	// 친구요청 수락시 친구 추가
	public int addFriend(int userId, int friendId) {
		int result = 0;
		result = userRepository.addFriendById(userId, friendId);
		return result;
	}

	// 친구삭제시 삭제요청한 유저에게만 친구삭제
	public int removeFriend(int userId, int friendId) {
		int result = 0;
		result = userRepository.removeFriendById(userId, friendId);
		return result;
	}

	// 친구요청 취소 or 거절시 요청목록에서 삭제
	public int removeWaitFriend(int userId, int friendId) {
		int result = 0;
		result = userRepository.removeWaitFriendById(userId, friendId);
		return result;
	}

	// 친구요청 수락시 여전히 친구요청중인지 확인하기
	public int checkWaitFriend(int userId, int friendId) {
		int result = 0;
		result = userRepository.checkStillWait(userId, friendId);
		return result;
	}
	
	// 온라인인 친구 찾기
	public List<User> checkOnlineFriend(int id) {
		List<User>onlineFriendList = null;
		int online = 1;
		onlineFriendList = userRepository.checkStatusFriend(id, online);
		return onlineFriendList;
	}
	
	// 오프라인인 친구 찾기
	public List<User> checkOfflineFriend(int id) {
		List<User>offlineFriendList = null;
		int offline = 0;
		offlineFriendList = userRepository.checkStatusFriend(id, offline);
		return offlineFriendList;
	}
	
	// 사용자에게 친구요청을 보낸 유저 리스트
	public List<User> checkWaitFriendList(int id) {
		List<User>waitFriendList = null;
		waitFriendList = userRepository.checkWaitFriend(id);
		return waitFriendList;
	}
	
	// 사용자가 친구요청을 보낸 유저 리스트
	public List<User> checkSendFriendList(int id) {
		List<User>sendFriendList = null;
		sendFriendList = userRepository.checkSendFriend(id);
		return sendFriendList;
	}
	
	// 친구 목록 리스트 가져오기
	public List<User> findFriendList(int id) {
		List<User>friendList = null;
		friendList = userRepository.checkFriendList(id);
		return friendList;
	}
}
