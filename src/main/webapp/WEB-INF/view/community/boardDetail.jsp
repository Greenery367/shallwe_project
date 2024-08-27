<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세보기</title>
</head>
<body>
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
                <td>${board.author}</td>
            </tr>
            <tr>
                <th>작성일</th>
                <td><fmt:formatDate value="${board.createdAt}" type="DATE" pattern="yyyy-MM-dd"/></td>
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

    <!-- 버튼: 목록으로 돌아가기 -->
    <a href="${pageContext.request.contextPath}/community/">목록으로 돌아가기</a>
</body>
</html>
