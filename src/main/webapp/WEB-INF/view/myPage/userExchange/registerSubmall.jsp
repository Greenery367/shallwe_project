<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ include file="/WEB-INF/view/layout/header.jsp"%>

<div style="display: flex;">
	<%@ include file="/WEB-INF/view/layout/myPageMenu.jsp"%>

	<div style="flex-grow: 1; padding: 20px;">
		<h1>환전 인증</h1>

		<!-- 메시지 출력 -->
		<c:if test="${not empty message}">
			<div class="alert">${message}</div>
		</c:if>

		<c:choose>
			<c:when test="${not empty bankAccount}">
				<!-- 계좌 정보가 있을 때 (수정 모드) -->
				<h2>계좌 수정</h2>
				<p>등록된 계좌가 있습니다. 본인 계좌가 맞지 않다면 수정해 주세요.</p>
				<form action="${pageContext.request.contextPath}/my-page/exchange-list/saveOrUpdateAccount" method="post" onsubmit="return confirmUpdate()">
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
				<p>계정에 등록된 계좌가 없습니다. 계좌를 등록해 주세요.</p>
				<form action="${pageContext.request.contextPath}/my-page/exchange-list/saveOrUpdateAccount" method="post">
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

		<!-- 서브몰 신청 버튼 -->
		<c:if test="${not empty bankAccount}">
			<h2>서브몰 신청</h2>
			<form action="${pageContext.request.contextPath}/my-page/exchange-list/registerSubmall" method="post">
				<input type="hidden" name="userId" value="${userId}" /> <input type="hidden" name="bankId" value="${bankAccount.bankId}" />
				<button type="submit">서브몰 신청</button>
			</form>
		</c:if>

	</div>
</div>

<%@ include file="/WEB-INF/view/layout/footer.jsp"%>