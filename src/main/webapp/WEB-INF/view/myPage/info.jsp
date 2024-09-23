<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="/WEB-INF/view/layout/header.jsp"%>

<style>
/* 기본 레이아웃 스타일 */
.container {
	display: flex;
}

.main-content {
	width: 80%;
	padding: 10px;
}

/* 개인정보 수정 영역 */
.personal-info {
	margin-bottom: 20px;
}

.personal-info h3 {
	font-size: 24px;
}

/* 프로필 사진 */
.profile-photo {
	display: flex;
	align-items: center;
	margin-bottom: 20px;
}

.profile-photo img {
	width: 150px;
	height: 150px;
	border-radius: 50%;
	object-fit: cover;
}

.profile-photo form {
	margin-left: 20px;
}

/* 개인정보 항목들 */
.personal-info-form {
	margin-bottom: 10px;
}

.personal-info-form label {
	font-weight: bold;
}

.personal-info-form input[type="text"] {
	width: 200px;
	padding: 5px;
	border: 1px solid #ccc;
	border-radius: 4px;
}

.personal-info-form button {
	margin-left: 10px;
	padding: 5px 10px;
	border: 1px solid #ccc;
	border-radius: 4px;
	background-color: ##4CAF50;
	cursor: pointer;
}

.personal-info-form button:hover {
	background-color: #45a049;
}

/* 계좌 관리 섹션 */
.account-management h2 {
	font-size: 20px;
	margin-bottom: 10px;
}

.account-management form {
	display: flex;
	align-items: center;
	gap: 10px;
}

.account-management select, .account-management input[type="text"] {
	padding: 5px;
	border: 1px solid #ccc;
	border-radius: 4px;
	width: 200px;
}

.account-management button {
	padding: 5px 10px;
	border: 1px solid #ccc;
	border-radius: 4px;
	background-color: ##4CAF50;
	cursor: pointer;
}

.account-management button:hover {
	background-color: #45a049;
}

/* 캐시 및 포인트 섹션 */
.cash-info {
	margin-top: 20px;
}

.cash-info h3 {
	font-size: 24px;
}

.cash-info p {
	font-size: 18px;
	margin-bottom: 5px;
}

/* 버튼 스타일 */
button {
	padding: 5px 10px;
	border-radius: 4px;
	height : 36px;
	width: 80px;
	background-color: #4CAF50;
	color: white;
	border: none;
	cursor: pointer;
}

button:hover {
	background-color: #45a049;
}

button:disabled {
	background-color: #ccc;
	cursor: not-allowed;
}

.custom-file-upload {
    display: inline-block;
    padding: 5px 10px;
    border: 1px solid #ccc;
    border-radius: 4px;
    background-color: #4CAF50; /* 기존 버튼 색상 */
    color: white; /* 글자 색상 */
    cursor: pointer;
}

.custom-file-upload input[type="file"] {
    display: none; /* 기본 파일 입력 숨김 */
}

.custom-file-upload:hover {
    background-color: #45a049; /* 호버 효과 */
}
</style>

