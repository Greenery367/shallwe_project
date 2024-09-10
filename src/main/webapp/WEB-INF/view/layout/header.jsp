<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게임 친구 매칭 사이트: 셸위?</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.css"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.js"></script>
<link rel="stylesheet" href="/static/css/header.css">
</head>
<body>
<div class="main">
	<header>
		<div class="header">
			<div class="account-menus">
			
					<img class="mini-logo" alt="로고" src="/static/images/shallwe-icon.png">
					<a href="/test/main"><b>홈</b></a>
					<p>|</p>
					<a href="/user/sign-in"><b>로그인</b></a>
					<p>|</p>
					<a href="/user/sign-up"><b>회원가입</b></a>
					<p>|</p>
					<div class="charge-cash">
						<a href="/cash/charge"><b>캐시충전</b></a>
						 <img src="/static/images/cash.png"> 
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
				<span href="#" class="menu">친구 찾기</span>
				<ul class="drop-down-menus-game">
					<li>
						<div class="recommended-user">
							<button class="btn-circle"><img src="/static/images/anonymus.png"></button>
							<div>롤</div>
						</div>
					</li>
					<li>
						<div class="recommended-user">
							<button class="btn-circle"><img src="/static/images/anonymus.png"></button>
							<div>배그</div>
						</div>
					</li>
				</ul>
			</div>
			<div class="menu-container">
				<a href="" class="menu">게임 강의</a>
				<ul class="drop-down-menus-game">
					<li>
						<div class="recommended-user">
							<button class="btn-circle"><img src="/static/images/anonymus.png"></button>
							<div>롤</div>
						</div>
					</li>
					<li>
						<div class="recommended-user">
							<button class="btn-circle"><img src="/static/images/anonymus.png"></button>
							<div>배그</div>
						</div>
					</li>
				</ul>
			</div>
			<div class="menu-container">
				<a href="" class="menu">커뮤니티</a>
				<ul class="drop-down-menus-game">
					<li>
						<div class="recommended-user">
							<button class="btn-circle"><img src="/static/images/anonymus.png"></button>
							<div>롤</div>
						</div>
					</li>
				</ul>
			</div>
			<p>|</p>

			<div class="menu-container">
				<a href="/notice/news-list" class="menu">뉴스</a>
				<ul class="drop-down-menus">
					<li><h4>공지사항</h4></li>
					<li OnClick="location.href ='/notice/news-list'" style="cursor:pointer;"><h4>뉴스</h4></li>
				</ul>
			</div>
			<div class="menu-container">
				<a href="" class="menu">고객 지원</a>
				<ul class="drop-down-menus">
					<li><h4>Q&A</h4></li>
				</ul>
			</div>
			<div class="menu-container">
				<a href="" class="menu">콘텐츠</a>
				<ul class="drop-down-menus">
					<li><h4>게임 성향 테스트</h4></li>
					<li><h4>게임 이상형 월드컵</h4></li>
					<li><h4>게임 챌린지</h4></li>
				</ul>
			</div>	
			<div class="menu-container">
				<a href="" class="menu">회원 정보</a>
			</div>	
		</div>
		
	</div>
	

		