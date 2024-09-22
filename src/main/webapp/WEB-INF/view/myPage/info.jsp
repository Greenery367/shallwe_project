<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="/WEB-INF/view/layout/header.jsp"%>

<div style="display: flex;">
<%@ include file="/WEB-INF/view/layout/myPageMenu.jsp"%>
	<!-- 개인정보 수정 영역 -->
	<div style="width: 80%; padding: 10px;">
		<h3>개인정보</h3>

		<!-- 프로필 사진 -->
		<div style="display: flex; align-items: center; margin-bottom: 20px;">
			<div>
				<c:choose>
					<c:when test="${empty user.uploadFileName}">
						<img src="/static/images/default.png" alt="프로필 사진" style="width: 150px; height: 150px; border-radius: 50%; object-fit: cover;" />
					</c:when>
					<c:otherwise>
						<img src="/images/${user.uploadFileName}" alt="프로필 사진" style="width: 150px; height: 150px; border-radius: 50%; object-fit: cover;" />
					</c:otherwise>
				</c:choose>
			</div>
			<div style="margin-left: 20px;">
				<!-- 프로필 사진 변경 폼 -->
				<form action="/my-page/update-profile" method="post" enctype="multipart/form-data">
					<input type="file" name="file" accept="image/*" />
					<button type="submit" style="margin-left: 10px;">사진 변경</button>
				</form>
			</div>
		</div>

		<!-- 개인정보 항목들 -->

		<div style="margin-bottom: 10px;">
			<label>ID:</label> <strong>${user.id}</strong>
		</div>
		<!-- 이름 수정 폼 -->
		<form action="/my-page/update-username" method="post" onsubmit="return confirmUpdate()">
			<div style="margin-bottom: 10px;">
				<label>이름:</label> <input type="text" name="username" placeholder="${user.username}" required />
				<button type="submit" style="margin-left: 10px;">개명</button>
			</div>
		</form>
		<form action="/my-page/update-nickname" method="post" onsubmit="return confirmUpdate()">
			<div style="margin-bottom: 10px;">
				<label>닉네임:</label> <input type="text" name="nickname" placeholder="${user.nickname}" required />
				<button type="submit" name="action" value="updateNickname" style="margin-left: 10px;">변경</button>
			</div>
		</form>
		<form action="/my-page/update-phone-number" method="post" onsubmit="return confirmUpdate()">
			<div style="margin-bottom: 10px;">
				<label>핸드폰:</label> <input type="text" name="phoneNumber" placeholder="${user.phoneNumber}"  maxlength="12" oninput="formatPhoneNumber(this)" required />
				<button type="submit" name="action" value="updatePhoneNumber" style="margin-left: 10px;">변경</button>
			</div>
		</form>
		<div style="margin-bottom: 10px;">
			<label>이메일:</label> <strong>${user.email}</strong>
		</div>
		<c:choose>
			<c:when test="${not empty bankAccount}">
				<!-- 계좌 정보가 있을 때 (수정 모드) -->
				<h2>계좌 수정</h2>
				<form action="${pageContext.request.contextPath}/my-page/saveOrUpdateAccount" method="post" onsubmit="return confirmUpdate()">
					<input type="hidden" name="userId" value="${userId}" /> 
					<label for="bankId">은행 선택:</label> 
					<select id="bankId" name="bankId">
						<c:forEach items="${banks}" var="bank">
							<option value="${bank.bankId}" <c:if test="${bank.bankId == bankAccount.bankId}">selected</c:if>>${bank.bankName}</option>
						</c:forEach>
					</select> <label for="accountNumber">계좌 번호:</label> <input type="text" id="accountNumber" name="accountNumber" placeholder="${bankAccount.accountNumber}" maxlength="16" oninput="formatAccountNumber(this)" onkeypress="return restrictInputToNumbers(event)" required />
					<button type="submit">수정</button>
				</form>
			</c:when>
			<c:otherwise>
				<!-- 계좌 정보가 없을 때 (생성 모드) -->
				<h2>계좌 생성</h2>
				<form action="${pageContext.request.contextPath}/my-page/saveOrUpdateAccount" method="post">
					<input type="hidden" name="userId" value="${userId}" /> <label for="bankId">은행 선택:</label> <select id="bankId" name="bankId">
						<c:forEach items="${banks}" var="bank">
							<option value="${bank.bankId}">${bank.bankName}</option>
						</c:forEach>
					</select> <label for="accountNumber">계좌 번호:</label> <input type="text" id="accountNumber" name="accountNumber" placeholder="최대 숫자 16자리 입력" maxlength="16" oninput="formatAccountNumber(this)" onkeypress="return restrictInputToNumbers(event)" required />
					<button type="submit">생성</button>
				</form>
			</c:otherwise>
		</c:choose>
		<div style="margin-bottom: 10px;">
			<label>MBTI:</label> <input type="text" name="mbti" placeholder="받아올겨~" />
			<button type="submit" name="action" value="updateMBTI" style="margin-left: 10px;">재검사</button>
		</div>


		<!-- 캐시 및 포인트 관리 -->
		<h3>캐시 및 포인트</h3>
		<div style="margin-bottom: 10px;">
			<p><strong>현재 캐시:</strong> ${user.currentCash} 캐시</p>
			<p><strong>현재 포인트:</strong> ${user.lectureCash} 포인트</p>
			<button style="margin-left: 10px;">충전</button>
			<button style="margin-left: 10px;">환전</button>
			<button style="margin-left: 10px;">환불</button>
		</div>
		<div style="margin-bottom: 10px;">
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
	        if (value.length > 10) {
	            value = value.slice(0, 10);
	        }

	        // 포맷 설정
	        let formattedValue = '';
	        if (value.length > 6) {
	            formattedValue += value.slice(0, 3) + '-';
	            formattedValue += value.slice(3, 6) + '-';
	            formattedValue += value.slice(6);
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
