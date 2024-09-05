<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/layout/header.jsp" %>	
<link rel="stylesheet" href="../css/cash.css">

	<div class="main-board">
		<div class="charge-cash-container">
		<div class="charge-title">
			<h1> 캐쉬 충전하기 </h1>
			<img src="../static/images/cash.png"> 
		</div>
			<div class="choose-cash">
				<div class="cash-container">
					<p> 금액 입력하기 : </p> &nbsp;
					<form>
						<input class="input-cash" type="text" placeholder='' required></input>
					</form>
				</div>
			</div>
			<button class="buy-btn" id="payment"><b>구매하기</b></button> <!-- 결제하기 버튼 생성 -->
		</div>
		
	</div>
	
	
	
    
    
    <script src="https://cdn.portone.io/v2/browser-sdk.js"></script>
    <script>

    </script>
<%@ include file="/WEB-INF/view/layout/footer.jsp" %>	