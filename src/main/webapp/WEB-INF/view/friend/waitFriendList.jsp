<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/layout/header.jsp" %>	
<%@ include file="/WEB-INF/view/layout/friendHeader.jsp" %>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="receive_friend">
	<span class="receive_friend_title">받은 초대</span>
	<input type="text" placeholder="받은 초대 이름으로 검색하기">
</div>
<c:forEach var="user" items="${waitList}">
	<div class="profile_box" id="profile_${user.id}">
		<img src="images/${user.uploadFilename}" class="user_img">
		<span class="user_name">${user.nickname}</span>
		<button class="accept_btn" id="accept" onclick="accept(${user})">수락</button>
		<button class="refuse_btn" id="refuse" onclick="refuse(${user})">거절</button>
	</div>
</c:forEach>

<div class="send_friend">
	<span class="send_friend_title">보낸 초대</span>
	<input type="text" placeholder="보낸 초대 이름으로 검색하기">
</div>
<c:forEach var="user" items="${sendList}">
	<div class="profile_box" id="${user.id}_profile">
		<img src="images/${user.uploadFilename}" class="user_img">
		<span class="user_name">${user.nickname}</span>
		<button class="cancel_btn" id="cancel" onclick="cancel(${user})">취소</button>
	</div>
</c:forEach>

<script>
    // 친구요청 수락시 친구추가처리
	function accept(user) {
		const profile = document.querySelector("#profile_" + user.id);
		fetch("http://192.168.0.131:8080/friends/accept/" + user.id)
		.then((response) => {
			if(response.ok) {
					profile.remove();
			}
		});
	}
	
    // 친구요청 거절시 요청테이블 삭제처리
	function refuse(user) {
		const profile = document.querySelector("#profile_" + user.id);
		fetch("http://192.168.0.131:8080/friends/refuse/" + user.id)
		.then((response) => {
			if(response.ok) {
					profile.remove();
			}
		});
	}
	
    // 친구요청 취소시 요청테이블 삭제처리
	function cancel(user) {
		const profile = document.querySelector("#profile_" + user.id);
		fetch("http://192.168.0.131:8080/friends/cancel/" + user.id)
		.then((response) => {
			if(response.ok) {
					profile.remove();
			}
		});
	}
</script>








<%@ include file="/WEB-INF/view/layout/footer.jsp" %>	
