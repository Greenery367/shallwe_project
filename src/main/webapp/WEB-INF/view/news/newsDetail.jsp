<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@ include file="/WEB-INF/view/layout/header.jsp" %>		
	<link rel="stylesheet" href="/static/css/news.css">
	<div class="news-detail-container">
	<div class="detail-container">
		<h2 class="title">${news.title}</h2>
		<p class="date">${news.createdAt}</p>
		<hr/>
		<img src="/static/images/news/${news.url}" class="image-detail-container">
		<h3 class="detail-text-box">${news.subTitle}</h3>
		<p class="detail-text-box">${news.content}</p>
		<button onClick="location.href='/notice/news-list'" class="list-button">목록으로 돌아가기</button>
	</div>
	</div>
<%@ include file="/WEB-INF/view/layout/footer.jsp" %>	
	