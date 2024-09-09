<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="https://fonts.googleapis.com/css2?family=Lato&display=swap" rel="stylesheet">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">


<title>관리자 페이지</title>
<link rel="stylesheet" href="/css/adminCommon.css">
</head>
<body>
	<div class="container">
		<div class="header">
			<div>
				<img src="../static/images/logo.png" alt="로고">
			</div>
			<div class="admin-title">
				<h1>관리자 페이지</h1>
			</div>
			<div class="header-menu">
				<button class="btn-open-modal-alarm">알림</button>
				<button class="btn-open-modal-qna">Q&A</button>
				<button class="btn-open-modal-report">신고내역</button>
			</div>
		</div>
		<div class="main-content">
			<div class="sidebar">
				<h2>관리 목록</h2>
				<ul>
					<li><a href="admin/dashboard">대시보드</a></li>
					<li><a href="user">이용자 관리</a></li>
					<li><a href="cash">캐쉬</a></li>
					<li><a href="support">고객 지원</a></li>
					<li><a href="admin/advertise">광고 / 배너</a></li>
					<li><a href="admin/community">카테고리 / 게시판</a></li>
					<li><a href="notice">뉴스 / 공지</a></li>
				</ul>
			</div>
			<div class="content">