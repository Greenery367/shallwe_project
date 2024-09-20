<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게임 친구 매칭 사이트: 셸위?</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.css"/>
<link rel="stylesheet" href="/static/css/header.css"/>
<link rel="stylesheet" href="/static/css/FriendHeader.css"/>
<script src="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.js"></script>
</head>
<body>
<div class="main">
	<header>
		<div class="header">
			<div class="account-menus">
				<img class="mini-logo" alt="로고" src="/static/images/shallwe-icon.png">
				<a href="/user/main"><b>홈</b></a>
				<p>|</p>
				<c:choose>
						<c:when test="${user == null }">
							<a href="/user/sign-in"><b>로그인</b></a>
							<p>|</p>
							<a href="/user/sign-up"><b>회원가입</b></a>
							<p>|</p>
						</c:when>
						<c:otherwise>
							<a href="/user/sign-in"><b>로그아웃</b></a>
							<p>|</p>
						</c:otherwise>
					</c:choose>
					
					<div class="charge-cash">
						<a href="/cash/charge"><b>캐시충전</b></a> <img
							src="/static/images/cash.png">
					</div>
			</div>
		</div>
	</header>
	<hr>
	<div class="banner">
		<img class="logo" alt="로고" src="/static/images/logo.png">
	</div>
	<div class="nav-bar">
		<div class="menus">
			<div class="menu-container">
				<a href="#" class="menu">친구 찾기</a>
				<div class="drop-down-menus-game">
					<ul>
						<div class="game-menu-box">
							<c:forEach var="category" items="${categoryList}">
								<li>
									<div onclick="match(${category.id})" class="game--category--menu">${category.gameName}</div>
								</li>
							</c:forEach>
							<li>
								<div onclick="location.href='${pageContext.request.contextPath}/admin/dashboard'" class="game--category--menu">기타게임</div>
							</li>
						</div>
					</ul>
				</div>
			</div>
			<div class="menu-container">
				<a href="" class="menu">게임 강의</a>
				<ul class="drop-down-menus-game">
					<div class="game-menu-box">
						<c:forEach var="category" items="${categoryList}">
							<li>
								<div onclick="location.href='${pageContext.request.contextPath}/lecture/category/${category.id}'" class="game--category--menu">${category.gameName}</div>
							</li>
						</c:forEach>
						<li>
							<div onclick="location.href='${pageContext.request.contextPath}/admin/dashboard'" class="game--category--menu">기타게임</div>
						</li>
					</div>
				</ul>
			</div>
			<div class="menu-container">
				<a href="" class="menu">커뮤니티</a>	
				<ul class="drop-down-menus-game">
					<div class="game-menu-box">
						<li>
							<div onclick="location.href='${pageContext.request.contextPath}/admin/dashboard'" class="game--category--menu">자유게시판</div>
						</li>
						<c:forEach var="category" items="${categoryList}">
							<li>
								<div onclick="location.href='${pageContext.request.contextPath}/community/category/${category.id}'" class="game--category--menu">${category.gameName}</div>
							</li>
						</c:forEach>
						<li>
							<div onclick="location.href='${pageContext.request.contextPath}/admin/dashboard'" class="game--category--menu">기타게임</div>
						</li>
					</div>
				</ul>
			</div>
			<div class="menu-container">
				<a href="" class="menu">친구</a>
				<div class="drop-down-menus">
					<ul>
						<li><a href='${pageContext.request.contextPath}/friends/all'><h4>나의 친구</h4></a></li>
						<li><a href='${pageContext.request.contextPath}/friends/findUser'><h4>친구 추가</h4></a></li>
						<li><a href='${pageContext.request.contextPath}/friends/wait'>대기중인 초대</h4></a></li>
					</ul>
				</div>
			</div>
			<p>|</p>
			<div class="menu-container">
				<a href="/notice/news-list" class="menu">뉴스</a>
				<ul class="drop-down-menus">
					<li onclick="location.href='/notice/notice-list'"><h4>공지사항</h4></li>
					<li onclick="location.href='/notice/news-list'"><h4>게임 뉴스</h4></li>
				</ul>
			</div>

			<div class="menu-container">
				<a href="/cs/main" class="menu">고객 지원</a>
				<ul class="drop-down-menus">
					<li><h4>Q&A</h4></li>
				</ul>
			</div>
			<div class="menu-container">
				<a href="" class="menu">콘텐츠</a>
				<div class="drop-down-menus">
					<ul>
						<li><a href='${pageContext.request.contextPath}/test/start-test'><h4>게임 성향 테스트</h4></a></li>
					</ul>
				</div>
			</div>
			<div class="menu-container">
				<a href="${pageContext.request.contextPath}/my-page/" class="menu">회원 정보</a>
			</div>	
		</div>
	</div>
</div>
<!-- 알람 받는 소켓 ON -->
<script>
	var socket = new WebSocket("ws://192.168.0.131:8080/alarm");
	socket.onmessage = function(event) {
		const message = JSON.parse(event.data); // json 형식인 alarmDTO를 다시 객체로 만듬
		const profile = message.uploadFileName; // 상대 프로필 사진
		const name = message.nickname; // 상대 이름
		const id = message.sendUserId; // 상대 userId
		const content = message.content; // 알람 내용 예) ** 님의 댓글 : 이건좀 아닌데
	}

	function match(id) {
		if (${principal.mbti == 0}) {
			alert("먼저 mbti 검사를 하셔야합니다!");
		} else {
			location.href = "http://192.168.123.140:8080/chat/match?type=" + id;
		}
	}
</script>