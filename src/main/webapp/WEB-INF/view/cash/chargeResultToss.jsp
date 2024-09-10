<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/layout/header.jsp" %>	
<link rel="stylesheet" href="../css/cash.css">

	<script src="https://cdn.portone.io/v2/browser-sdk.js"></script>
	<div class="main-board">
		<div class="charge-cash-container">
		<div class="charge-title">
			<h1> 캐쉬 충전하기 </h1>
			<img src="../static/images/cash.png"> 
		</div>
			<c:choose>
				<c:if test="${status.equals('true')}">
					<h1>결제에 성공했습니다.</h1>
				</c:if>	
				<c:otherwise>
					<h1>결제에 실패했습니다.</h1>
				</c:otherwise>
			</c:choose>
		</div>
		
	</div>
	
    <script>
    
    </script>
<%@ include file="/WEB-INF/view/layout/footer.jsp" %>	