<%@page import="com.example.demo.repository.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>강의 상세 정보</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/lectureReview.css">
</head>
<body>
    <!-- 헤더 포함 -->
    <%@ include file="/WEB-INF/view/layout/header.jsp" %>

    <!-- 강의 상세 정보 -->
    <div class="container">
        <div class="lecture-detail">
            <img src="/images/${lecture.uploadFileName}" alt="강사 사진">
            <div class="lecture-info">
                <h1>${lecture.title}</h1>
                <p>평균 평점: ${avg} / 5</p>
                <p>${lecture.subtitle}</p>
                <p>강사: ${lecture.nickName}</p>
                <p>수강생: ${lecture.currentNum} / ${lecture.limitNum}</p>
                <p>가격: <span class="price">${lecture.price}원</span></p>
                <p>강의 내용: ${lecture.content}</p>
            </div>
        </div>

        <!-- 수강 인원 수, 수강평 갯수, 수익을 카드형으로 가로 배치 -->
        <div class="stats-container">
            <div class="stat-card">
                <h3>수강 인원</h3>
                <p>${spends.size()}명</p>
            </div>
            <div class="stat-card">
                <h3>수강평 갯수</h3>
                <p>${reviews.size()}개</p>
            </div>
            <div class="stat-card">
                <h3>강의 수익</h3>
                <p>${spendSum}원(₩)</p>
            </div>
        </div>

        <!-- 리뷰 섹션 -->
        <div class="reviews">
            <h2>리뷰</h2>
            <c:forEach var="review" items="${reviews}">
                <div class="review">
                    <strong>${review.nickName}</strong>
                    <p>${review.comment}</p>
                    <p>평점: ${review.grade} / 5</p>
                </div>
            </c:forEach>
        </div>
    </div>

    <!-- 푸터 포함 -->
    <%@ include file="/WEB-INF/view/layout/footer.jsp" %>
</body>
</html>
