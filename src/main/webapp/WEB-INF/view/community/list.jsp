<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="/WEB-INF/view/layout/header.jsp"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/board.css">
<div class="main-page">
	<h1 href="">${categoryId}  게시글 목록</h1>

	<!-- 새 게시글 작성 버튼 -->
	<button class="new-post-button" type="button" onclick="window.location.href='${pageContext.request.contextPath}/community/createBoard/${categoryId}';">새글 작성</button>

	<table>
		<thead>
			<tr>
				<td>번호</td>
				<td>제목</td>
				<td>작성자</td>
				<td>작성일</td>
				<td>조회수</td>
				<td>추천</td>
			</tr>
		</thead>
		<tbody>
			<c:if test="${not empty boardList}">
				<c:forEach var="board" items="${boardList}">
					<tr>
						<th>${board.id}</th>
						<td><a href="/community/boardDetail/${board.id}">${board.title}</a></td>
						<th>${board.nickName}</th>
						<td><fmt:formatDate value="${board.createdAt}" type="DATE" pattern="yyyy-MM-dd" /></td>
						<th>${board.viewNum}</th>
						<th>${board.good}</th>
					</tr>
				</c:forEach>
			</c:if>
			<!-- 게시글이 없을 때 메시지 표시 -->
			<c:if test="${empty boardList}">
				<tr>
					<td colspan="6">게시글이 없습니다.</td>
				</tr>
			</c:if>
		</tbody>
	</table>
	<div class="pagination">
        <c:forEach var="page" begin="1" end="${totalPage}">
            <a href="?searchField=${searchField}&searchValue=${searchValue}&currentPage=${page}">${page}</a>
        </c:forEach>
    </div>

	<div class="container">
		<div class="row">
			<form method="get" action="${pageContext.request.contextPath}/community/category/${categoryId}">
				<select name="searchField">
					<option value="title" ${searchField eq 'title' ? 'selected' : ''} selected>제목</option>
					<option value="content" ${searchField eq 'content' ? 'selected' : ''}>내용</option>
					<option value="nickName" ${searchField eq 'nickName' ? 'selected' : ''}>작성자</option>
				</select> </select>
				<!-- 검색어 입력 필드 -->
				<input type="text" name="searchValue" value="${searchValue}" placeholder="검색어 입력"> <input type="hidden" name="currentPage" value="${currentPage}">
				<!-- 현재 페이지 유지 -->
				<input type="submit" value="검색">
			</form>
		</div>
	</div>
</div>
<%@ include file="/WEB-INF/view/layout/footer.jsp"%>