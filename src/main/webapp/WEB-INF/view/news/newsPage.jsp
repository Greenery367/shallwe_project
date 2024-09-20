<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@ include file="/WEB-INF/view/layout/header.jsp" %>
	<link rel="stylesheet" href="/static/css/news.css">
	<div class="news-container">
		<h1>뉴스 (${totalNewsNum})</h1>
			<c:forEach var="news" items="${newsList}">	
				<div class="news-box" OnClick="location.href ='news-detail/${news.id}'" style="cursor:pointer;">
					<img src="../static/images/news/${news.url}" class="image-container">
					<div class="column-box">
						<h3>${news.title}</h3>
						<h5 class="text-box date" >${news.subTitle}</h5>
						<p>${news.createdAt}</p>
					</div>	
				</div>
			</c:forEach>
			<div class = "pagination">
				<c:forEach var="number" begin="1" end="${pageSize}" >
					<button class="pagination-btn">${number}</button>
				</c:forEach>
			</div>
	</div>
<%@ include file="/WEB-INF/view/layout/footer.jsp" %>	
	