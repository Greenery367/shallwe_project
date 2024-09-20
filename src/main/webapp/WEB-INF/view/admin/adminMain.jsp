<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/view/layout/adminHeader.jsp"%>
			
				<h2>대시 보드</h2>
				<p>유저</p>
				<div class="statistics-user-box">
					<div class="number-of-user-box">
						<div class="number-of-user-content">
							<h1>${numberOfUser}</h1>
							<p>회원수</p>
						</div>
						<div class="number-of-user-info-box">
							<a href="${pageContext.request.contextPath}/admin/user" class="btn-info">자세히 보기</a>
						</div>
					</div>

					<div class="number-of-user-box">
						<div class="number-of-user-content">
							<h1>200</h1>
							<p>매칭수</p>
						</div>
						<div class="number-of-user-info-box">
							<a href="dashboard/matching" class="btn-info">자세히 보기</a>
						</div>
					</div>
					<div class="number-of-user-box">
						<div class="number-of-user-content">
							<h1>200 / 10</h1>
							<p>회원가입/탈퇴수</p>
						</div>
						<div class="number-of-user-info-box">
							<a href="${pageContext.request.contextPath}/admin/user" class="btn-info">자세히 보기</a>
						</div>
					</div>
				</div>

				<p>캐쉬</p>
				<div class="statistics-user-box">
					<div class="number-of-user-box">
						<div class="number-of-user-content">
							<h1>${cashUseRate}%</h1>
							<p>캐쉬 사용률</p>
						</div>
						<div class="number-of-user-info-box">
							<a href="${pageContext.request.contextPath}/admin/cash" class="btn-info">자세히 보기</a>
						</div>
					</div>

					<div class="number-of-user-box">
						<div class="number-of-user-content">
							<h1>${numberOfChargeCash}</h1>
							<p>캐쉬충전액</p>
						</div>
						<div class="number-of-user-info-box">
							<a href="${pageContext.request.contextPath}/admin/cash" class="btn-info">자세히 보기</a>
						</div>
					</div>
					<div class="number-of-user-box">
						<div class="number-of-user-content">
							<h1>500000</h1>
							<p>캐쉬환전액</p>
						</div>
						<div class="number-of-user-info-box">
							<a href="${pageContext.request.contextPath}/admin/cash" class="btn-info">자세히 보기</a>
						</div>
					</div>
					<div class="number-of-user-box">
						<div class="number-of-user-content">
							<h1>200000</h1>
							<p>캐쉬 환불액</p>
						</div>
						<div class="number-of-user-info-box">
							<a href="${pageContext.request.contextPath}/admin/cash" class="btn-info">자세히 보기</a>
						</div>
					</div>
				</div>
				
				<p>광고 및 게시판</p>
				<div class="statistics-user-box">
					<div class="number-of-user-box">
						<div class="number-of-user-content">
							<h1>400000</h1>
							<p>광고수익</p>
						</div>
						<div class="number-of-user-info-box">
							<a href="${pageContext.request.contextPath}/admin/advertise" class="btn-info">자세히 보기</a>
						</div>
					</div>

					<div class="number-of-user-box">
						<div class="number-of-user-content">
							<h1>142</h1>
							<p>게시글 작성수</p>
						</div>
						<div class="number-of-user-info-box">
							<a href="${pageContext.request.contextPath}/admin/community" class="btn-info">자세히 보기</a>
						</div>
					</div>
					<div class="number-of-user-box">
						<div class="number-of-user-content">
							<h1>430</h1>
							<p>댓글 작성수</p>
						</div>
						<div class="number-of-user-info-box">
							<a href="${pageContext.request.contextPath}/admin/community" class="btn-info">자세히 보기</a>
						</div>
					</div>
				</div>


				<div class="main-canvas">
					<div style="position: relative; height: 35vh; width:50%">
						<canvas id="myChart"></canvas>
					</div>
				</div>


				<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
				
				<script>
    const ctx = document.getElementById('myChart');
    let dateArray = [];
    let amountArray = [];

    function makeAmountArray(array, data) {
        for (let i = 0; i < data.length; i++) {
            let a = data[i].amount; // 'amount' 필드 사용
            array.push(a);
            console.log("추가된 금액: " + a);
        }
    }

    function makeDateArray(array, data) {
        for (let i = 0; i < data.length; i++) {
            let a = data[i].createdAt; // 'createdAt' 필드 사용
            array.push(a);
            console.log("추가된 날짜: " + a);
        }
    }

    // 서버에서 데이터 가져오기
    fetch('/admin/dashboard')
        .then(response => response.json())
        .then(data => {
            makeAmountArray(amountArray, data);
            makeDateArray(dateArray, data);

            new Chart(ctx, {
                type: 'line',
                data: {
                    labels: dateArray,
                    datasets: [{
                        label: '금액',
                        data: amountArray,
                        borderWidth: 1
                    }]
                },
                options: {
                    scales: {
                        y: {
                            beginAtZero: true
                        }
                    }
                }
            });
        })
        .catch(error => {
            console.error('데이터 가져오기 오류:', error);
        });
</script>


				
					
<%@ include file="/WEB-INF/view/layout/adminFooter.jsp"%>

