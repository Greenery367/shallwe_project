<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="/WEB-INF/view/layout/header.jsp" %>
	<h1>게시글 상세보기</h1>

	<!-- 게시글 정보 표시 -->
	<c:if test="${not empty board}">
		<table border="1">
			<tr>
				<th>번호</th>
				<td>${board.id}</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>${board.title}</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>${board.nickName}</td>
			</tr>
			<tr>
				<th>작성일</th>
				<td><fmt:formatDate value="${board.createdAt}" type="DATE" pattern="yyyy-MM-dd" /></td>
			</tr>
			<tr>
				<th>조회수</th>
				<td>${board.viewNum}</td>
			</tr>
			<tr>
				<th>추천</th>
				<td>${board.good}</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>${board.content}</td>
			</tr>
		</table>
	</c:if>

	<button type="button" onclick="window.location.href='${pageContext.request.contextPath}/community/update-board/${board.id}';">수정</button>

	<!-- 게시글 삭제 폼 -->
	<form action="${pageContext.request.contextPath}/community/delet-board/${board.id}" method="post" style="display: inline;">
		<input type="hidden" name="categoryId" value="${board.categoryId}" /> <input type="hidden" name="authorId" value="${board.authorId}" />
		<button type="submit">삭제</button>
	</form>

	<!-- 댓글 목록 표시 -->
	<h2>댓글</h2>
	<c:if test="${not empty comments}">
		<table border="1">
			<thead>
				<tr>
					<th>작성자</th>
					<th>내용</th>
					<th>작성일</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="comment" items="${comments}">
					<tr>
						<td>${comment.nickName}</td>
						<td>${comment.content}</td>
						<td><fmt:formatDate value="${comment.createdAt}" type="DATE" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
	<c:if test="${empty comments}">
		<p>댓글이 없습니다.</p>
	</c:if>

	<!-- 댓글 작성 폼 -->
	<h3>댓글 작성</h3>
	<form action="${pageContext.request.contextPath}/community/createComment" method="post">
		<input type="hidden" name="postId" value="${board.id}" /> <input type="hidden" name="authorId" value="${user.userId}"/>
		<!-- 로그인한 사용자 ID -->
		<div>
			<textarea name="content" rows="4" cols="50" placeholder="댓글을 입력하세요." required></textarea>
		</div>
		<button type="submit">댓글 작성</button>
		
	</form>
	
	

	<!-- 버튼: 목록으로 돌아가기 -->
	<a href="${pageContext.request.contextPath}/community/category/${board.categoryId}?currentPage=${currentPage}">목록으로 돌아가기</a>
	<c:if test="${not empty authorId}">
		<p>작성자 ID: ${authorId}</p>
	</c:if>
	
<%@ include file="/WEB-INF/view/layout/footer.jsp" %>