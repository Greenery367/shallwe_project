<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@ include file="/WEB-INF/view/layout/header.jsp" %>		
	<link rel="stylesheet" href="/static/css/news.css">
	<div class="news-detail-container">
	<div class="detail-container">
		<h1>${news.title}</h1>
		<p>${news.createdAt}</p>
		<hr/>
		<img src="/static/images/news/${news.image}" class="image-detail-container">
		<h2 class="detail-text-box">${news.subTitle}</h2>
		<p class="detail-text-box">${news.content}</p>
		<button  onClick="location.href='/notice/news-list'">목록으로 돌아가기</button>
	</div>
	</div>
<%@ include file="/WEB-INF/view/layout/footer.jsp" %>	
	