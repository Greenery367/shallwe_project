<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@ include file="/WEB-INF/view/layout/header.jsp" %>	
	<link rel="stylesheet" href="/static/css/news.css">
	
	<div class="notice--container">
	
		<div class="notice-container">
		<h1>공지사항 (${noticeList.size()})</h1>
		<table class="notice-table">
			<thead>
				<tr>
					<th></th>
					<th>No</th>
					<th>글 제목</th>
					<th>작성 시간</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="notice" items="${noticeList}">
					<tr onclick="location.href='/notice/notice-detail/${notice.id}'">
						<td></td>
						<td>${notice.id}</td>
						<td>${notice.title}</td>
						<td>${notice.createdAt}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	
	</div>
	</div>
	
<%@ include file="/WEB-INF/view/layout/footer.jsp" %>	
	