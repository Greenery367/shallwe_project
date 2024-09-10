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
		<hr/>
			<div class="choose-cash">
			
				<h4>현재 보유 캐쉬 : ${user.currentCash}</h4>
				<div class="choose-cash">
					<h4> 충전할 캐쉬 </h4>
					<h4> 결제 하실 금액 </h4>
					<div class="choose-container">
						<div>
							1,000 원<input type="radio" name="cash-btn" value="1000">
						</div> 
						<div>
							3,000 원<input type="radio" name="cash-btn" value="3000">
						</div> 
						<div>
							5,000 원<input type="radio" name="cash-btn" value="5000">
						</div> 
						<div>
							10,000 원<input type="radio" name="cash-btn" value="10000">
						</div> 
						<div>
							30,000 원<input type="radio" name="cash-btn" value="3000">
						</div> 
					</div>
					<p> 결제 후 내 캐쉬 10,000 캐쉬</p>
				</div>
				<div class="cash-container">
					<input type="checkbox" class="check1">[필수] 전체 동의</div>
					<input type="checkbox" class="check2">상품, 가격, 결제 전 주의사항을 확인하였습니다.</div>
				</div>
				<h5>결제 수단 선택</h5>
				<div class="cash-container">
					<div class="button-container">
						<button class = "resultButton-1" onclick="startPayment(this)" value="kakao">카카오페이</button>
						<button class = "resultButton-2" onclick="startPayment(this)" value="toss">토스페이</button>	
					</div>
				</div>
		</div>
		
	</div>
	<script src="https://js.tosspayments.com/v2/standard"></script>
  	<script>	
  	
  	// 약관 동의 체크박스
    var chk1 = document.querySelector(".check1");
    var chk2 = document.querySelector(".check2");
    const clientKey = "test_ck_D5GePWvyJnrK0W0k6q8gLzN97Eoq";
    const secretKey = "test_sk_zXLkKEypNArWmo50nX3lmeaxYG5R";
    
    // 결제 진행
    function startPayment(button){
    	let btnValue = button.value;
    	let cashAmount= document.querySelector('input[name="cash-btn"]:checked');
    	
    	// 유효성 검사 - 약관 동의
    	if((chk1.checked==false) || (chk2.checked==false)){
    		alert('결제 전 주의사항과 동의 내역을 확인해주세요.');
    		return;
    	}
    	
    	// 유효성 검사 - 결제 금액 선택
    	else if(cashAmount == null){
    		alert('결제할 금액을 선택해주세요.');
    		return;
    	}
    	else{
    		
    		var cash = cashAmount.value;
    		// 카카오 결제 진행
    		if(btnValue == "kakao"){
    			// 카카오 결제 처리
    			console.log("카카오 결제 요청"+cashAmount.value);
    			requestPaymentToKakao(cash);
    			
    		// 토스 결제 진행
    		} else if (btnValue == "toss"){
    			console.log("토스 결제 요청"+cashAmount.value);
    			requestPaymentToToss(cash);
    		}
    	}
    	
    	
    	
    	// 카카오 결제 요청 
    	function requestPaymentToKakao(totalAmount) {
	        fetch(`http://localhost:8080/cash/send-request-kakao/`+totalAmount, {
	            method: "POST",
	            headers: {
	                "Content-Type": "application/json",
	                // "charset"은 필요하지 않음. 브라우저가 자동으로 처리
	            },
	            body: JSON.stringify({ "totalAmount": totalAmount }) // 요청 본문에 데이터를 포함
	        })
	        .then(response => response.json()) // 응답을 JSON 형식으로 변환
	        .then(data => {
	            // 응답에서 next_redirect_pc_url을 가져와서 리디렉션
	            if (data.next_redirect_pc_url) {
	                location.href = data.next_redirect_pc_url;
	            } else {
	                console.error('Redirect URL이 응답에 포함되어 있지 않습니다.');
	            }
	        })
	        .catch(error => {
	            // 오류를 콘솔에 출력
	            console.error('요청 처리 중 오류 발생:', error);
	        });
	    }
    	
    	
    	
    	// 토스 결제 요청
    	function requestPaymentToToss(totalAmount){
    		console.log("로깅1");
    		const clientKey = "test_ck_ALnQvDd2VJzdqzNAkgNYVMj7X41m";
		    const customerKey = "zS_QRvhJpHd1yF22lCg8f";
		    const tossPayments = TossPayments(clientKey);
		    const payment = tossPayments.payment({ customerKey });
		    console.log("로깅2");
		    async function requestPaymentByToss() {
		        console.log("로깅3");
		        await payment.requestPayment({
		          method: "CARD", // 카드 결제
		          amount: {
		            currency: "KRW",
		            value: totalAmount,
		          },
		          orderId: "0chFSksC-2fuxtUOhm7b6", // 고유 주분번호
		          orderName: "토스 티셔츠 외 2건",
		          successUrl: window.location.origin + "/success", // 결제 요청이 성공하면 리다이렉트되는 URL
		          failUrl: window.location.origin + "/fail", // 결제 요청이 실패하면 리다이렉트되는 URL
		          customerEmail: "customer123@gmail.com",
		          customerName: "김토스",
		          customerMobilePhone: "01012341234",
		          // 카드 결제에 필요한 정보
		          card: {
		            useEscrow: false,
		            flowMode: "DEFAULT", // 통합결제창 여는 옵션
		            useCardPoint: false,
		            useAppCardOnly: false,
		          },
		        });
		      }
    	}
    	
    }
    
    </script>
<%@ include file="/WEB-INF/view/layout/footer.jsp" %>	