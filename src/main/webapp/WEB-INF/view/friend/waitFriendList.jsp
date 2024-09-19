<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/layout/header.jsp" %>	
<%@ include file="/WEB-INF/view/layout/friendHeader.jsp" %>	
<div class="receive_friend">
	<span class="receive_friend_title">받은 초대</span>
	<input type="text" placeholder="받은 초대 이름으로 검색하기">
<c:forEach var="user" items="${waitList}">
	<div class="profile_box" id="profile_${user.userId}">
		<c:choose>
            <c:when test="${user.uploadFileName == null}">
            <img src="/static/images/defaultProfile.jpeg">
            </c:when>
            <c:otherwise>
            <img src="/images/${user.uploadFileName}" alt="Profile Picture">
            </c:otherwise>
        </c:choose>
		<span class="user_name">${user.nickname}</span>
		<button class="accept_btn" id="accept" onclick="accept(${user.userId})">수락</button>
		<button class="refuse_btn" id="refuse" onclick="refuse(${user.userId})">거절</button>
	</div>
</c:forEach>
</div>
<div class="send_friend">
	<span class="send_friend_title">보낸 초대</span>
	<input type="text" placeholder="보낸 초대 이름으로 검색하기">

<c:forEach var="user" items="${sendList}">
	<div class="profile_box" id="profile_${user.userId}">
		<c:choose>
            <c:when test="${user.uploadFileName == null}">
            <img src="/static/images/defaultProfile.jpeg">
            </c:when>
            <c:otherwise>
            <img src="/images/${user.uploadFileName}" alt="Profile Picture">
            </c:otherwise>
        </c:choose>
		<span class="user_name">${user.nickname}</span>
		<button class="cancel_btn" id="cancel" onclick="cancel(${user.userId})">취소</button>
	</div>
</c:forEach>
</div>
<script>
	console.log("미치겠네");
    // 친구요청 수락시 친구추가처리
	function accept(userId) {
		const profile = document.querySelector("#profile_" + userId);
		fetch("http://192.168.0.131:8080/friends/accept/" + userId)
		.then((response) => response.text())
		.then((text) => {
			alert(text),
			profile.remove()
		});
	}
	
    // 친구요청 거절시 요청테이블 삭제처리
	function refuse(userId) {
		const profile = document.querySelector("#profile_" + userId);
		fetch("http://192.168.0.131:8080/friends/refuse/" + userId)
		.then((response) => response.text())
		.then((text) => {
			alert(text);
			profile.remove();
		});
	}
	
    // 친구요청 취소시 요청테이블 삭제처리
	function cancel(userId) {
		const profile = document.querySelector("#profile_" + userId);
		fetch("http://192.168.0.131:8080/friends/cancel/" + userId)
		.then((response) => response.text())
		.then((text) => {
			alert(text);
			profile.remove();
		});
	}
</script>






<%@ include file="/WEB-INF/view/layout/footer.jsp" %>	
