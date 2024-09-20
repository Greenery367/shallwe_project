<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/layout/header.jsp"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/lectureDetail.css">

<div class="container">
	<!-- 강의 상세 정보와 이미지가 가로로 배치됨 -->
	<div class="lecture-detail">
		<img src="/images/${lecture.uploadFileName}" alt="강사 사진"
							style="width: 150px; height: 150px; border-radius: 50%; object-fit: cover;" />
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
	
	<div>
		<div>수강 인원 수 : ${spends.size()}명</div>
		<div>수강평 갯수 : ${reviews.size()}개</div>
		<div>강의 수익 : ${spendSum}원(₩)</div>
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

</div>

<%@ include file="/WEB-INF/view/layout/footer.jsp"%>
