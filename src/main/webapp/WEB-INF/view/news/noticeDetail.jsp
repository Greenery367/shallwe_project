<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@ include file="/WEB-INF/view/layout/header.jsp" %>		
	<link rel="stylesheet" href="/static/css/news.css">
	<div class="news-detail-container">
	<div class="notice-detail-container">
		<h2 class="title">${notice.title}</h2>
		<p class="date">${notice.createdAt}</p>
		<hr/>
		<p class="detail-text-box">${notice.content}</p>
		<button  onClick="location.href='/notice/notice-list'" class="list-button">목록으로 돌아가기</button>
	</div>
	</div>
<%@ include file="/WEB-INF/view/layout/footer.jsp" %>	
	