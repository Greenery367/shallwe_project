<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.css"/>
<link rel="stylesheet" href="../css/header.css">
<script src="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.js"></script>
</head>
<body>
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
	
</script>
<div class="main">
	<header>
		<div class="header">
			<div class="account-menus">
					<a href="">홈</a>
					<a href="">로그인</a>
					<a href="">회원가입</a>
				</div>
			</div>
		</div>
	</header>
				<div class="banner">
					<img class="logo" alt="로고" src="../images/logo.png">
				</div>
	
	<div class="nav-bar">
		<div class="menus">
			<a href="" class="menu">친구 찾기</a>
			<a href="" class="menu">게임 강의</a>
			<a href="" class="menu">커뮤니티</a>
			<p>|</p>
			<a href="" class="menu">뉴스</a>
			<a href="" class="menu">고객 지원</a>
			<a href="" class="menu">콘텐츠</a>
			<a href="" class="menu">회원 정보</a>
		</div>
	</div>
	
	<div class="main-board">
		<div><img  class="admin-main-1" alt="로고" src="../images/banner1.png"></div>
	
		<div class="main-page">
			<div class="swiper">
				<div class="swiper-wrapper"></div>
			</div>
		