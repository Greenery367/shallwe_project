<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
<div>
	<table>
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
					<th>${status.count}</th>
					<th>${post.title}</th>
					<th>${post.writer}</th>
					<th>${post.createdAt}</th>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div>
		<ul>
			<!-- previous Page Link -->
			<li class="page-item <c:if test='${curruntPage == 1}'>disabled</c:if>">
				<a class="page-link" href="/page=1/size=${size}">First Page</a>
			</li>
			<li class="page-item <c:if test='${curruntPage == 1}'>disabled</c:if>">
				<a class="page-link" href="/page=${curruntPage-1}/size=${size}">Previous</a>
			</li>
			
			<!-- Page Numbers -->
			<c:set var="beginPage" value="${curruntPage - 2}" />
			<c:set var="endPage" value="${curruntPage + 2}" />
			
			<!-- ensure that beginPage is not less than 1 -->
			<c:if test="${beginPage < 1}">
				<c:set var="beginPage" value="1" />
			</c:if>
			
			<!-- ensure that endPage does not exceed totalPages -->
			<c:if test="${endPage > totalPages}">
				<c:set var="endPage" value="${totalPages}" />
			</c:if>
			
			<c:forEach begin="${beginPage}" end="${endPage}" var="page">
				<li class="page-item <c:if test='${page == curruntPage}'>active</c:if>">
					<a class="page-link" href="?type=${type}&page=${page}&size=${size}">${page}</a>
				</li>
			</c:forEach>
			
			<!-- next Page Link -->
			<li class="page-item <c:if test='${curruntPage == totalPages}'>disabled</c:if>">
				<a class="page-link" href="/page=${curruntPage+1}/size=${size}">Next</a>
			</li>
			<li class="page-item <c:if test='${curruntPage == totalPages}'>disabled</c:if>">
				<a class="page-link" href="/page=${totalPages}/size=${size}">Last Page</a>
			</li>
		</ul>
	</div>
</div>
</body>
</html>
