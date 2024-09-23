<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/layout/header.jsp"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/lectureDetail.css">

<div class="container">
	<!-- 메시지 표시 -->
	<c:if test="${not empty message}">
		<div class="alert">
			<p>${message}</p>
		</div>
	</c:if>

	<!-- 강의 상세 정보와 이미지가 가로로 배치됨 -->
	<div class="lecture-detail">
		<c:choose>
			<c:when test="${empty lecture.uploadFileName}">
				<img src="${pageContext.request.contextPath}/static/images/default.png" alt="${lecture.title}" style="width: 150px; height: 150px; border-radius: 50%; object-fit: cover;" />
			</c:when>
			<c:otherwise>
				<img src="${pageContext.request.contextPath}/images/${lecture.uploadFileName}" alt="${lecture.title}"
					style="width: 150px; height: 150px; border-radius: 50%; object-fit: cover;" />
			</c:otherwise>
		</c:choose>
		<div class="lecture-info">
			<h1>${lecture.title}</h1>
			<p>
				평균 평점: <span id="avgRating"></span> / 5
			</p>
			<p>${lecture.subtitle}</p>
			<p>강사: ${lecture.nickName}</p>
			<p>수강생: ${lecture.currentNum} / ${lecture.limitNum}</p>
			<p>
				가격: <span class="price">${lecture.price}원</span>
			</p>

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
				<p>
					<strong>${review.nickName}</strong>
				</p>
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
			<label for="grade">평점</label> <select id="grade" name="grade" required>
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
		<p>
			가격: <span class="price">${lecture.price}원</span>
		</p>

		<!-- 결제 완료 버튼 -->
		<form id="paymentForm" action="${pageContext.request.contextPath}/lecture/payment/complete" method="post" onsubmit="return validatePayment()">
			<input type="hidden" name="classId" value="${lecture.id}" /> <input type="hidden" name="price" value="${lecture.price}" />
			<button id="completePaymentButton" type="submit" class="btn complete-payment-btn">결제 완료</button>
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
	background-color: rgb(0, 0, 0);
	background-color: rgba(0, 0, 0, 0.4);
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

.close:hover, .close:focus {
	color: black;
	text-decoration: none;
	cursor: pointer;
}

.alert {
	color: red;
	font-weight: bold;
}
</style>

<!-- JavaScript -->
<script>
	//JSP에서 전달된 avg 값을 소수점 한 자리까지 반올림
	const avg = parseFloat("${avg}");
	const avgFormatted = avg.toFixed(1); // 소수점 한 자리까지 반올림

	// avg 값을 HTML에 삽입
	document.getElementById("avgRating").textContent = avgFormatted;

	// 모달 열기
	function openPaymentModal() {
		document.getElementById("paymentModal").style.display = "block";
	}

	// 모달 닫기
	function closePaymentModal() {
		document.getElementById("paymentModal").style.display = "none";
	}

	// 결제 검증 및 처리
	function validatePayment(event) {
		event.preventDefault(); // 기본 폼 제출 방지

		var userCash = $
		{
			user.currentCash
		}
		; // 서버에서 전달받은 현재 캐시
		var lecturePrice = $
		{
			lecture.price
		}
		; // 서버에서 전달받은 강의 가격

		if (userCash < lecturePrice) {
			// 잔액 부족 시 경고
			alert("잔액이 부족합니다. 캐시를 충전한 후 다시 시도해 주세요.");
			closePaymentModal(); // 모달 닫기
			return false; // 결제 진행하지 않음
		} else {
			// 결제 성공 시 알림
			alert("결제가 완료되었습니다.");
			// 결제 완료 후 폼 제출
			var paymentForm = document.getElementById("paymentForm");
			paymentForm.submit(); // 폼 제출
			return true; // 결제 진행
		}
	}

	document.addEventListener("DOMContentLoaded", function() {
		var paymentForm = document.getElementById("paymentForm");
		paymentForm.addEventListener("submit", validatePayment);
	});
</script>

<%@ include file="/WEB-INF/view/layout/footer.jsp"%>
