<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
			<button class="btn pay-btn" onclick="openPaymentModal()">결제하기</button>

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
	<form action="${pageContext.request.contextPath}/lecture/create-review" method="post">
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

<!-- 결제 모달창 -->
<div id="paymentModal" class="modal">
	<div class="modal-content">
		<span class="close" onclick="closePaymentModal()">&times;</span>
		<h2>결제 정보 확인</h2>
		<p>강의명: ${lecture.title}</p>
		<p>강사: ${lecture.nickName}</p>
		<p>가격: <span class="price">${lecture.price}원</span></p>

		<!-- 결제 완료 버튼 -->
		<form action="${pageContext.request.contextPath}/lecture/payment/complete" method="post">
			<input type="hidden" name="classId" value="${lecture.id}" />
			<input type="hidden" name="price" value="${lecture.price}" />
			<button type="submit" class="btn complete-payment-btn">결제 완료</button>
		</form>
	</div>
</div>

<!-- 스타일 (CSS) -->
<style>
	/* The Modal (background) */
	.modal {
		display: none; /* Hidden by default */
		position: fixed;
		z-index: 1;
		left: 0;
		top: 0;
		width: 100%;
		height: 100%;
		overflow: auto;
		background-color: rgb(0,0,0);
		background-color: rgba(0,0,0,0.4);
	}

	/* Modal Content */
	.modal-content {
		background-color: #fefefe;
		margin: 15% auto;
		padding: 20px;
		border: 1px solid #888;
		width: 80%;
	}

	/* Close Button */
	.close {
		color: #aaa;
		float: right;
		font-size: 28px;
		font-weight: bold;
	}

	.close:hover,
	.close:focus {
		color: black;
		text-decoration: none;
		cursor: pointer;
	}
</style>

<!-- JavaScript -->
<script>
	// 모달 열기
	function openPaymentModal() {
		document.getElementById("paymentModal").style.display = "block";
	}

	// 모달 닫기
	function closePaymentModal() {
		document.getElementById("paymentModal").style.display = "none";
	}
</script>

<%@ include file="/WEB-INF/view/layout/footer.jsp"%>
