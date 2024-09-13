<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@ include file="/WEB-INF/view/layout/header.jsp" %>	
	<link rel="stylesheet" href="/static/css/news.css">
	<div class="news-container">
		<h1>뉴스</h1>
			<c:forEach var="news" items="${newsList}">	
				<div class="news-box" OnClick="location.href ='news-detail/${news.id}'" style="cursor:pointer;">
					<img src="../static/images/news/${news.image}" class="image-container">
					<div class="column-box">
						<h3>${news.title}</h3>
						<h5 class="text-box">${news.subTitle}</h5>
						<p>${news.createdAt}</p>
					</div>	
				</div>
			</c:forEach>
	</div>
<%@ include file="/WEB-INF/view/layout/footer.jsp" %>	
	