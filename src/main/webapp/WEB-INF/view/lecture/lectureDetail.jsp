<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/view/layout/header.jsp"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/lectureDetail.css">

<div class="container">
	<!-- 강의 상세 정보와 이미지가 가로로 배치됨 -->
	<div class="lecture-detail">
		<img src="${pageContext.request.contextPath}/static/images/lalLecture.png" alt="${lecture.title}">
		<div class="lecture-info">
			<h1>${lecture.title}</h1>
			<p>평균 평점: ${averageRating} / 5</p>
			<p>${lecture.subtitle}</p>
			<p>강사: ${lecture.nickName}</p>
			<p>수강생: ${lecture.currentNum} / ${lecture.limitNum}</p>
			<p>가격: <span class="price">${lecture.price}원</span></p>

			<!-- 결제 버튼 -->
			<button class="btn pay-btn">결제하기</button>

			<!-- 결제 전 채팅 -->
			<button class="btn chat-btn">채팅 문의</button>

			<p>강의 내용: ${lecture.content}</p>
		</div>
	</div>

	<!-- 리뷰 섹션을 이미지 및 설명 아래에 배치 -->
	<div class="reviews">
		<h2>리뷰</h2>
		<c:forEach var="review" items="${reviews}">
			<div class="review">
				<p><strong>${review.nickName}</strong></p>
				<p>${review.comment}</p>
				<p>평점: ${review.grade} / 5</p>
			</div>
		</c:forEach>
	</div>

	<!-- 리뷰 작성 폼 -->
	<form action="${pageContext.request.contextPath}/lecture/createReview" method="post">
		<input type="hidden" name="classId" value="${lecture.id}" />
		<div>
			<label for="comment">리뷰 작성</label>
			<textarea id="comment" name="comment" required></textarea>
		</div>
		<div>
			<label for="grade">평점</label>
			<select id="grade" name="grade" required>
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
			</select>
		</div>
		<button type="submit">리뷰 작성</button>
	</form>
</div>

<%@ include file="/WEB-INF/view/layout/footer.jsp"%>