<div class="container">
	<%@ include file="/WEB-INF/view/layout/myPageMenu.jsp"%>
	<!-- 개인정보 수정 영역 -->
	<div class="main-content">
		<h3>개인정보</h3>

		<!-- 프로필 사진 -->
		<div class="profile-photo">
			<div>
				<c:choose>
					<c:when test="${empty user.uploadFileName}">
						<img src="/static/images/default.png" alt="프로필 사진" />
					</c:when>
					<c:otherwise>
						<img src="/images/${user.uploadFileName}" alt="프로필 사진" />
					</c:otherwise>
				</c:choose>
			</div>
			<div>
				<!-- 프로필 사진 변경 폼 -->
				<form action="/my-page/update-profile" method="post" enctype="multipart/form-data">
					<label class="custom-file-upload"> <input type="file" name="file" accept="image/*" /> 사진 선택
					</label>
					<button type="submit">사진 변경</button>
				</form>
			</div>
		</div>

		<!-- 개인정보 항목들 -->
		<div class="personal-info">
			<div>
				<label>ID:</label> <strong>${user.id}</strong>
			</div>

			<!-- 이름 수정 폼 -->
			<form action="/my-page/update-username" method="post" onsubmit="return confirmUpdate()" class="personal-info-form">
				<div>
					<label>이름:</label> <input type="text" name="username" placeholder="${user.username}" required />
					<button type="submit">개명</button>
				</div>
			</form>

			<form action="/my-page/update-nickname" method="post" onsubmit="return confirmUpdate()" class="personal-info-form">
				<div>
					<label>닉네임:</label> <input type="text" name="nickname" placeholder="${user.nickname}" required />
					<button type="submit">변경</button>
				</div>
			</form>

			<form action="/my-page/update-phone-number" method="post" onsubmit="return confirmUpdate()" class="personal-info-form">
				<div>
					<label>핸드폰:</label> <input type="text" name="phoneNumber" placeholder="${user.phoneNumber}" maxlength="13" oninput="formatPhoneNumber(this)" required />
					<button type="submit">변경</button>
				</div>
			</form>

			<div>
				<label>이메일:</label> <strong>${user.email}</strong>
			</div>

			<c:choose>
				<c:when test="${not empty bankAccount}">
					<!-- 계좌 정보가 있을 때 (수정 모드) -->
					<h2>계좌 수정</h2>
					<form action="${pageContext.request.contextPath}/my-page/saveOrUpdateAccount" method="post" onsubmit="return confirmUpdate()" class="account-management">
						<input type="hidden" name="userId" value="${userId}" /> <label for="bankId">은행 선택:</label> <select id="bankId" name="bankId">
							<c:forEach items="${banks}" var="bank">
								<option value="${bank.bankId}" <c:if test="${bank.bankId == bankAccount.bankId}">selected</c:if>>${bank.bankName}</option>
							</c:forEach>
						</select> <label for="accountNumber">계좌 번호:</label> <input type="text" id="accountNumber" name="accountNumber" placeholder="${bankAccount.accountNumber}" maxlength="16"
							oninput="formatAccountNumber(this)" onkeypress="return restrictInputToNumbers(event)" required />
						<button type="submit">수정</button>
					</form>
				</c:when>
				<c:otherwise>
					<!-- 계좌 정보가 없을 때 (생성 모드) -->
					<h2>계좌 생성</h2>
					<form action="${pageContext.request.contextPath}/my-page/saveOrUpdateAccount" method="post" class="account-management">
						<input type="hidden" name="userId" value="${userId}" /> <label for="bankId">은행 선택:</label> <select id="bankId" name="bankId">
							<c:forEach items="${banks}" var="bank">
								<option value="${bank.bankId}">${bank.bankName}</option>
							</c:forEach>
						</select> <label for="accountNumber">계좌 번호:</label> <input type="text" id="accountNumber" name="accountNumber" placeholder="최대 숫자 16자리 입력" maxlength="16"
							oninput="formatAccountNumber(this)" onkeypress="return restrictInputToNumbers(event)" required />
						<button type="submit">생성</button>
					</form>
				</c:otherwise>
			</c:choose>
		</div>

		<!-- 캐시 및 포인트 관리 -->
		<h3>캐시 및 포인트</h3>
		<div class="cash-info">
			<p>
				<strong>현재 캐시:</strong> ${user.currentCash} 캐시
			</p>
			<p>
				<strong>현재 포인트:</strong> ${user.lectureCash} 포인트
			</p>
		</div>
	</div>
</div>

<script>
	function confirmUpdate() {
		return confirm("정말 수정 하시겠습니까?");
	}

	function formatPhoneNumber(input) {
		// 입력값에서 모든 비숫자 문자 제거
		let value = input.value.replace(/\D/g, '');
		// 10자리 숫자까지만 허용
		if (value.length > 11) {
			value = value.slice(0, 11);
		}
		// 포맷 설정
		let formattedValue = '';
		if (value.length > 6) {
			formattedValue += value.slice(0, 3) + '-';
			formattedValue += value.slice(3, 7) + '-';
			formattedValue += value.slice(7);
		} else if (value.length > 3) {
			formattedValue += value.slice(0, 3) + '-';
			formattedValue += value.slice(3);
		} else {
			formattedValue += value;
		}
		// 입력 필드에 포맷된 값 적용
		input.value = formattedValue;
	}

	function formatAccountNumber(input) {
		// 입력값에서 모든 비숫자 문자 제거
		let value = input.value.replace(/\D/g, '');
		// 16자리까지만 허용
		if (value.length > 16) {
			value = value.slice(0, 16);
		}
		// 입력 필드에 값 적용
		input.value = value;
	}

	function restrictInputToNumbers(event) {
		// 키가 숫자(48-57) 또는 백스페이스(8)인지 확인
		let keyCode = event.which || event.keyCode;
		if ((keyCode >= 48 && keyCode <= 57) || keyCode === 8) {
			return true;
		}
		event.preventDefault(); // 숫자와 백스페이스가 아닌 경우 입력을 차단
		return false;
	}
</script>

<%@ include file="/WEB-INF/view/layout/footer.jsp"%>
