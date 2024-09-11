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
  
<script>
    // 약관 동의 체크박스
    var chk1 = document.querySelector(".check1");
    var chk2 = document.querySelector(".check2");

    // 결제 요청에 필요한 변수 선언
    let amount = 50000;
    let path = "/";
    let successUrl = window.location.origin + path + "success";
    let failUrl = window.location.origin + path + "fail";
    let callbackUrl = window.location.origin + path + "va_callback";
    let orderId = new Date().getTime();

    let jsons = {
        "card": {
            "amount": amount,
            "orderId": "sample-" + orderId,
            "orderName": "토스 티셔츠 외 2건",
            "successUrl": successUrl,
            "failUrl": failUrl,
            "cardCompany": null,
            "cardInstallmentPlan": null,
            "maxCardInstallmentPlan": null,
            "useCardPoint": false,
            "customerName": "박토스",
            "customerEmail": null,
            "customerMobilePhone": null,
            "taxFreeAmount": null,
            "useInternationalCardOnly": false,
            "flowMode": "DEFAULT",
            "discountCode": null,
            "appScheme": null
        },
        "vaccount": { //가상계좌 결제창

            "amount": amount,
            "orderId": "sample-" + orderId,
            "orderName": "토스 티셔츠 외 2건",
            "successUrl": successUrl,
            "failUrl": failUrl,
            "validHours": 72,
            "virtualAccountCallbackUrl": callbackUrl,
            "customerName": "박토스",
            "customerEmail": null,
            "customerMobilePhone": null,
            "taxFreeAmount": null,
            "cashReceipt": {
                "type": "소득공제"
            },
            "useEscrow": false

        },
        "transfer": { //계좌이체 결제창

            "amount": amount,
            "orderId": "sample-" + orderId,
            "orderName": "토스 티셔츠 외 2건",
            "successUrl": successUrl,
            "failUrl": failUrl,
            "customerName": "박토스",
            "customerEmail": null,
            "customerMobilePhone": null,
            "taxFreeAmount": null,
            "cashReceipt": {
                "type": "소득공제"
            },
            "useEscrow": false

        },
        "phone": { // 휴대폰 결제창

            "amount": amount,
            "orderId": "sample-" + orderId,
            "orderName": "토스 티셔츠 외 2건",
            "successUrl": successUrl,
            "failUrl": failUrl,
            "mobileCarrier": null

        },

        "certificate": { //상품권 결제창
            "amount": amount,
            "orderId": "sample-" + orderId,
            "orderName": "토스 티셔츠 외 2건",
            "successUrl": successUrl,
            "failUrl": failUrl
        },

        "bookcert": { //도서문화상품권 결제창
            "amount": amount,
            "orderId": "sample-" + orderId,
            "orderName": "토스 티셔츠 외 2건",
            "successUrl": successUrl,
            "failUrl": failUrl
        },
        "gamecert": { // 게임문화상품권 결제창
            "amount": amount,
            "orderId": "sample-" + orderId,
            "orderName": "토스 티셔츠 외 2건",
            "successUrl": successUrl,
            "failUrl": failUrl
        }

        // 다른 결제 방법 정의는 생략
    };

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
            requestPaymentToToss('gamecert', jsons.card);
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
            if (data.next_redirect_pc_url) {
                location.href = data.next_redirect_pc_url;
            } else {
                console.error('Redirect URL이 응답에 포함되어 있지 않습니다.');
            }
        })
        .catch(error => {
            console.error('요청 처리 중 오류 발생:', error);
        });
    }

    // 토스 결제 요청
    function requestPaymentToToss(method, requestJson) {
        let tossPayments = TossPayments("test_ck_Wd46qopOB89vWdgjoR73ZmM75y0v");
        tossPayments.requestPayment(method, requestJson)
            .catch(function (error) {
                if (error.code === "USER_CANCEL") {
                    alert('유저가 취소했습니다.');
                } else {
                    alert(error.message);
                }
            });
    }
</script>
<%@ include file="/WEB-INF/view/layout/footer.jsp" %>	