<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 목록</title>
</head>
<body>
    <h1>게시글 목록</h1>

    <!-- 새 게시글 작성 버튼 -->
	<button type="button" onclick="window.location.href='${pageContext.request.contextPath}/community/createBoard';">새글 작성</button>

    <table border="1">
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
            <c:forEach var="board" items="${boardList}">
                <tr>
                    <th>${board.id}</th>
                    <td><a href="/community/boardDetail/${board.id}">${board.title}</a></td>
                    <th>${board.author}</th>
                     <td><fmt:formatDate value="${board.createdAt}" type="DATE" pattern="yyyy-MM-dd"/></td>
                    <th>${board.viewNum}</th>
                    <th>${board.good}</th>
                </tr>
            </c:forEach>
        </tbody>
    </table>

</body>
</html>
