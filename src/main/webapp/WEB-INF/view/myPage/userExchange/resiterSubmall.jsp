<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="/WEB-INF/view/layout/header.jsp"%>

<div style="display: flex;">
    <%@ include file="/WEB-INF/view/layout/myPageMenu.jsp"%>

    <div style="flex-grow: 1; padding: 20px;">
        <h2>계좌 본인 인증</h2>

        <form action="${pageContext.request.contextPath}/api/verify/verifyAccount" method="POST">
	        <div style="margin-bottom: 10px;">
	            <label for="bankId">은행 선택:</label>
	            <select id="bankId" name="bankId">
	                <c:forEach items="${banks}" var="bank">
	                    <option value="${bank.bankId}">${bank.bankName}</option>
	                </c:forEach>
	            </select>
	        </div>
	
	        <div style="margin-bottom: 10px;">
	            <label for="accountNum">계좌번호</label>
	            <input type="text" id="accountNum" name="accountNum" placeholder="계좌번호 입력" required>
	        </div>
	
	        <div style="margin-bottom: 10px;">
	            <label for="accountHolderName">예금주 이름</label>
	            <input type="text" id="accountHolderName" name="accountHolderName" placeholder="예금주 이름 입력" required>
	        </div>
	
	        <div>
	            <button type="submit">인증 요청</button>
	        </div>
    	</form>
    </div>
</div>

<%@ include file="/WEB-INF/view/layout/footer.jsp"%>
