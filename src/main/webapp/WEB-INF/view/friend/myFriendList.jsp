<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/layout/header.jsp" %>	
<%@ include file="/WEB-INF/view/layout/friendHeader.jsp" %>	
<link rel="stylesheet" href="../css/myFriendList.css"> <!-- 추가된 스타일 시트 -->

	<div class="content">
		<div class="friends_count">
		<span>나의 친구 ${count}</span>
		<button id="manage_friends" class="manage_fiends_btn">
			<span>친구 목록 관리</span>
		</button>
		<button id="add_friends" class="add_friends_btn">
			<span>친구 추가</span>
		</button>
		</div>
		<div class="search_friend">
			<input type="text" placeholder="이름으로 친구 검색">
		</div>
		<div id="state_online" class="state_block">온라인</div>
		<c:forEach var="online" items="${onlineList}">
			<div class="user_box">
				<a class="select_overlay" href="/chat/profileInfo?id=${online.id}"></a>
				<img src="images/${online.uploadFilename}">
				<span>${online.nickname}</span>
			</div>
		</c:forEach>
		<div id="state_offline" class="state_block">오프라인</div>
		<c:forEach var="offline" items="${offlineList}">
			<div class="user_box">
				<a class="select_overlay" href="/chat/profileInfo?id=${offline.id}"></a>
				<img src="images/${offline.uploadFilename}">
				<span>${offline.nickname}</span>
			</div>
		</c:forEach>
	</div>
<%@ include file="/WEB-INF/view/layout/footer.jsp" %>	