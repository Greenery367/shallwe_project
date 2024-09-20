<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/layout/header.jsp" %>	
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/lecture.css">
<!DOCTYPE html>
    <meta charset="UTF-8">
    <title>강의 개설 페이지</title>
</head>
<body>
    <h1>강의 개설</h1>
    <form action="/lecture/create-lecture" method="POST">
        <!-- 카테고리 선택 -->
        <div>
            <label for="categoryId">카테고리</label>
            <select id="categoryId" name="categoryId" required>
                <option value="">카테고리를 선택하세요</option>
                <c:forEach var="category" items="${categoryList}">
                    <option value="${category.id}">${category.gameName}</option>
                </c:forEach>
            </select>
        </div>
        
        
        <!-- 인풋 필드 히든 타입으로 userId 를 컨트롤러에 전달 -->
        <div>
            <input type="hidden" id="authorId" name="authorId" value="${user.userId}" >
        </div>

        <div>
            <label for="title">강의 제목</label>
            <input type="text" id="title" name="title" maxlength="20" required placeholder="강의제목 입력란(20자 이내)">
        </div>
        
        <div>
            <label for="subtitle">강의 부제</label>
            <input type="text" id="subtitle" name="subtitle" maxlength="30" required placeholder="간략한 소개 입력란(30자 제한)">
        </div>
        
        <div>
            <label for="content">강의 내용</label>
            <textarea id="content" name="content" rows="5" required placeholder="강의 세부 내용을 서술해 주세요."></textarea>
        </div>
        
        <div>
            <label for="limitNum">수강 인원 제한</label>
            <input type="number" id="limitNum" name="limitNum">
        </div>

        <!-- 강의 가격 -->
        <div>
            <label for="price">강의 가격 (₩)</label>
            <input type="number" id="price" name="price">
        </div>

        <!-- 총 강의 횟수 -->
        <div>
            <label for="totalNum">총 강의 회차</label>
            <input type="number" id="totalNum" name="totalNum">
        </div>

        <!-- 강의 개설 버튼 -->
        <div>
            <button type="submit">강의 개설</button>
        </div>
    </form>
<%@ include file="/WEB-INF/view/layout/footer.jsp" %>
