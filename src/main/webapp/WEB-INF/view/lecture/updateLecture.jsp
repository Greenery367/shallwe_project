<%@page import="com.example.demo.repository.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>강의 정보 수정 페이지</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/createLecture.css">
</head>
<body>
    <!-- 헤더 포함 -->
    <%@ include file="/WEB-INF/view/layout/header.jsp" %>

    <!-- 컨텐츠 시작 -->
    <div class="container">
        <h1>강의 정보 수정</h1>
        <form action="/lecture/lecture-update" method="POST">
            <!-- 강좌 ID (숨김 필드) -->
            <input type="hidden" id="classId" name="classId" value="${lecture.id}" />

            <!-- 카테고리 수정 -->
            <div>
                <label for="categoryId">카테고리</label>
                <select id="categoryId" name="categoryId" required>
                    <option value="">카테고리를 선택하세요</option>
                    <c:forEach var="category" items="${categoryList}">
                        <option value="${category.id}" <c:if test="${lecture.categoryId == category.id}">selected</c:if>>${category.gameName}</option>
                    </c:forEach>
                </select>
            </div>

            <!-- 강좌 작성자 ID (숨김 필드) -->
            <input type="hidden" id="authorId" name="authorId" value="${user.userId}" />

            <!-- 강의 제목 -->
            <div>
                <label for="title">강의 제목</label>
                <input type="text" id="title" name="title" maxlength="20" required value="${lecture.title}" placeholder="강의제목 입력란(20자 이내)">
            </div>

            <!-- 강의 부제 -->
            <div>
                <label for="subtitle">강의 부제</label>
                <input type="text" id="subtitle" name="subtitle" maxlength="30" required value="${lecture.subtitle}" placeholder="간략한 소개 입력란(30자 제한)">
            </div>

            <!-- 강의 내용 -->
            <div>
                <label for="content">강의 내용</label>
                <textarea id="content" name="content" rows="5" required placeholder="강의 세부 내용을 서술해 주세요.">${lecture.content}</textarea>
            </div>

            <!-- 강의 가격 -->
            <div>
                <label for="price">강의 가격 (₩)</label>
                <input type="number" id="price" name="price" value="${lecture.price}">
            </div>

            <!-- 총 강의 회차 -->
            <div>
                <label for="totalNum">총 강의 회차</label>
                <input type="number" id="totalNum" name="totalNum" value="${lecture.totalNum}">
            </div>

            <!-- 강의 수정 버튼 -->
            <div>
                <button type="submit">강의 수정</button>
            </div>
        </form>
    </div>

    <!-- 푸터 포함 -->
    <%@ include file="/WEB-INF/view/layout/footer.jsp" %>
</body>
</html>
