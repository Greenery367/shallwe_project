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
							<a href="dashboard/user" class="btn-info">자세히 보기</a>
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
							<a href="dashboard/userRegistrations" class="btn-info">자세히 보기</a>
						</div>
					</div>
					<div class="number-of-user-box">
						<div class="number-of-user-content">
							<h1>5123</h1>
							<p>방문수</p>
						</div>
						<div class="number-of-user-info-box">
							<a href="dashboard/visitor" class="btn-info">자세히 보기</a>
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
							<a href="dashboard/cashUtilization" class="btn-info">자세히 보기</a>
						</div>
					</div>

					<div class="number-of-user-box">
						<div class="number-of-user-content">
							<h1>${numberOfChargeCash}</h1>
							<p>캐쉬충전액</p>
						</div>
						<div class="number-of-user-info-box">
							<a href="dashboard/charge" class="btn-info">자세히 보기</a>
						</div>
					</div>
					<div class="number-of-user-box">
						<div class="number-of-user-content">
							<h1>500000</h1>
							<p>캐쉬환전액</p>
						</div>
						<div class="number-of-user-info-box">
							<a href="dashboard/exchange" class="btn-info">자세히 보기</a>
						</div>
					</div>
					<div class="number-of-user-box">
						<div class="number-of-user-content">
							<h1>200000</h1>
							<p>캐쉬 환불액</p>
						</div>
						<div class="number-of-user-info-box">
							<a href="dashboard/refund" class="btn-info">자세히 보기</a>
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
							<a href="dashboard/cashUtilization" class="btn-info">자세히 보기</a>
						</div>
					</div>

					<div class="number-of-user-box">
						<div class="number-of-user-content">
							<h1>142</h1>
							<p>게시글 작성수</p>
						</div>
						<div class="number-of-user-info-box">
							<a href="dashboard/charge" class="btn-info">자세히 보기</a>
						</div>
					</div>
					<div class="number-of-user-box">
						<div class="number-of-user-content">
							<h1>430</h1>
							<p>댓글 작성수</p>
						</div>
						<div class="number-of-user-info-box">
							<a href="dashboard/exchange" class="btn-info">자세히 보기</a>
						</div>
					</div>
				</div>


				<div class="main-canvas">
					<div style="position: relative; height: 35vh; width:50%">
						<canvas id="myChart"></canvas>
					</div>
					<div style="position: relative; height: 35vh; width: 50%">
						<canvas id="myChart2"></canvas>
					</div>
				</div>



				<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
				<script>
					const ctx1 = document.getElementById('myChart').getContext('2d');
					const ctx2 = document.getElementById('myChart2').getContext('2d');

					new Chart(ctx1, {
						type: 'line',
						data: {
							labels: ['2024-08-18', '2024-08-19', '2024-08-20', '2024-08-21', '2024-08-22', '2024-08-23'],
							datasets: [
							{
								label: '캐쉬 충전량',
								data: [152, 201, 30, 51, 166, 250],
								borderWidth: 1
							},
							{
								label: '캐쉬 사용량',
								data: [52, 121, 120, 21, 106, 150],
								borderWidth: 1
							},
							]
						},
						options: {
							scales: {
								y: {
									beginAtZero: true
								}
							}
						}
					});

					new Chart(ctx2, {
						type: 'bar',
						data: {
							labels: ['2024-08-18', '2024-08-19', '2024-08-20', '2024-08-21', '2024-08-22', '2024-08-23'],
							datasets: [
								{
								label: '회원 가입수',
								data: [152, 201, 30, 131, 166, 250],
						        backgroundColor: [
						                'rgba(255, 99, 132, 0.2)',
						                'rgba(54, 162, 235, 0.2)',
						                'rgba(255, 206, 86, 0.2)',
						                'rgba(75, 192, 192, 0.2)',
						                'rgba(153, 102, 255, 0.2)',
						                'rgba(255, 159, 64, 0.2)'
						            ],
						            borderColor: [
						                'rgba(255, 99, 132, 1)',
						                'rgba(54, 162, 235, 1)',
						                'rgba(255, 206, 86, 1)',
						                'rgba(75, 192, 192, 1)',
						                'rgba(153, 102, 255, 1)',
						                'rgba(255, 159, 64, 1)'
						            ],
						        borderWidth: 1
							},
							 {
						        label: '회원 탈퇴수',
						        data: [75, 182, 250, 31, 201, 35],
						        backgroundColor: [
						                'rgba(255, 99, 132, 0.2)',
						                'rgba(54, 162, 235, 0.2)',
						                'rgba(255, 206, 86, 0.2)',
						                'rgba(75, 192, 192, 0.2)',
						                'rgba(153, 102, 255, 0.2)',
						                'rgba(255, 159, 64, 0.2)'
						            ],
						            borderColor: [
						                'rgba(255, 99, 132, 1)',
						                'rgba(54, 162, 235, 1)',
						                'rgba(255, 206, 86, 1)',
						                'rgba(75, 192, 192, 1)',
						                'rgba(153, 102, 255, 1)',
						                'rgba(255, 159, 64, 1)'
						            ],
						        borderWidth: 1
						      }
							]
						},
						options: {
							scales: {
								y: {
									beginAtZero: true
								}
							}
						}
					});
					
				</script>
				
					
<%@ include file="/WEB-INF/view/layout/adminFooter.jsp"%>

