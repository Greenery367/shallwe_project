<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/layout/header.jsp" %>	
<link rel="stylesheet" href="../css/cash.css">

	<script src="https://js.tosspayments.com/v1"></script>
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
							30,000 원<input type="radio" name="cash-btn" value="30000">
						</div> 
					</div>
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
  
<script>
    // 약관 동의 체크박스
    var chk1 = document.querySelector(".check1");
    var chk2 = document.querySelector(".check2");

   
    
    
    // 결제 진행 함수
    function startPayment(button) {
        let btnValue = button.value;
        let cashAmount = document.querySelector('input[name="cash-btn"]:checked');

        // 유효성 검사 - 약관 동의
        if (!chk1.checked || !chk2.checked) {
            alert('결제 전 주의사항과 동의 내역을 확인해주세요.');
            return;
        }

        // 유효성 검사 - 결제 금액 선택
        if (!cashAmount) {
            alert('결제할 금액을 선택해주세요.');
            return;
        }

        // 결제 진행
        if (btnValue == "kakao") {
            console.log("카카오 결제 요청" + cashAmount.value);
            requestPaymentToKakao(cashAmount.value);
        } else if (btnValue == "toss") {
            console.log("토스 결제 요청" + cashAmount.value);
            requestPaymentToToss(cashAmount.value);
        }
    }

    // 카카오 결제 요청
    function requestPaymentToKakao(totalAmount) {
        fetch(`http://localhost:8080/cash/send-request/kakao/` + totalAmount, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ "totalAmount": totalAmount })
        })
        .then(response => response.json())
        .then(data => {
   			 if (data != null) {
     		   console.log("Redirecting to:", data.next_redirect_pc_url); // 리다이렉트 URL 출력
     		   location.href = data.next_redirect_pc_url;
  		  } else {
   		     console.error("결제 요청 실패:", data.message);
   		 }
		})
 
        .catch(error => {
            console.error('요청 처리 중 오류 발생:', error);
        });
    }

    // 토스 보안 처리
    function addInfoForSafe(orderId,totalAmount){
    	fetch(`http://localhost:8080/toss-pay/send-request`, {
    	    method: "POST",
    	    headers: {
    	        "Content-Type": "application/json"
    	    },
    	    body: JSON.stringify({ "orderId": orderId, "totalAmount": totalAmount })
    	})
    	.then(response => {
    		console.log("보안 처리 성공");
    	})
    	.catch(error => {
    	    console.error("Error:", error);
    	});

    }
    
    
    // 토스 결제 요청
    function requestPaymentToToss(totalAmount) {
    	
    	// 결제 요청에 필요한 변수 선언
        let orderId = new Date().getTime();
        let path = "/";
        let successUrl = window.location.origin + path + "toss-pay/success";
        let failUrl = window.location.origin + path + "toss-pay/failed";
        let callbackUrl = window.location.origin + path + "cash/charge";
        
        
        // 결제 정보
        let json = {
            "card": {
                "amount": totalAmount,
                "orderId": "sample-" + orderId,
                "orderName": "셸위 캐쉬 충전",
                "successUrl": successUrl,
                "failUrl": failUrl,
                "cardCompany": null,
                "cardInstallmentPlan": null,
                "maxCardInstallmentPlan": null,
                "useCardPoint": false,
                "customerName": "유저id",
                "customerEmail": null,
                "customerMobilePhone": null,
                "taxFreeAmount": 0,
                "useInternationalCardOnly": false,
                "flowMode": "DEFAULT",
                "discountCode": null,
                "appScheme": null
            }
        };
        let tossPayments = TossPayments("test_ck_ALnQvDd2VJzdqzNAkgNYVMj7X41m");
        
        // 정보 무결성을 위한 결제 정보 저장 처리
        // var nextUrl = addInfoForSafe("sample-" + orderId, totalAmount);
        
        // 결제 처리
        tossPayments.requestPayment("card", json.card)
        	.catch(function (error) {
                if (error.code === "USER_CANCEL") {
                    alert('결제가 취소되었습니다.');
                } else {
                    alert(error.message);
                }
            });
    }
</script>
<%@ include file="/WEB-INF/view/layout/footer.jsp" %>	