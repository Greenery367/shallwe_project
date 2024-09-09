<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/view/layout/header.jsp" %>
<link rel="stylesheet" href="/static/css/cs.css">
<div class="qna-container">
	<nav class="qna-nav">
		<ul>
			<li><a href="main">자주 묻는 질문</a></li>
			<li><a href="FAQ">FAQ</a></li>
			<li><a href="#">1:1 문의</a></li>
			<li><a href="#">공지사항</a></li>
		</ul>
	</nav>

	<table class="qna-table">
		<thead>
			<tr>
				<th>No</th>
				<th>글 제목</th>
				<th>작성자</th>
				<th>작성 시간</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="post" varStatus="status" items="${postList}">
				<tr>
					<td>${status.count}</td>
					<td>${post.title}</td>
					<td>${post.writer}</td>
					<td>${post.createdAt}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<div class="pagination">
		<ul>
			<li class="page-item <c:if test='${curruntPage == 1}'>disabled</c:if>">
				<a class="page-link" href="?page=1&size=${size}">First</a>
			</li>
			<li class="page-item <c:if test='${curruntPage == 1}'>disabled</c:if>">
				<a class="page-link" href="?page=${curruntPage-1}&size=${size}">Previous</a>
			</li>
			<c:set var="beginPage" value="${curruntPage - 2}" />
			<c:set var="endPage" value="${curruntPage + 2}" />
			<c:if test="${beginPage < 1}">
				<c:set var="beginPage" value="1" />
			</c:if>
			<c:if test="${endPage > totalPages}">
				<c:set var="endPage" value="${totalPages}" />
			</c:if>
			<c:forEach begin="${beginPage}" end="${endPage}" var="page">
				<li class="page-item <c:if test='${page == curruntPage}'>active</c:if>">
					<a class="page-link" href="?page=${page}&size=${size}">${page}</a>
				</li>
			</c:forEach>
			<li class="page-item <c:if test='${curruntPage == totalPages}'>disabled</c:if>">
				<a class="page-link" href="?page=${curruntPage+1}&size=${size}">Next</a>
			</li>
			<li class="page-item <c:if test='${curruntPage == totalPages}'>disabled</c:if>">
				<a class="page-link" href="?page=${totalPages}&size=${size}">Last</a>
			</li>
		</ul>
	</div>
	
	<form class ="wrbtn" action="post-FAQ">
		<button class="qna-write-btn" type="submit">문의글 작성</button>
	</form>
</div>

<%@ include file="/WEB-INF/view/layout/footer.jsp" %>
